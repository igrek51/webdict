package igrek.webdict.db.dictionary;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import igrek.webdict.model.Dictionary;

@Repository
public interface DictionaryDao {
	
	int count();
	
	Optional<Dictionary> findOne(long id);
	
	Optional<Dictionary> findByLangs(String sourceLanguage, String targetLanguage);
	
	List<Dictionary> findAll();
}
