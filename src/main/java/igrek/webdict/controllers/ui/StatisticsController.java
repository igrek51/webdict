package igrek.webdict.controllers.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import igrek.webdict.controllers.ui.common.BaseUIController;
import igrek.webdict.db.rank.RankDao;
import igrek.webdict.db.userword.UserWordDao;
import igrek.webdict.db.word.WordDao;
import igrek.webdict.model.DictionaryCode;
import igrek.webdict.model.dto.DictionaryStatisticsDTO;
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
		
		List<DictionaryStatisticsDTO> dictStats = new ArrayList<>();
		dictStats.add(generateDictStats(dictionary, user, false));
		dictStats.add(generateDictStats(dictionary, user, true));
		model.put("dictStats", dictStats);
		
		return "statistics";
	}
	
	private DictionaryStatisticsDTO generateDictStats(Dictionary dictionary, User user, boolean reversedDictionary) {
		
		// dictionary display name
		String dictDisplayName = DictionaryCode.toDictionaryDisplayName(dictionary, reversedDictionary);
		
		// all user dictionary ranks
		List<Rank> ranks = rankDao.findByDictionaryAndUser(dictionary, reversedDictionary, user);
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
	
}
