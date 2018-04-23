package igrek.webdict.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import igrek.webdict.repository.DictionaryRepository;
import igrek.webdict.repository.LanguageRepository;
import igrek.webdict.repository.RankRepository;
import igrek.webdict.repository.UserRepository;
import igrek.webdict.repository.UserWordRepository;
import igrek.webdict.repository.WordRepository;
import igrek.webdict.repository.test.DBInitializer;
import igrek.webdict.service.DictionaryService;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = {WordRepository.class, UserRepository.class, DictionaryRepository.class, RankRepository.class, LanguageRepository.class, UserWordRepository.class})
public class DbConfiguration {
	
	@Configuration
	@Profile("data")
	public class TestDbConfiguration {
		
		@Bean
		public DBInitializer provideDBInitializer(DictionaryRepository dictionaryRepository, LanguageRepository languageRepository, UserRepository userRepository, WordRepository wordRepository, UserWordRepository userwordRepository, RankRepository rankRepository, DictionaryService dictionaryService) {
			return new DBInitializer(dictionaryRepository, languageRepository, userRepository, wordRepository, userwordRepository, rankRepository, dictionaryService);
		}
		
	}
}
