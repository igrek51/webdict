package igrek.webdict.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import igrek.webdict.domain.entity.Dictionary;
import igrek.webdict.domain.entity.Language;
import igrek.webdict.domain.entity.Rank;
import igrek.webdict.domain.entity.User;
import igrek.webdict.domain.entity.UserWord;
import igrek.webdict.domain.entity.Word;
import igrek.webdict.repository.DictionaryRepository;
import igrek.webdict.repository.LanguageRepository;
import igrek.webdict.repository.RankRepository;
import igrek.webdict.repository.UserRepository;
import igrek.webdict.repository.UserWordRepository;
import igrek.webdict.repository.WordRepository;
import igrek.webdict.service.DictionaryService;

@Service
public class DBInitializer {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final DictionaryRepository dictionaryRepository;
	private final LanguageRepository languageRepository;
	private final UserRepository userRepository;
	private final WordRepository wordRepository;
	private final UserWordRepository userwordRepository;
	private final RankRepository rankRepository;
	private final DictionaryService dictionaryService;
	
	@Autowired
	public DBInitializer(DictionaryRepository dictionaryRepository, LanguageRepository languageRepository, UserRepository userRepository, WordRepository wordRepository, UserWordRepository userwordRepository, RankRepository rankRepository, DictionaryService dictionaryService) {
		this.dictionaryRepository = dictionaryRepository;
		this.languageRepository = languageRepository;
		this.userRepository = userRepository;
		this.wordRepository = wordRepository;
		this.userwordRepository = userwordRepository;
		this.rankRepository = rankRepository;
		this.dictionaryService = dictionaryService;
	}
	
	@PostConstruct
	public void init() {
		logger.debug("Initializing languages...");
		addSampleLanguage("en");
		addSampleLanguage("pl");
		
		logger.debug("Initializing dictionaries...");
		addSampleDictionary("en", "pl");
		addSampleDictionary("pl", "en");
		
		logger.debug("Initializing users...");
		addSampleUser("igrek", "dupa");
		addSampleUser("janusz", "dupa");
		addSampleUser("jancio", "dupa");
		
		logger.debug("Initializing words...");
		addSampleWord("dick", "dik");
		addSampleWord("moby dick", "taki wieloryb");
		addSampleWord("ass", "dupa");
		addSampleWord("mock", "próbny");
		
		logger.debug("Initializing userwords...");
		addSampleUserWord("dick", 1L);
		addSampleUserWord("moby dick", 1L);
		addSampleUserWord("ass", 1L);
		addSampleUserWord("mock", 1L);
		addSampleUserWord("dick", 2L);
		addSampleUserWord("moby dick", 2L);
		
		logger.debug("Initializing ranks...");
		List<UserWord> userWords = userwordRepository.findByUserId(1L);
		for (UserWord userWord : userWords) {
			addSampleRank(userWord, false, 0, 0);
		}
		
		logger.info("Test DB initialized.");
	}
	
	private void addSampleDictionary(String sourceLanguageCode, String targetLanguageCode) {
		Language sourceLang = languageRepository.findByCode(sourceLanguageCode).get(0);
		Language targetLang = languageRepository.findByCode(targetLanguageCode).get(0);
		dictionaryRepository.save(new Dictionary(sourceLang, targetLang));
	}
	
	private void addSampleLanguage(String languageCode) {
		languageRepository.save(new Language(languageCode));
	}
	
	private void addSampleRank(UserWord userWord, boolean reversedDictionary, double rankValue, int triesCount) {
		rankRepository.save(new Rank(userWord, reversedDictionary, null, rankValue, triesCount));
	}
	
	private void addSampleUser(String login, String pass) {
		String encoded = encodePassword(pass);
		userRepository.save(new User(login, encoded));
	}
	
	private String encodePassword(String plain) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(plain.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			StringBuilder hashtext = new StringBuilder(bigInt.toString(16));
			// Now we need to zero pad it if you actually want the full 32 chars.
			while (hashtext.length() < 32) {
				hashtext.insert(0, "0");
			}
			return hashtext.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	private void addSampleWord(String word, String definition) {
		Optional<Dictionary> oDictionary = dictionaryService.findByLanguages("en", "pl");
		wordRepository.save(new Word(oDictionary.get(), word, definition));
	}
	
	private void addSampleUserWord(String wordName, Long userId) {
		Optional<Dictionary> oDictionary = dictionaryService.findByLanguages("en", "pl");
		User user = userRepository.findOne(userId);
		Word word = wordRepository.findByNameAndDictionaryId(wordName, oDictionary.get().getId());
		userwordRepository.save(new UserWord(user, word));
	}
}
