package igrek.webdict.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.db.rank.RankDao;
import igrek.webdict.db.user.UserDao;
import igrek.webdict.logic.TopWordComparator;
import igrek.webdict.model.DictionaryCode;
import igrek.webdict.model.dto.WordRankDTO;
import igrek.webdict.model.dto.WordRanksDetailsDTO;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.User;

@RestController
@RequestMapping("/rest/rank")
class WordRankRestController {
	
	private final DictionaryDao dictionaryDao;
	private final RankDao rankDao;
	private final UserDao userDao;
	
	@Autowired
	public WordRankRestController(DictionaryDao dictionaryDao, RankDao rankDao, UserDao userDao) {
		this.dictionaryDao = dictionaryDao;
		this.rankDao = rankDao;
		this.userDao = userDao;
	}
	
	@GetMapping({"", "all"})
	public List<WordRankDTO> getAll() {
		return rankDao.findAll().stream().map(WordRankDTO::createDTO).collect(Collectors.toList());
	}
	
	@GetMapping("/tops/{userId}/{dictionaryCode}")
	public List<WordRanksDetailsDTO> getAllOrderByTop(@PathVariable("userId") long userId, @PathVariable("dictionaryCode") String dictionaryCode) {
		// user retrieval and validation
		Optional<User> oUser = userDao.findOne(userId);
		if (!oUser.isPresent())
			throw new IllegalArgumentException("user with given id doesn't exist");
		
		// dictionary retrieval and validation
		DictionaryCode dictCode = DictionaryCode.parse(dictionaryCode);
		Optional<Dictionary> oDictionary = dictionaryDao.findByLanguages(dictCode.getSourceLanguage(), dictCode
				.getTargetLanguage());
		if (!oDictionary.isPresent())
			throw new IllegalArgumentException("dictionary with given languages doesn't exist");
		boolean reversedDictionary = dictCode.isReversedDictionary();
		
		return rankDao.findByDictionaryAndUser(oDictionary.get(), reversedDictionary, oUser.get())
				.stream()
				.sorted(new TopWordComparator())
				.map(WordRanksDetailsDTO::createDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/all/{userId}/{dictionaryCode}")
	public List<WordRanksDetailsDTO> getAll(@PathVariable("userId") long userId, @PathVariable("dictionaryCode") String dictionaryCode) {
		// user retrieval and validation
		Optional<User> oUser = userDao.findOne(userId);
		if (!oUser.isPresent())
			throw new IllegalArgumentException("user with given id doesn't exist");
		
		// dictionary retrieval and validation
		DictionaryCode dictCode = DictionaryCode.parse(dictionaryCode);
		Optional<Dictionary> oDictionary = dictionaryDao.findByLanguages(dictCode.getSourceLanguage(), dictCode
				.getTargetLanguage());
		if (!oDictionary.isPresent())
			throw new IllegalArgumentException("dictionary with given languages doesn't exist");
		boolean reversedDictionary = dictCode.isReversedDictionary();
		
		return rankDao.findByDictionaryAndUser(oDictionary.get(), reversedDictionary, oUser.get())
				.stream()
				.map(WordRanksDetailsDTO::createDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{rankId}")
	public WordRankDTO findById(@PathVariable("rankId") long rankId) {
		return rankDao.findOne(rankId).map(WordRankDTO::createDTO).
				orElse(null);
	}
	
	@GetMapping("/top/{userId}/{dictionaryCode}")
	public WordRankDTO getTop(@PathVariable("userId") long userId, @PathVariable("dictionaryCode") String dictionaryCode) {
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
		
		return rankDao.getTop(oDictionary.get(), reversedDictionary, oUser.get())
				.map(WordRankDTO::createDTO)
				.orElse(null);
	}
	
	@PostMapping("/{rankId}/skip")
	public ResponseEntity<WordRankDTO> skipEntry(@PathVariable("rankId") long rankId) {
		return responseDictEntryOK(updateRankRelative(rankId, 0));
	}
	
	@PostMapping("/{rankId}/answer/correct")
	public ResponseEntity<WordRankDTO> answerCorrect(@PathVariable("rankId") long rankId) {
		return responseDictEntryOK(updateRankRelative(rankId, -1));
	}
	
	@PostMapping("/{rankId}/answer/wrong")
	public ResponseEntity<WordRankDTO> answerWrong(@PathVariable("rankId") long rankId) {
		return responseDictEntryOK(updateRankRelative(rankId, +1));
	}
	
	private Rank findWordRank(long rankId) {
		Optional<Rank> oRank = rankDao.findOne(rankId);
		if (!oRank.isPresent())
			throw new IllegalArgumentException("word rank with given id doesn't exist");
		return oRank.get();
	}
	
	private Rank updateRankRelative(long rankId, double relativeRank) {
		Rank rank = findWordRank(rankId);
		rank.setRankValue(rank.getRankValue() + relativeRank);
		// increment tries count
		rank.setTriesCount(rank.getTriesCount() + 1);
		// update last use
		rank.setLastUse(LocalDateTime.now());
		rankDao.save(rank);
		return rank;
	}
	
	private ResponseEntity<WordRankDTO> responseDictEntryOK(Rank rank) {
		return new ResponseEntity<>(WordRankDTO.createDTO(rank), HttpStatus.OK);
	}
}
