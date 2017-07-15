package igrek.webdict.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import igrek.webdict.db.dictentry.DictEntryDao;
import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.model.dto.DictEntryDTO;
import igrek.webdict.model.dto.parser.DictEntryDTOParser;

@Controller
@RequestMapping("/dict")
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
	
	@GetMapping("/showAll")
	public String listAll(Map<String, Object> model) {
		model.put("title", "All dict entries");
		
		List<DictEntryDTO> entries = dictEntryDao.findAll()
				.stream()
				.map(DictEntryDTOParser::parse)
				.collect(Collectors.toList());
		model.put("entries", entries);
		
		return VIEW_SUBDIR + "listAll"; // view listAll
	}
}
