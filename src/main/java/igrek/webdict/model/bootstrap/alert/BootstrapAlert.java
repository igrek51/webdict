package igrek.webdict.model.bootstrap.alert;

public class BootstrapAlert {
	
	private String message;
	
	private BootstrapAlertType type;
	
	public BootstrapAlert(String message, BootstrapAlertType type) {
		this.message = message;
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public BootstrapAlertType getType() {
		return type;
	}
	
	public String getBootstrapStyle() {
		return type.getBootstrapStyle();
	}
}
