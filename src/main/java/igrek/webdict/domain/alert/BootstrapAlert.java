package igrek.webdict.domain.alert;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BootstrapAlert {
	
	private String message;
	private BootstrapAlertType type;
	
	public String getBootstrapStyle() {
		return type.getBootstrapStyle();
	}
}
