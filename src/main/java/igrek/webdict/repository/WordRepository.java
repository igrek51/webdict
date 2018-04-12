package igrek.webdict.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import igrek.webdict.domain.word.Word;

@Transactional
public interface WordRepository extends JpaRepository<Word, Long> {
	
	Word findByNameAndDictionaryId(String name, Long dictionaryId);
	
}
