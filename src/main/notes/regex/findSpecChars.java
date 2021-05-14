package main.notes.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class findSpecChars {
	public static void main(String[] args) {
		Pattern p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher("10.95.40.21");
		
//		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
//		Matcher m = p.matcher("I am a string");
		
		
		boolean b = m.find();

		if (b) {
		   System.out.println("There is a special character in my string");
		} else {
			System.out.println("No spec chars");
		}
	}
}
