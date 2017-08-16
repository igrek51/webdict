package igrek.webdict.model.dto;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import igrek.webdict.model.entity.Rank;

public class WordRankDTO {
	
	private Long rankId;
	private long dictionaryId;
	private String wordName;
	private String definition;
	private String rankValue;
	private String lastUse;
	
	private static final DateTimeFormatter LAST_USE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public WordRankDTO() {}
	
	public WordRankDTO(Long rankId, long dictionaryId, String wordName, String definition, String rankValue, String lastUse) {
		this.rankId = rankId;
		this.dictionaryId = dictionaryId;
		this.wordName = wordName;
		this.definition = definition;
		this.rankValue = rankValue;
		this.lastUse = lastUse;
	}
	
	public static WordRankDTO createDTO(Rank rank) {
		String lastUse = rank.getLastUse() == null ? null : rank.getLastUse()
				.format(LAST_USE_FORMATTER);
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String rankValue = df.format(rank.getRankValue());
		return new WordRankDTO(rank.getId(), rank.getWord().getDictionary().getId(), rank.getWord()
				.getName(), rank.getWord().getDefinition(), rankValue, lastUse);
	}
	
	public Long getRankId() {
		return rankId;
	}
	
	public long getDictionaryId() {
		return dictionaryId;
	}
	
	public String getWordName() {
		return wordName;
	}
	
	public String getDefinition() {
		return definition;
	}
	
	public String getRankValue() {
		return rankValue;
	}
	
	public String getLastUse() {
		return lastUse;
	}
}
