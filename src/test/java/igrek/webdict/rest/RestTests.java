package igrek.webdict.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import igrek.webdict.controller.info.InfoController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("memdb")
public class RestTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private InfoController infoController;
	
	@Test
	public void testHelloRest() {
		assertThat(infoController).isNotNull();
		assertThat(infoController.info()).startsWith("status: UP");
		
		String body = this.restTemplate.getForObject("/api/info", String.class);
		assertThat(body).startsWith("status: UP");
	}
	
}
