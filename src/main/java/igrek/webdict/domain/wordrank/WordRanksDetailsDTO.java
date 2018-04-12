package igrek.webdict.domain.wordrank;

import java.time.format.DateTimeFormatter;

import igrek.webdict.domain.word.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordRanksDetailsDTO {
	
	private long rankId;
	private String word;
	private double rankValue;
	private double cooldownPenalty;
	private double effectiveRank;
	private int triesCount;
	private String lastUse;
	
	public static WordRanksDetailsDTO createDTO(Rank rank) {
		WordRanksDetailsDTO dto = new WordRanksDetailsDTO();
		Word word = rank.getUserWord().getWord();
		dto.rankId = rank.getId();
		dto.word = word.getName();
		dto.rankValue = rank.getRankValue();
		dto.cooldownPenalty = TopWordComparator.getSingleCooldownPenalty(rank);
		dto.effectiveRank = TopWordComparator.getEffectiveRankValue(rank);
		dto.triesCount = rank.getTriesCount();
		if (rank.getLastUse() != null) {
			dto.lastUse = rank.getLastUse()
					.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		}
		return dto;
	}
}
