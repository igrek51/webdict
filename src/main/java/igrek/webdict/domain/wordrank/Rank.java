package igrek.webdict.domain.wordrank;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import igrek.webdict.domain.word.UserWord;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Rank {
	
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
	
	private transient Optional<Rank> reversedRank = Optional.empty();
	
	public Rank(UserWord userWord, boolean reversedDictionary, LocalDateTime lastUse, double rankValue, int triesCount) {
		this.userWord = userWord;
		this.reversedDictionary = reversedDictionary;
		this.lastUse = lastUse;
		this.rankValue = rankValue;
		this.triesCount = triesCount;
	}
}
