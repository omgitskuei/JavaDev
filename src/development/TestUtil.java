package development;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Set;

import util.CheckSubstring;
import util.GetRuntimeInput;
import util.Sort;

public class TestUtil {

	public static void main(String[] args) {
		// Get Input
		GetRuntimeInput util = new GetRuntimeInput();
		String input = util.getConsoleInputBR("Input String:");
		
		// Create new instance of Whatever you're testing's class
		CheckSubstring u = new CheckSubstring();
		
		int count = u.countNums(input);
		
		System.out.println(count);
		
		// Get the number of decimals used for INPUT ISO 4217 Currency code (TWD for taiwan)
		//Currency t = Currency.getInstance("USD");
		//int decimals = t.getDefaultFractionDigits();
		
		//System.out.println(decimals);
		
	}

}
