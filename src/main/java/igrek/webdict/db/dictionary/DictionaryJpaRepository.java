package igrek.webdict.db.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Language;

@Transactional
public interface DictionaryJpaRepository extends JpaRepository<Dictionary, Long> {
	
	List<Dictionary> findBySourceLanguageAndTargetLanguage(Language sourceLanguage, Language targetLanguage);
	
}