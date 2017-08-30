package igrek.webdict.db.rank;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import igrek.webdict.db.common.BaseInMemoryDao;
import igrek.webdict.db.userword.UserWordDao;
import igrek.webdict.logic.TopWordComparator;
import igrek.webdict.model.DictionaryCode;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.User;
import igrek.webdict.model.entity.UserWord;

public class RankInMemoryDao extends BaseInMemoryDao<Rank> implements RankDao {
	
	private UserWordDao userWordDao;
	
	@Autowired
	public RankInMemoryDao(UserWordDao userWordDao) {
		this.userWordDao = userWordDao;
		
		List<UserWord> userWords = userWordDao.findByUserId(1L);
		for (UserWord userWord : userWords) {
			addSampleEntity(userWord, false, 0, 0);
		}
	}
	
	private void addSampleEntity(UserWord userWord, boolean reversedDictionary, double rankValue, int triesCount) {
		super.addSampleEntity(new Rank(userWord, reversedDictionary, LocalDateTime.now(), rankValue, triesCount));
	}
	
	@Override
	public List<Rank> findByDictionaryAndUser(Dictionary dictionary, boolean reversedDictionary, User user) {
		createMissingRanks(dictionary, reversedDictionary, user);
		// get already existing ranks
		return getOnlyExistingRanks(dictionary, reversedDictionary, user);
	}
	
	private List<Rank> getOnlyExistingRanks(Dictionary dictionary, boolean reversedDictionary, User user) {
		return entities.stream()
				.filter(rank -> Objects.equals(rank.isReversedDictionary(), reversedDictionary))
				.filter(rank -> Objects.equals(rank.getUserWord().getWord()
						.getDictionary().getId(), dictionary.getId()))
				.filter(rank -> Objects.equals(rank.getUserWord().getUser().getId(), user.getId()))
				.collect(Collectors.toList());
	}
	
	@Override
	public Optional<Rank> getTop(Dictionary dictionary, boolean reversedDictionary, User user) {
		List<Rank> ranks = findByDictionaryAndUser(dictionary, reversedDictionary, user);
		// shuffle list in order to get random entry when ranks are equal
		Collections.shuffle(ranks);
		return ranks.stream().min(new TopWordComparator());
	}
	
	@Override
	public List<UserWord> findUserWordsWithoutRank(Dictionary dictionary, boolean reversedDictionary, User user) {
		// get existing ranks (which have been used at least once)
		List<Rank> existingRanks = getOnlyExistingRanks(dictionary, reversedDictionary, user);
		
		// get all words of user and dictionary
		List<UserWord> allUserDictionaryWords = userWordDao.findByUserIdAndDictionaryId(user.getId(), dictionary
				.getId());
		// remove words that have been took into consideration already
		for (Rank rank : existingRanks) {
			Long userWordId = rank.getUserWord().getId();
			allUserDictionaryWords.removeIf(userWord -> Objects.equals(userWord.getId(), userWordId));
		}
		
		return allUserDictionaryWords;
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
