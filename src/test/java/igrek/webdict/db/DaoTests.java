package igrek.webdict.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.db.language.LanguageDao;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.Language;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("memdb")
public class DaoTests {
	
	@Autowired
	private DictionaryDao dictionaryDao;
	
	@Autowired
	private LanguageDao languageDao;
	
	@Test
	public void testDictionaryDao() {
		
		Language sourceLang = languageDao.findByCode("en").get();
		Language targetLang = languageDao.findByCode("pl").get();
		dictionaryDao.save(new Dictionary(sourceLang, targetLang));
		
		Optional<Dictionary> oDictionary = dictionaryDao.findByLanguages("en", "pl");
		
		assertTrue(oDictionary.isPresent());
	}
	
}
