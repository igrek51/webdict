package igrek.webdict.model.dto;

public class SettingsDTO {
	
	private Long userId;
	private String dictionaryCode;
	
	public SettingsDTO() {}
	
	public SettingsDTO(Long userId, String dictionaryCode) {
		this.userId = userId;
		this.dictionaryCode = dictionaryCode;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getDictionaryCode() {
		return dictionaryCode;
	}
	
	public void setDictionaryCode(String dictionaryCode) {
		this.dictionaryCode = dictionaryCode;
	}
}
