package igrek.webdict.db.dictentry;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import igrek.webdict.model.DictEntry;

public class InMemoryDictEntryDao implements DictEntryDao {
	
	private List<DictEntry> dictEntries = new ArrayList<>();
	
	@Autowired
	public InMemoryDictEntryDao() {
		addSampleEntry("ass", "dupa");
		addSampleEntry("as", "gdy");
		addSampleEntry("dick", "dik");
	}
	
	private void addSampleEntry(String word, String definition) {
		dictEntries.add(new DictEntry(getMaxId() + 1, 1, word, definition, 0, LocalDateTime.now()));
	}
	
	@Override
	public long count() {
		return dictEntries.size();
	}
	
	@Override
	public Optional<DictEntry> findOne(Long id) {
		return dictEntries.stream().
				filter(d -> Objects.equals(d.getId(), id)).
				findAny();
	}
	
	@Override
	public List<DictEntry> findAll() {
		return new ArrayList<>(dictEntries);
	}
	
	@Override
	public List<DictEntry> findByDictionaryId(Long dictionaryId) {
		return dictEntries.stream().filter(d -> Objects.equals(d.getDictionaryId(), dictionaryId))
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
	
	@Override
	public boolean exists(Long id) {
		return dictEntries.stream().anyMatch(d -> Objects.equals(d.getId(), id));
	}
	
	@Override
	public Optional<DictEntry> getTop() {
		
		Comparator<DictEntry> effectiveRankComparator = (o1, o2) -> {
			double diff = o1.getEffectiveRank() - o2.getEffectiveRank();
			return diff < 0 ? -1 : 1;
		};
		
		return dictEntries.stream().max(effectiveRankComparator);
	}
	
	@Override
	public Optional<DictEntry> findByWord(String word) {
		return dictEntries.stream().
				filter(d -> Objects.equals(d.getWord(), word)).
				findAny();
	}
}
