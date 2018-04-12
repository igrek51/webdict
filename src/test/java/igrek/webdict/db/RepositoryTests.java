package igrek.webdict.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import igrek.webdict.domain.dictionary.Dictionary;
import igrek.webdict.domain.dictionary.Language;
import igrek.webdict.service.DictionaryService;
import igrek.webdict.service.LanguageService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("testdb")
public class RepositoryTests {
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private LanguageService languageService;
	
	@Test
	public void testDictionaryDao() {
		
		Language sourceLang = languageService.findByCode("en").get();
		Language targetLang = languageService.findByCode("pl").get();
		dictionaryService.save(new Dictionary(sourceLang, targetLang));
		
		Optional<Dictionary> oDictionary = dictionaryService.findByLanguages("en", "pl");
		assertThat(oDictionary.isPresent()).isTrue();
	}
	
}
