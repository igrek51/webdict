package igrek.webdict.model.dto;

import java.time.format.DateTimeFormatter;

import igrek.webdict.model.DictEntry;

public class DictEntryRanksDTO {
	
	public long id;
	
	public String word;
	
	public double rank;
	
	public double cooldownPenalty;
	
	public double effectiveRank;
	
	public String lastUse;
	
	public static DictEntryRanksDTO createRanks(DictEntry dictEntry) {
		DictEntryRanksDTO dto = new DictEntryRanksDTO();
		dto.id = dictEntry.getId();
		dto.word = dictEntry.getWord();
		dto.rank = dictEntry.getRank();
		dto.cooldownPenalty = dictEntry.getCooldownPenalty();
		dto.effectiveRank = dictEntry.getEffectiveRank();
		dto.lastUse = dictEntry.getLastUse()
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return dto;
	}
}