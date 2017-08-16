package igrek.webdict.model.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.User;

@Component
@SessionScope
public class SessionSettings {
	
	private User user;
	
	private Dictionary dictionary;
	
	private boolean reversedDictionary = false;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Dictionary getDictionary() {
		return dictionary;
	}
	
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	public boolean isReversedDictionary() {
		return reversedDictionary;
	}
	
	public void setReversedDictionary(boolean reversedDictionary) {
		this.reversedDictionary = reversedDictionary;
	}
	
	public boolean isLoggedIn() {
		return user != null;
	}
}
