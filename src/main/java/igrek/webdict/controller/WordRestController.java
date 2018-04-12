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
import igrek.webdict.repository.dictionary.DictionaryDao;
import igrek.webdict.repository.rank.RankDao;
import igrek.webdict.repository.user.UserDao;
import igrek.webdict.repository.word.WordDao;

@RestController
@RequestMapping("/api/word")
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
		
		return rankDao.findUserWordsWithoutRank(oDictionary.get(), reversedDictionary, oUser.get())
				.stream()
				.map(UserWord::getWord)
				.map(WordDTO::createDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public WordDTO getById(@PathVariable("id") long id) {
		return wordDao.findOne(id).
				map(WordDTO::createDTO).
				orElse(null);
	}
	
}
