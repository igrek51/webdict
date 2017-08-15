package igrek.webdict.db.word;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import igrek.webdict.db.common.BaseJpaDao;
import igrek.webdict.model.entity.Word;

public class JpaWordDao extends BaseJpaDao<Word> implements WordDao {
	
	private final WordJpaRepository jpaRepository;
	
	@Autowired
	public JpaWordDao(WordJpaRepository jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}
	
	@Override
	public List<Word> findByDictionaryAndUser(Long dictionaryId, Long userId) {
		if (userId == null) {
			return jpaRepository.findByDictionaryId(dictionaryId);
		} else {
			return jpaRepository.findByDictionaryIdAndUserId(dictionaryId, userId);
		}
	}
	
	@Override
	public Optional<Word> findByName(String name) {
		return jpaRepository.findByName(name).stream().findAny();
	}
	
}
