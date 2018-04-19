package igrek.webdict.domain.env;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnvironmentInfo {
	
	private String environmentName;
	private String buildVersion;
	
}
