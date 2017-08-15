package igrek.webdict.controllers.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	@GetMapping(value = "/", produces = "text/plain")
	public @ResponseBody
	String welcome() {
		return "hello";
	}
	
	@GetMapping("/jsp")
	public String welcomeJSP(Map<String, Object> model) {
		model.put("message", "hello");
		return "welcome"; // view welcome
	}
}
