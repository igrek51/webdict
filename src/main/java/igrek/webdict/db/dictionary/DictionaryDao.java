package igrek.webdict.db.dictionary;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import igrek.webdict.model.Dictionary;

@Repository
public interface DictionaryDao {
	
	int count();
	
	Optional<Dictionary> findOne(long id);
	
	List<Dictionary> findAll();
}
