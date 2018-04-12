package igrek.webdict.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.domain.entity.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByLogin(String login);
	
}