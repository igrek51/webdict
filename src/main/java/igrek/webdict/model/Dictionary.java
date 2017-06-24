package igrek.webdict.model;

public class Dictionary {
	
	private long id;
	
	private String sourceLanguage;
	
	private String targetLanguage;
	
	public Dictionary(long id, String sourceLanguage, String targetLanguage) {
		this.id = id;
		this.sourceLanguage = sourceLanguage;
		this.targetLanguage = targetLanguage;
	}
	
	public long getId() {
		return id;
	}
	
	public String getSourceLanguage() {
		return sourceLanguage;
	}
	
	public String getTargetLanguage() {
		return targetLanguage;
	}
}
