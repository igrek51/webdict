package igrek.webdict.db.word;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Optional;

import igrek.webdict.db.common.BaseInMemoryDao;
import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Word;

public class WordInMemoryDao extends BaseInMemoryDao<Word> implements WordDao {
	
	private DictionaryDao dictionaryDao;
	
	@Autowired
	public WordInMemoryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
		
		addSampleEntities();
	}
	
	private void addSampleEntities() {
		addSampleEntity("dick", "dik");
		addSampleEntity("moby dick", "taki wieloryb");
		addSampleEntity("ass", "dupa");
		addSampleEntity("mock", "próbny");
	}
	
	private void addSampleEntity(String word, String definition) {
		Optional<Dictionary> oDictionary = dictionaryDao.findByLanguages("en", "pl");
		super.addSampleEntity(new Word(oDictionary.get(), word, definition));
	}
	
	@Override
	public Optional<Word> findByName(String wordName, Long dictionaryId) {
		return entities.stream()
				.filter(word -> Objects.equals(word.getName(), wordName))
				.filter(word -> Objects.equals(word.getDictionary().getId(), dictionaryId))
				.findAny();
	}
}
