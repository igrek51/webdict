package igrek.webdict.db.word;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.model.entity.Word;

@Transactional
public interface WordJpaRepository extends JpaRepository<Word, Long> {
	
	List<Word> findByDictionaryId(Long dictionaryId);
	
	List<Word> findByDictionaryIdAndUserId(Long dictionaryId, Long userId);
	
	List<Word> findByName(String name);
	
}
