package igrek.webdict.controllers.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value = "/", produces = "text/plain")
	public @ResponseBody
	String welcome() {
		logger.info("This is an info message");
		return "hello";
	}
	
	@GetMapping("/jsp")
	public String welcomeJSP(Map<String, Object> model) {
		model.put("message", "hello");
		return "welcome"; // view welcome
	}
}
