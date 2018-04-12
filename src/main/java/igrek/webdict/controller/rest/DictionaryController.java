package igrek.webdict.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import igrek.webdict.domain.dto.DictionaryDTO;
import igrek.webdict.domain.entity.Dictionary;
import igrek.webdict.service.DictionaryService;

@RestController
@RequestMapping("/api/dictionary")
class DictionaryController {
	
	private final DictionaryService dictionaryService;
	
	@Autowired
	public DictionaryController(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
	
	@GetMapping({"", "/", "all"})
	public List<DictionaryDTO> getAll() {
		return dictionaryService.findAll()
				.stream()
				.map(DictionaryDTO::createDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{sourceLanguage}/{targetLanguage}")
	public DictionaryDTO findByLanguages(@PathVariable("sourceLanguage") String sourceLanguage, @PathVariable("targetLanguage") String targetLanguage) {
		Optional<Dictionary> dictionary = dictionaryService.findByLanguages(sourceLanguage, targetLanguage);
		return dictionary.map(DictionaryDTO::createDTO).orElse(null);
	}
	
}
