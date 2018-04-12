package igrek.webdict.domain.dto;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import igrek.webdict.domain.entity.Rank;
import igrek.webdict.domain.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
	public static WordRankDTO createDTO(Rank rank) {
		String lastUse = rank.getLastUse() == null ? null : rank.getLastUse().format(LAST_USE_FORMATTER);
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String rankValue = df.format(rank.getRankValue());
		Word word = rank.getUserWord().getWord();
		return new WordRankDTO(rank.getId(), word.getDictionary().getId(), rank.isReversedDictionary(), word.getName(), word.getDefinition(), rankValue, rank
				.getTriesCount(), lastUse);
	}
	
}
