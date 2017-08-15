package igrek.webdict.db.user;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import igrek.webdict.db.common.BaseJpaDao;
import igrek.webdict.model.entity.User;

public class JpaUserDao extends BaseJpaDao<User> implements UserDao {
	
	private final UserJpaRepository jpaRepository;
	
	@Autowired
	public JpaUserDao(UserJpaRepository jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}
	
	@Override
	public Optional<User> findByLogin(String login) {
		return jpaRepository.findByLogin(login).stream().findAny();
	}
	
}
