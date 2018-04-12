package igrek.webdict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import igrek.webdict.domain.DictionaryCode;
import igrek.webdict.domain.dto.WordDTO;
import igrek.webdict.domain.entity.Dictionary;
import igrek.webdict.domain.entity.User;
import igrek.webdict.domain.entity.UserWord;
import igrek.webdict.service.DictionaryService;
import igrek.webdict.service.RankService;
import igrek.webdict.service.UserService;
import igrek.webdict.service.WordService;

@RestController
@RequestMapping("/api/word")
class WordController {
	
	private final WordService wordService;
	private final UserService userService;
	private final RankService rankService;
	private final DictionaryService dictionaryService;
	
	@Autowired
	public WordController(WordService wordService, UserService userService, RankService rankService, DictionaryService dictionaryService) {
		this.wordService = wordService;
		this.userService = userService;
		this.rankService = rankService;
		this.dictionaryService = dictionaryService;
	}
	
	@GetMapping({"", "all"})
	public List<WordDTO> getAll() {
		return wordService.findAll().stream().map(WordDTO::createDTO).collect(Collectors.toList());
	}
	
	@GetMapping("/withoutRank/{userId}/{dictionaryCode}")
	public List<WordDTO> findWordsWithoutRank(@PathVariable("userId") long userId, @PathVariable("dictionaryCode") String dictionaryCode) {
		// user retrieval and validation
		Optional<User> oUser = userService.findOne(userId);
		if (!oUser.isPresent()) {
			throw new IllegalArgumentException("user with given id doesn't exist");
		}
		
		// dictionary retrieval and validation
		DictionaryCode dictCode = DictionaryCode.parse(dictionaryCode);
		Optional<Dictionary> oDictionary = dictionaryService.findByLanguages(dictCode.getSourceLanguage(), dictCode
				.getTargetLanguage());
		if (!oDictionary.isPresent()) {
			throw new IllegalArgumentException("dictionary with given languages doesn't exist");
		}
		boolean reversedDictionary = dictCode.isReversedDictionary();
		
		return rankService.findUserWordsWithoutRank(oDictionary.get(), reversedDictionary, oUser.get())
				.stream()
				.map(UserWord::getWord)
				.map(WordDTO::createDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public WordDTO getById(@PathVariable("id") long id) {
		return wordService.findOne(id).
				map(WordDTO::createDTO).
				orElse(null);
	}
	
}
