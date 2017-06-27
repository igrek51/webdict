package igrek.webdict.db.dictentry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
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
	public List<DictEntry> findByDictionaryId(Long dictionaryId) {
		return dictEntryRepository.findByDictionaryId(dictionaryId);
	}
	
	@Override
	public Optional<DictEntry> getTop() {
		List<DictEntry> entries = findAll();
		
		Comparator<DictEntry> effectiveRankComparator = (o1, o2) -> {
			double diff = o1.getEffectiveRank() - o2.getEffectiveRank();
			return diff < 0 ? -1 : 1;
		};
		
		return entries.stream().max(effectiveRankComparator);
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
