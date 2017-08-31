package igrek.webdict.db.userword;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import igrek.webdict.db.common.BaseJpaDao;
import igrek.webdict.model.entity.UserWord;

public class UserWordJpaDao extends BaseJpaDao<UserWord> implements UserWordDao {
	
	private final UserWordJpaRepository jpaRepository;
	
	@Autowired
	public UserWordJpaDao(UserWordJpaRepository jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}
	
	@Override
	public List<UserWord> findByUserId(Long userId) {
		return jpaRepository.findByUserId(userId);
	}
	
	@Override
	public Optional<UserWord> findByUserIdAndWordId(Long userId, Long wordId) {
		return Optional.ofNullable(jpaRepository.findByUserIdAndWordId(userId, wordId));
	}
	
	@Override
	public List<UserWord> findByUserIdAndDictionaryId(Long userId, Long dictionaryId) {
		return jpaRepository.findByUserIdAndWordDictionaryId(userId, dictionaryId);
	}
	
	@Override
	public List<UserWord> findByDictionaryAndUser(Long dictionaryId, Long userId) {
		return jpaRepository.findByDictionaryIdAndUserId(dictionaryId, userId);
	}
	
	@Override
	public Optional<UserWord> findByName(String wordName, Long dictionaryId, Long userId) {
		return Optional.ofNullable(jpaRepository.findByNameAndDictionaryIdAndUserId(wordName, dictionaryId, userId));
	}
	
}
