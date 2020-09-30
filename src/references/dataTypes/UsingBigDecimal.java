package references.dataTypes;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class UsingBigDecimal {

	public static void main(String[] args) {
		// Instantiate
		UsingBigDecimal instance = new UsingBigDecimal();

		// Example 1 - Comparing BigDecimals
		BigDecimal num1 = new BigDecimal("9"); // BigDecimal new declaration sytanx;
		BigDecimal num2 = new BigDecimal("10.00");
		BigDecimal num3 = new BigDecimal("10");
		BigDecimal num4 = new BigDecimal("11");
		instance.printCompareTo(num1, num2); // num1 less than num2
		instance.printCompareTo(num2, num3); // num2 equals num3
		instance.printCompareTo(num4, num2); // num4 greater than num2

		// Example 2 - An application of BigDecimal; formatting money (commas, decimals)
		String numStr = "100000004.0409";
		String numStr1 = "100000004";
		String numStr2 = "100000004.9999";
		String numStr3 = "-0000.3";
		String result = instance.convertMoney(numStr);
		String result1 = instance.convertMoney(numStr1);
		String result2 = instance.convertMoney(numStr2);
		String result3 = instance.convertMoney(numStr3);
		System.out.println("Example 2: " + result + ", " + result1 + ", " + result2 + ", " + result3);

		// Example 3 - Trying out exceptions
		try {
//			BigDecimal likelyException1 = new BigDecimal("~1");			// this DOES break; NumberFormatException
//			System.out.println(likelyException1);
//			BigDecimal likelyException2 = new BigDecimal("one");		// this DOES break; NumberFormatException
//			System.out.println(likelyException2);
//			BigDecimal likelyException3 = new BigDecimal("One");		// this DOES break; NumberFormatException
//			System.out.println(likelyException3);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private void printCompareTo(BigDecimal num1, BigDecimal num2) {
		// compareTo returns; -1 (num1<num2), 0 (num1=num2), 1 (num1>num2)
		int result = num1.compareTo(num2);
		if (result == -1) {
			System.out.println("Example 1: " + num1 + " is LESS THAN " + num2 + " (" + result + ")");
		} else if (result == 0) {
			System.out.println("Example 1: " + num1 + " is EQUAL TO " + num2 + " (" + result + ")");
		} else if (result == 1) {
			System.out.println("Example 1: " + num1 + " is GREATER THAN " + num2 + " (" + result + ")");
		}
	}

	private String convertMoney(String numStr) {
		if (numStr == null || numStr.equals("")) {
			System.out.println("Only spaces entered");
			return "";
		} else {
			String result = "";
			BigDecimal numBigDecimal = new BigDecimal(numStr);
			// Dictate bigDecimal format
			DecimalFormat decimalFormat = new DecimalFormat("#.##");
			decimalFormat.setGroupingUsed(true);
			decimalFormat.setGroupingSize(3); // adds commas
			decimalFormat.setMinimumFractionDigits(2); // set minimum decimals
			// format the bigDecimal obj
			result = decimalFormat.format(numBigDecimal);
			return result;
		}
	}

}
