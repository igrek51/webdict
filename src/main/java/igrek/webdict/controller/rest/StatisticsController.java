package igrek.webdict.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import igrek.webdict.domain.dictionary.Dictionary;
import igrek.webdict.domain.dictionary.DictionaryCode;
import igrek.webdict.domain.statistics.BidirectionalRank;
import igrek.webdict.domain.statistics.DictionaryStatisticsDTO;
import igrek.webdict.domain.statistics.WordStatisticsLogic;
import igrek.webdict.domain.user.User;
import igrek.webdict.domain.wordrank.Rank;
import igrek.webdict.service.DictionaryService;
import igrek.webdict.service.RankService;
import igrek.webdict.service.UserService;

@RestController
@RequestMapping("/api/stats")
public class StatisticsController {
	
	private final UserService userService;
	private final RankService rankService;
	private final DictionaryService dictionaryService;
	
	@Autowired
	public StatisticsController(UserService userService, RankService rankService, DictionaryService dictionaryService) {
		this.userService = userService;
		this.rankService = rankService;
		this.dictionaryService = dictionaryService;
	}
	
	@GetMapping("/{userId}/{dictionaryCode}")
	public List<DictionaryStatisticsDTO> showAll(@PathVariable("userId") long userId, @PathVariable("dictionaryCode") String dictionaryCode) {
		// user retrieval and validation
		Optional<User> oUser = userService.findOne(userId);
		if (!oUser.isPresent()) {
			throw new IllegalArgumentException("user with given id doesn't exist");
		}
		
		// dictionary retrieval and validation
		DictionaryCode dictCode = DictionaryCode.parse(dictionaryCode);
		Optional<Dictionary> oDictionary = dictionaryService.findByLanguages(dictCode.getSourceLanguage(), dictCode
				.getTargetLanguage());
		if (!oDictionary.isPresent()) {
			throw new IllegalArgumentException("dictionary with given languages doesn't exist");
		}
		
		List<Rank> simpleRanks = rankService.findByDictionaryAndUser(oDictionary.get(), false, oUser
				.get());
		List<Rank> reversedRanks = rankService.findByDictionaryAndUser(oDictionary.get(), true, oUser
				.get());
		
		List<DictionaryStatisticsDTO> dictStats = new ArrayList<>();
		dictStats.add(generateDictStats(oDictionary.get(), false, simpleRanks));
		dictStats.add(generateDictStats(oDictionary.get(), true, reversedRanks));
		dictStats.add(generateBothDirectionStats(oDictionary.get(), simpleRanks, reversedRanks));
		return dictStats;
	}
	
	private DictionaryStatisticsDTO generateDictStats(Dictionary dictionary, boolean reversedDictionary, List<Rank> ranks) {
		// dictionary display name
		String dictDisplayName = DictionaryCode.toDictionaryDisplayName(dictionary, reversedDictionary);
		// all user dictionary ranks
		long all = ranks.size();
		
		DictionaryStatisticsDTO statsDto = new DictionaryStatisticsDTO(dictDisplayName, all);
		
		if (!ranks.isEmpty()) {
			// trained words
			long trainedCount = ranks.stream().filter(WordStatisticsLogic::isWordTrained).count();
			statsDto.trained = statsDto.new ProgressBarData(trainedCount);
			
			// training in progress words
			long trainingInProgressCount = ranks.stream()
					.filter(WordStatisticsLogic::isWordInProgess)
					.count();
			statsDto.trainingInProgress = statsDto.new ProgressBarData(trainingInProgressCount);
			
			// touched words
			long touchedCount = ranks.stream().filter(WordStatisticsLogic::isWordTouched).count();
			statsDto.touched = statsDto.new ProgressBarData(touchedCount);
			
			// cooling down words
			long coolingDownCount = ranks.stream()
					.filter(WordStatisticsLogic::isWordCoolingDown)
					.count();
			statsDto.coolingDown = statsDto.new ProgressBarData(coolingDownCount);
		}
		
		return statsDto;
	}
	
	private DictionaryStatisticsDTO generateBothDirectionStats(Dictionary dictionary, List<Rank> simpleRanks, List<Rank> reversedRanks) {
		// create list with bidirectional ranks
		List<BidirectionalRank> bidirectionalRanks = new ArrayList<>();
		for (Rank simpleRank : simpleRanks) {
			// find respective reversed rank
			Optional<Rank> reversedRank = findRespectiveReversedRank(simpleRank, reversedRanks);
			if (!reversedRank.isPresent())
				throw new RuntimeException("respective reversed rank was not found");
			BidirectionalRank bRank = new BidirectionalRank(simpleRank, reversedRank.get());
			bidirectionalRanks.add(bRank);
		}
		
		String dictDisplayName = buildBothDirectionDictionaryName(dictionary);
		long all = bidirectionalRanks.size();
		DictionaryStatisticsDTO statsDto = new DictionaryStatisticsDTO(dictDisplayName, all);
		
		if (!bidirectionalRanks.isEmpty()) {
			// trained words
			long trainedCount = bidirectionalRanks.stream()
					.filter(bRank -> WordStatisticsLogic.isWordTrained(bRank.getSimpleRank()))
					.filter(bRank -> WordStatisticsLogic.isWordTrained(bRank.getReversedRank()))
					.count();
			statsDto.trained = statsDto.new ProgressBarData(trainedCount);
			
			// training in progress words
			long trainingInProgressCount = bidirectionalRanks.stream()
					.filter(bRank -> WordStatisticsLogic.isWordInProgess(bRank.getSimpleRank()))
					.filter(bRank -> WordStatisticsLogic.isWordInProgess(bRank.getReversedRank()))
					.count();
			statsDto.trainingInProgress = statsDto.new ProgressBarData(trainingInProgressCount);
			
			// touched words
			long touchedCount = bidirectionalRanks.stream()
					.filter(bRank -> WordStatisticsLogic.isWordTouched(bRank.getSimpleRank()))
					.filter(bRank -> WordStatisticsLogic.isWordTouched(bRank.getReversedRank()))
					.count();
			statsDto.touched = statsDto.new ProgressBarData(touchedCount);
			
			// cooling down words
			long coolingDownCount = bidirectionalRanks.stream()
					.filter(bRank -> WordStatisticsLogic.isWordCoolingDown(bRank.getSimpleRank()))
					.filter(bRank -> WordStatisticsLogic.isWordCoolingDown(bRank.getReversedRank()))
					.count();
			statsDto.coolingDown = statsDto.new ProgressBarData(coolingDownCount);
		}
		
		return statsDto;
	}
	
	private Optional<Rank> findRespectiveReversedRank(Rank simpleRank, List<Rank> reversedRanks) {
		for (Rank reversedRank : reversedRanks) {
			if (reversedRank.getUserWord().getId().equals(simpleRank.getUserWord().getId()))
				return Optional.of(reversedRank);
		}
		return Optional.empty();
	}
	
	private String buildBothDirectionDictionaryName(Dictionary dictionary) {
		StringBuilder sb = new StringBuilder();
		sb.append(dictionary.getSourceLanguage().getCode());
		sb.append(" <-> ");
		sb.append(dictionary.getTargetLanguage().getCode());
		sb.append(" (both directions)");
		return sb.toString();
	}
	
}
