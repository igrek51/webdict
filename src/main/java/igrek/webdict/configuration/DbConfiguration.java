package igrek.webdict.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.db.dictionary.DictionaryJpaRepository;
import igrek.webdict.db.dictionary.InMemoryDictionaryDao;
import igrek.webdict.db.dictionary.JpaDictionaryDao;
import igrek.webdict.db.language.InMemoryLanguageDao;
import igrek.webdict.db.language.JpaLanguageDao;
import igrek.webdict.db.language.LanguageDao;
import igrek.webdict.db.language.LanguageJpaRepository;
import igrek.webdict.db.rank.InMemoryRankDao;
import igrek.webdict.db.rank.JpaRankDao;
import igrek.webdict.db.rank.RankDao;
import igrek.webdict.db.rank.RankJpaRepository;
import igrek.webdict.db.user.InMemoryUserDao;
import igrek.webdict.db.user.JpaUserDao;
import igrek.webdict.db.user.UserDao;
import igrek.webdict.db.user.UserJpaRepository;
import igrek.webdict.db.word.InMemoryWordDao;
import igrek.webdict.db.word.JpaWordDao;
import igrek.webdict.db.word.WordDao;
import igrek.webdict.db.word.WordJpaRepository;

@Configuration
public class DbConfiguration {
	
	@Configuration
	@Profile("default")
	@EnableAutoConfiguration
	@EnableJpaRepositories(basePackageClasses = {WordJpaRepository.class, UserJpaRepository.class, DictionaryJpaRepository.class, RankJpaRepository.class, LanguageJpaRepository.class})
	public class DefaultDbConfiguration {
		@Bean
		public WordDao provideWordDao(WordJpaRepository wordJpaRepository) {
			return new JpaWordDao(wordJpaRepository);
		}
		
		@Bean
		public DictionaryDao provideDictionaryDao(DictionaryJpaRepository jpaRepository, LanguageDao languageDao) {
			return new JpaDictionaryDao(jpaRepository, languageDao);
		}
		
		@Bean
		public UserDao provideUserDao(UserJpaRepository jpaRepository) {
			return new JpaUserDao(jpaRepository);
		}
		
		@Bean
		public LanguageDao provideLanguageDao(LanguageJpaRepository jpaRepository) {
			return new JpaLanguageDao(jpaRepository);
		}
		
		@Bean
		public RankDao provideRankDao(RankJpaRepository jpaRepository) {
			return new JpaRankDao(jpaRepository);
		}
	}
	
	@Configuration
	@Profile("memdb")
	@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
	public class MemDbConfiguration {
		@Bean
		public WordDao provideWordDao(DictionaryDao dictionaryDao, UserDao userDao) {
			return new InMemoryWordDao(dictionaryDao, userDao);
		}
		
		@Bean
		public DictionaryDao provideDictionaryDao(LanguageDao languageDao) {
			return new InMemoryDictionaryDao(languageDao);
		}
		
		@Bean
		public UserDao provideUserDao() {
			return new InMemoryUserDao();
		}
		
		@Bean
		public LanguageDao provideLanguageDao() {
			return new InMemoryLanguageDao();
		}
		
		@Bean
		public RankDao provideRankDao(WordDao wordDao) {
			return new InMemoryRankDao(wordDao);
		}
	}
	
}
