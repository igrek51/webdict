package igrek.webdict.model.dto;

import igrek.webdict.model.entity.Dictionary;

public class DictionaryDTO {
	
	private Long dictionaryId;
	
	private String sourceLanguage;
	
	private String targetLanguage;
	
	public DictionaryDTO() {}
	
	public DictionaryDTO(Long dictionaryId, String sourceLanguage, String targetLanguage) {
		this.dictionaryId = dictionaryId;
		this.sourceLanguage = sourceLanguage;
		this.targetLanguage = targetLanguage;
	}
	
	public static DictionaryDTO createDTO(Dictionary dictionary) {
		String sourceLanguage = dictionary.getSourceLanguage().getCode();
		String targetLanguage = dictionary.getTargetLanguage().getCode();
		return new DictionaryDTO(dictionary.getId(), sourceLanguage, targetLanguage);
	}
	
	public Long getDictionaryId() {
		return dictionaryId;
	}
	
	public String getSourceLanguage() {
		return sourceLanguage;
	}
	
	public String getTargetLanguage() {
		return targetLanguage;
	}
}
