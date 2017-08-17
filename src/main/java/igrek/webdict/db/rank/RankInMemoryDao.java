package igrek.webdict.db.rank;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
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
	
	private void addSampleEntity(Word word, boolean reversed, double rankValue) {
		super.addSampleEntity(new Rank(word, reversed, LocalDateTime.now(), rankValue));
	}
	
	@Override
	public List<Rank> findByDictionaryAndUser(Dictionary dictionary, boolean reversed, User user) {
		// get existing ranks (which have been used at least once)
		Stream<Rank> rankStream = entities.stream()
				.filter(rank -> Objects.equals(rank.isReversedDictionary(), reversed))
				.filter(rank -> Objects.equals(rank.getWord()
						.getDictionary().getId(), dictionary.getId()));
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
		// create default ranks for words without ranks
		for (Word word : allWords) {
			Rank newRank = new Rank(word, reversed, null, 0.0);
			save(newRank);
			existingRanks.add(newRank);
		}
		return existingRanks;
	}
	
	@Override
	public Optional<Rank> getTop(Dictionary dictionary, boolean reversed, User user) {
		List<Rank> ranks = findByDictionaryAndUser(dictionary, reversed, user);
		// shuffle list in order to get random entry when ranks are equal
		Collections.shuffle(ranks);
		return ranks.stream().min(new TopWordComparator());
	}
}
