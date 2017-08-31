package igrek.webdict.db.word;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import igrek.webdict.db.common.BaseJpaDao;
import igrek.webdict.model.entity.Word;

public class WordJpaDao extends BaseJpaDao<Word> implements WordDao {
	
	private final WordJpaRepository jpaRepository;
	
	@Autowired
	public WordJpaDao(WordJpaRepository jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}
	
	@Override
	public Optional<Word> findByName(String wordName, Long dictionaryId) {
		return Optional.ofNullable(jpaRepository.findByNameAndDictionaryId(wordName, dictionaryId));
	}
	
}
