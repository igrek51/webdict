package igrek.webdict.model.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
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
	
	@ManyToOne(optional = false)
	private UserWord userWord;
	
	@Column(nullable = false)
	private boolean reversedDictionary;
	
	@Column(nullable = true)
	private LocalDateTime lastUse;
	
	@Column(nullable = false)
	private double rankValue;
	
	@Column(nullable = false)
	private int triesCount;
	
	private final transient Duration COOLDOWN_TIME = Duration.ofMinutes(10);
	private final transient double COOLDOWN_MAX_PENALTY = 20;
	
	public Rank() {
	}
	
	public Rank(UserWord userWord, boolean reversedDictionary, LocalDateTime lastUse, double rankValue, int triesCount) {
		this.userWord = userWord;
		this.reversedDictionary = reversedDictionary;
		this.lastUse = lastUse;
		this.rankValue = rankValue;
		this.triesCount = triesCount;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public UserWord getUserWord() {
		return userWord;
	}
	
	public boolean isReversedDictionary() {
		return reversedDictionary;
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
	
	public int getTriesCount() {
		return triesCount;
	}
	
	public void setTriesCount(int triesCount) {
		this.triesCount = triesCount;
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
