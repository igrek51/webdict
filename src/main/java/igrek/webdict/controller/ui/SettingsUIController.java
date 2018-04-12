package igrek.webdict.controller.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import igrek.webdict.domain.DictionaryCode;
import igrek.webdict.domain.alert.BootstrapAlert;
import igrek.webdict.domain.alert.BootstrapAlertType;
import igrek.webdict.domain.dto.SettingsDTO;
import igrek.webdict.domain.entity.Dictionary;
import igrek.webdict.domain.entity.User;
import igrek.webdict.domain.session.SessionSettings;
import igrek.webdict.service.DictionaryService;
import igrek.webdict.service.UserService;

@Controller
@SessionScope
@RequestMapping("/settings")
public class SettingsUIController extends BaseUIController {
	
	private final UserService userService;
	private final DictionaryService dictionaryService;
	
	@Autowired
	public SettingsUIController(SessionSettings sessionSettings, UserService userService, DictionaryService dictionaryService) {
		super(sessionSettings);
		this.userService = userService;
		this.dictionaryService = dictionaryService;
		this.sessionSettings = sessionSettings;
	}
	
	@GetMapping({"", "/"})
	public String showSettings(Map<String, Object> model) {
		setTitle(model, "Settings");
		setActiveTab(model, "settings");
		setSettingsData(model);
		
		Long userId = sessionSettings.getUser() == null ? null : sessionSettings.getUser().getId();
		String dictionaryCode = sessionSettings.getDictionary() == null ? null : DictionaryCode.toDictionaryCode(sessionSettings
				.getDictionary(), sessionSettings.isReversedDictionary());
		SettingsDTO settingsDTO = new SettingsDTO(userId, dictionaryCode);
		model.put("settingsDTO", settingsDTO);
		
		List<User> users = userService.findAll();
		model.put("users", users);
		
		List<Dictionary> dicts = dictionaryService.findAll();
		Map<String, String> dictsMap = new LinkedHashMap<>();
		for (Dictionary dict : dicts) {
			dictsMap.put(DictionaryCode.toDictionaryCode(dict, false), DictionaryCode.toDictionaryDisplayName(dict, false));
			//reversed
			dictsMap.put(DictionaryCode.toDictionaryCode(dict, true), DictionaryCode.toDictionaryDisplayName(dict, true));
		}
		model.put("dictionaries", dictsMap);
		
		return "settings";
	}
	
	@PostMapping({"", "/"})
	public String showSettings(@ModelAttribute("settingsDTO") SettingsDTO settingsDTO, Map<String, Object> model) {
		// bootstrap alerts
		List<BootstrapAlert> alerts = new ArrayList<>();
		model.put("alerts", alerts);
		
		// update user
		Optional<User> oUser = userService.findOne(settingsDTO.getUserId());
		sessionSettings.setUser(oUser.get());
		
		// update dictionary and direction
		DictionaryCode dictionaryCode = DictionaryCode.parse(settingsDTO.getDictionaryCode());
		sessionSettings.setReversedDictionary(dictionaryCode.isReversedDictionary());
		
		Optional<Dictionary> oDictionary = dictionaryService.findByLanguages(dictionaryCode.getSourceLanguage(), dictionaryCode
				.getTargetLanguage());
		sessionSettings.setDictionary(oDictionary.get());
		
		addAlert(alerts, "Settings have been saved successfully.", BootstrapAlertType.SUCCESS);
		
		return showSettings(model);
	}
	
}
