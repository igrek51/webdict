package igrek.webdict.db.rank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.User;

@Transactional
public interface RankJpaRepository extends JpaRepository<Rank, Long> {
	
	@Query("from Rank rank where rank.word.dictionary = ?1 and rank.reversedDictionary = ?2")
	List<Rank> findByDictionaryAndReversed(Dictionary dictionary, boolean reversedDictionary);
	
	@Query("from Rank rank where rank.word.dictionary = ?1 and rank.reversedDictionary = ?2 and rank.word.user = ?3")
	List<Rank> findByDictionaryAndReversedAndUser(Dictionary dictionary, boolean reversedDictionary, User user);
	
}