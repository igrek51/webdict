package igrek.webdict.db.word;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import igrek.webdict.model.entity.Word;

@Transactional
public interface WordJpaRepository extends JpaRepository<Word, Long> {
	
	Word findByNameAndDictionaryId(String name, Long dictionaryId);
	
}
