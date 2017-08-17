package igrek.webdict.controllers.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	@GetMapping(value = {"", "/"}, produces = "text/plain")
	public @ResponseBody
	String welcome() {
		return "hello Dupa";
	}
	
	@GetMapping("/thymeleaf")
	public String welcome(Model model) {
		model.addAttribute("message", "hello Dupa");
		return "hello/welcome"; // view welcome
	}
	
}
