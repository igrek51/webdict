package igrek.webdict.db.rank;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
		
		addSampleEntity("ass", false, 0);
		addSampleEntity("dick", false, 0);
		addSampleEntity("moby dick", false, 0);
		addSampleEntity("mock", false, 0);
	}
	
	private void addSampleEntity(String wordName, boolean reversed, double rankValue) {
		Optional<Word> oWord = wordDao.findByName(wordName);
		super.addSampleEntity(new Rank(oWord.get(), reversed, LocalDateTime.now(), rankValue));
	}
	
	@Override
	public List<Rank> findByDictionary(Dictionary dictionary, boolean reversed, User user) {
		return entities.stream()
				.filter(rank -> Objects.equals(rank.isReversed(), reversed))
				.filter(rank -> Objects.equals(rank.getWord()
						.getDictionary()
						.getId(), dictionary.getId()))
				.filter(rank -> rank.getWord().getUser() == null || Objects.equals(rank.getWord()
						.getUser()
						.getId(), user.getId()))
				.collect(Collectors.toList());
	}
	
	@Override
	public Optional<Rank> getTop(Dictionary dictionary, boolean reversed, User user) {
		return entities.stream()
				.filter(rank -> Objects.equals(rank.isReversed(), reversed))
				.filter(rank -> Objects.equals(rank.getWord()
						.getDictionary()
						.getId(), dictionary.getId()))
				.filter(rank -> rank.getWord().getUser() == null || Objects.equals(rank.getWord()
						.getUser()
						.getId(), user.getId()))
				.min(new TopWordComparator());
	}
}
