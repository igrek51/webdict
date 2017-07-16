package igrek.webdict.model.dto;

public class AddDictEntryDTO {
	
	private String word;
	
	private String definition;
	
	public AddDictEntryDTO() {}
	
	public AddDictEntryDTO(String word, String definition) {
		this.word = word;
		this.definition = definition;
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
	
}
