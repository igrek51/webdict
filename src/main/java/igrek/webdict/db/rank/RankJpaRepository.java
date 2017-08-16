package igrek.webdict.db.rank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.User;
import igrek.webdict.model.entity.Word;

@Transactional
public interface RankJpaRepository extends JpaRepository<Rank, Long> {
	
	List<Rank> findByWordAndReversed(Word word, boolean reversed);
	
	@Query("from Rank rank where rank.word.dictionary = ?1 and rank.reversed = ?2")
	List<Rank> findByDictionaryAndReversed(Dictionary dictionary, boolean reversed);
	
	@Query("from Rank rank where rank.word.dictionary = ?1 and rank.reversed = ?2 and rank.word.user = ?3")
	List<Rank> findByDictionaryAndReversedAndUser(Dictionary dictionary, boolean reversed, User user);
	
}