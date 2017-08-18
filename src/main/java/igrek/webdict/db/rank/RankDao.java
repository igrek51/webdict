package igrek.webdict.db.rank;

import java.util.List;
import java.util.Optional;

import igrek.webdict.db.common.BaseDao;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Rank;
import igrek.webdict.model.entity.User;

public interface RankDao extends BaseDao<Rank> {
	
	List<Rank> findByDictionaryAndUser(Dictionary dictionary, boolean reversedDictionary, User user);
	
	Optional<Rank> getTop(Dictionary dictionary, boolean reversedDictionary, User user);
	
}
