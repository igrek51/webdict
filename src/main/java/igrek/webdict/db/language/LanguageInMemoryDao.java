package igrek.webdict.db.language;

import java.util.Objects;
import java.util.Optional;

import igrek.webdict.db.common.BaseInMemoryDao;
import igrek.webdict.model.entity.Language;

public class LanguageInMemoryDao extends BaseInMemoryDao<Language> implements LanguageDao {
	
	public LanguageInMemoryDao() {
		addSampleEntity("en");
		addSampleEntity("pl");
	}
	
	private void addSampleEntity(String languageCode) {
		super.addSampleEntity(new Language(languageCode));
	}
	
	@Override
	public Optional<Language> findByCode(String code) {
		return entities.stream().
				filter(language -> Objects.equals(language.getCode(), code)).
				findAny();
	}
	
}
