package igrek.webdict.db.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import igrek.webdict.model.Dictionary;

public class InMemoryDictionaryDao implements DictionaryDao {
	
	private List<Dictionary> dicts = new ArrayList<>();
	
	public InMemoryDictionaryDao() {
		dicts.add(new Dictionary(1L, "en", "pl"));
	}
	
	@Override
	public int count() {
		return dicts.size();
	}
	
	@Override
	public Optional<Dictionary> findOne(long id) {
		return dicts.stream().filter(d -> d.getId() == id).findAny();
	}
	
	@Override
	public List<Dictionary> findAll() {
		return new ArrayList<>(dicts);
	}
}
