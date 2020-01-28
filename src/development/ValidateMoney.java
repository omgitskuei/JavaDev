package development;

import java.util.ArrayList;
import java.util.Currency;

import util.CheckSubstring;
import util.GetRuntimeInput;

public class ValidateMoney {

	public static void main(String[] args) {
		GetRuntimeInput util = new GetRuntimeInput();
		String input = util.getConsoleInputBR("Enter Money Amount:");
		
		float thisMoney = Float.parseFloat(input);
		
		boolean result = validateMoney(thisMoney);
		System.out.println("result: "+result);
	}
	
	private static boolean validateMoney(float thisMoney) {
		boolean success = false;
		try {
			System.out.println("BEGIN: ValidateMoney(float)");
			// Get an instance of the local Currency
			Currency localCurrency = Currency.getInstance("TWD");
			// Get how many decimals are allowed for this Currency
			int validCurrencyDecimals = localCurrency.getDefaultFractionDigits();  //equals =2 for TWD
			// Check if the user input has at most this amount of decimals
			String thisMoneyString = Float.toString(thisMoney);
			// Get the length of the substring after the "."
			CheckSubstring util = new CheckSubstring();
			ArrayList<String> delimitedThisMoney = util.delimitAtDot(thisMoneyString);
			
			// If user entered a number with <= the number of digits allowed, success = TRUE
			if (validCurrencyDecimals >= delimitedThisMoney.get(1).length()) {
				System.out.println("VALID FLOAT");
				success = true;
			} else {
				System.out.println("INVALID FLOAT");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("FINISH: validateMoney(float)");
		return success;
	}
}
