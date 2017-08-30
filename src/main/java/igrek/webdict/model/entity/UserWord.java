package igrek.webdict.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import igrek.webdict.model.HasId;

@Entity
public class UserWord implements HasId {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	private User user;
	
	@ManyToOne(optional = false)
	private Word word;
	
	public UserWord() {
	}
	
	public UserWord(User user, Word word) {
		this.user = user;
		this.word = word;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public Word getWord() {
		return word;
	}
}
