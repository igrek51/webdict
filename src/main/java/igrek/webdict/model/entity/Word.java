package igrek.webdict.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import igrek.webdict.model.HasId;

@Entity
public class Word implements HasId {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Dictionary dictionary;
	
	@ManyToOne
	private User user;
	
	private String name;
	
	private String definition;
	
	public Word() {
	}
	
	public Word(Dictionary dictionary, User user, String name, String definition) {
		this.dictionary = dictionary;
		this.user = user;
		this.name = name;
		this.definition = definition;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public Dictionary getDictionary() {
		return dictionary;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDefinition() {
		return definition;
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
}
