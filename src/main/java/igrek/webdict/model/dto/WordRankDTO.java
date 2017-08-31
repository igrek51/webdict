package igrek.webdict.model.dto;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.Word;

public class WordRankDTO {
	
	private Long rankId;
	private long dictionaryId;
	private boolean reversedDictionary;
	private String wordName;
	private String definition;
	private String rankValue;
	private int triesCount;
	private String lastUse;
	
	private static final DateTimeFormatter LAST_USE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public WordRankDTO() {}
	
	public WordRankDTO(Long rankId, long dictionaryId, boolean reversedDictionary, String wordName, String definition, String rankValue, int triesCount, String lastUse) {
		this.rankId = rankId;
		this.dictionaryId = dictionaryId;
		this.reversedDictionary = reversedDictionary;
		this.wordName = wordName;
		this.definition = definition;
		this.rankValue = rankValue;
		this.triesCount = triesCount;
		this.lastUse = lastUse;
	}
	
	public static WordRankDTO createDTO(Rank rank) {
		String lastUse = rank.getLastUse() == null ? null : rank.getLastUse()
				.format(LAST_USE_FORMATTER);
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String rankValue = df.format(rank.getRankValue());
		Word word = rank.getUserWord().getWord();
		return new WordRankDTO(rank.getId(), word.getDictionary()
				.getId(), rank.isReversedDictionary(), word.getName(), word.getDefinition(), rankValue, rank
				.getTriesCount(), lastUse);
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
	
	public boolean isReversedDictionary() {
		return reversedDictionary;
	}
	
	public int getTriesCount() {
		return triesCount;
	}
}
