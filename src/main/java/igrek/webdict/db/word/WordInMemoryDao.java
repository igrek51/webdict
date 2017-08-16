package igrek.webdict.db.word;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import igrek.webdict.db.common.BaseInMemoryDao;
import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.db.user.UserDao;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.User;
import igrek.webdict.model.entity.Word;

public class WordInMemoryDao extends BaseInMemoryDao<Word> implements WordDao {
	
	private DictionaryDao dictionaryDao;
	private UserDao userDao;
	
	@Autowired
	public WordInMemoryDao(DictionaryDao dictionaryDao, UserDao userDao) {
		this.dictionaryDao = dictionaryDao;
		this.userDao = userDao;
		
		addSampleEntity("dick", "dik");
		addSampleEntity("moby dick", "taki wieloryb");
		addSampleEntity("ass", "dupa");
		addSampleEntity("mock", "próbny");
	}
	
	private void addSampleEntity(String word, String definition) {
		Dictionary dictionary = dictionaryDao.findAll().get(0);
		User user = userDao.findAll().get(0);
		super.addSampleEntity(new Word(dictionary, user, word, definition));
	}
	
	@Override
	public List<Word> findByDictionaryAndUser(Long dictionaryId, Long userId) {
		if (userId == null) {
			return entities.stream()
					.filter(word -> Objects.equals(word.getDictionary().getId(), dictionaryId))
					.collect(Collectors.toList());
		} else {
			return entities.stream()
					.filter(word -> Objects.equals(word.getDictionary().getId(), dictionaryId))
					.filter(word -> Objects.equals(word.getUser().getId(), userId))
					.collect(Collectors.toList());
		}
	}
	
	@Override
	public Optional<Word> findByName(String name) {
		return entities.stream().
				filter(word -> Objects.equals(word.getName(), name)).
				findAny();
	}
}
