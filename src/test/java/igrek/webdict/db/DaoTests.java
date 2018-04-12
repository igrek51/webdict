package igrek.webdict.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import igrek.webdict.domain.entity.Dictionary;
import igrek.webdict.domain.entity.Language;
import igrek.webdict.service.DictionaryService;
import igrek.webdict.service.LanguageService;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("memdb")
public class DaoTests {
	
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
		
		assertTrue(oDictionary.isPresent());
	}
	
}
