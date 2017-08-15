package igrek.webdict.db.user;

import java.util.Optional;

import igrek.webdict.db.common.BaseDao;
import igrek.webdict.model.entity.User;

public interface UserDao extends BaseDao<User> {
	
	Optional<User> findByLogin(String login);
	
}
