package references;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class UsingBigDecimal {

	public static void main(String[] args) {
		UsingBigDecimal instance = new UsingBigDecimal();
		String numStr = "1004.003";
		BigDecimal numBDecimal = new BigDecimal(numStr);
		
		String result = instance.convertMoney(numStr);
		
		
	}
	
	private String convertMoney(String numbers) {
		if (numbers == null || numbers.equals("")) {
			System.out.println("Only spaces entered");
			return "";
		} else {
			String result = "";
			BigDecimal obj = new BigDecimal(numbers);
			DecimalFormat decimalFormat = new DecimalFormat("#.##"); 
			decimalFormat.setGroupingUsed(true); 
			decimalFormat.setGroupingSize(3);
			decimalFormat.setMinimumFractionDigits(2);
			result = decimalFormat.format(obj);
			return result;
		}
	}

}
