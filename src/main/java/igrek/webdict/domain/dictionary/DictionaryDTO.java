package igrek.webdict.domain.dictionary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryDTO {
	
	private Long dictionaryId;
	private String sourceLanguage;
	private String targetLanguage;
	
	public static DictionaryDTO createDTO(Dictionary dictionary) {
		String sourceLanguage = dictionary.getSourceLanguage().getCode();
		String targetLanguage = dictionary.getTargetLanguage().getCode();
		return new DictionaryDTO(dictionary.getId(), sourceLanguage, targetLanguage);
	}
	
}
