package igrek.webdict.controllers.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Map;

import igrek.webdict.controllers.ui.common.BaseUIController;
import igrek.webdict.db.rank.RankDao;
import igrek.webdict.db.userword.UserWordDao;
import igrek.webdict.db.word.WordDao;
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
		boolean reversed = sessionSettings.isReversedDictionary();
		
		// all user dictionary ranks
		List<Rank> ranks = rankDao.findByDictionaryAndUser(dictionary, reversed, user);
		model.put("allWordsCount", ranks.size());
		
		if (!ranks.isEmpty()) {
			
			// trained words
			long trainedCount = ranks.stream()
					.filter(rank -> rank.getRankValue() < 0 && rank.getTriesCount() > 0)
					.count();
			model.put("trainedCount", trainedCount);
			
			// touched words
			long touchedCount = ranks.stream().filter(rank -> rank.getTriesCount() > 0).count();
			model.put("touchedCount", touchedCount);
			
			// cooling down words
			long coolingDownCount = ranks.stream()
					.filter(rank -> rank.getCooldownPenalty() > 0.0)
					.count();
			model.put("coolingDownCount", coolingDownCount);
			
			// training in progress words
			long trainingInProgressCount = ranks.stream()
					.filter(rank -> rank.getRankValue() > 0)
					.count();
			model.put("trainingInProgressCount", trainingInProgressCount);
			
		}
		
		return "statistics";
	}
	
}
