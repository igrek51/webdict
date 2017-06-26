package igrek.webdict.model.dto;

public class DictEntryDTO {
	
	private Long id;
	
	private long dictionaryId;
	
	private String word;
	
	private String definition;
	
	private double rank;
	
	private String lastUse;
	
	public DictEntryDTO() {}
	
	;
	
	public DictEntryDTO(Long id, long dictionaryId, String word, String definition, double rank, String lastUse) {
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
	
	public String getLastUse() {
		return lastUse;
	}
	
	public void setLastUse(String lastUse) {
		this.lastUse = lastUse;
	}
}
