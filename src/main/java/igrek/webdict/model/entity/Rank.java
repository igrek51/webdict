package igrek.webdict.model.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import igrek.webdict.model.HasId;

@Entity
public class Rank implements HasId {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Word word;
	
	private boolean reversed;
	
	private LocalDateTime lastUse;
	
	private double rankValue;
	
	private final transient Duration COOLDOWN_TIME = Duration.ofMinutes(10);
	private final transient double COOLDOWN_MAX_PENALTY = 20;
	
	public Rank() {
	}
	
	public Rank(Word word, boolean reversed, LocalDateTime lastUse, double rankValue) {
		this.word = word;
		this.reversed = reversed;
		this.lastUse = lastUse;
		this.rankValue = rankValue;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public Word getWord() {
		return word;
	}
	
	public boolean isReversed() {
		return reversed;
	}
	
	public LocalDateTime getLastUse() {
		return lastUse;
	}
	
	public void setLastUse(LocalDateTime lastUse) {
		this.lastUse = lastUse;
	}
	
	public double getRankValue() {
		return rankValue;
	}
	
	public void setRankValue(double rankValue) {
		this.rankValue = rankValue;
	}
	
	public double getCooldownPenalty() {
		if (getLastUse() == null)
			return 0;
		
		long elapsedSeconds = LocalDateTime.from(getLastUse())
				.until(LocalDateTime.now(), ChronoUnit.SECONDS);
		long cooldownSeconds = COOLDOWN_TIME.getSeconds();
		
		if (elapsedSeconds >= cooldownSeconds)
			return 0;
		
		return (cooldownSeconds - elapsedSeconds) * COOLDOWN_MAX_PENALTY / cooldownSeconds;
	}
	
	public double getEffectiveRankValue() {
		return getRankValue() - getCooldownPenalty();
	}
}
