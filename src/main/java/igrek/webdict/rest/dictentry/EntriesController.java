package igrek.webdict.rest.dictentry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import igrek.webdict.db.dictentry.DictEntryDao;
import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.model.DictEntry;
import igrek.webdict.model.dto.DictEntryDTO;
import igrek.webdict.model.dto.DictEntryRanksDTO;
import igrek.webdict.model.dto.parser.DictEntryDTOParser;

@RestController
@RequestMapping("/entries")
class EntriesController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final DictionaryDao dictionaryDao;
	
	private final DictEntryDao dictEntryDao;
	
	@Autowired
	public EntriesController(DictionaryDao dictionaryDao, DictEntryDao dictEntryDao) {
		this.dictionaryDao = dictionaryDao;
		this.dictEntryDao = dictEntryDao;
	}
	
	@GetMapping()
	public List<DictEntryDTO> getAll() {
		return dictEntryDao.findAll()
				.stream()
				.map(DictEntryDTOParser::parse)
				.collect(Collectors.toList());
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody List<DictEntryDTO> dtos) {
		for (DictEntryDTO dto : dtos) {
			DictEntry dictEntry = DictEntryDTOParser.parse(dto);
			dictEntryDao.save(dictEntry);
		}
	}
	
	@GetMapping("/top")
	public List<DictEntryRanksDTO> getRanks() {
		Comparator<DictEntryRanksDTO> effectiveRankComparator = (o1, o2) -> {
			double diff = o1.effectiveRank - o2.effectiveRank;
			return diff < 0 ? +1 : -1;
		};
		
		List<DictEntryRanksDTO> dtos = dictEntryDao.findAll()
				.stream()
				.map(DictEntryRanksDTO::createRanks)
				.collect(Collectors.toList());
		dtos.sort(effectiveRankComparator);
		return dtos;
	}
}
