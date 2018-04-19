package igrek.webdict.controller.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igrek.webdict.domain.env.EnvironmentInfo;

@RestController
@RequestMapping("/api/info")
class AppInfoController {
	
	@Value("${info.build.env}")
	private String environmentName;
	
	@Value("${info.build.version}")
	private String buildVersion;
	
	@GetMapping({"", "/"})
	public EnvironmentInfo getEnvironmentInfo() {
		return new EnvironmentInfo(environmentName, buildVersion);
	}
	
}
