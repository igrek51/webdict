package igrek.webdict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import igrek.webdict.domain.entity.User;
import igrek.webdict.repository.UserRepository;
import igrek.webdict.service.repository.RepositoryService;

@Service
public class UserService extends RepositoryService<User> {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}
	
	public Optional<User> findByLogin(String login) {
		return userRepository.findByLogin(login).stream().findAny();
	}
	
}
