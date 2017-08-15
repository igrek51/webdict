package igrek.webdict.model.dto;

public class WordRankDTO {
	
	private Long rankId;
	
	private long dictionaryId;
	
	private String wordName;
	
	private String definition;
	
	private String rankValue;
	
	private String lastUse;
	
	public WordRankDTO() {}
	
	public WordRankDTO(Long rankId, long dictionaryId, String wordName, String definition, String rankValue, String lastUse) {
		this.rankId = rankId;
		this.dictionaryId = dictionaryId;
		this.wordName = wordName;
		this.definition = definition;
		this.rankValue = rankValue;
		this.lastUse = lastUse;
	}
	
	public Long getRankId() {
		return rankId;
	}
	
	public void setRankId(Long rankId) {
		this.rankId = rankId;
	}
	
	public long getDictionaryId() {
		return dictionaryId;
	}
	
	public void setDictionaryId(long dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	
	public String getWordName() {
		return wordName;
	}
	
	public void setWordName(String wordName) {
		this.wordName = wordName;
	}
	
	public String getDefinition() {
		return definition;
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public String getRankValue() {
		return rankValue;
	}
	
	public void setRankValue(String rankValue) {
		this.rankValue = rankValue;
	}
	
	public String getLastUse() {
		return lastUse;
	}
	
	public void setLastUse(String lastUse) {
		this.lastUse = lastUse;
	}
}
