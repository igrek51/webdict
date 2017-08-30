package igrek.webdict.db.userword;

import java.util.List;
import java.util.Optional;

import igrek.webdict.db.common.BaseDao;
import igrek.webdict.model.entity.UserWord;

public interface UserWordDao extends BaseDao<UserWord> {
	
	List<UserWord> findByUserId(Long userId);
	
	Optional<UserWord> findByUserIdAndWordId(Long userId, Long wordId);
	
	List<UserWord> findByUserIdAndDictionaryId(Long userId, Long dictionaryId);
	
	List<UserWord> findByDictionaryAndUser(Long dictionaryId, Long userId);
	
	Optional<UserWord> findByName(String wordName, Long dictionaryId, Long userId);
	
}
