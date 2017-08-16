package igrek.webdict.db.rank;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import igrek.webdict.db.common.BaseJpaDao;
import igrek.webdict.logic.TopWordComparator;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.User;

public class RankJpaDao extends BaseJpaDao<Rank> implements RankDao {
	
	private final RankJpaRepository jpaRepository;
	
	@Autowired
	public RankJpaDao(RankJpaRepository jpaRepository) {
		
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}
	
	@Override
	public List<Rank> findByDictionary(Dictionary dictionary, boolean reversed, User user) {
		if (user == null) { // do not filter by user
			return jpaRepository.findByDictionaryAndReversed(dictionary, reversed);
		} else {
			return jpaRepository.findByDictionaryAndReversedAndUser(dictionary, reversed, user);
		}
	}
	
	@Override
	public Optional<Rank> getTop(Dictionary dictionary, boolean reversed, User user) {
		List<Rank> ranks = findByDictionary(dictionary, reversed, user);
		// shuffle list in order to get random entry when ranks are equal
		Collections.shuffle(ranks);
		
		return ranks.stream().min(new TopWordComparator());
	}
}
