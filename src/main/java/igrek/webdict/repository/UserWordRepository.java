package igrek.webdict.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.domain.word.UserWord;

@Transactional
public interface UserWordRepository extends JpaRepository<UserWord, Long> {
	
	List<UserWord> findByUserId(Long userId);
	
	UserWord findByUserIdAndWordId(Long userId, Long wordId);
	
	List<UserWord> findByUserIdAndWordDictionaryId(Long userId, Long wordDictionaryId);
	
	@Query("select userWord from UserWord userWord where userWord.word.dictionary.id = ?1 and userWord.user.id = ?2")
	List<UserWord> findByDictionaryIdAndUserId(Long dictionaryId, Long userId);
	
	@Query("select userWord from UserWord userWord where userWord.word.name = ?1 and userWord.word.dictionary.id = ?2 and userWord.user.id = ?3")
	UserWord findByNameAndDictionaryIdAndUserId(String name, Long dictionaryId, Long userId);
	
}
