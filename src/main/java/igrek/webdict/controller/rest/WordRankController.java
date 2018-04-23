package igrek.webdict.controller.rest;

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

import igrek.webdict.domain.dictionary.Dictionary;
import igrek.webdict.domain.dictionary.DictionaryCode;
import igrek.webdict.domain.user.User;
import igrek.webdict.domain.wordrank.Rank;
import igrek.webdict.domain.wordrank.TopWordComparator;
import igrek.webdict.domain.wordrank.WordRankDTO;
import igrek.webdict.service.DictionaryService;
import igrek.webdict.service.RankService;
import igrek.webdict.service.UserService;

@RestController
@RequestMapping("/api/rank")
class WordRankController {
	
	private final UserService userService;
	private final RankService rankService;
	private final DictionaryService dictionaryService;
	
	@Autowired
	public WordRankController(UserService userService, RankService rankService, DictionaryService dictionaryService) {
		this.userService = userService;
		this.rankService = rankService;
		this.dictionaryService = dictionaryService;
	}
	
	@GetMapping({"", "all"})
	public List<WordRankDTO> getAll() {
		return rankService.findAll()
				.stream()
				.map(WordRankDTO::createDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/all/{userId}/{dictionaryCode}")
	public List<WordRankDTO> getAll(@PathVariable("userId") long userId, @PathVariable("dictionaryCode") String dictionaryCode) {
		// user retrieval and validation
		Optional<User> oUser = userService.findOne(userId);
		if (!oUser.isPresent())
			throw new IllegalArgumentException("user with given id doesn't exist");
		
		// dictionary retrieval and validation
		DictionaryCode dictCode = DictionaryCode.parse(dictionaryCode);
		Optional<Dictionary> oDictionary = dictionaryService.findByLanguages(dictCode.getSourceLanguage(), dictCode
				.getTargetLanguage());
		if (!oDictionary.isPresent())
			throw new IllegalArgumentException("dictionary with given languages doesn't exist");
		boolean reversedDictionary = dictCode.isReversedDictionary();
		
		return rankService.findByDictionaryAndUser(oDictionary.get(), reversedDictionary, oUser.get())
				.stream().sorted(new TopWordComparator()).map(WordRankDTO::createDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{rankId}")
	public WordRankDTO findById(@PathVariable("rankId") long rankId) {
		return rankService.findOne(rankId).map(WordRankDTO::createDTO).
				orElse(null);
	}
	
	@GetMapping("/top/{userId}/{dictionaryCode}")
	public WordRankDTO getTop(@PathVariable("userId") long userId, @PathVariable("dictionaryCode") String dictionaryCode) {
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
		
		return rankService.getTop(oDictionary.get(), reversedDictionary, oUser.get())
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
	
	@PostMapping("/offset/{userId}/{dictionaryCode}/{relativeOffset}")
	public ResponseEntity offsetRanks(@PathVariable("userId") long userId, @PathVariable("dictionaryCode") String dictionaryCode, @PathVariable("relativeOffset") long relativeOffset) {
		// user retrieval and validation
		Optional<User> oUser = userService.findOne(userId);
		if (!oUser.isPresent())
			throw new IllegalArgumentException("user with given id doesn't exist");
		
		// dictionary retrieval and validation
		DictionaryCode dictCode = DictionaryCode.parse(dictionaryCode);
		Optional<Dictionary> oDictionary = dictionaryService.findByLanguages(dictCode.getSourceLanguage(), dictCode
				.getTargetLanguage());
		if (!oDictionary.isPresent())
			throw new IllegalArgumentException("dictionary with given languages doesn't exist");
		boolean reversedDictionary = dictCode.isReversedDictionary();
		
		// change filtered ranks by the same relative offset
		List<Rank> ranks = rankService.findByDictionaryAndUser(oDictionary.get(), reversedDictionary, oUser
				.get());
		ranks.forEach(rank -> {
			rank.setRankValue(rank.getRankValue() + relativeOffset);
			rankService.save(rank);
		});
		
		return new ResponseEntity<>("all ranks of user " + userId + " and dict " + dictionaryCode + " have been changed by " + relativeOffset, HttpStatus.OK);
	}
	
	private Rank findWordRank(long rankId) {
		Optional<Rank> oRank = rankService.findOne(rankId);
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
		rankService.save(rank);
		return rank;
	}
	
	private ResponseEntity<WordRankDTO> responseDictEntryOK(Rank rank) {
		return new ResponseEntity<>(WordRankDTO.createDTO(rank), HttpStatus.OK);
	}
}
