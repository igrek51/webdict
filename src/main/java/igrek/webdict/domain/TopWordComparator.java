package igrek.webdict.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import igrek.webdict.domain.entity.Rank;

public class TopWordComparator implements Comparator<Rank> {
	
	private Map<Rank, Double> effectiveRankValueCache = new HashMap<>();
	
	private final static transient Duration COOLDOWN_TIME = Duration.ofMinutes(10);
	private final static transient double COOLDOWN_MAX_PENALTY = 20;
	
	@Override
	public int compare(Rank o1, Rank o2) {
		// prior - entries with highest effective rank
		Double f1 = getCachedEffectiveRankValue(o1);
		Double f2 = getCachedEffectiveRankValue(o2);
		if (f1.compareTo(f2) != 0)
			return -f1.compareTo(f2) * 20;
		
		// prior - entries which were never used (no last use)
		if (o1.getLastUse() == null && o2.getLastUse() == null)
			return 0;
		if (o1.getLastUse() == null)
			return -10;
		if (o2.getLastUse() == null)
			return 10;
		
		// prior - entries which were used more times (more difficult)
		if (o1.getTriesCount() != o2.getTriesCount())
			return o2.getTriesCount() - o1.getTriesCount();

		return 0;
	}
	
	private double getCachedEffectiveRankValue(Rank rank) {
		//get from cache
		Double cached = effectiveRankValueCache.get(rank);
		if (cached != null)
			return cached;
		// store value in cache due to time dependent value
		double value = getEffectiveRankValue(rank);
		effectiveRankValueCache.put(rank, value);
		return value;
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
	
	public static double getBothCooldownPenalty(Rank rank) {
		if (!rank.getReversedRank().isPresent())
			return getSingleCooldownPenalty(rank);
		return Math.max(getSingleCooldownPenalty(rank), getSingleCooldownPenalty(rank.getReversedRank()
				.get()));
	}
	
	public static double getEffectiveRankValue(Rank rank) {
		return rank.getRankValue() - getBothCooldownPenalty(rank);
	}
	
}
