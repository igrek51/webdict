package igrek.webdict.db.dictionary;

import java.util.Optional;

import igrek.webdict.db.common.BaseDao;
import igrek.webdict.model.entity.Dictionary;

public interface DictionaryDao extends BaseDao<Dictionary> {
	
	Optional<Dictionary> findByLanguages(String sourceLanguage, String targetLanguage);
	
}
