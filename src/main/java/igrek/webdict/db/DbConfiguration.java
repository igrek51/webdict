package igrek.webdict.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import igrek.webdict.db.dictentry.DictEntryDao;
import igrek.webdict.db.dictentry.InMemoryDictEntryDao;
import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.db.dictionary.InMemoryDictionaryDao;

@Configuration
public class DbConfiguration {
	
	@Bean
	public DictionaryDao provideDictionaryDao() {
		return new InMemoryDictionaryDao();
	}
	
	@Bean
	public DictEntryDao provideDictEntryDao() {
		return new InMemoryDictEntryDao(provideDictionaryDao());
	}
	
}
