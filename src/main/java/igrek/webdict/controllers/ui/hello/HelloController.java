package igrek.webdict.controllers.ui.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	@Value("${info.build.version}")
	private String buildVersion;
	
	@GetMapping(value = {"", "/"}, produces = "text/plain")
	public @ResponseBody
	String welcome() {
		return "Hello Dupa v" + buildVersion;
	}
	
	@GetMapping("/thymeleaf")
	public String welcome(Model model) {
		model.addAttribute("message", "hello Dupa");
		return "hello/welcome"; // view welcome
	}
	
}
