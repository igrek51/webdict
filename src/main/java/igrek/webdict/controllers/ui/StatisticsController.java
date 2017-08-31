package igrek.webdict.controllers.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import igrek.webdict.controllers.ui.common.BaseUIController;
import igrek.webdict.db.rank.RankDao;
import igrek.webdict.db.userword.UserWordDao;
import igrek.webdict.db.word.WordDao;
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
		
		// get session settings
		Dictionary dictionary = sessionSettings.getDictionary();
		User user = sessionSettings.getUser();
		
		List<DictionaryStatisticsDTO> dictStats = new ArrayList<>();
		dictStats.add(generateDictStats(dictionary, user, false, "en -> pl"));
		dictStats.add(generateDictStats(dictionary, user, true, "en <- pl"));
		model.put("dictStats", dictStats);
		
		return "statistics";
	}
	
	private DictionaryStatisticsDTO generateDictStats(Dictionary dictionary, User user, boolean reversed, String dictDisplayName) {
		
		DictionaryStatisticsDTO dto = new DictionaryStatisticsDTO();
		
		// dictionary display name
		dto.dictDisplayName = dictDisplayName;
		Map<String, Object> stats = dto.stats;
		
		// all user dictionary ranks
		List<Rank> ranks = rankDao.findByDictionaryAndUser(dictionary, reversed, user);
		long all = ranks.size();
		stats.put("allWordsCount", all);
		
		if (!ranks.isEmpty()) {
			
			// trained words
			long trainedCount = ranks.stream()
					.filter(rank -> rank.getRankValue() < 0 && rank.getTriesCount() > 0)
					.count();
			putCounAndPercentage(stats, "trained", trainedCount, all);
			
			// training in progress words
			long trainingInProgressCount = ranks.stream()
					.filter(rank -> rank.getRankValue() > 0)
					.count();
			putCounAndPercentage(stats, "trainingInProgress", trainingInProgressCount, all);
			
			// touched words
			long touchedCount = ranks.stream().filter(rank -> rank.getTriesCount() > 0).count();
			putCounAndPercentage(stats, "touched", touchedCount, all);
			
			// cooling down words
			long coolingDownCount = ranks.stream()
					.filter(rank -> rank.getCooldownPenalty() > 0.0)
					.count();
			putCounAndPercentage(stats, "coolingDown", coolingDownCount, all);
			
		}
		
		return dto;
	}
	
	private void putCounAndPercentage(Map<String, Object> model, String name, long count, long all) {
		model.put(name + "Count", count);
		double percentage = ((double) count * 100) / all;
		// converting to string always with dot format
		DecimalFormatSymbols decimalSymbols = DecimalFormatSymbols.getInstance();
		decimalSymbols.setDecimalSeparator('.');
		DecimalFormat format = new DecimalFormat("#.##", decimalSymbols);
		model.put(name + "Percentage", format.format(percentage));
	}
	
}
