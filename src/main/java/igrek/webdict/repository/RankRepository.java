package igrek.webdict.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.domain.entity.Dictionary;
import igrek.webdict.domain.entity.Rank;
import igrek.webdict.domain.entity.User;
import igrek.webdict.domain.entity.UserWord;

@Transactional
public interface RankRepository extends JpaRepository<Rank, Long> {
	
	@Query("select rank from Rank rank where rank.reversedDictionary = ?2 and rank.userWord.word.dictionary = ?1 and rank.userWord.user = ?3")
	List<Rank> findByDictionaryAndReversedAndUser(Dictionary dictionary, boolean reversedDictionary, User user);
	
	@Query("select userWord from UserWord userWord where userWord.user = ?3 and userWord.word.dictionary = ?1 and userWord.id not in (select rank.userWord.id from Rank rank where rank.reversedDictionary = ?2)")
	List<UserWord> findUserWordsWithoutRank(Dictionary dictionary, boolean reversedDictionary, User user);
	
}