package igrek.webdict.model.dto;

import java.util.HashMap;
import java.util.Map;

public class DictionaryStatisticsDTO {
	
	public String dictDisplayName;
	
	public Map<String, Object> stats = new HashMap<>();
	
	
	public static class ProgressBarData {
		
		long count
	}
}
