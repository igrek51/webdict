package igrek.webdict.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DictEntry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private long dictionaryId;
	
	private String word;
	
	private String definition;
	
	private double rank;
	
	private LocalDateTime lastUse;
	
	private final transient Duration COOLDOWN_TIME = Duration.ofMinutes(10);
	private final transient double COOLDOWN_MAX_PENALTY = 20;
	
	public DictEntry(Long id, long dictionaryId, String word, String definition, double rank, LocalDateTime lastUse) {
		this.id = id;
		this.dictionaryId = dictionaryId;
		this.word = word;
		this.definition = definition;
		this.rank = rank;
		this.lastUse = lastUse;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public long getDictionaryId() {
		return dictionaryId;
	}
	
	public void setDictionaryId(long dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getDefinition() {
		return definition;
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public double getRank() {
		return rank;
	}
	
	public void setRank(double rank) {
		this.rank = rank;
	}
	
	public LocalDateTime getLastUse() {
		return lastUse;
	}
	
	public void setLastUse(LocalDateTime lastUse) {
		this.lastUse = lastUse;
	}
	
	public double getCooldownPenalty() {
		if (getLastUse() == null)
			return 0;
		
		long elapsedSeconds = LocalDateTime.from(getLastUse()).until(LocalDateTime.now(), ChronoUnit.SECONDS);
		long cooldownSeconds = COOLDOWN_TIME.getSeconds();
		
		if (elapsedSeconds >= cooldownSeconds)
			return 0;
		
		return (cooldownSeconds - elapsedSeconds) * COOLDOWN_MAX_PENALTY / cooldownSeconds;
	}
	
	public double getEffectiveRank() {
		return getRank() + getCooldownPenalty();
	}
}
