package igrek.webdict.controllers.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import igrek.webdict.controllers.ui.common.BaseUIController;
import igrek.webdict.db.rank.RankDao;
import igrek.webdict.db.userword.UserWordDao;
import igrek.webdict.db.word.WordDao;
import igrek.webdict.logic.statistics.BidirectionalRank;
import igrek.webdict.logic.statistics.DictionaryStatisticsDTO;
import igrek.webdict.model.DictionaryCode;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.User;
import igrek.webdict.model.session.SessionSettings;

@Controller
@SessionScope
@RequestMapping("/statistics")
public class StatisticsController extends BaseUIController {
	
	private final WordDao wordDao;
	private final RankDao rankDao;
	private final UserWordDao userWordDao;
	
	@Autowired
	public StatisticsController(WordDao wordDao, RankDao rankDao, SessionSettings sessionSettings, UserWordDao userWordDao) {
		super(sessionSettings);
		this.wordDao = wordDao;
		this.rankDao = rankDao;
		this.sessionSettings = sessionSettings;
		this.userWordDao = userWordDao;
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
		
		List<Rank> simpleRanks = rankDao.findByDictionaryAndUser(dictionary, false, user);
		List<Rank> reversedRanks = rankDao.findByDictionaryAndUser(dictionary, true, user);
		
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
			long trainedCount = ranks.stream()
					.filter(rank -> rank.getRankValue() < 0 && rank.getTriesCount() > 0)
					.count();
			statsDto.trained = statsDto.new ProgressBarData(trainedCount);
			
			// training in progress words
			long trainingInProgressCount = ranks.stream()
					.filter(rank -> rank.getRankValue() > 0)
					.count();
			statsDto.trainingInProgress = statsDto.new ProgressBarData(trainingInProgressCount);
			
			// touched words
			long touchedCount = ranks.stream().filter(rank -> rank.getTriesCount() > 0).count();
			statsDto.touched = statsDto.new ProgressBarData(touchedCount);
			
			// cooling down words
			long coolingDownCount = ranks.stream()
					.filter(rank -> rank.getCooldownPenalty() > 0.0)
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
					.filter(bRank -> bRank.getSimpleRank()
							.getRankValue() < 0 && bRank.getSimpleRank().getTriesCount() > 0)
					.filter(bRank -> bRank.getReversedRank()
							.getRankValue() < 0 && bRank.getReversedRank().getTriesCount() > 0)
					.count();
			statsDto.trained = statsDto.new ProgressBarData(trainedCount);
			
			// training in progress words
			long trainingInProgressCount = bidirectionalRanks.stream()
					.filter(bRank -> bRank.getSimpleRank().getRankValue() > 0)
					.filter(bRank -> bRank.getReversedRank().getRankValue() > 0)
					.count();
			statsDto.trainingInProgress = statsDto.new ProgressBarData(trainingInProgressCount);
			
			// touched words
			long touchedCount = bidirectionalRanks.stream()
					.filter(bRank -> bRank.getSimpleRank().getTriesCount() > 0)
					.filter(bRank -> bRank.getReversedRank().getTriesCount() > 0)
					.count();
			statsDto.touched = statsDto.new ProgressBarData(touchedCount);
			
			// cooling down words
			long coolingDownCount = bidirectionalRanks.stream()
					.filter(bRank -> bRank.getSimpleRank().getCooldownPenalty() > 0.0)
					.filter(bRank -> bRank.getReversedRank().getCooldownPenalty() > 0.0)
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
