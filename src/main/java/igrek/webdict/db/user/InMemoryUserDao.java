package igrek.webdict.db.user;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;

import igrek.webdict.db.common.BaseInMemoryDao;
import igrek.webdict.model.entity.User;

public class InMemoryUserDao extends BaseInMemoryDao<User> implements UserDao {
	
	public InMemoryUserDao() {
		addSampleEntity("janusz", "dupa");
	}
	
	private void addSampleEntity(String login, String pass) {
		String decoded = decodePassword(pass);
		super.addSampleEntity(new User(login, decoded));
	}
	
	@Override
	public Optional<User> findByLogin(String login) {
		return entities.stream().
				filter(user -> Objects.equals(user.getLogin(), login)).
				findAny();
	}
	
	private String decodePassword(String plain) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(plain.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			StringBuilder hashtext = new StringBuilder(bigInt.toString(16));
			// Now we need to zero pad it if you actually want the full 32 chars.
			while (hashtext.length() < 32) {
				hashtext.insert(0, "0");
			}
			return hashtext.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
