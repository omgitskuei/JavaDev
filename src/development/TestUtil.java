package development;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import util.CheckSubstring;
import util.GetRuntimeInput;

public class TestUtil {

	public static void main(String[] args) {
		try {
			GetRuntimeInput util = new GetRuntimeInput();
			// String input = util.getInputOnce("Input:");
			
			boolean s = false;
			while (s!=true) {
				util.getInput("Input:");
				util.
				s = validatePwd(input);
			}
			
			
			System.out.println(s);
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean validatePwd(String pwd) {
		System.out.println("Begin: validatePwd(String)");
		try {
			// A valid password must have >=8 characters
			if (pwd.length() >= 8) {
				// Partition pwd into substrings of each character
				ArrayList<String> pwdEachChar = new ArrayList<String>();
				for (int index = 0; index < pwd.length(); index++) {
					pwdEachChar.add(pwd.substring(index, index + 1));
				}
				// A valid password must NOT contain spaces
				if (!(pwdEachChar.contains(" "))) {
					// A valid password must contain >=1 special characters
					// A valid password must contain >=1 Capitalized letters
					// A valid password must contain >=1 Lower-case letters
					CheckSubstring util = new CheckSubstring();
					if (util.countCapLetters(pwd) >= 1 && util.countLowLetters(pwd) >= 1
							&& util.countSpecialCharacters(pwd) >= 1) {
						// A valid password must contain >=1 numbers
						if (util.countNums(pwd) >= 1) {
							System.out.println("Valid Input: Pwd");
							return true;
						} else {
							System.out.println("Invalid Input: Pwd must contain at least one number");
						}
					} else {
						System.out.println(
								"Invalid Input: Pwd must contain at least one Special character, Capital letter, and Lower-case letter");
					}
				} else {
					System.out.println("Invalid Input: Pwd must not contain spaces");
				}
			} else {
				System.out.println("Invalid Input: Pwd must be longer than 8 characters");
			}
			System.out.println("Finish: validatePwd(String)");
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
