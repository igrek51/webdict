package igrek.webdict.db.language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.model.entity.Language;

@Transactional
public interface LanguageJpaRepository extends JpaRepository<Language, Long> {
	
	List<Language> findByCode(String code);
	
}