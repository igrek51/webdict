package igrek.webdict.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDTO {
	
	private Long userId;
	private String dictionaryCode;
	
}
