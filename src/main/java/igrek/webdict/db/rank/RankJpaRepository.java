package igrek.webdict.db.rank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.User;
import igrek.webdict.model.entity.UserWord;

@Transactional
public interface RankJpaRepository extends JpaRepository<Rank, Long> {
	
	@Query("select rank from Rank rank where rank.reversedDictionary = ?2 and rank.userWord.word.dictionary = ?1 and rank.userWord.user = ?3")
	List<Rank> findByDictionaryAndReversedAndUser(Dictionary dictionary, boolean reversedDictionary, User user);
	
	@Query("select userWord from UserWord userWord where userWord.user = ?3 and userWord.word.dictionary = ?1 and userWord.id not in (select rank.userWord.id from Rank rank where rank.reversedDictionary = ?2)")
	List<UserWord> findUserWordsWithoutRank(Dictionary dictionary, boolean reversedDictionary, User user);
	
}