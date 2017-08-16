package igrek.webdict.model.dto;

import java.time.format.DateTimeFormatter;

import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.Word;

public class WordRanksDetailsDTO {
	
	public long rankId;
	
	public String word;
	
	public double rankValue;
	
	public double cooldownPenalty;
	
	public double effectiveRank;
	
	public String lastUse;
	
	public static WordRanksDetailsDTO createDTO(Rank rank) {
		WordRanksDetailsDTO dto = new WordRanksDetailsDTO();
		Word word = rank.getWord();
		dto.rankId = rank.getId();
		dto.word = word.getName();
		dto.rankValue = rank.getRankValue();
		dto.cooldownPenalty = rank.getCooldownPenalty();
		dto.effectiveRank = rank.getEffectiveRankValue();
		if (rank.getLastUse() != null) {
			dto.lastUse = rank.getLastUse()
					.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		}
		return dto;
	}
}
