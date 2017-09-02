package igrek.webdict.model.dto;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DictionaryStatisticsDTO {
	
	public String dictDisplayName;
	
	public long allCount;
	
	public DictionaryStatisticsDTO(String dictDisplayName, long allCount) {
		this.dictDisplayName = dictDisplayName;
		this.allCount = allCount;
	}
	
	// stats
	public ProgressBarData trained;
	public ProgressBarData trainingInProgress;
	public ProgressBarData touched;
	public ProgressBarData coolingDown;
	
	public class ProgressBarData {
		
		public long count;
		public String percentage;
		
		public ProgressBarData(long count) {
			this.count = count;
			this.percentage = generatePercentage();
		}
		
		private String generatePercentage() {
			double percentage = ((double) count * 100) / allCount;
			// converting to string always with dot format
			DecimalFormatSymbols decimalSymbols = DecimalFormatSymbols.getInstance();
			decimalSymbols.setDecimalSeparator('.');
			DecimalFormat format = new DecimalFormat("#.##", decimalSymbols);
			return format.format(percentage);
		}
		
		public long getAllWordsCount() {
			return allCount;
		}
	}
}
