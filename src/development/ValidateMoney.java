package development;

import java.util.ArrayList;

import util.CheckSubstring;
import util.GetRuntimeInput;

public class ValidateMoney {

	public static void main(String[] args) {
		GetRuntimeInput util = new GetRuntimeInput();
		String input = util.getConsoleInputBR("Enter Money Amount:");
		
		CheckSubstring util1 = new CheckSubstring();
		
		ArrayList<String> delimitedThisMoney = util1.delimitAtDot(input);
		
		System.out.println("second half = "+delimitedThisMoney.get(1));
		System.out.println("second half #digits =" +delimitedThisMoney.get(1).length());
		
		if (delimitedThisMoney.get(1).length() <= 2) {
			System.out.println("good");
		} else {
			System.out.println("bad");
		}
	}
}
