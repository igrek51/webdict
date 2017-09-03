package igrek.webdict.controllers.ui;

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

import igrek.webdict.controllers.ui.common.BaseUIController;
import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.db.user.UserDao;
import igrek.webdict.model.DictionaryCode;
import igrek.webdict.model.dto.SettingsDTO;
import igrek.webdict.model.entity.Dictionary;
import igrek.webdict.model.entity.User;
import igrek.webdict.model.session.SessionSettings;
import igrek.webdict.ui.alert.BootstrapAlert;
import igrek.webdict.ui.alert.BootstrapAlertType;

@Controller
@SessionScope
@RequestMapping("/settings")
public class SettingsController extends BaseUIController {
	
	private final UserDao userDao;
	private final DictionaryDao dictionaryDao;
	
	@Autowired
	public SettingsController(SessionSettings sessionSettings, UserDao userDao, DictionaryDao dictionaryDao) {
		super(sessionSettings);
		this.sessionSettings = sessionSettings;
		this.userDao = userDao;
		this.dictionaryDao = dictionaryDao;
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
		
		List<User> users = userDao.findAll();
		model.put("users", users);
		
		List<Dictionary> dicts = dictionaryDao.findAll();
		Map<String, String> dictsMap = new LinkedHashMap<>();
		for (Dictionary dict : dicts) {
			StringBuilder sb = new StringBuilder();
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
		Optional<User> oUser = userDao.findOne(settingsDTO.getUserId());
		sessionSettings.setUser(oUser.get());
		
		// update dictionary and direction
		DictionaryCode dictionaryCode = DictionaryCode.parse(settingsDTO.getDictionaryCode());
		sessionSettings.setReversedDictionary(dictionaryCode.isReversedDictionary());
		
		Optional<Dictionary> oDictionary = dictionaryDao.findByLanguages(dictionaryCode.getSourceLanguage(), dictionaryCode
				.getTargetLanguage());
		sessionSettings.setDictionary(oDictionary.get());
		
		addAlert(alerts, "Settings have been saved successfully.", BootstrapAlertType.SUCCESS);
		
		return showSettings(model);
	}
	
}
