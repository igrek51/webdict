package igrek.webdict.model;

public class DictEntry {
	
	private long id;
	
	private long dictionaryId;
	
	private String word;
	
	private String definition;
	
	public DictEntry(long id, long dictionaryId, String word, String definition) {
		this.id = id;
		this.dictionaryId = dictionaryId;
		this.word = word;
		this.definition = definition;
	}
	
	public long getId() {
		return id;
	}
	
	public long getDictionaryId() {
		return dictionaryId;
	}
	
	public String getWord() {
		return word;
	}
	
	public String getDefinition() {
		return definition;
	}
}
