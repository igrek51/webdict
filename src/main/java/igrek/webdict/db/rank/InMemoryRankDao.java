package igrek.webdict.db.rank;

import org.springframework.beans.factory.annotation.Autowired;

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

public class InMemoryRankDao extends BaseInMemoryDao<Rank> implements RankDao {
	
	private WordDao wordDao;
	
	@Autowired
	public InMemoryRankDao(WordDao wordDao) {
		this.wordDao = wordDao;
		//TODO sample entities
	}
	
	// TODO
	//	private void addSampleEntity(String word, String definition) {
	//		super.addSampleEntity(new Word(dictionary, user, word, String definition));
	//	}
	
	@Override
	public List<Rank> findByDictionary(Dictionary dictionary, boolean reversed, User user) {
		return entities.stream()
				.filter(rank -> Objects.equals(rank.isReversed(), reversed))
				.filter(rank -> Objects.equals(rank.getWord()
						.getDictionary()
						.getId(), dictionary.getId()))
				.filter(rank -> Objects.equals(rank.getWord().getUser().getId(), user.getId()))
				.collect(Collectors.toList());
	}
	
	@Override
	public Optional<Rank> getTop(Dictionary dictionary, boolean reversed, User user) {
		return entities.stream()
				.filter(rank -> Objects.equals(rank.isReversed(), reversed))
				.filter(rank -> Objects.equals(rank.getWord()
						.getDictionary()
						.getId(), dictionary.getId()))
				.filter(rank -> Objects.equals(rank.getWord().getUser().getId(), user.getId()))
				.min(new TopWordComparator());
	}
}
