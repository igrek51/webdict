package igrek.webdict.domain.word;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import igrek.webdict.domain.dictionary.Dictionary;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Word {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	private Dictionary dictionary;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String definition;
	
	public Word(Dictionary dictionary, String name, String definition) {
		this.dictionary = dictionary;
		this.name = name;
		this.definition = definition;
	}
}
