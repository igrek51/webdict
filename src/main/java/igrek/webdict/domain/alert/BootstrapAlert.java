package igrek.webdict.domain.alert;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BootstrapAlert {
	
	private String message;
	private BootstrapAlertType type;
	
	public String getBootstrapStyle() {
		return type.getBootstrapStyle();
	}
}
