package igrek.webdict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import igrek.webdict.domain.word.Word;
import igrek.webdict.repository.WordRepository;
import igrek.webdict.service.repository.AbstractRepositoryService;

@Service
public class WordService extends AbstractRepositoryService<Word> {
	
	private final WordRepository wordRepository;
	
	@Autowired
	public WordService(WordRepository wordRepository) {
		super(wordRepository);
		this.wordRepository = wordRepository;
	}
	
	public Optional<Word> findByName(String wordName, Long dictionaryId) {
		return Optional.ofNullable(wordRepository.findByNameAndDictionaryId(wordName, dictionaryId));
	}
	
}
