package igrek.webdict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import igrek.webdict.domain.entity.Language;
import igrek.webdict.repository.LanguageRepository;
import igrek.webdict.service.repository.AbstractRepositoryService;

@Service
public class LanguageService extends AbstractRepositoryService<Language> {
	
	private final LanguageRepository languageRepository;
	
	@Autowired
	public LanguageService(LanguageRepository languageRepository) {
		super(languageRepository);
		this.languageRepository = languageRepository;
	}
	
	public Optional<Language> findByCode(String code) {
		return languageRepository.findByCode(code).stream().findAny();
	}
	
}
