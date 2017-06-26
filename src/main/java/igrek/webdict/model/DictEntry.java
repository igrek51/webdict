package igrek.webdict.model;

import java.time.LocalDateTime;

public class DictEntry {
	
	private Long id;
	
	private long dictionaryId;
	
	private String word;
	
	private String definition;
	
	private double rank;
	
	private LocalDateTime lastUse;
	
	public DictEntry(Long id, long dictionaryId, String word, String definition, double rank, LocalDateTime lastUse) {
		this.id = id;
		this.dictionaryId = dictionaryId;
		this.word = word;
		this.definition = definition;
		this.rank = rank;
		this.lastUse = lastUse;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public long getDictionaryId() {
		return dictionaryId;
	}
	
	public void setDictionaryId(long dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getDefinition() {
		return definition;
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public double getRank() {
		return rank;
	}
	
	public void setRank(double rank) {
		this.rank = rank;
	}
	
	public LocalDateTime getLastUse() {
		return lastUse;
	}
	
	public void setLastUse(LocalDateTime lastUse) {
		this.lastUse = lastUse;
	}
}
