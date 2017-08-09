package igrek.webdict.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import igrek.webdict.db.dictentry.DictEntryDao;
import igrek.webdict.db.dictentry.DictEntryRepository;
import igrek.webdict.db.dictentry.InMemoryDictEntryDao;
import igrek.webdict.db.dictentry.JpaDictEntryDao;
import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.db.dictionary.InMemoryDictionaryDao;

@Configuration
public class DbConfiguration {
	
	@Bean
	public DictionaryDao provideDictionaryDao() {
		return new InMemoryDictionaryDao();
	}
	
	@Configuration
	@Profile("default")
	@EnableAutoConfiguration
	@EnableJpaRepositories
	public static class DefaultDbConfiguration {
		@Bean
		public DictEntryDao provideDictEntryDao(DictEntryRepository dictEntryRepository) {
			return new JpaDictEntryDao(dictEntryRepository);
		}
	}
	
	@Configuration
	@Profile("memdb")
	@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
	public class MemDbConfiguration {
		@Bean
		public DictEntryDao provideDictEntryDao() {
			return new InMemoryDictEntryDao();
		}
	}
	
}
