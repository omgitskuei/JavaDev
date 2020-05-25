package development;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class TestSnippet {

	public TestSnippet() {
		
	}
	
	public static void main(String[] args) {
		TestSnippet instance = new TestSnippet();
		
//		String s = "123456.789";
//		String s = "  2312.314 4343   ";
		
//		System.out.println(instance.convertMoney(s));
//		
//		Timestamp thisLastUpdateTime = new Timestamp(2018, 01, 01, 13, 30, 40, 21212121);
//		String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(thisLastUpdateTime);
//		System.out.println(formattedDate);
		System.out.println(instance.getBranch("0060", true));
		System.out.println(instance.getBranch("0060", false));
		System.out.println(instance.getBranch("060", true));
		System.out.println(instance.getBranch("060", false));

		
	}
	
	private String getBranch(String branch, boolean isReturn) {
		// 取後三位數
		if (branch.length()==0 || branch==null) {
			return "";
		} else {
			if (isReturn) {
				return branch.length() > 3 ? branch.substring(branch.length() - 3, branch.length()) : branch;
			}
			else {
				return "0"+branch;
			}
		}
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
