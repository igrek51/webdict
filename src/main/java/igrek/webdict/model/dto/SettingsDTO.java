package igrek.webdict.model.dto;

public class SettingsDTO {
	
	private String userLogin;
	private String dictionaryCode;
	
	public SettingsDTO() {}
	
	public SettingsDTO(String userLogin, String dictionaryCode) {
		this.userLogin = userLogin;
		this.dictionaryCode = dictionaryCode;
	}
	
	public String getUserLogin() {
		return userLogin;
	}
	
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
	public String getDictionaryCode() {
		return dictionaryCode;
	}
	
	public void setDictionaryCode(String dictionaryCode) {
		this.dictionaryCode = dictionaryCode;
	}
}
