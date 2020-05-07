package development;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TestSnippet {

	public TestSnippet() {
		
	}
	
	public static void main(String[] args) {
//		String s = "123456.789";
		String s = "  2312.314 4343   ";
		TestSnippet instance = new TestSnippet();
		System.out.println(instance.convertMoney(s));
	}
	
	private String convertMoney(String numbers) {
		numbers = numbers.trim();
		System.out.println(numbers);
		
		int dotIndex = 0;
		if (numbers.contains(".")) {
			dotIndex = numbers.indexOf(".");
		}
		System.out.println(numbers);
		
		numbers = numbers.replaceAll("\\D", "");		// strips away all non-digit chars
		System.out.println(numbers);
		
		numbers = numbers.substring(0, dotIndex) + "." + numbers.substring(dotIndex);
		System.out.println(numbers);
		
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
