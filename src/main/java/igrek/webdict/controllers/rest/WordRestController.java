package igrek.webdict.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.db.rank.RankDao;
import igrek.webdict.db.user.UserDao;
import igrek.webdict.db.word.WordDao;
import igrek.webdict.model.dto.WordRankDTO;
import igrek.webdict.model.dto.WordRanksDetailsDTO;
import igrek.webdict.model.dto.parser.WordRankDTOParser;

@RestController
@RequestMapping("/rest/word")
class WordRestController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final DictionaryDao dictionaryDao;
	private final WordDao wordDao;
	private final UserDao userDao;
	private final RankDao rankDao;
	
	@Autowired
	public WordRestController(DictionaryDao dictionaryDao, WordDao wordDao, UserDao userDao, RankDao rankDao) {
		this.dictionaryDao = dictionaryDao;
		this.wordDao = wordDao;
		this.userDao = userDao;
		this.rankDao = rankDao;
	}
	
	@GetMapping({"", "all"})
	public List<WordRankDTO> getAll() {
		return rankDao.findAll()
				.stream()
				.map(WordRankDTOParser::parse)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/topAll")
	public List<WordRanksDetailsDTO> getRanks() {
		Comparator<WordRanksDetailsDTO> effectiveRankComparator = (o1, o2) -> {
			double diff = o1.effectiveRank - o2.effectiveRank;
			return diff < 0 ? +1 : -1;
		};
		
		List<WordRanksDetailsDTO> dtos = rankDao.findAll()
				.stream()
				.map(WordRanksDetailsDTO::createRank)
				.collect(Collectors.toList());
		dtos.sort(effectiveRankComparator);
		return dtos;
	}
	
	//TODO complete REWRITE
	//	@GetMapping("/{id}")
	//	public WordRankDTO getById(@PathVariable("id") long id) {
	//		return wordDao.findOne(id).
	//				map(WordRankDTOParser::parse).
	//				orElse(null);
	//	}
	//
	//	@PutMapping("/{id}")
	//	public ResponseEntity<WordRankDTO> update(@PathVariable("id") long id, @RequestBody WordRankDTO wordRankDTO) {
	//
	//		Optional<Word> word = wordDao.findOne(id);
	//
	//		if (!wordDao.exists(word.getId()))
	//			throw new IllegalArgumentException("object with given id doesn't exist");
	//
	//		Word word = wordDao.fin WordRankDTOParser.parse(wordRankDTO);
	//
	//		if (word.getId() == null || !word.getId().equals(id))
	//			throw new IllegalArgumentException("invalid object id");
	//
	//
	//		wordDao.save(word);
	//		return responseDictEntryOK(word);
	//	}
	//
	//	@PutMapping("/{id}/rank")
	//	public ResponseEntity<WordRankDTO> updateRank(@PathVariable("id") long id, @RequestParam("rank") double rank) {
	//
	//		Word word = findDictEntry(id);
	//		word.setRank(rank);
	//		updateLastUse(word);
	//		wordDao.save(word);
	//
	//		return responseDictEntryOK(word);
	//	}
	//
	//	@PutMapping("/{id}/skip")
	//	public ResponseEntity<WordRankDTO> skipEntry(@PathVariable("id") long id) {
	//
	//		Word word = updateRelativeRank(id, 0);
	//
	//		return responseDictEntryOK(word);
	//	}
	//
	//	@PutMapping("/{id}/answer/correct")
	//	public ResponseEntity<WordRankDTO> answerCorrect(@PathVariable("id") long id) {
	//
	//		Word word = updateRelativeRank(id, -1);
	//
	//		return responseDictEntryOK(word);
	//	}
	//
	//	@PutMapping("/{id}/answer/wrong")
	//	public ResponseEntity<WordRankDTO> answerWrong(@PathVariable("id") long id) {
	//
	//		Word word = updateRelativeRank(id, +1);
	//
	//		return responseDictEntryOK(word);
	//	}
	//
	//	@GetMapping("/top")
	//	public WordRankDTO getTop() {
	//		return wordDao.getTop().
	//				map(WordRankDTOParser::parse).
	//				orElse(null);
	//	}
	//
	//
	//	private Word findDictEntry(long id) {
	//		Optional<Word> oDictEntry = wordDao.findOne(id);
	//		if (!oDictEntry.isPresent())
	//			throw new IllegalArgumentException("object with given id doesn't exist");
	//
	//		return oDictEntry.get();
	//	}
	//
	//	private void updateLastUse(Word word) {
	//		word.setLastUse(LocalDateTime.now());
	//	}
	//
	//	private Word updateRelativeRank(long id, double relativeRank) {
	//		Word word = findDictEntry(id);
	//		word.setRank(word.getRank() + relativeRank);
	//		updateLastUse(word);
	//		wordDao.save(word);
	//		return word;
	//	}
	//
	//	private ResponseEntity<WordRankDTO> responseDictEntryOK(Word word) {
	//		return new ResponseEntity<>(WordRankDTOParser.parse(word), HttpStatus.OK);
	//	}
}
