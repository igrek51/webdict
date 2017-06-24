package igrek.webdict;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HelloController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${ui.hello.message}")
	private String message;
	
	@GetMapping(value = "/", produces = "text/plain")
	public @ResponseBody
	String welcome() {
		logger.info("This is an info message");
		return message;
	}
}
