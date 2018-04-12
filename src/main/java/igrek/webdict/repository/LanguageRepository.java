package igrek.webdict.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.domain.dictionary.Language;

@Transactional
public interface LanguageRepository extends JpaRepository<Language, Long> {
	
	List<Language> findByCode(String code);
	
}