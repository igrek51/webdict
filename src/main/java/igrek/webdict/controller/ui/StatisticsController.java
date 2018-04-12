package igrek.webdict.controller.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import igrek.webdict.controller.common.BaseUIController;
import igrek.webdict.domain.DictionaryCode;
import igrek.webdict.domain.entity.Dictionary;
import igrek.webdict.domain.entity.Rank;
import igrek.webdict.domain.entity.User;
import igrek.webdict.domain.session.SessionSettings;
import igrek.webdict.domain.statistics.BidirectionalRank;
import igrek.webdict.domain.statistics.DictionaryStatisticsDTO;
import igrek.webdict.domain.statistics.WordStatisticsLogic;
import igrek.webdict.service.RankService;

@Controller
@SessionScope
@RequestMapping("/statistics")
public class StatisticsController extends BaseUIController {
	
	private final RankService rankService;
	
	@Autowired
	public StatisticsController(SessionSettings sessionSettings, RankService rankService) {
		super(sessionSettings);
		this.rankService = rankService;
		this.sessionSettings = sessionSettings;
	}
	
	@GetMapping({"", "/"})
	public String showAll(Map<String, Object> model) {
		checkSessionValid();
		model.put("title", "Statistics");
		setActiveTab(model, "statistics");
		setSettingsData(model);
		
		// get session settings
		Dictionary dictionary = sessionSettings.getDictionary();
		User user = sessionSettings.getUser();
		
		List<Rank> simpleRanks = rankService.findByDictionaryAndUser(dictionary, false, user);
		List<Rank> reversedRanks = rankService.findByDictionaryAndUser(dictionary, true, user);
		
		List<DictionaryStatisticsDTO> dictStats = new ArrayList<>();
		dictStats.add(generateDictStats(dictionary, false, simpleRanks));
		dictStats.add(generateDictStats(dictionary, true, reversedRanks));
		dictStats.add(generateBothDirectionStats(dictionary, simpleRanks, reversedRanks));
		model.put("dictStats", dictStats);
		
		return "statistics";
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
