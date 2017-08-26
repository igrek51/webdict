package igrek.webdict.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.db.rank.RankDao;
import igrek.webdict.db.user.UserDao;
import igrek.webdict.db.word.WordDao;
import igrek.webdict.model.DictionaryCode;
import igrek.webdict.model.dto.WordDTO;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.User;
import igrek.webdict.model.entity.Word;

@RestController
@RequestMapping("/rest/word")
class WordRestController {
	
	private final WordDao wordDao;
	private final UserDao userDao;
	private final RankDao rankDao;
	private final DictionaryDao dictionaryDao;
	
	@Autowired
	public WordRestController(WordDao wordDao, UserDao userDao, RankDao rankDao, DictionaryDao dictionaryDao) {
		this.wordDao = wordDao;
		this.userDao = userDao;
		this.rankDao = rankDao;
		this.dictionaryDao = dictionaryDao;
	}
	
	@GetMapping({"", "all"})
	public List<WordDTO> getAll() {
		return wordDao.findAll().stream().map(WordDTO::createDTO).collect(Collectors.toList());
	}
	
	@GetMapping("/withoutRank/{userId}/{dictionaryCode}")
	public List<WordDTO> findWordsWithoutRank(@PathVariable("userId") long userId, @PathVariable("dictionaryCode") String dictionaryCode) {
		// user retrieval and validation
		Optional<User> oUser = userDao.findOne(userId);
		if (!oUser.isPresent()) {
			throw new IllegalArgumentException("user with given id doesn't exist");
		}
		
		// dictionary retrieval and validation
		DictionaryCode dictCode = DictionaryCode.parse(dictionaryCode);
		Optional<Dictionary> oDictionary = dictionaryDao.findByLanguages(dictCode.getSourceLanguage(), dictCode
				.getTargetLanguage());
		if (!oDictionary.isPresent()) {
			throw new IllegalArgumentException("dictionary with given languages doesn't exist");
		}
		boolean reversedDictionary = dictCode.isReversedDictionary();
		
		return rankDao.findWordsWithoutRank(oDictionary.get(), reversedDictionary, oUser.get())
				.stream()
				.map(WordDTO::createDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public WordDTO getById(@PathVariable("id") long id) {
		return wordDao.findOne(id).
				map(WordDTO::createDTO).
				orElse(null);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<WordDTO> update(@PathVariable("id") long id, @RequestBody WordDTO wordDTO) {
		
		Optional<Word> oWord = wordDao.findOne(id);
		
		if (!oWord.isPresent())
			throw new IllegalArgumentException("object with given id doesn't exist");
		
		//update fields
		Word word = oWord.get();
		word.setDefinition(wordDTO.getDefinition());
		word.setName(wordDTO.getName());
		
		Optional<User> oUser = userDao.findByLogin(wordDTO.getUserLogin());
		word.setUser(oUser.orElse(null));
		
		wordDao.save(word);
		return responseDictEntryOK(word);
	}
	
	private ResponseEntity<WordDTO> responseDictEntryOK(Word word) {
		return new ResponseEntity<>(WordDTO.createDTO(word), HttpStatus.OK);
	}
}
