package igrek.webdict.ui.alert;

public enum BootstrapAlertType {
	
	SUCCESS("success"),
	
	INFO("info"),
	
	WARNING("warning"),
	
	ERROR("danger");
	
	private String bootstrapStyle;
	
	BootstrapAlertType(String bootstrapStyle) {
		this.bootstrapStyle = bootstrapStyle;
	}
	
	public String getBootstrapStyle() {
		return bootstrapStyle;
	}
}
