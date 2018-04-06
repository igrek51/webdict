package igrek.webdict.logic.statistics;

import igrek.webdict.logic.TopWordComparator;
import igrek.webdict.model.entity.Rank;

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
		return TopWordComparator.getCooldownPenalty(rank) > 0.0;
	}
	
}
