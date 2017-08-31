package igrek.webdict.model.dto;

import igrek.webdict.model.entity.Word;

public class WordDTO {
	
	private Long wordId;
	
	private String sourceLanguage;
	
	private String targetLanguage;
	
	private String name;
	
	private String definition;
	
	public WordDTO() {}
	
	public WordDTO(Long wordId, String sourceLanguage, String targetLanguage, String name, String definition) {
		this.wordId = wordId;
		this.sourceLanguage = sourceLanguage;
		this.targetLanguage = targetLanguage;
		this.name = name;
		this.definition = definition;
	}
	
	public static WordDTO createDTO(Word word) {
		String sourceLanguage = word.getDictionary().getSourceLanguage().getCode();
		String targetLanguage = word.getDictionary().getTargetLanguage().getCode();
		return new WordDTO(word.getId(), sourceLanguage, targetLanguage, word.getName(), word.getDefinition());
	}
	
	public Long getWordId() {
		return wordId;
	}
	
	public String getSourceLanguage() {
		return sourceLanguage;
	}
	
	public String getTargetLanguage() {
		return targetLanguage;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDefinition() {
		return definition;
	}
	
}
