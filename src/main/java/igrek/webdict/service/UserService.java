package igrek.webdict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import igrek.webdict.domain.user.User;
import igrek.webdict.repository.UserRepository;
import igrek.webdict.service.repository.AbstractRepositoryService;

@Service
public class UserService extends AbstractRepositoryService<User> {
	
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
