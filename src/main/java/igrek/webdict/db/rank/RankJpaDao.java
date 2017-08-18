package igrek.webdict.db.rank;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
		// TODO needs optimization
		// get existing ranks (which have been used at least once)
		List<Rank> existingRanks;
		if (user == null) { // do not filter by user
			existingRanks = jpaRepository.findByDictionaryAndReversed(dictionary, reversedDictionary);
		} else {
			existingRanks = jpaRepository.findByDictionaryAndReversedAndUser(dictionary, reversedDictionary, user);
		}
		
		// get words without ranks
		Long userId = user == null ? null : user.getId();
		List<Word> allWords = wordDao.findByDictionaryAndUser(dictionary.getId(), userId);
		// remove words that have been took into consideration already
		for (Rank rank : existingRanks) {
			Long wordId = rank.getWord().getId();
			allWords.removeIf(word -> Objects.equals(word.getId(), wordId));
		}
		// create default ranks for words without ranks
		for (Word word : allWords) {
			Rank newRank = new Rank(word, reversedDictionary, null, 0.0);
			save(newRank);
			existingRanks.add(newRank);
		}
		return existingRanks;
	}
	
	@Override
	public Optional<Rank> getTop(Dictionary dictionary, boolean reversedDictionary, User user) {
		List<Rank> ranks = findByDictionaryAndUser(dictionary, reversedDictionary, user);
		// shuffle list in order to get random entry when ranks are equal
		Collections.shuffle(ranks);
		return ranks.stream().min(new TopWordComparator());
	}
}
