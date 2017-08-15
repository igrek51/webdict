package igrek.webdict.model.dto.parser;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import igrek.webdict.model.dto.WordRankDTO;
import igrek.webdict.model.entity.Rank;

public class WordRankDTOParser {
	
	private static final DateTimeFormatter LAST_USE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public static WordRankDTO parse(Rank rank) {
		String lastUse = rank.getLastUse() == null ? null : rank.getLastUse()
				.format(LAST_USE_FORMATTER);
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String rankValue = df.format(rank.getRankValue());
		return new WordRankDTO(rank.getId(), rank.getWord().getDictionary().getId(), rank.getWord()
				.getName(), rank.getWord().getDefinition(), rankValue, lastUse);
	}
}
