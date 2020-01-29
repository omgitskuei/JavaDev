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
		// String input = util.getConsoleInputBR("Input num:");

		// Create new instance of Whatever you're testing's class
		// CheckSubstring u = new CheckSubstring();
		// GetDateOrTime u = new GetDateOrTime();
		Sort u = new Sort();
		ArrayList<Integer> sortThis = new ArrayList<Integer>();

		sortThis.add(3);
		sortThis.add(2);
		sortThis.add(6);
		sortThis.add(2);
		sortThis.add(1);
		sortThis.add(1);
		sortThis.add(1);
		sortThis.add(1);
		sortThis.add(1);

		u.bubbleSort(sortThis, true);

		System.out.println(sortThis);

		// Get the number of decimals used for INPUT ISO 4217 Currency code (TWD for
		// taiwan)
		// Currency t = Currency.getInstance("USD");
		// int decimals = t.getDefaultFractionDigits();

		// System.out.println(decimals);

	}

}
