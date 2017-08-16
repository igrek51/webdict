package igrek.webdict.controllers.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.db.rank.RankDao;
import igrek.webdict.db.user.UserDao;
import igrek.webdict.db.word.WordDao;
import igrek.webdict.model.dto.AddWordDTO;
import igrek.webdict.model.dto.WordRankDTO;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.User;
import igrek.webdict.model.entity.Word;
import igrek.webdict.ui.alert.BootstrapAlert;
import igrek.webdict.ui.alert.BootstrapAlertType;

@Controller
@RequestMapping("/")
public class WordController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final DictionaryDao dictionaryDao;
	private final WordDao wordDao;
	private final RankDao rankDao;
	private final UserDao userDao;
	
	@Autowired
	public WordController(DictionaryDao dictionaryDao, WordDao wordDao, RankDao rankDao, UserDao userDao) {
		this.dictionaryDao = dictionaryDao;
		this.wordDao = wordDao;
		this.rankDao = rankDao;
		this.userDao = userDao;
	}
	
	@GetMapping({"", "/", "/top"})
	public String showTop(Map<String, Object> model) {
		model.put("title", "Top word");
		
		// TODO select dictionary and user from session or address
		Dictionary dictionary = dictionaryDao.findAll().get(0);
		User user = userDao.findAll().get(0);
		boolean reversed = false;
		
		WordRankDTO wordrank = rankDao.getTop(dictionary, reversed, user)
				.map(WordRankDTO::createDTO)
				.orElse(null);
		
		model.put("wordrank", wordrank);
		
		return view("top");
	}
	
	@GetMapping("/all")
	public String listAll(Map<String, Object> model) {
		model.put("title", "All dictionary words");
		
		List<WordRankDTO> entries = rankDao.findAll().stream().map(WordRankDTO::createDTO)
				.collect(Collectors.toList());
		model.put("wordranks", entries);
		
		return view("listAll");
	}
	
	@GetMapping("/add")
	public String addNew(Map<String, Object> model) {
		model.put("title", "Add new word");
		return view("add");
	}
	
	@PostMapping("/add")
	public String addNew(@ModelAttribute("addWordDTO") AddWordDTO addWordDTO, Map<String, Object> model) {
		model.put("title", "Add new word");
		String view = view("add");
		// bootstrap alerts
		List<BootstrapAlert> alerts = new ArrayList<>();
		model.put("alerts", alerts);
		
		// TODO select dictionary id from dropdown or session
		Optional<Dictionary> dictionary = dictionaryDao.findByLanguages("en", "pl");
		if (!dictionary.isPresent())
			throw new IllegalArgumentException("no such dictionary");
		User user = userDao.findAll().get(0);
		
		String name = addWordDTO.getWord();
		String definition = addWordDTO.getDefinition();
		
		if (name == null || name.isEmpty()) {
			addAlert(alerts, "word field is empty", BootstrapAlertType.ERROR);
			return view;
		}
		
		if (definition == null || definition.isEmpty()) {
			addAlert(alerts, "definition field is empty", BootstrapAlertType.ERROR);
			return view;
		}
		
		// TODO filter also by dictionary id and user
		if (wordDao.findByName(name).isPresent()) {
			addAlert(alerts, "word '" + name + "' already exists", BootstrapAlertType.ERROR);
			return view;
		}
		
		// create word
		Word word = new Word(dictionary.get(), user, name, definition);
		wordDao.save(word);
		model.put("word", word);
		
		// create initial ranks
		LocalDateTime lastUse = LocalDateTime.now();
		double rankValue = 0;
		boolean reversed = false;
		Rank rank = new Rank(word, reversed, lastUse, rankValue);
		rankDao.save(rank);
		
		addAlert(alerts, "Word '" + name + "' has been added successfully.", BootstrapAlertType.SUCCESS);
		logger.info("new word has been added: " + name + ": " + definition);
		
		return view;
	}
	
	private String view(String viewName) {
		return "dict/" + viewName;
	}
	
	private void addAlert(List<BootstrapAlert> alerts, String message, BootstrapAlertType type) {
		BootstrapAlert alert = new BootstrapAlert(message, type);
		alerts.add(alert);
	}
}
