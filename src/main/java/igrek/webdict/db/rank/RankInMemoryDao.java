package igrek.webdict.db.rank;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import igrek.webdict.db.common.BaseInMemoryDao;
import igrek.webdict.db.word.WordDao;
import igrek.webdict.logic.TopWordComparator;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.User;
import igrek.webdict.model.entity.Word;

public class RankInMemoryDao extends BaseInMemoryDao<Rank> implements RankDao {
	
	private WordDao wordDao;
	
	@Autowired
	public RankInMemoryDao(WordDao wordDao) {
		this.wordDao = wordDao;
		
		List<Word> words = wordDao.findAll();
		for (Word word : words) {
			addSampleEntity(word, false, 0);
		}
	}
	
	private void addSampleEntity(Word word, boolean reversedDictionary, double rankValue) {
		super.addSampleEntity(new Rank(word, reversedDictionary, LocalDateTime.now(), rankValue));
	}
	
	@Override
	public List<Rank> findByDictionaryAndUser(Dictionary dictionary, boolean reversedDictionary, User user) {
		createMissingRanks(dictionary, reversedDictionary, user);
		// get existing ranks (which have been used at least once)
		Stream<Rank> rankStream = entities.stream()
				.filter(rank -> Objects.equals(rank.isReversedDictionary(), reversedDictionary))
				.filter(rank -> Objects.equals(rank.getWord()
						.getDictionary()
						.getId(), dictionary.getId()));
		if (user != null) { // filter by user
			rankStream = rankStream.filter(rank -> Objects.equals(rank.getWord()
					.getUser()
					.getId(), user.getId()));
		}
		List<Rank> ranks = rankStream.collect(Collectors.toList());
		return ranks;
	}
	
	@Override
	public Optional<Rank> getTop(Dictionary dictionary, boolean reversedDictionary, User user) {
		List<Rank> ranks = findByDictionaryAndUser(dictionary, reversedDictionary, user);
		// shuffle list in order to get random entry when ranks are equal
		Collections.shuffle(ranks);
		return ranks.stream().min(new TopWordComparator());
	}
	
	@Override
	public List<Word> findWordsWithoutRank(Dictionary dictionary, boolean reversedDictionary, User user) {
		// get existing ranks (which have been used at least once)
		Stream<Rank> rankStream = entities.stream()
				.filter(rank -> Objects.equals(rank.isReversedDictionary(), reversedDictionary))
				.filter(rank -> Objects.equals(rank.getWord()
						.getDictionary()
						.getId(), dictionary.getId()));
		if (user != null) { // filter by user
			rankStream = rankStream.filter(rank -> Objects.equals(rank.getWord()
					.getUser()
					.getId(), user.getId()));
		}
		List<Rank> existingRanks = rankStream.collect(Collectors.toList());
		
		// get words without ranks
		Long userId = user == null ? null : user.getId();
		List<Word> allWords = wordDao.findByDictionaryAndUser(dictionary.getId(), userId);
		// remove words that have been took into consideration already
		for (Rank rank : existingRanks) {
			Long wordId = rank.getWord().getId();
			allWords.removeIf(word -> Objects.equals(word.getId(), wordId));
		}
		
		return allWords;
	}
	
	@Override
	public void createMissingRanks(Dictionary dictionary, boolean reversedDictionary, User user) {
		List<Word> wordsWithout = findWordsWithoutRank(dictionary, reversedDictionary, user);
		if (!wordsWithout.isEmpty()) {
			List<Rank> newRanks = new ArrayList<>();
			// create default ranks for words without ranks
			for (Word word : wordsWithout) {
				Rank newRank = new Rank(word, reversedDictionary, null, 0.0);
				newRanks.add(newRank);
			}
			logger.info(String.format("creating missing ranks for %d words", newRanks.size()));
			save(newRanks);
		}
	}
}
