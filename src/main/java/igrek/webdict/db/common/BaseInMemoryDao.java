package igrek.webdict.db.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import igrek.webdict.model.HasId;

public abstract class BaseInMemoryDao<T extends HasId> {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected List<T> entities = new ArrayList<>();
	
	public long count() {
		return entities.size();
	}
	
	public Optional<T> findOne(Long id) {
		return entities.stream().
				filter(d -> Objects.equals(d.getId(), id)).
				findAny();
	}
	
	public List<T> findAll() {
		return new ArrayList<>(entities);
	}
	
	public void save(T entity) {
		if (entity.getId() == null) {
			entity.setId(getMaxId() + 1);
		}
		entities.removeIf(d -> d.getId().equals(entity.getId()));
		entities.add(entity);
	}
	
	public void save(Iterable<T> newEntities) {
		for (T newEntity : newEntities) {
			save(newEntity);
		}
	}
	
	public boolean exists(Long id) {
		return entities.stream().anyMatch(d -> Objects.equals(d.getId(), id));
	}
	
	protected long getMaxId() {
		return entities.stream().mapToLong(T::getId).max().orElse(0);
	}
	
	protected void addSampleEntity(T entity) {
		entity.setId(getMaxId() + 1);
		entities.add(entity);
	}
	
}
