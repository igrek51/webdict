package igrek.webdict.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.model.dto.DictionaryDTO;
import igrek.webdict.model.entity.Dictionary;

@RestController
@RequestMapping("/rest/dictionary")
class DictionaryRestController {
	
	private final DictionaryDao dictionaryDao;
	
	@Autowired
	public DictionaryRestController(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}
	
	@GetMapping({"", "all"})
	public List<DictionaryDTO> getAll() {
		return dictionaryDao.findAll()
				.stream()
				.map(DictionaryDTO::createDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{sourceLanguage}/{targetLanguage}")
	public DictionaryDTO findByLanguages(@PathVariable("sourceLanguage") String sourceLanguage, @PathVariable("targetLanguage") String targetLanguage) {
		Optional<Dictionary> dictionary = dictionaryDao.findByLanguages(sourceLanguage, targetLanguage);
		return dictionary.map(DictionaryDTO::createDTO).orElse(null);
	}
	
}
