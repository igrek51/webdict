package igrek.webdict.logic;

import java.util.Comparator;

import igrek.webdict.model.entity.Rank;

public class TopWordComparator implements Comparator<Rank> {
	
	@Override
	public int compare(Rank o1, Rank o2) {
		// first - entries with highest effective rank
		double diff = o2.getEffectiveRankValue() - o1.getEffectiveRankValue();
		if (diff != 0)
			return diff < 0 ? -1 : 1;
		
		// first - entries which were never used (no last use)
		if (o1.getLastUse() == null)
			return -1;
		if (o2.getLastUse() == null)
			return 1;
		
		return 0;
	}
	
}
