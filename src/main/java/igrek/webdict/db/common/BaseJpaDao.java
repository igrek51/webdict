package igrek.webdict.db.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseJpaDao<T> {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected JpaRepository<T, Long> jpaRepository;
	
	public BaseJpaDao(JpaRepository<T, Long> jpaRepository) {
		this.jpaRepository = jpaRepository;
	}
	
	public long count() {
		return jpaRepository.count();
	}
	
	public Optional<T> findOne(Long id) {
		return Optional.of(jpaRepository.findOne(id));
	}
	
	public List<T> findAll() {
		return jpaRepository.findAll();
	}
	
	public void save(T entity) {
		jpaRepository.save(entity);
	}
	
	public void save(Iterable<T> entities) {
		jpaRepository.save(entities);
	}
	
	public boolean exists(Long id) {
		return jpaRepository.exists(id);
	}
	
}
