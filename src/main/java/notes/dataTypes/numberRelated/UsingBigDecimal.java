package main.java.notes.dataTypes.numberRelated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class UsingBigDecimal {

	public static void main(String[] args) {
		// Instantiate
		UsingBigDecimal instance = new UsingBigDecimal();

		// Example 1 - Comparing BigDecimals
		BigDecimal num9 = new BigDecimal("9"); // BigDecimal new declaration sytanx;
		BigDecimal num10 = new BigDecimal("10.00");
		BigDecimal anotherNum10 = new BigDecimal("10");
		BigDecimal num11 = new BigDecimal("11");
		
		// BigDecimal.compareTo() returns: -1 (num1<num2), 0 (num1=num2), 1 (num1>num2)
		int result = num9.compareTo(num10);           // num9  < num10         returns -1
		System.out.println("Example 1: " + num9 + " is LESS THAN " + num10 + " (" + result + ")");
		result = num10.compareTo(anotherNum10);       // num10 = anotherNum10  returns 0
		System.out.println("Example 1: " + num10 + " is EQUAL TO " + anotherNum10 + " (" + result + ")");
		result = num11.compareTo(num10);              // num11 > num10         returns 1
		System.out.println("Example 1: " + num11 + " is GREATER THAN " + num10 + " (" + result + ")");
		

		// Example 2 - An application of BigDecimal; formatting money (commas, decimals)
		String numStr = "100000004.0409";
		String numStr1 = "100000004";
		String numStr2 = "100000004.9999";
		String numStr3 = "-0000.3";
		String resultStr = instance.convertMoney(numStr);
		String resultStr1 = instance.convertMoney(numStr1);
		String resultStr2 = instance.convertMoney(numStr2);
		String resultStr3 = instance.convertMoney(numStr3);
		System.out.println("Example 2: " + resultStr + ", " + resultStr1 + ", " + resultStr2 + ", " + resultStr3);

		// Example 3 - Trying out exceptions
		try {
			BigDecimal likelyException1 = new BigDecimal("~1");			// NumberFormatException
			System.out.println(likelyException1);
		} catch (NumberFormatException e) {
			System.out.println("Example 3.1: threw " + e.getClass());
		}
		try {
            BigDecimal likelyException1 = new BigDecimal("one");         // NumberFormatException
            System.out.println(likelyException1);
        } catch (NumberFormatException e) {
            System.out.println("Example 3.2: threw " + e.getClass());
        }
		try {
            BigDecimal likelyException1 = new BigDecimal("One");         // NumberFormatException
            System.out.println(likelyException1);
        } catch (NumberFormatException e) {
            System.out.println("Example 3.3: threw " + e.getClass());
        }
		try {
		    BigDecimal aSmallNum = new BigDecimal("1.1");
		    System.out.println(aSmallNum);                                // 1.1
		    System.out.println(aSmallNum.intValue());                     // 1
		    System.out.println(new BigDecimal("-1.023").intValue());      // -1
		    System.out.println(aSmallNum.intValueExact());                // ArithmeticException
		} catch (Exception e) {
		    System.out.println("Example 3.4: threw " + e.getClass());
        }
		try {
		    String nullStr = null;
		    BigDecimal aSmallNum = new BigDecimal(nullStr);
		    System.out.println(aSmallNum);                                // NullPointerException
        } catch (Exception e) {
            System.out.println("Example 3.5: threw " + e.getClass());
        }
		try {
		    BigDecimal a = new BigDecimal(123456789.123456789);
		    System.out.println("Example 4.1: print " + new BigDecimal(123456789.123456789));    // 123456789.12345679104328155517578125 - UNEXPECTED RESULT
		    System.out.println("Example 4.2: print " + a.setScale(3, RoundingMode.DOWN));
		    System.out.println(a);
		    // note a is immutable so you need to reassign a for changes to stick
		    a = a.movePointLeft(2);                                       // 1234567.8912345679104328155517578125 - UNEXPECTED RESULT
		    System.out.println(a);
		    System.out.println(new BigDecimal("0.02300000000000000").stripTrailingZeros());     // 0.023
		    System.out.println(new BigDecimal("00000"));                      // 0
		    System.out.println(new BigDecimal("00000").stripTrailingZeros()); // 0
            System.out.println(a.max(new BigDecimal("0.024")));                                 // 0.024 is returned, because 0.023 is smaller than 0.024
            System.out.println(a.negate());                                                     // -.0.024, added "-" in front of number
            System.out.println(new BigDecimal("0.12345678901234567890").doubleValue());         // 0.12345678901234568 - UNEXPECTED RESULT, loss of precision
            
		} catch (Exception e) {
		    System.out.println("Example 3.6: threw " + e.getClass());     // ArithmeticException
		}
		
		// Example 4 - 
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
