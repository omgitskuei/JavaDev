package util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckSubstring {
	// Local Fields
	
	// Constructors
	public CheckSubstring() {
		System.out.println("BEGIN: util.CheckSubstring");
	}
	
	public static void main(String args[]) {
		CheckSubstring util = new CheckSubstring();
		System.out.println(util.countCapLetters("WqSsd3")); // only count++ for CapLetters
	}
	
	
	// Methods
	public int countSpecialCharacters(String checkThisString) {
		System.out.println("BEGIN: util.CheckSubstring.countSpecialCharacters");
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
		System.out.println("FINISH: util.CheckSubstring.countSpecialCharacters");
		return counter;
	}

	public int countSpaces(String checkThisString) {
		System.out.println("BEGIN: util.CheckSubstring.countSpaces");
		// Define counter
		int counter = 0;
		// For each letter of String checkThisString, ...
		for (int index = 0; index < checkThisString.length(); index++) {
			// ... Match the letter to a space
			String letter = checkThisString.substring(index, index + 1);
			// If a space is found, add 1 to counter
			if (letter.equals(" ")) {
				counter++;
			}
		}
		System.out.println("FINISH: util.CheckSubstring.countSpaces");
		return counter;
	}
	
	public int countCapLetters(String checkThisString) {
		System.out.println("BEGIN: util.CheckSubstring.countCapLetters");
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
		System.out.println("FINISH: util.CheckSubstring.countCapLetters");
		return counter;
	}

	public int countLowLetters(String checkThisString) {
		System.out.println("BEGIN: util.CheckSubstring.countLowLetters");
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
		System.out.println("FINISH: util.CheckSubstring.countLowLetters");
		return counter;
	}

	public int countNums(String checkThisString) {
		System.out.println("BEGIN: util.CheckSubstring.countNums");
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
		System.out.println("FINISH: util.CheckSubstring.countNums");
		return counter;
	}
	
	// Note The passed String only contains ONE dot in it.
	public ArrayList<String> delimitAtDot(String delimitThisString) {
		System.out.println("BEGIN: util.CheckSubstring.delimitAtDot");
		// Split passed string along dot
		int dotIndex = delimitThisString.indexOf(".");
		String beforeDot = delimitThisString.substring(0, dotIndex);
		String afterDot = delimitThisString.substring(dotIndex+1, delimitThisString.length());
		// Declare new ArrayList for return
		ArrayList<String> delimitedString = new ArrayList<String>();
		// Add two halves of passed string to ArrayList for return
		delimitedString.add(beforeDot);
		delimitedString.add(afterDot);
		System.out.println("FINISH: util.CheckSubstring.delimitAtDot");
		return delimitedString;
	}
}
