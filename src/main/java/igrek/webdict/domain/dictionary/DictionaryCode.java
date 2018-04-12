package igrek.webdict.domain.dictionary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryCode {
	
	private String sourceLanguage;
	private String targetLanguage;
	private boolean reversedDictionary;
	
	public DictionaryCode(String sourceLanguage, String targetLanguage, boolean reversedDictionary) {
		this.sourceLanguage = sourceLanguage;
		this.targetLanguage = targetLanguage;
		this.reversedDictionary = reversedDictionary;
	}
	
	public static String toDictionaryCode(Dictionary dictionary, boolean reversedDictionary) {
		return new DictionaryCode(dictionary.getSourceLanguage()
				.getCode(), dictionary.getTargetLanguage()
				.getCode(), reversedDictionary).toString();
	}
	
	public static String toDictionaryDisplayName(Dictionary dictionary, boolean reversedDictionary) {
		StringBuilder sb = new StringBuilder();
		sb.append(dictionary.getSourceLanguage().getCode());
		if (!reversedDictionary) {
			sb.append(" -> ");
		} else {
			sb.append(" <- ");
		}
		sb.append(dictionary.getTargetLanguage().getCode());
		return sb.toString();
	}
	
	public static DictionaryCode parse(String code) {
		Pattern regex = Pattern.compile("^(\\w{2})-(\\w{2})(-r)?$");
		Matcher matcher = regex.matcher(code);
		if (matcher.find()) {
			String sourceLanguage = matcher.group(1);
			String targetLanguage = matcher.group(2);
			boolean reversed = matcher.group(3) != null && !matcher.group(3).isEmpty();
			return new DictionaryCode(sourceLanguage, targetLanguage, reversed);
		}
		throw new IllegalArgumentException("invalid dictionary code: " + code);
	}
	
	public String getSourceLanguage() {
		return sourceLanguage;
	}
	
	public String getTargetLanguage() {
		return targetLanguage;
	}
	
	public boolean isReversedDictionary() {
		return reversedDictionary;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(sourceLanguage);
		sb.append("-");
		sb.append(targetLanguage);
		if (reversedDictionary)
			sb.append("-r");
		return sb.toString();
	}
}
