package igrek.webdict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import igrek.webdict.domain.entity.UserWord;
import igrek.webdict.repository.UserWordRepository;
import igrek.webdict.service.repository.RepositoryService;

@Service
public class UserWordService extends RepositoryService<UserWord> {
	
	private final UserWordRepository userWordRepository;
	
	@Autowired
	public UserWordService(UserWordRepository userWordRepository) {
		super(userWordRepository);
		this.userWordRepository = userWordRepository;
	}
	
	public List<UserWord> findByUserId(Long userId) {
		return userWordRepository.findByUserId(userId);
	}
	
	public Optional<UserWord> findByUserIdAndWordId(Long userId, Long wordId) {
		return Optional.ofNullable(userWordRepository.findByUserIdAndWordId(userId, wordId));
	}
	
	public List<UserWord> findByUserIdAndDictionaryId(Long userId, Long dictionaryId) {
		return userWordRepository.findByUserIdAndWordDictionaryId(userId, dictionaryId);
	}
	
	public List<UserWord> findByDictionaryAndUser(Long dictionaryId, Long userId) {
		return userWordRepository.findByDictionaryIdAndUserId(dictionaryId, userId);
	}
	
	public Optional<UserWord> findByName(String wordName, Long dictionaryId, Long userId) {
		return Optional.ofNullable(userWordRepository.findByNameAndDictionaryIdAndUserId(wordName, dictionaryId, userId));
	}
	
}
