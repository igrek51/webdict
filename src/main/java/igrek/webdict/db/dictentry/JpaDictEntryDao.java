package igrek.webdict.db.dictentry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import igrek.webdict.model.DictEntry;

public class JpaDictEntryDao implements DictEntryDao {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final DictEntryRepository dictEntryRepository;
	
	@Autowired
	public JpaDictEntryDao(DictEntryRepository dictEntryRepository) {
		this.dictEntryRepository = dictEntryRepository;
	}
	
	@Override
	public long count() {
		return dictEntryRepository.count();
	}
	
	@Override
	public Optional<DictEntry> findOne(Long id) {
		return Optional.of(dictEntryRepository.findOne(id));
	}
	
	@Override
	public List<DictEntry> getByDictionaryId(Long dictionaryId) {
		return null; // TODO
	}
	
	@Override
	public Optional<DictEntry> getTop() {
		return null; // TODO
	}
	
	@Override
	public List<DictEntry> findAll() {
		return dictEntryRepository.findAll();
	}
	
	@Override
	public void save(DictEntry dictEntry) {
		dictEntryRepository.save(dictEntry);
	}
	
	@Override
	public boolean exists(Long id) {
		return dictEntryRepository.exists(id);
	}
}
