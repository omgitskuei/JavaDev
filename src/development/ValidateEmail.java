package development;
import java.util.ArrayList;

import util.GetUserInput;

public class ValidateEmail {

	public static void main(String[] args) {
		System.out.println("Enter Email:");
		GetUserInput g = new GetUserInput();
		String input = g.getUserInputBR();
		
		validateEmail(input);
	}

	private static boolean validateEmail(String email) {
		//email = "kuei.feng.tungchris@gmail.com";

		// Partition email string based on email syntax; localpart@domain
		String localpart = email.substring(0, email.indexOf("@"));
		String domain = email.substring(email.indexOf("@") + 1, email.length());
		
		// Localpart and Domain must not exceed 64 characters
		if (localpart.length() <= 64 && domain.length() <= 63) {
			// Create empty ArrayList for storing where "." appear in email String
			ArrayList<Integer> dotIndexes = new ArrayList<Integer>();
			// Create counter for "@"
			int countAt = 0;
			boolean noSpaces = true;
			// Take apart the email String
			for (int index = 0; index < email.length(); index++) {
				// Split email into substrings
				String substring = email.substring(index, index + 1);
				// Count how many "@"
				if (substring.equals("@")) {
					countAt++;
				}
				// Check if there are any spaces in the email
				if (substring.equals(" ")) {
					noSpaces = false;
				}
				// Note where "." dots appear in the email, store into ArrayList
				if (substring.equals(".")) {
					dotIndexes.add(index);
				}
			}
			System.out.println(dotIndexes);
			// A valid email must have "@" and can only have one "@"
			if (countAt == 1) {
				// A valid email must have no spaces
				if (noSpaces) {
					// A valid email cannot begin or end on "."
					if (!(dotIndexes.contains(0) || dotIndexes.contains(email.length()-1))) {
						// Domain must comply with LDH rule (letters, digits, hyphen)
						if ( !(domain.contains("!") && domain.contains("@") && domain.contains("#")
								&& domain.contains("$") && domain.contains("%") && domain.contains("^")
								&& domain.contains("&") && domain.contains("*") && domain.contains("(")
								&& domain.contains(")") && domain.contains("~") && domain.contains("+")
								&& domain.contains("=") && domain.contains("[") && domain.contains("]")
								&& domain.contains("{") && domain.contains("}") && domain.contains("|")
								&& domain.contains(";") && domain.contains("'") && domain.contains(",")
								&& domain.contains("?") && domain.contains("/") && domain.contains("\\")
								&& domain.contains("<") && domain.contains(">") && domain.contains("`"))) {
							// Domain must contain one "."
							if (domain.contains(".")) {
								boolean noConsecutiveDotsFlag = true;
								// Domain cannot have any consecutive dots
								for (int index=0;index<dotIndexes.size()-1;index++) {
									if ((dotIndexes.get(index+1)-dotIndexes.get(index))==1) {
										noConsecutiveDotsFlag = false;
									}
								}
								if(noConsecutiveDotsFlag) {
									System.out.println("Yay, good email");
									return true;
								}
							} else {
								System.out.println("Invalid Input: Email domain missing . character");
							}
						} else {
							System.out.println("Invalid Input: Email may only use letters, digits, hyphen");
						}
					} else {
						System.out.println("Invalid Input: Email may not end on a . character");
					}
				} else {
					System.out.println("Invalid Input: Email may not use any Spaces");
				}
			} else {
				System.out.println("Invalid Input: Email may only have one @ character");
			}
		} else {
			System.out.println("Invalid Input: Email localpart and domain must be under 64 characters");
		}
		return false;
	}
}
