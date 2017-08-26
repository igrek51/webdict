package igrek.webdict.controllers.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import igrek.webdict.ui.alert.BootstrapAlert;
import igrek.webdict.ui.alert.BootstrapAlertType;

public abstract class BaseUIController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected void setActiveTab(Map<String, Object> model, String activeTab) {
		model.put("activeTab", activeTab);
	}
	
	protected void addAlert(List<BootstrapAlert> alerts, String message, BootstrapAlertType type) {
		BootstrapAlert alert = new BootstrapAlert(message, type);
		alerts.add(alert);
	}
}
