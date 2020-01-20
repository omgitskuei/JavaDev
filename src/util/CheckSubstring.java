package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckSubstring {

	public int countSpecialCharacters(String checkThisString) {
		// Define counter
		int counter = 0;
		// Define "Special characters" in regex pattern
		Pattern capitalLetters = Pattern.compile("[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]");
		Matcher m;
		// For each letter of String checkThisString, ...
		for (int index = 0; index < checkThisString.length(); index++) {
			// ... Match the letter to the regex pattern
			m = capitalLetters.matcher(checkThisString.substring(index, index + 1));
			// If the letter is found within the regex pattern, add 1 to counter
			if (m.matches()) {
				counter++;
			}
		}
		return counter;
	}

	public int countCapLetters(String checkThisString) {
		// Define counter
		int counter = 0;
		// Define "Capital letters" in regex pattern
		Pattern capitalLetters = Pattern.compile("[A-Z]");
		Matcher m;
		// For each letter of String checkThisString, ...
		for (int index = 0; index < checkThisString.length(); index++) {
			// ... Match the letter to the regex pattern
			m = capitalLetters.matcher(checkThisString.substring(index, index + 1));
			// If the letter is found within the regex pattern, add 1 to counter
			if (m.matches()) {
				counter++;
			}
		}
		return counter;
	}

	public int countLowLetters(String checkThisString) {
		// Define counter
		int counter = 0;
		// Define "Lower-case letters" in regex pattern
		Pattern capitalLetters = Pattern.compile("[a-z]");
		Matcher m;
		// For each letter of String checkThisString, ...
		for (int index = 0; index < checkThisString.length(); index++) {
			// ... Match the letter to the regex pattern
			m = capitalLetters.matcher(checkThisString.substring(index, index + 1));
			// If the letter is found within the regex pattern, add 1 to counter
			if (m.matches()) {
				counter++;
			}
		}
		return counter;
	}

	public int countNums(String checkThisString) {
		// Define counter
		int counter = 0;
		// Define "Capital letters" in regex pattern
		Pattern capitalLetters = Pattern.compile("[0-9]");
		Matcher m;
		// For each letter of String checkThisString, ...
		for (int index = 0; index < checkThisString.length(); index++) {
			// ... Match the letter to the regex pattern
			m = capitalLetters.matcher(checkThisString.substring(index, index + 1));
			// If the letter is found within the regex pattern, add 1 to counter
			if (m.matches()) {
				counter++;
			}
		}
		return counter;
	}
}
