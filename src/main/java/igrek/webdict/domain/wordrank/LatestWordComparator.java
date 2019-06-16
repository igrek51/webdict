package igrek.webdict.domain.wordrank;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LatestWordComparator implements Comparator<Rank> {
	
	private Map<Rank, Double> valueCache = new HashMap<>();
	
	private final static transient Duration COOLDOWN_TIME = Duration.ofMinutes(30);
	private final static transient double COOLDOWN_MAX_PENALTY = 1000;
	
	@Override
	public int compare(Rank o1, Rank o2) {
		Double v1 = getCachedValue(o1);
		Double v2 = getCachedValue(o2);
		return -v1.compareTo(v2);
	}
	
	private double getCachedValue(Rank rank) {
		//get from cache
		Double cached = valueCache.get(rank);
		if (cached != null)
			return cached;
		// store value in cache due to time dependent value
		double value = getValue(rank);
		valueCache.put(rank, value);
		return value;
	}
	
	public static double getValue(Rank rank) {
		LocalDateTime lastUse = rank.getLastUse();
		if (lastUse == null) {
			lastUse = LocalDateTime.now();
		}
		long epochSeconds = lastUse.toEpochSecond(ZoneOffset.UTC);
		return epochSeconds - getBothCooldownPenalty(rank);
	}
	
	private static double getBothCooldownPenalty(Rank rank) {
		if (!rank.getReversedRank().isPresent())
			return getSingleCooldownPenalty(rank);
		return Math.max(getSingleCooldownPenalty(rank), getSingleCooldownPenalty(rank.getReversedRank()
				.get()));
	}
	
	private static double getSingleCooldownPenalty(Rank rank) {
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
