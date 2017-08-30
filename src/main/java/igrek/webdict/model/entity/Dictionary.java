package igrek.webdict.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import igrek.webdict.model.HasId;

@Entity
public class Dictionary implements HasId {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Language sourceLanguage;
	
	@ManyToOne
	private Language targetLanguage;
	
	public Dictionary() {
	}
	
	public Dictionary(Language sourceLanguage, Language targetLanguage) {
		this.sourceLanguage = sourceLanguage;
		this.targetLanguage = targetLanguage;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public Language getSourceLanguage() {
		return sourceLanguage;
	}
	
	public Language getTargetLanguage() {
		return targetLanguage;
	}
	
}
