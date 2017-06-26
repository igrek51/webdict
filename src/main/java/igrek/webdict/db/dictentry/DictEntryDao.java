package igrek.webdict.db.dictentry;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import igrek.webdict.model.DictEntry;

@Repository
public interface DictEntryDao {
	
	int count();
	
	Optional<DictEntry> findOne(long id);
	
	List<DictEntry> getByDictionaryId(long dictionaryId);
	
	List<DictEntry> findAll();
	
	void save(DictEntry dictEntry);
	
}
