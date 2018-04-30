package igrek.webdict.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import igrek.webdict.domain.dictionary.Dictionary;
import igrek.webdict.domain.dictionary.DictionaryCode;
import igrek.webdict.domain.user.User;
import igrek.webdict.domain.wordrank.Rank;
import igrek.webdict.domain.wordrank.TopWordComparator;
import igrek.webdict.service.DictionaryService;
import igrek.webdict.service.RankService;
import igrek.webdict.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("data")
public class TopWordComparatorTests {
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RankService rankService;
	
	@Test
	public void testComparatorContract() {
		
		Optional<User> oUser = userService.findOne((long) 1);
		if (!oUser.isPresent())
			throw new IllegalArgumentException("user with given id doesn't exist");
		
		DictionaryCode dictCode = DictionaryCode.parse("en-pl");
		Optional<Dictionary> oDictionary = dictionaryService.findByLanguages(dictCode.getSourceLanguage(), dictCode
				.getTargetLanguage());
		if (!oDictionary.isPresent())
			throw new IllegalArgumentException("dictionary with given languages doesn't exist");
		boolean reversedDictionary = dictCode.isReversedDictionary();
		
		List<Rank> ranks = rankService.findByDictionaryAndUser(oDictionary.get(), reversedDictionary, oUser
				.get());
		
		TopWordComparator comparator = new TopWordComparator();
		
		for (Rank rank : ranks) {
			assertThat(comparator.compare(rank, rank)).isEqualTo(0);
		}
		
		// sgn(compare(x, y)) == -sgn(compare(y, x))
		for (int i = 0; i < ranks.size(); i++) {
			for (Rank rank : ranks) {
				Rank r1 = ranks.get(i);
				Rank r2 = rank;
				assertThat(comparator.compare(r1, r2)).isEqualTo(-comparator.compare(r2, r1));
			}
		}
		
		Collections.sort(ranks, new TopWordComparator());
	}
	
}
