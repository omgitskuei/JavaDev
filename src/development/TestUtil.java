package development;

import java.util.ArrayList;

import util.CheckSubstring;
import util.GetUserInput;

public class TestUtil {

	public static void main(String[] args) {
		// Get Input
		GetUserInput g = new GetUserInput();
		System.out.println("Input string:");
		String input = g.getUserInputBR();
		
		// Create new instance of Whatever you're testing's class
		CheckSubstring c = new CheckSubstring();
		
		// Test logic
		ArrayList<String> checkThisArrayList = new ArrayList<String>();
		for (int index = 0; index < input.length(); index++) {
			checkThisArrayList.add(input.substring(index, index+1));
			
		}
		
		int s = c.countNums(input);
		System.out.println(s);
	}

}
