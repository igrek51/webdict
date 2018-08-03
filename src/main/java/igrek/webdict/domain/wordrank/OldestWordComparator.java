package igrek.webdict.domain.wordrank;

import java.util.Comparator;

public class OldestWordComparator implements Comparator<Rank> {
	
	@Override
	public int compare(Rank o1, Rank o2) {
		// prior - entries which were never used (no last use)
		if (o1.getLastUse() == null && o2.getLastUse() == null)
			return 0;
		if (o1.getLastUse() == null)
			return -1;
		if (o2.getLastUse() == null)
			return 1;
		// older words first
		return o1.getLastUse().compareTo(o2.getLastUse());
	}
	
}
