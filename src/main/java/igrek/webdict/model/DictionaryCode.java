package igrek.webdict.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import igrek.webdict.model.entity.Dictionary;

public class DictionaryCode {
	
	private String sourceLanguage;
	private String targetLanguage;
	private boolean reversed;
	
	public DictionaryCode(String sourceLanguage, String targetLanguage, boolean reversed) {
		this.sourceLanguage = sourceLanguage;
		this.targetLanguage = targetLanguage;
		this.reversed = reversed;
	}
	
	public String getSourceLanguage() {
		return sourceLanguage;
	}
	
	public String getTargetLanguage() {
		return targetLanguage;
	}
	
	public boolean isReversed() {
		return reversed;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(sourceLanguage);
		sb.append("-");
		sb.append(targetLanguage);
		if (reversed)
			sb.append("-r");
		return sb.toString();
	}
	
	public static String toDictionaryCode(Dictionary dictionary, boolean reversed) {
		return new DictionaryCode(dictionary.getSourceLanguage()
				.getCode(), dictionary.getTargetLanguage().getCode(), reversed).toString();
	}
	
	public static DictionaryCode parse(String code) {
		Pattern regex = Pattern.compile("^(\\w{2})-(\\w{2})(-r)?$");
		Matcher matcher = regex.matcher(code);
		if (matcher.find()) {
			String sourceLanguage = matcher.group(1);
			String targetLanguage = matcher.group(2);
			boolean reversed = !matcher.group(3).isEmpty();
			return new DictionaryCode(sourceLanguage, targetLanguage, reversed);
		}
		throw new IllegalArgumentException("invalid dictionary code: " + code);
	}
}
