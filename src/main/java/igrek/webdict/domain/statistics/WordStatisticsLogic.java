package igrek.webdict.domain.statistics;

import igrek.webdict.domain.TopWordComparator;
import igrek.webdict.domain.entity.Rank;

public class WordStatisticsLogic {
	
	public static boolean isWordTrained(Rank rank) {
		return rank.getRankValue() < 0 && rank.getTriesCount() > 0;
	}
	
	public static boolean isWordInProgess(Rank rank) {
		return rank.getRankValue() >= 0 && rank.getTriesCount() > 0;
	}
	
	public static boolean isWordTouched(Rank rank) {
		return rank.getTriesCount() > 0;
	}
	
	public static boolean isWordCoolingDown(Rank rank) {
		return TopWordComparator.getSingleCooldownPenalty(rank) > 0.0;
	}
	
}
