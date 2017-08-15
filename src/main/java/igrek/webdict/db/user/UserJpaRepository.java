package igrek.webdict.db.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import igrek.webdict.model.entity.User;

@Transactional
public interface UserJpaRepository extends JpaRepository<User, Long> {
	
	List<User> findByLogin(String login);
	
}