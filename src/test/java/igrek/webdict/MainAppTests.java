package igrek.webdict;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("default") // turn off other profiles
public class MainAppTests {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void contextLoads() {
		logger.debug("Spring Context loaded successfully.");
	}
	
}
