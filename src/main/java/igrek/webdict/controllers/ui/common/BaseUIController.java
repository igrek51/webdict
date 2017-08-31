package igrek.webdict.controllers.ui.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import igrek.webdict.model.session.NotLoggedInException;
import igrek.webdict.model.session.SessionSettings;
import igrek.webdict.ui.alert.BootstrapAlert;
import igrek.webdict.ui.alert.BootstrapAlertType;

public abstract class BaseUIController {
	
	protected SessionSettings sessionSettings;
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected BaseUIController(SessionSettings sessionSettings) {
		this.sessionSettings = sessionSettings;
	}
	
	protected void setActiveTab(Map<String, Object> model, String activeTab) {
		model.put("activeTab", activeTab);
	}
	
	protected void setTitle(Map<String, Object> model, String title) {
		model.put("title", title);
	}
	
	protected void addAlert(List<BootstrapAlert> alerts, String message, BootstrapAlertType type) {
		BootstrapAlert alert = new BootstrapAlert(message, type);
		alerts.add(alert);
	}
	
	protected void checkSessionValid() throws NotLoggedInException {
		if (sessionSettings.getUser() == null || sessionSettings.getDictionary() == null)
			throw new NotLoggedInException();
	}
	
	@ExceptionHandler(NotLoggedInException.class)
	public View handleException(RedirectAttributes redir) {
		
		logger.debug("Not logged in - redirecting to settings page.");
		
		// show alert
		List<BootstrapAlert> alerts = new ArrayList<>();
		redir.addFlashAttribute("alerts", alerts);
		addAlert(alerts, "Choose an user and dictionary first.", BootstrapAlertType.WARNING);
		
		return new RedirectView("/settings", true);
	}
	
}
