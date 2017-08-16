package igrek.webdict.db.dictionary;

import java.util.Objects;
import java.util.Optional;

import igrek.webdict.db.common.BaseInMemoryDao;
import igrek.webdict.db.language.LanguageDao;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Language;

public class DictionaryInMemoryDao extends BaseInMemoryDao<Dictionary> implements DictionaryDao {
	
	private LanguageDao languageDao;
	
	public DictionaryInMemoryDao(LanguageDao languageDao) {
		this.languageDao = languageDao;
		addSampleEntity("en", "pl");
	}
	
	private void addSampleEntity(String sourceLanguageCode, String targetLanguageCode) {
		Language sourceLang = languageDao.findByCode(sourceLanguageCode).get();
		Language targetLang = languageDao.findByCode(targetLanguageCode).get();
		super.addSampleEntity(new Dictionary(sourceLang, targetLang));
	}
	
	@Override
	public Optional<Dictionary> findByLanguages(String sourceLanguage, String targetLanguage) {
		return entities.stream()
				.filter(dictionary -> Objects.equals(dictionary.getSourceLanguage(), sourceLanguage))
				.filter(dictionary -> Objects.equals(dictionary.getTargetLanguage(), targetLanguage))
				.findAny();
	}
}
