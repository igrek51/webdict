package igrek.webdict.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import igrek.webdict.repository.DictionaryRepository;
import igrek.webdict.repository.LanguageRepository;
import igrek.webdict.repository.RankRepository;
import igrek.webdict.repository.UserRepository;
import igrek.webdict.repository.UserWordRepository;
import igrek.webdict.repository.WordRepository;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = {WordRepository.class, UserRepository.class, DictionaryRepository.class, RankRepository.class, LanguageRepository.class, UserWordRepository.class})
public class DbConfiguration {
	
}
