package igrek.webdict.db.common;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaseDao<T> {
	
	long count();
	
	List<T> findAll();
	
	Optional<T> findOne(Long id);
	
	void save(T word);
	
}
