package igrek.webdict.domain.wordrank;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class HardestWordComparator implements Comparator<Rank> {
	
	private Map<Rank, Double> valueCache = new HashMap<>();
	
	private final static transient Duration COOLDOWN_TIME = Duration.ofMinutes(30);
	private final static transient double COOLDOWN_MAX_PENALTY = 1000;
	
	@Override
	public int compare(Rank o1, Rank o2) {
		// prior - entries with highest tries count minus cooldown
		Double f1 = getCachedValue(o1);
		Double f2 = getCachedValue(o2);
		if (f1.compareTo(f2) != 0)
			return -f1.compareTo(f2) * 20;
		
		// higher rank
		if (o1.getRankValue() != o2.getRankValue())
			return -Double.compare(o1.getRankValue(), o2.getRankValue());
		
		// prior - entries which were never used (no last use)
		if (o1.getLastUse() == null && o2.getLastUse() == null)
			return 0;
		if (o1.getLastUse() == null)
			return -10;
		if (o2.getLastUse() == null)
			return 10;
		
		return 0;
	}
	
	private double getCachedValue(Rank rank) {
		//get from cache
		Double cached = valueCache.get(rank);
		if (cached != null)
			return cached;
		// store value in cache due to time dependent value
		double value = getRankValue(rank);
		valueCache.put(rank, value);
		return value;
	}
	
	public static double getRankValue(Rank rank) {
		return rank.getTriesCount() - getSingleCooldownPenalty(rank);
	}
	
	public static double getBothCooldownPenalty(Rank rank) {
		if (!rank.getReversedRank().isPresent())
			return getSingleCooldownPenalty(rank);
		return Math.max(getSingleCooldownPenalty(rank), getSingleCooldownPenalty(rank.getReversedRank()
				.get()));
	}
	
	public static double getSingleCooldownPenalty(Rank rank) {
		if (rank.getLastUse() == null)
			return 0;
		
		long elapsedSeconds = LocalDateTime.from(rank.getLastUse())
				.until(LocalDateTime.now(), ChronoUnit.SECONDS);
		long cooldownSeconds = COOLDOWN_TIME.getSeconds();
		
		if (elapsedSeconds >= cooldownSeconds)
			return 0;
		
		return (cooldownSeconds - elapsedSeconds) * COOLDOWN_MAX_PENALTY / cooldownSeconds;
	}
	
}
