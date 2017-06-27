package igrek.webdict.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import igrek.webdict.db.dictentry.DictEntryDao;
import igrek.webdict.db.dictentry.DictEntryRepository;
import igrek.webdict.db.dictentry.JpaDictEntryDao;
import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.db.dictionary.InMemoryDictionaryDao;

@Configuration
@EnableJpaRepositories
public class DbConfiguration {
	
	@Bean
	public DictionaryDao provideDictionaryDao() {
		return new InMemoryDictionaryDao();
	}
	
	@Bean
	public DictEntryDao provideDictEntryDao(DictEntryRepository dictEntryRepository) {
		// possible switching between in-memory DB or JPA
		return new JpaDictEntryDao(dictEntryRepository);
	}
	
}
