package igrek.webdict.domain.word;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddWordDTO {
	
	private String word;
	private String definition;
	private long userId;
	private String dictionaryCode;
	
}
