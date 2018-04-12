package igrek.webdict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import igrek.webdict.domain.dictionary.Dictionary;
import igrek.webdict.domain.dictionary.Language;
import igrek.webdict.repository.DictionaryRepository;
import igrek.webdict.repository.LanguageRepository;
import igrek.webdict.service.repository.AbstractRepositoryService;

@Service
public class DictionaryService extends AbstractRepositoryService<Dictionary> {
	
	private final DictionaryRepository dictionaryRepository;
	private final LanguageRepository languageRepository;
	
	@Autowired
	public DictionaryService(DictionaryRepository dictionaryRepository, LanguageRepository languageRepository) {
		super(dictionaryRepository);
		this.dictionaryRepository = dictionaryRepository;
		this.languageRepository = languageRepository;
	}
	
	public Optional<Dictionary> findByLanguages(String sourceLanguage, String targetLanguage) {
		Optional<Language> sourceLang = languageRepository.findByCode(sourceLanguage)
				.stream()
				.findAny();
		if (!sourceLang.isPresent())
			return Optional.empty();
		Optional<Language> targetLang = languageRepository.findByCode(targetLanguage)
				.stream()
				.findAny();
		if (!targetLang.isPresent())
			return Optional.empty();
		return dictionaryRepository.findBySourceLanguageAndTargetLanguage(sourceLang.get(), targetLang
				.get()).stream().findAny();
	}
}
