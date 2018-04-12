package igrek.webdict.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String login;
	
	private String pass;
	
	public User(String login, String pass) {
		this.login = login;
		this.pass = pass;
	}
	
	@Override
	public String toString() {
		return login;
	}
}
