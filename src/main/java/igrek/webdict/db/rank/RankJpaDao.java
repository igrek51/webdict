package igrek.webdict.db.rank;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import igrek.webdict.db.common.BaseJpaDao;
import igrek.webdict.logic.TopWordComparator;
import igrek.webdict.model.DictionaryCode;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.User;
import igrek.webdict.model.entity.UserWord;

public class RankJpaDao extends BaseJpaDao<Rank> implements RankDao {
	
	private final RankJpaRepository jpaRepository;
	
	@Autowired
	public RankJpaDao(RankJpaRepository jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}
	
	@Override
	public List<Rank> findByDictionaryAndUser(Dictionary dictionary, boolean reversedDictionary, User user) {
		createMissingRanks(dictionary, reversedDictionary, user);
		// get existing ranks (which have been used at least once) and new ranks
		return jpaRepository.findByDictionaryAndReversedAndUser(dictionary, reversedDictionary, user);
	}
	
	@Override
	public Optional<Rank> getTop(Dictionary dictionary, boolean reversedDictionary, User user) {
		// FIXME merge bidirectional ranks into one entity
		List<Rank> ranks = findByDictionaryAndUser(dictionary, reversedDictionary, user);
		List<Rank> reversedRanks = findByDictionaryAndUser(dictionary, !reversedDictionary, user);
		ranks.forEach(rank -> {
			rank.setReversedRank(findRespectiveReversedRank(rank, reversedRanks));
		});
		// shuffle list in order to get random entry when ranks are equal
		Collections.shuffle(ranks);
		return ranks.stream().min(new TopWordComparator());
	}
	
	private Optional<Rank> findRespectiveReversedRank(Rank simpleRank, List<Rank> reversedRanks) {
		for (Rank reversedRank : reversedRanks) {
			if (reversedRank.getUserWord().getId().equals(simpleRank.getUserWord().getId()))
				return Optional.of(reversedRank);
		}
		return Optional.empty();
	}
	
	@Override
	public List<UserWord> findUserWordsWithoutRank(Dictionary dictionary, boolean reversedDictionary, User user) {
		return jpaRepository.findUserWordsWithoutRank(dictionary, reversedDictionary, user);
	}
	
	@Override
	public void createMissingRanks(Dictionary dictionary, boolean reversedDictionary, User user) {
		List<UserWord> userWordsWithout = findUserWordsWithoutRank(dictionary, reversedDictionary, user);
		if (!userWordsWithout.isEmpty()) {
			List<Rank> newRanks = new ArrayList<>();
			// create default ranks for words without ranks
			for (UserWord userWord : userWordsWithout) {
				Rank newRank = new Rank(userWord, reversedDictionary, null, 0.0, 0);
				newRanks.add(newRank);
			}
			String dictionaryCode = DictionaryCode.toDictionaryCode(dictionary, reversedDictionary);
			logger.info(String.format("Creating missing ranks for %d words, user: %s, dictionary: %s", newRanks
					.size(), user, dictionaryCode));
			save(newRanks);
		}
	}
}
