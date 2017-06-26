package igrek.webdict.rest.dictentry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igrek.webdict.db.dictentry.DictEntryDao;
import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.model.DictEntry;
import igrek.webdict.model.dto.DictEntryDTO;
import igrek.webdict.model.dto.parser.DictEntryDTOParser;

@RestController
@RequestMapping("/entry")
class EntryController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final DictionaryDao dictionaryDao;
	
	private final DictEntryDao dictEntryDao;
	
	@Autowired
	public EntryController(DictionaryDao dictionaryDao, DictEntryDao dictEntryDao) {
		this.dictionaryDao = dictionaryDao;
		this.dictEntryDao = dictEntryDao;
	}
	
	@GetMapping("/{id}")
	public DictEntryDTO getById(@PathVariable("id") long id) {
		return dictEntryDao.
				findOne(id).
				map(DictEntryDTOParser::parse).
				orElse(null);
	}
	
	@PostMapping()
	public ResponseEntity<DictEntryDTO> create(@RequestBody DictEntryDTO dictEntryDTO) {
		DictEntry dictEntry = DictEntryDTOParser.parse(dictEntryDTO);
		dictEntryDao.save(dictEntry);
		return new ResponseEntity<>(DictEntryDTOParser.parse(dictEntry), HttpStatus.OK);
	}
}
