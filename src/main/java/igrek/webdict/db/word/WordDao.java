package igrek.webdict.db.word;

import java.util.List;
import java.util.Optional;

import igrek.webdict.db.common.BaseDao;
import igrek.webdict.model.entity.Word;

public interface WordDao extends BaseDao<Word> {
	
	List<Word> findByDictionaryAndUser(Long dictionaryId, Long userId);
	
	Optional<Word> findByName(String wordName, Long dictionaryId, Long userId);
	
}
