package igrek.webdict.db.dictentry;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.model.DictEntry;

public class InMemoryDictEntryDao implements DictEntryDao {
	
	private List<DictEntry> dictEntries = new ArrayList<>();
	
	private final DictionaryDao dictionaryDao;
	
	@Autowired
	public InMemoryDictEntryDao(DictionaryDao dictionaryDao) {
		dictEntries.add(new DictEntry(1L, 1, "ass", "dupa", 0, LocalDateTime.now()));
		dictEntries.add(new DictEntry(2L, 1, "as", "gdy", 0, LocalDateTime.now()));
		this.dictionaryDao = dictionaryDao;
	}
	
	@Override
	public int count() {
		return dictEntries.size();
	}
	
	@Override
	public Optional<DictEntry> findOne(long id) {
		return dictEntries.stream().
				filter(d -> d.getId() == id).
				findAny();
	}
	
	@Override
	public List<DictEntry> findAll() {
		return new ArrayList<>(dictEntries);
	}
	
	@Override
	public List<DictEntry> getByDictionaryId(long dictionaryId) {
		return dictEntries.stream()
				.filter(d -> d.getDictionaryId() == dictionaryId)
				.collect(Collectors.toList());
	}
	
	private long getMaxId() {
		return dictEntries.stream().mapToLong(DictEntry::getId).max().orElse(0);
	}
	
	@Override
	public void save(DictEntry dictEntry) {
		if (dictEntry.getId() == null) {
			dictEntry.setId(getMaxId() + 1);
		}
		dictEntries.removeIf(d -> d.getId().equals(dictEntry.getId()));
		dictEntries.add(dictEntry);
	}
}
