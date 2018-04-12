package igrek.webdict.domain.word;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordDTO {
	
	private Long wordId;
	private String sourceLanguage;
	private String targetLanguage;
	private String name;
	private String definition;
	
	public static WordDTO createDTO(Word word) {
		String sourceLanguage = word.getDictionary().getSourceLanguage().getCode();
		String targetLanguage = word.getDictionary().getTargetLanguage().getCode();
		return new WordDTO(word.getId(), sourceLanguage, targetLanguage, word.getName(), word.getDefinition());
	}
	
}
