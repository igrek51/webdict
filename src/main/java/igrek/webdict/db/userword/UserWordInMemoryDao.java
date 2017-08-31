package igrek.webdict.db.userword;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import igrek.webdict.db.common.BaseInMemoryDao;
import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.db.user.UserDao;
import igrek.webdict.db.word.WordDao;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.User;
import igrek.webdict.model.entity.UserWord;
import igrek.webdict.model.entity.Word;

public class UserWordInMemoryDao extends BaseInMemoryDao<UserWord> implements UserWordDao {
	
	private DictionaryDao dictionaryDao;
	private UserDao userDao;
	private WordDao wordDao;
	
	@Autowired
	public UserWordInMemoryDao(DictionaryDao dictionaryDao, UserDao userDao, WordDao wordDao) {
		this.dictionaryDao = dictionaryDao;
		this.userDao = userDao;
		this.wordDao = wordDao;
		
		addSampleEntities();
	}
	
	private void addSampleEntities() {
		addSampleEntity("dick", 1L);
		addSampleEntity("moby dick", 1L);
		addSampleEntity("ass", 1L);
		addSampleEntity("mock", 1L);
		
		addSampleEntity("dick", 2L);
		addSampleEntity("moby dick", 2L);
	}
	
	private void addSampleEntity(String wordName, Long userId) {
		Optional<Dictionary> oDictionary = dictionaryDao.findByLanguages("en", "pl");
		Optional<User> oUser = userDao.findOne(userId);
		Optional<Word> oWord = wordDao.findByName(wordName, oDictionary.get().getId());
		super.addSampleEntity(new UserWord(oUser.get(), oWord.get()));
	}
	
	@Override
	public List<UserWord> findByUserId(Long userId) {
		return entities.stream()
				.filter(userWord -> Objects.equals(userWord.getUser().getId(), userId))
				.collect(Collectors.toList());
	}
	
	@Override
	public Optional<UserWord> findByUserIdAndWordId(Long userId, Long wordId) {
		return entities.stream()
				.filter(userWord -> Objects.equals(userWord.getWord().getId(), wordId))
				.filter(userWord -> Objects.equals(userWord.getUser().getId(), userId))
				.findAny();
	}
	
	@Override
	public List<UserWord> findByUserIdAndDictionaryId(Long userId, Long dictionaryId) {
		return entities.stream()
				.filter(userWord -> Objects.equals(userWord.getUser().getId(), userId))
				.filter(userWord -> Objects.equals(userWord.getWord()
						.getDictionary()
						.getId(), dictionaryId))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<UserWord> findByDictionaryAndUser(Long dictionaryId, Long userId) {
		return findByUserId(userId).stream()
				.filter(userWord -> Objects.equals(userWord.getWord()
						.getDictionary()
						.getId(), dictionaryId))
				.collect(Collectors.toList());
	}
	
	@Override
	public Optional<UserWord> findByName(String wordName, Long dictionaryId, Long userId) {
		return findByUserId(userId).stream()
				.filter(userWord -> Objects.equals(userWord.getWord().getName(), wordName))
				.filter(userWord -> Objects.equals(userWord.getWord()
						.getDictionary()
						.getId(), dictionaryId))
				.findAny();
	}
}
