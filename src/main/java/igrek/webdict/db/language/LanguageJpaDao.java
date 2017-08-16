package igrek.webdict.db.language;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import igrek.webdict.db.common.BaseJpaDao;
import igrek.webdict.model.entity.Language;

public class LanguageJpaDao extends BaseJpaDao<Language> implements LanguageDao {
	
	private final LanguageJpaRepository jpaRepository;
	
	@Autowired
	public LanguageJpaDao(LanguageJpaRepository jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}
	
	@Override
	public Optional<Language> findByCode(String code) {
		return jpaRepository.findByCode(code).stream().findAny();
	}
	
}
