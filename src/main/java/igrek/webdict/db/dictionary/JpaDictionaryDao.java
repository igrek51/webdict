package igrek.webdict.db.dictionary;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import igrek.webdict.db.common.BaseJpaDao;
import igrek.webdict.db.language.LanguageDao;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Language;

public class JpaDictionaryDao extends BaseJpaDao<Dictionary> implements DictionaryDao {
	
	private final DictionaryJpaRepository jpaRepository;
	private LanguageDao languageDao;
	
	@Autowired
	public JpaDictionaryDao(DictionaryJpaRepository jpaRepository, LanguageDao languageDao) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
		this.languageDao = languageDao;
	}
	
	@Override
	public Optional<Dictionary> findByLanguages(String sourceLanguage, String targetLanguage) {
		Optional<Language> sourceLang = languageDao.findByCode(sourceLanguage);
		if (!sourceLang.isPresent())
			return Optional.empty();
		Optional<Language> targetLang = languageDao.findByCode(targetLanguage);
		if (!targetLang.isPresent())
			return Optional.empty();
		return jpaRepository.findBySourceLanguageAndTargetLanguage(sourceLang.get(), targetLang.get())
				.stream()
				.findAny();
	}
}