package igrek.webdict.db.rank;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import igrek.webdict.db.common.BaseJpaDao;
import igrek.webdict.db.word.WordDao;
import igrek.webdict.logic.TopWordComparator;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.User;
import igrek.webdict.model.entity.Word;

public class RankJpaDao extends BaseJpaDao<Rank> implements RankDao {
	
	private final RankJpaRepository jpaRepository;
	private final WordDao wordDao;
	
	@Autowired
	public RankJpaDao(RankJpaRepository jpaRepository, WordDao wordDao) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
		this.wordDao = wordDao;
	}
	
	@Override
	public List<Rank> findByDictionaryAndUser(Dictionary dictionary, boolean reversedDictionary, User user) {
		createMissingRanks(dictionary, reversedDictionary, user);
		// get existing ranks (which have been used at least once) and new ranks
		if (user == null) { // do not filter by user
			return jpaRepository.findByDictionaryAndReversed(dictionary, reversedDictionary);
		} else {
			return jpaRepository.findByDictionaryAndReversedAndUser(dictionary, reversedDictionary, user);
		}
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
		return jpaRepository.findWordsWithoutRank(dictionary, reversedDictionary, user);
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
