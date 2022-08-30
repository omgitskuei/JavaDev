package main.notes.regex;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexFindAnyChars {

	/*
	 * Notice the example String has a Chinese character.
	 * To pattern match while allowing anything, aka skip pattern matching the chinese character,
	 * use .{min number of any chars, maximum number of any chars}
	 */
	public static void main(String[] args) {
		
		String exampleStr = "P1P 連ASD";
		
		Pattern pattern = Pattern.compile("[A-Z0-9]{1,3} .{1,20}");
		Matcher matcher = pattern.matcher(exampleStr);
		boolean matchFound = matcher.matches();
		System.out.println(matchFound);
		// true

	}

}
