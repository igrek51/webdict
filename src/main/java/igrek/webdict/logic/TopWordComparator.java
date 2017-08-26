package igrek.webdict.logic;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import igrek.webdict.model.entity.Rank;

public class TopWordComparator implements Comparator<Rank> {
	
	private Map<Rank, Double> effectiveRankValueCache = new HashMap<>();
	
	@Override
	public int compare(Rank o1, Rank o2) {
		// prior - entries with highest effective rank
		Double f1 = getCachedEffectiveRankValue(o1);
		Double f2 = getCachedEffectiveRankValue(o2);
		if (f1.compareTo(f2) != 0)
			return -f1.compareTo(f2) * 20;
		
		// prior - entries which were used less
		if (o1.getTriesCount() != o2.getTriesCount())
			return o1.getTriesCount() - o2.getTriesCount();
		
		// prior - entries which were never used (no last use)
		if (o1.getLastUse() == null && o2.getLastUse() == null)
			return 0;
		if (o1.getLastUse() == null)
			return -10;
		if (o2.getLastUse() == null)
			return 10;

		return 0;
	}
	
	private double getCachedEffectiveRankValue(Rank rank) {
		//get from cache
		Double cached = effectiveRankValueCache.get(rank);
		if (cached != null)
			return cached;
		// store value in cache due to time dependent value
		double value = rank.getEffectiveRankValue();
		effectiveRankValueCache.put(rank, value);
		return value;
	}
	
}
