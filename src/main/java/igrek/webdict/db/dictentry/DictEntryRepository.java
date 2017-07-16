package igrek.webdict.db.dictentry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.model.DictEntry;

@Transactional()
public interface DictEntryRepository extends JpaRepository<DictEntry, Long> {
	
	List<DictEntry> findByDictionaryId(Long dictionaryId);
	
	List<DictEntry> findByWord(String word);
	
}
