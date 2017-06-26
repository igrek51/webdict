package igrek.webdict.model;

public class Dictionary {
	
	private Long id;
	
	private String sourceLanguage;
	
	private String targetLanguage;
	
	public Dictionary(Long id, String sourceLanguage, String targetLanguage) {
		this.id = id;
		this.sourceLanguage = sourceLanguage;
		this.targetLanguage = targetLanguage;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getSourceLanguage() {
		return sourceLanguage;
	}
	
	public String getTargetLanguage() {
		return targetLanguage;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setSourceLanguage(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}
	
	public void setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
	}
}
