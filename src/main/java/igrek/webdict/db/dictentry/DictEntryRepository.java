package igrek.webdict.db.dictentry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import igrek.webdict.model.DictEntry;

@Transactional()
public interface DictEntryRepository extends JpaRepository<DictEntry, Long> {

}
