package util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckSubstring {
	// Local Fields

	// Constructors
	public CheckSubstring() {
		System.out.println("BEGIN: util.CheckSubstring()");
	}

	public static void main(String args[]) {
		CheckSubstring util = new CheckSubstring();
		
		ArrayList<String> test = new ArrayList<String>();
		test.add("ProductName:蔥");
		test.add("ProductID:1");
		test.add("ProductType:Vegetable");
		test.add("ProductStock:10");
		System.out.println("Example #1 - Removing a char from all elements in an ArrayList, returns ArrayList: \r\n"+test);
		util.removeAnyChar(test, "\"");
		System.out.println(test);
		test = util.delimitAtAnyChar(test, ":");
		System.out.println(test);
		test = util.removeAnyChar(test, ":");
		System.out.println("(ArrayList) "+test);
		System.out.println("--------------");
		
		String test1 = "(String) {productID:1, productName:Onion, productType:Vegetable, productStock:20}";
		System.out.println("Example #2 - Removing a char from a String, Returns ArrayList: \r\n"+test1);
		test1 = util.removeAnyChar(test1, "{");
		System.out.println(test1);
		test1 = util.removeAnyChar(test1, "}");
		System.out.println(test1);
		ArrayList<String> test2 = util.delimitAtAnyChar(test1, ":");
		System.out.println("(ArrayList) "+test2);
		test2 = util.removeAnyChar(test2, ":");
		System.out.println("(ArrayList) "+test2);
		System.out.println("--------------");
		
		String test3 = "(String) TXhois 1a$$pp1le XisoX ve$ry ta$st1y";
		System.out.println("Example #3 - Removing an ArrayList of chars from a String, returns String: \r\n"+test3);
		ArrayList<String> listOfJunk = new ArrayList<String>();
		listOfJunk.add("o");
		listOfJunk.add("1");
		listOfJunk.add("$");
		listOfJunk.add("X");
		test3 = util.removeAnyChars(test3, listOfJunk);
		System.out.println(test3);
		System.out.println("--------------");
		
		ArrayList<String> test4 =  new ArrayList<String>();
		test4.add("(ArrayList) TXhois 1a$$pp1le XisoX ve$ry ta$st1y. ");
		test4.add("(ArrayList) O1MXG tXh1Xi1s $i1s 1$ta1s1$t1y.");
		System.out.println("Example #4 - Removing an ArrayList of chars from an ArrayList, returns String: \r\n"+test4);
		String test4Result = util.removeAnyChars(test4, listOfJunk);
		System.out.println(test4Result);
		System.out.println("--------------");
	}

	// Methods
	public ArrayList<String> delimitAtAnyChar(String delimitThisString, String delimiterChar) {
		ArrayList<Integer> delimiterIndex = new ArrayList<Integer>();
		// Add first index
		delimiterIndex.add(0);
		// Add middle indices
		for (int index = 0; index < delimitThisString.length() - 1; index++) {
			if (delimitThisString.substring(index, index + 1).equals(delimiterChar)) {
				delimiterIndex.add(index);
			}
		}
		// Add last index
		delimiterIndex.add(delimitThisString.length());
		// Slice
		ArrayList<String> slicedStrings = new ArrayList<String>();
		for (int index = 0; index < delimiterIndex.size() - 1; index++) {
			String slice = delimitThisString.substring(delimiterIndex.get(index), delimiterIndex.get(index + 1));
			slicedStrings.add(slice);
		}
		return slicedStrings;
	}
	
	public ArrayList<String> delimitAtAnyChar(ArrayList<String> delimitThisArray, String delimiterChar) {
		// Convert ArrayList to a single String
		String totalString = "";
		for (int index=0;index<delimitThisArray.size();index++) {
			totalString = totalString + "|" + delimitThisArray.get(index);
		}
		// Delimit String at both "|" and delimiterChar
		ArrayList<Integer> sliceIndex = new ArrayList<Integer>();
		// Add middle indices
		for (int index = 0; index < totalString.length(); index++) {
			if (totalString.substring(index, index+1).equals(delimiterChar) || totalString.substring(index, index+1).equals("|")) {
				sliceIndex.add(index);
			}
		}
		// Add last index
		sliceIndex.add(totalString.length());
		// Slice
		ArrayList<String> slicedStrings = new ArrayList<String>();
		for (int index = 0; index < sliceIndex.size() - 1; index++) {
			String slice = totalString.substring(sliceIndex.get(index), sliceIndex.get(index + 1));
			slicedStrings.add(slice);
		}
		// Remove "|" from above
		for (int index = 0; index < slicedStrings.size(); index++) {
			String result = "";
			for (int index1 = 0; index1 < slicedStrings.get(index).length(); index1++) {
				if (!slicedStrings.get(index).substring(index1, index1 + 1).equals("|")) {
					result = result + slicedStrings.get(index).substring(index1, index1 + 1);
				}
			}
			slicedStrings.set(index, result);
		}
		return slicedStrings;
	}
	
	public String removeAnyChars(String passedString, ArrayList<String> removeTheseChars) {
		String result = "";
		for (int index = 0; index < passedString.length(); index++) {
			boolean isJunk = false;
			for (int index1=0;index1<removeTheseChars.size();index1++) {
				if (passedString.substring(index, index + 1).equals(removeTheseChars.get(index1))) {
					isJunk=true;
				}
			}
			if (isJunk == false) {
				result = result + passedString.substring(index, index + 1);
			}
		}
		return result;
	}
	
	public String removeAnyChars(ArrayList<String> passedArrayList, ArrayList<String> removeTheseChars) {
		String result = "";
		for (int index = 0; index < passedArrayList.size(); index++) {
			for (int index1=0;index1<passedArrayList.get(index).length();index1++) {
				boolean isJunk = false;
				// For each character of removeTheseChars, ..
				for(int index2=0;index2<removeTheseChars.size();index2++) {
					//System.out.println("Compare these: "+passedArrayList.get(index).substring(index1, index1+1)+removeTheseChars.get(index2));
					if (passedArrayList.get(index).substring(index1, index1+1).equals(removeTheseChars.get(index2))) {
						isJunk=true;
					}
				}
				if (isJunk == false) {
					result = result+(passedArrayList.get(index).substring(index1, index1+1));
				}
			}
		}
		return result;
	}

	public String removeAnyChar(String passedString, String removeThisChar) {
		String result = "";
		for (int index = 0; index < passedString.length(); index++) {
			if (!passedString.substring(index, index + 1).equals(removeThisChar)) {
				result = result + passedString.substring(index, index + 1);
			}
		}
		return result;
	}

	public ArrayList<String> removeAnyChar(ArrayList<String> slicedStrings, String removeThisChar) {
		for (int index = 0; index < slicedStrings.size(); index++) {
			String result = "";
			for (int index1 = 0; index1 < slicedStrings.get(index).length(); index1++) {
				if (!slicedStrings.get(index).substring(index1, index1 + 1).equals(removeThisChar)) {
					result = result + slicedStrings.get(index).substring(index1, index1 + 1);
				}
			}
			slicedStrings.set(index, result);
		}
		return slicedStrings;
	}

	public int countSpecialCharacters(String checkThisString) {
		System.out.println("	BEGIN: util.CheckSubstring.countSpecialCharacters");
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
		System.out.println("	FINISH: util.CheckSubstring.countSpecialCharacters");
		return counter;
	}

	public int countSpaces(String checkThisString) {
		System.out.println("	BEGIN: util.CheckSubstring.countSpaces");
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
		System.out.println("	FINISH: util.CheckSubstring.countSpaces");
		return counter;
	}

	public int countCapLetters(String checkThisString) {
		System.out.println("	BEGIN: util.CheckSubstring.countCapLetters");
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
		System.out.println("	FINISH: util.CheckSubstring.countCapLetters");
		return counter;
	}

	public int countLowLetters(String checkThisString) {
		System.out.println("	BEGIN: util.CheckSubstring.countLowLetters");
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
		System.out.println("	FINISH: util.CheckSubstring.countLowLetters");
		return counter;
	}

	public int countNums(String checkThisString) {
		System.out.println("	BEGIN: util.CheckSubstring.countNums");
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
		System.out.println("	FINISH: util.CheckSubstring.countNums");
		return counter;
	}

	// Note The passed String only contains ONE dot in it.
	public ArrayList<String> delimitAtDot(String delimitThisString) {
		System.out.println("	BEGIN: util.CheckSubstring.delimitAtDot");
		System.out.println("		Passed string: " + delimitThisString);
		// Declare new ArrayList for return
		ArrayList<String> delimitedString = new ArrayList<String>();
		if (delimitThisString.contains(".")) {

			// Split passed string along dot
			int dotIndex = delimitThisString.indexOf(".");
			String beforeDot = delimitThisString.substring(0, dotIndex);
			String afterDot = delimitThisString.substring(dotIndex + 1, delimitThisString.length());
			System.out.println("			Added substring: " + beforeDot);
			System.out.println("			Added substring: " + afterDot);
			// Add two halves of passed string to ArrayList for return
			delimitedString.add(beforeDot);
			delimitedString.add(afterDot);

		} else {
			System.out.println("			No dot (.) in passed string ");
		}
		System.out.println("	FINISH: util.CheckSubstring.delimitAtDot");
		return delimitedString;
	}
}
