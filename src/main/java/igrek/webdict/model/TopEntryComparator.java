package igrek.webdict.model;

import java.util.Comparator;

public class TopEntryComparator implements Comparator<DictEntry> {
	
	@Override
	public int compare(DictEntry o1, DictEntry o2) {
		// first - entries with highest effective rank
		double diff = o2.getEffectiveRank() - o1.getEffectiveRank();
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
