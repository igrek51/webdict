package igrek.webdict.db.rank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.Word;

@Transactional
public interface RankJpaRepository extends JpaRepository<Rank, Long> {
	
	List<Rank> findByWordAndReversed(Word word, boolean reversed);
	
	// TODO HQL for joining with word table
	//	List<Rank> findByDictionary(Dictionary dictionary, boolean reversed, User user);
	
}