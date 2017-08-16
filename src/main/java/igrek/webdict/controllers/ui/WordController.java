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
	
	private String view(String viewName) {
		return "dict/" + viewName;
	}
	
	@GetMapping({"", "/", "/top"})
	public String showTop(Map<String, Object> model) {
		model.put("title", "Top word");
		
		// TODO select dictionary and user from session or address
		Dictionary dictionary = dictionaryDao.findAll().get(0);
		User user = userDao.findAll().get(0);
		boolean reversed = false;
		
		WordRankDTO dictEntry = rankDao.getTop(dictionary, reversed, user).
				map(WordRankDTOConverter::toDTO).
				orElse(null);
		model.put("entry", dictEntry);
		
		return view("top");
	}
	
	@GetMapping("/all")
	public String listAll(Map<String, Object> model) {
		model.put("title", "All dictionary entries");
		
		List<WordRankDTO> entries = rankDao.findAll().stream().map(WordRankDTOConverter::toDTO)
				.collect(Collectors.toList());
		model.put("entries", entries);
		
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
		List<BootstrapAlert> alerts = new ArrayList<>();
		model.put("alerts", alerts);
		
		LocalDateTime lastUse = LocalDateTime.now();
		double rank = 0;
		// TODO select dictionary id from dropdown
		Optional<Dictionary> dictionary = dictionaryDao.findByLanguages("en", "pl");
		User user = userDao.findAll().get(0);
		boolean reversed = false;
		
		String name = addWordDTO.getWord();
		String definition = addWordDTO.getDefinition();
		
		if (name == null || name.isEmpty()) {
			addAlert(alerts, "word is empty", BootstrapAlertType.ERROR);
			return view;
		}
		
		if (definition == null || definition.isEmpty()) {
			addAlert(alerts, "definition is empty", BootstrapAlertType.ERROR);
			return view;
		}
		
		// TODO filter also by dictionary id
		if (wordDao.findByName(name).isPresent()) {
			addAlert(alerts, "word '" + name + "' already exists", BootstrapAlertType.ERROR);
			return view;
		}
		
		Word dictEntry = new Word(dictionary.get(), user, name, definition);
		wordDao.save(dictEntry);
		model.put("entry", dictEntry);
		
		addAlert(alerts, "Word '" + name + "' has been added successfully.", BootstrapAlertType.SUCCESS);
		return view;
	}
	
	private void addAlert(List<BootstrapAlert> alerts, String message, BootstrapAlertType type) {
		BootstrapAlert alert = new BootstrapAlert(message, type);
		alerts.add(alert);
	}
}
