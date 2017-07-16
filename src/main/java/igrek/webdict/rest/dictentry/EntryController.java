package igrek.webdict.rest.dictentry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

import igrek.webdict.db.dictentry.DictEntryDao;
import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.model.DictEntry;
import igrek.webdict.model.dto.DictEntryDTO;
import igrek.webdict.model.dto.parser.DictEntryDTOParser;

@RestController
@RequestMapping("/rest/entry")
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
		return dictEntryDao.findOne(id).
				map(DictEntryDTOParser::parse).
				orElse(null);
	}
	
	@PostMapping()
	public ResponseEntity<DictEntryDTO> create(@RequestBody DictEntryDTO dictEntryDTO) {
		DictEntry dictEntry = DictEntryDTOParser.parse(dictEntryDTO);
		
		if (dictEntry.getId() == null)
			throw new IllegalArgumentException("null object id");
		
		if (dictEntryDao.exists(dictEntry.getId()))
			throw new IllegalArgumentException("object with given id already exists");
		
		dictEntryDao.save(dictEntry);
		return responseDictEntryOK(dictEntry);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DictEntryDTO> update(@PathVariable("id") long id, @RequestBody DictEntryDTO dictEntryDTO) {
		DictEntry dictEntry = DictEntryDTOParser.parse(dictEntryDTO);
		
		if (dictEntry.getId() == null || !dictEntry.getId().equals(id))
			throw new IllegalArgumentException("invalid object id");
		
		if (!dictEntryDao.exists(dictEntry.getId()))
			throw new IllegalArgumentException("object with given id doesn't exist");
		
		dictEntryDao.save(dictEntry);
		return responseDictEntryOK(dictEntry);
	}
	
	@PutMapping("/{id}/rank")
	public ResponseEntity<DictEntryDTO> updateRank(@PathVariable("id") long id, @RequestParam("rank") double rank) {
		
		DictEntry dictEntry = findDictEntry(id);
		dictEntry.setRank(rank);
		updateLastUse(dictEntry);
		dictEntryDao.save(dictEntry);
		
		return responseDictEntryOK(dictEntry);
	}
	
	@PutMapping("/{id}/skip")
	public ResponseEntity<DictEntryDTO> skipEntry(@PathVariable("id") long id) {
		
		DictEntry dictEntry = updateRelativeRank(id, 0);
		
		return responseDictEntryOK(dictEntry);
	}
	
	@PutMapping("/{id}/answer/correct")
	public ResponseEntity<DictEntryDTO> answerCorrect(@PathVariable("id") long id) {
		
		DictEntry dictEntry = updateRelativeRank(id, -1);
		
		return responseDictEntryOK(dictEntry);
	}
	
	@PutMapping("/{id}/answer/wrong")
	public ResponseEntity<DictEntryDTO> answerWrong(@PathVariable("id") long id) {
		
		DictEntry dictEntry = updateRelativeRank(id, +1);
		
		return responseDictEntryOK(dictEntry);
	}
	
	@GetMapping("/top")
	public DictEntryDTO getTop() {
		return dictEntryDao.getTop().
				map(DictEntryDTOParser::parse).
				orElse(null);
	}
	
	
	private DictEntry findDictEntry(long id) {
		Optional<DictEntry> oDictEntry = dictEntryDao.findOne(id);
		if (!oDictEntry.isPresent())
			throw new IllegalArgumentException("object with given id doesn't exist");
		
		return oDictEntry.get();
	}
	
	private void updateLastUse(DictEntry dictEntry) {
		dictEntry.setLastUse(LocalDateTime.now());
	}
	
	private DictEntry updateRelativeRank(long id, double relativeRank) {
		DictEntry dictEntry = findDictEntry(id);
		dictEntry.setRank(dictEntry.getRank() + relativeRank);
		updateLastUse(dictEntry);
		dictEntryDao.save(dictEntry);
		return dictEntry;
	}
	
	private ResponseEntity<DictEntryDTO> responseDictEntryOK(DictEntry dictEntry) {
		return new ResponseEntity<>(DictEntryDTOParser.parse(dictEntry), HttpStatus.OK);
	}
}
