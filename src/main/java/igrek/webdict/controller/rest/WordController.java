package igrek.webdict.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import igrek.webdict.controller.payload.PayloadResponse;
import igrek.webdict.domain.dictionary.Dictionary;
import igrek.webdict.domain.dictionary.DictionaryCode;
import igrek.webdict.domain.user.User;
import igrek.webdict.domain.word.AddWordDTO;
import igrek.webdict.domain.word.UserWord;
import igrek.webdict.domain.word.Word;
import igrek.webdict.domain.word.WordDTO;
import igrek.webdict.service.DictionaryService;
import igrek.webdict.service.RankService;
import igrek.webdict.service.UserService;
import igrek.webdict.service.UserWordService;
import igrek.webdict.service.WordService;

@RestController
@RequestMapping("/api/word")
class WordController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final WordService wordService;
	private final UserService userService;
	private final RankService rankService;
	private final DictionaryService dictionaryService;
	private final UserWordService userWordService;
	
	@Autowired
	public WordController(WordService wordService, UserService userService, RankService rankService, DictionaryService dictionaryService, UserWordService userWordService) {
		this.wordService = wordService;
		this.userService = userService;
		this.rankService = rankService;
		this.dictionaryService = dictionaryService;
		this.userWordService = userWordService;
	}
	
	@GetMapping({"", "/", "all"})
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
	
	@PostMapping({"", "/", "add"})
	public PayloadResponse<WordDTO> addNewWord(@RequestBody AddWordDTO addWordDTO) {
		// user retrieval and validation
		Optional<User> oUser = userService.findOne(addWordDTO.getUserId());
		if (!oUser.isPresent())
			return PayloadResponse.error("user with given id doesn't exist");
		
		// dictionary retrieval and validation
		DictionaryCode dictCode = DictionaryCode.parse(addWordDTO.getDictionaryCode());
		Optional<Dictionary> oDictionary = dictionaryService.findByLanguages(dictCode.getSourceLanguage(), dictCode
				.getTargetLanguage());
		if (!oDictionary.isPresent())
			return PayloadResponse.error("dictionary with given languages doesn't exist");
		
		String name = addWordDTO.getWord();
		String definition = addWordDTO.getDefinition();
		
		if (name == null || name.isEmpty())
			return PayloadResponse.error("word field is empty");
		
		if (definition == null || definition.isEmpty())
			return PayloadResponse.error("definition field is empty");
		
		Long userId = oUser.get().getId();
		if (userWordService.findByName(name, oDictionary.get().getId(), userId).isPresent())
			return PayloadResponse.error("word '" + name + "' already exists");
		
		// create word
		Word word = new Word(oDictionary.get(), name, definition);
		wordService.save(word);
		
		// and user word
		UserWord userWord = new UserWord(oUser.get(), word);
		userWordService.save(userWord);
		
		logger.info("new word has been added: " + name + ": " + definition);
		
		return PayloadResponse.ok(WordDTO.createDTO(word), "Word '" + name + "' has been added successfully.");
	}
}
