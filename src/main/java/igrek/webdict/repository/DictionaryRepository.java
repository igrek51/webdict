package igrek.webdict.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.domain.dictionary.Dictionary;
import igrek.webdict.domain.dictionary.Language;

@Transactional
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
	
	List<Dictionary> findBySourceLanguageAndTargetLanguage(Language sourceLanguage, Language targetLanguage);
	
}