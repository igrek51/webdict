package igrek.webdict.db.dictentry;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import igrek.webdict.model.DictEntry;

@Repository
public interface DictEntryDao {
	
	long count();
	
	Optional<DictEntry> findOne(Long id);
	
	List<DictEntry> findByDictionaryId(Long dictionaryId);
	
	Optional<DictEntry> getTop();
	
	List<DictEntry> findAll();
	
	void save(DictEntry dictEntry);
	
	boolean exists(Long id);
	
	Optional<DictEntry> findByWord(String word);
	
}
