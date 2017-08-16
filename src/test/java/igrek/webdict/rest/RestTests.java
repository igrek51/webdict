package igrek.webdict.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import igrek.webdict.controllers.ui.HelloController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("memdb")
public class RestTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private HelloController helloController;
	
	@Test
	public void testHelloRest() {
		assertThat(helloController).isNotNull();
		assertThat(helloController.welcome()).isEqualTo("hello");
		
		String body = this.restTemplate.getForObject("/hello", String.class);
		assertThat(body).isEqualTo("hello");
	}
	
}
