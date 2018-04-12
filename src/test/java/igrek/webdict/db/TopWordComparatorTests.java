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

import igrek.webdict.domain.DictionaryCode;
import igrek.webdict.domain.TopWordComparator;
import igrek.webdict.domain.entity.Dictionary;
import igrek.webdict.domain.entity.Rank;
import igrek.webdict.domain.entity.User;
import igrek.webdict.repository.dictionary.DictionaryDao;
import igrek.webdict.repository.rank.RankDao;
import igrek.webdict.repository.user.UserDao;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("memdb")
public class TopWordComparatorTests {
	
	@Autowired
	private DictionaryDao dictionaryDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RankDao rankDao;
	
	@Test
	public void testComparatorContract() {
		
		Optional<User> oUser = userDao.findOne((long) 1);
		if (!oUser.isPresent())
			throw new IllegalArgumentException("user with given id doesn't exist");
		
		DictionaryCode dictCode = DictionaryCode.parse("en-pl");
		Optional<Dictionary> oDictionary = dictionaryDao.findByLanguages(dictCode.getSourceLanguage(), dictCode
				.getTargetLanguage());
		if (!oDictionary.isPresent())
			throw new IllegalArgumentException("dictionary with given languages doesn't exist");
		boolean reversedDictionary = dictCode.isReversedDictionary();
		
		List<Rank> ranks = rankDao.findByDictionaryAndUser(oDictionary.get(), reversedDictionary, oUser
				.get());
		
		TopWordComparator comparator = new TopWordComparator();
		
		for (Rank rank : ranks) {
			assertEquals(0, comparator.compare(rank, rank));
		}
		
		// sgn(compare(x, y)) == -sgn(compare(y, x))
		for (int i = 0; i < ranks.size(); i++) {
			for (int j = 0; j < ranks.size(); j++) {
				Rank r1 = ranks.get(i);
				Rank r2 = ranks.get(j);
				assertEquals(comparator.compare(r1, r2), -comparator.compare(r2, r1));
			}
		}
		
		Collections.sort(ranks, new TopWordComparator());
		
	}
	
}
