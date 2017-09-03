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
import igrek.webdict.db.dictionary.DictionaryInMemoryDao;
import igrek.webdict.db.dictionary.DictionaryJpaDao;
import igrek.webdict.db.dictionary.DictionaryJpaRepository;
import igrek.webdict.db.language.LanguageDao;
import igrek.webdict.db.language.LanguageInMemoryDao;
import igrek.webdict.db.language.LanguageJpaDao;
import igrek.webdict.db.language.LanguageJpaRepository;
import igrek.webdict.db.rank.RankDao;
import igrek.webdict.db.rank.RankInMemoryDao;
import igrek.webdict.db.rank.RankJpaDao;
import igrek.webdict.db.rank.RankJpaRepository;
import igrek.webdict.db.user.UserDao;
import igrek.webdict.db.user.UserInMemoryDao;
import igrek.webdict.db.user.UserJpaDao;
import igrek.webdict.db.user.UserJpaRepository;
import igrek.webdict.db.userword.UserWordDao;
import igrek.webdict.db.userword.UserWordInMemoryDao;
import igrek.webdict.db.userword.UserWordJpaDao;
import igrek.webdict.db.userword.UserWordJpaRepository;
import igrek.webdict.db.word.WordDao;
import igrek.webdict.db.word.WordInMemoryDao;
import igrek.webdict.db.word.WordJpaDao;
import igrek.webdict.db.word.WordJpaRepository;

@Configuration
public class DbConfiguration {
	
	@Configuration
	@Profile("!memdb")
	@EnableAutoConfiguration
	@EnableJpaRepositories(basePackageClasses = {WordJpaRepository.class, UserJpaRepository.class, DictionaryJpaRepository.class, RankJpaRepository.class, LanguageJpaRepository.class, UserWordJpaRepository.class})
	public class DefaultDbConfiguration {
		
		@Bean
		public WordDao provideWordDao(WordJpaRepository wordJpaRepository) {
			return new WordJpaDao(wordJpaRepository);
		}
		
		@Bean
		public DictionaryDao provideDictionaryDao(DictionaryJpaRepository jpaRepository, LanguageDao languageDao) {
			return new DictionaryJpaDao(jpaRepository, languageDao);
		}
		
		@Bean
		public UserDao provideUserDao(UserJpaRepository jpaRepository) {
			return new UserJpaDao(jpaRepository);
		}
		
		@Bean
		public LanguageDao provideLanguageDao(LanguageJpaRepository jpaRepository) {
			return new LanguageJpaDao(jpaRepository);
		}
		
		@Bean
		public RankDao provideRankDao(RankJpaRepository jpaRepository) {
			return new RankJpaDao(jpaRepository);
		}
		
		@Bean
		public UserWordDao provideUserWordDao(UserWordJpaRepository jpaRepository) {
			return new UserWordJpaDao(jpaRepository);
		}
	}
	
	@Configuration
	@Profile("memdb")
	@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
	public class MemDbConfiguration {
		
		@Bean
		public WordDao provideWordDao(DictionaryDao dictionaryDao) {
			return new WordInMemoryDao(dictionaryDao);
		}
		
		@Bean
		public DictionaryDao provideDictionaryDao(LanguageDao languageDao) {
			return new DictionaryInMemoryDao(languageDao);
		}
		
		@Bean
		public UserDao provideUserDao() {
			return new UserInMemoryDao();
		}
		
		@Bean
		public LanguageDao provideLanguageDao() {
			return new LanguageInMemoryDao();
		}
		
		@Bean
		public RankDao provideRankDao(UserWordDao userWordDao) {
			return new RankInMemoryDao(userWordDao);
		}
		
		@Bean
		public UserWordDao provideUserWordDao(DictionaryDao dictionaryDao, UserDao userDao, WordDao wordDao) {
			return new UserWordInMemoryDao(dictionaryDao, userDao, wordDao);
		}
	}
	
}
