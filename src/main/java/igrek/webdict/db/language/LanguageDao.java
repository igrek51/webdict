package igrek.webdict.db.language;

import java.util.Optional;

import igrek.webdict.db.common.BaseDao;
import igrek.webdict.model.entity.Language;

public interface LanguageDao extends BaseDao<Language> {
	
	Optional<Language> findByCode(String code);
	
}
