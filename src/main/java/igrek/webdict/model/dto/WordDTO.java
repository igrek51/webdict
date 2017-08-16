package igrek.webdict.model.dto;

import igrek.webdict.model.entity.Word;

public class WordDTO {
	
	private Long wordId;
	
	private String sourceLanguage;
	
	private String targetLanguage;
	
	private String name;
	
	private String definition;
	
	private String userLogin;
	
	public WordDTO() {}
	
	public WordDTO(Long wordId, String sourceLanguage, String targetLanguage, String name, String definition, String userLogin) {
		this.wordId = wordId;
		this.sourceLanguage = sourceLanguage;
		this.targetLanguage = targetLanguage;
		this.name = name;
		this.definition = definition;
		this.userLogin = userLogin;
	}
	
	public static WordDTO createDTO(Word word) {
		String sourceLanguage = word.getDictionary().getSourceLanguage().getCode();
		String targetLanguage = word.getDictionary().getTargetLanguage().getCode();
		String user = word.getUser() == null ? null : word.getUser().getLogin();
		return new WordDTO(word.getId(), sourceLanguage, targetLanguage, word.getName(), word.getDefinition(), user);
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
	
	public String getUserLogin() {
		return userLogin;
	}
}
