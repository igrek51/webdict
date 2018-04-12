package igrek.webdict.controller.info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/info")
public class InfoController {
	
	@Value("${info.build.version}")
	private String buildVersion;
	
	@GetMapping(value = {"", "/"}, produces = "text/plain")
	public @ResponseBody
	String info() {
		return "status: UP, version: " + buildVersion;
	}
	
}
