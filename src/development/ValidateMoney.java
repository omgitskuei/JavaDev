package development;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;

import util.CheckSubstring;
import util.GetRuntimeInput;

public class ValidateMoney {
	// Local fields

	// Constructors

	// Executable
	public static void main(String[] args) throws IOException {
		// Take user input
		GetRuntimeInput util = new GetRuntimeInput("Enter Money Amount:");
		String input = util.returnInput();
		util.closeReader();
		// Check if money is valid
		boolean result = new ValidateMoney().validateMoney(input, "TWD");
		System.out.println("result: " + result);
	}

	// Methods
	private boolean validateMoney(String thisMoney, String currencyCodeISO4217) {
		System.out.println("BEGIN: ValidateMoney(float, String)");
		// CustomClasses if passed currency code is valid
		try {
			Currency localCurrency = Currency.getInstance(currencyCodeISO4217);
			System.out.println("	Passed a Currency code of [" + currencyCodeISO4217 + "]");
			System.out.println("		Currency Display Name: " + localCurrency.getDisplayName());
			System.out.println("		Currency Numeric Code: " + localCurrency.getNumericCode());
			System.out.println("		Currency Symbol: " + localCurrency.getSymbol());
			System.out.println("		Currency Fraction Digits: " + localCurrency.getDefaultFractionDigits());
			// Try to parse String to Float
			try {
				// Check if the passed amount has at most this amount of decimals
				System.out.println("	Passed Money amount of [" + thisMoney + "]");
				float thisMoneyFloat = Float.parseFloat(thisMoney);
				System.out.println(thisMoney + " converted to float [" + thisMoneyFloat + "]");
				// Add back the ".00" to passed whole numbers
				thisMoney = String.valueOf(thisMoneyFloat);
				System.out.println("		Passed Money is a Valid float");
				// Get how many decimals are allowed for this Currency
				int validCurrencyDecimals = localCurrency.getDefaultFractionDigits(); // equals =2 for most currencies
				// Get the number of digits (decimals)
				CheckSubstring util = new CheckSubstring();
				ArrayList<String> delimitedThisMoney = util.delimitAtDot(thisMoney);
				// If user entered a number with decimals <= the max amount of decimals allowed,
				// return True
				if (validCurrencyDecimals >= delimitedThisMoney.get(1).length()) {
					System.out.println("		Passed money decimal digits [" + delimitedThisMoney.get(1).length()
							+ "] is <= Currency fraction digits [" + localCurrency.getDefaultFractionDigits() + "]");
					System.out.println("			Input is a VALID MONEY");
					System.out.println("FINISH: validateMoney(float)");
					return true;
				} else {
					System.out.println("		Passed money decimal digits [" + delimitedThisMoney.get(1).length()
							+ "] is >= Currency fraction digits [" + localCurrency.getDefaultFractionDigits() + "]");
					System.out.println("			Input is a INVALID MONEY");
				}
			} catch (NumberFormatException e) {
				System.out.println("	Input is NOT A VALID FLOAT");
				System.out.println("FINISH: ValidateMoney(float, String)");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("	currencyCode is NOT a supported ISO 4217 code");
		}
		System.out.println("FINISH: validateMoney(float)");
		return false;
	}
}
