package igrek.webdict.controller.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import igrek.webdict.controller.common.BaseUIController;
import igrek.webdict.domain.TopWordComparator;
import igrek.webdict.domain.alert.BootstrapAlert;
import igrek.webdict.domain.alert.BootstrapAlertType;
import igrek.webdict.domain.dto.AddWordDTO;
import igrek.webdict.domain.dto.WordRankDTO;
import igrek.webdict.domain.entity.Dictionary;
import igrek.webdict.domain.entity.User;
import igrek.webdict.domain.entity.UserWord;
import igrek.webdict.domain.entity.Word;
import igrek.webdict.domain.session.SessionSettings;
import igrek.webdict.repository.rank.RankDao;
import igrek.webdict.repository.userword.UserWordDao;
import igrek.webdict.repository.word.WordDao;

@Controller
@SessionScope
@RequestMapping("/")
public class WordController extends BaseUIController {
	
	private final WordDao wordDao;
	private final RankDao rankDao;
	private final UserWordDao userWordDao;
	
	@Autowired
	public WordController(WordDao wordDao, RankDao rankDao, SessionSettings sessionSettings, UserWordDao userWordDao) {
		super(sessionSettings);
		this.wordDao = wordDao;
		this.rankDao = rankDao;
		this.sessionSettings = sessionSettings;
		this.userWordDao = userWordDao;
	}
	
	@GetMapping({"", "/", "/top"})
	public String showTop(Map<String, Object> model) {
		checkSessionValid();
		setTitle(model, "Top word");
		setActiveTab(model, "top");
		setSettingsData(model);
		
		Dictionary dictionary = sessionSettings.getDictionary();
		User user = sessionSettings.getUser();
		boolean reversed = sessionSettings.isReversedDictionary();
		
		WordRankDTO wordrank = rankDao.getTop(dictionary, reversed, user)
				.map(WordRankDTO::createDTO)
				.orElse(null);
		model.put("wordrank", wordrank);
		
		return "top";
	}
	
	@GetMapping("/all")
	public String listAll(Map<String, Object> model) {
		checkSessionValid();
		setTitle(model, "All dictionary words");
		setActiveTab(model, "all");
		setSettingsData(model);
		
		Dictionary dictionary = sessionSettings.getDictionary();
		User user = sessionSettings.getUser();
		boolean reversed = sessionSettings.isReversedDictionary();
		
		List<WordRankDTO> entries = rankDao.findByDictionaryAndUser(dictionary, reversed, user)
				.stream()
				.sorted(new TopWordComparator())
				.map(WordRankDTO::createDTO)
				.collect(Collectors.toList());
		model.put("wordranks", entries);
		
		return "listAll";
	}
	
	@GetMapping("/add")
	public String addNew(Map<String, Object> model) {
		checkSessionValid();
		setTitle(model, "Add new word");
		setActiveTab(model, "add");
		setSettingsData(model);
		return "add";
	}
	
	@PostMapping("/add")
	public String addNew(@ModelAttribute("addWordDTO") AddWordDTO addWordDTO, Map<String, Object> model) {
		checkSessionValid();
		
		// bootstrap alerts
		List<BootstrapAlert> alerts = new ArrayList<>();
		model.put("alerts", alerts);
		
		Dictionary dictionary = sessionSettings.getDictionary();
		User user = sessionSettings.getUser();
		
		String name = addWordDTO.getWord();
		String definition = addWordDTO.getDefinition();
		
		if (name == null || name.isEmpty()) {
			addAlert(alerts, "word field is empty", BootstrapAlertType.ERROR);
			return addNew(model);
		}
		
		if (definition == null || definition.isEmpty()) {
			addAlert(alerts, "definition field is empty", BootstrapAlertType.ERROR);
			return addNew(model);
		}
		
		Long userId = user == null ? null : user.getId();
		if (userWordDao.findByName(name, dictionary.getId(), userId).isPresent()) {
			addAlert(alerts, "word '" + name + "' already exists", BootstrapAlertType.ERROR);
			return addNew(model);
		}
		
		// create word
		Word word = new Word(dictionary, name, definition);
		wordDao.save(word);
		model.put("word", word);
		
		// and user word
		UserWord userWord = new UserWord(user, word);
		userWordDao.save(userWord);
		
		addAlert(alerts, "Word '" + name + "' has been added successfully.", BootstrapAlertType.SUCCESS);
		logger.info("new word has been added: " + name + ": " + definition);
		
		return addNew(model);
	}
	
}
