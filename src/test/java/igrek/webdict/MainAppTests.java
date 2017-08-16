package igrek.webdict;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("memdb")
public class MainAppTests {

	@Test
	public void contextLoads() {
		//TODO add tests: REST, mvc view, inmemory
	}

}
