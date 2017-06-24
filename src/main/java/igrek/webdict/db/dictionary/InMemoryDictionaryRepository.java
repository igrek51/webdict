package igrek.webdict.db.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import igrek.webdict.db.DictionaryRepository;
import igrek.webdict.model.Dictionary;

public class InMemoryDictionaryRepository implements DictionaryRepository {
	
	private List<Dictionary> dicts = new ArrayList<>();
	
	public InMemoryDictionaryRepository() {
		dicts.add(new Dictionary(1, "en", "pl"));
		dicts.add(new Dictionary(2, "pl", "en"));
	}
	
	public int size() {
		return dicts.size();
	}
	
	public Optional<Dictionary> findById(long id) {
		return dicts.stream().filter(d -> d.getId() == id).findAny();
	}
	
	public List<Dictionary> getAll() {
		return new ArrayList<>(dicts);
	}
}
