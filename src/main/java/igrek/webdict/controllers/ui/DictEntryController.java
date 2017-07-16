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
import java.util.stream.Collectors;

import igrek.webdict.db.dictentry.DictEntryDao;
import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.model.DictEntry;
import igrek.webdict.model.dto.AddDictEntryDTO;
import igrek.webdict.model.dto.DictEntryDTO;
import igrek.webdict.model.dto.parser.DictEntryDTOParser;
import igrek.webdict.ui.alert.BootstrapAlert;
import igrek.webdict.ui.alert.BootstrapAlertType;

@Controller
@RequestMapping("/")
public class DictEntryController {
	
	private static final String VIEW_SUBDIR = "dict/";
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final DictionaryDao dictionaryDao;
	
	private final DictEntryDao dictEntryDao;
	
	@Autowired
	public DictEntryController(DictionaryDao dictionaryDao, DictEntryDao dictEntryDao) {
		this.dictionaryDao = dictionaryDao;
		this.dictEntryDao = dictEntryDao;
	}
	
	@GetMapping({"", "/", "/top"})
	public String showTop(Map<String, Object> model) {
		model.put("title", "Top word");
		
		DictEntryDTO dictEntry = dictEntryDao.getTop().
				map(DictEntryDTOParser::parse).
				orElse(null);
		model.put("entry", dictEntry);
		
		return VIEW_SUBDIR + "top";
	}
	
	@GetMapping("/all")
	public String listAll(Map<String, Object> model) {
		model.put("title", "All dictionary entries");
		
		List<DictEntryDTO> entries = dictEntryDao.findAll()
				.stream()
				.map(DictEntryDTOParser::parse)
				.collect(Collectors.toList());
		model.put("entries", entries);
		
		return VIEW_SUBDIR + "listAll";
	}
	
	@GetMapping("/add")
	public String addNew(Map<String, Object> model) {
		model.put("title", "Add new word");
		
		return VIEW_SUBDIR + "add";
	}
	
	@PostMapping("/add")
	public String addNew(@ModelAttribute("addDictEntryDTO") AddDictEntryDTO addDictEntryDTO, Map<String, Object> model) {
		model.put("title", "Add new word");
		String view = VIEW_SUBDIR + "add";
		List<BootstrapAlert> alerts = new ArrayList<>();
		model.put("alerts", alerts);
		
		LocalDateTime lastUse = LocalDateTime.now();
		double rank = 0;
		// TODO select dictionary id from dropdown
		long dictionaryId = 1;
		String word = addDictEntryDTO.getWord();
		String definition = addDictEntryDTO.getDefinition();
		
		if (word == null || word.isEmpty()) {
			addAlert(alerts, "word is empty", BootstrapAlertType.ERROR);
			return view;
		}
		
		if (definition == null || definition.isEmpty()) {
			addAlert(alerts, "definition is empty", BootstrapAlertType.ERROR);
			return view;
		}
		
		// TODO filter also by dictionary id
		if (dictEntryDao.findByWord(word).isPresent()) {
			addAlert(alerts, "word '" + word + "' already exists", BootstrapAlertType.ERROR);
			return view;
		}
		
		DictEntry dictEntry = new DictEntry(null, dictionaryId, word, definition, rank, lastUse);
		
		dictEntryDao.save(dictEntry);
		model.put("entry", dictEntry);
		
		addAlert(alerts, "Word '" + word + "' has been added successfully.", BootstrapAlertType.SUCCESS);
		return view;
	}
	
	private void addAlert(List<BootstrapAlert> alerts, String message, BootstrapAlertType type) {
		BootstrapAlert alert = new BootstrapAlert(message, type);
		alerts.add(alert);
	}
}
