package igrek.webdict.db.dictentry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import igrek.webdict.db.DictEntryRepository;
import igrek.webdict.model.DictEntry;

public class InMemoryDictEntryRepository implements DictEntryRepository {
	
	private List<DictEntry> dictEntries = new ArrayList<>();
	
	public InMemoryDictEntryRepository() {
	
	}
	
	public int size() {
		return dictEntries.size();
	}
	
	public Optional<DictEntry> findById(long id) {
		return dictEntries.stream().filter(d -> d.getId() == id).findAny();
	}
	
	public List<DictEntry> getAll() {
		return new ArrayList<>(dictEntries);
	}
}
