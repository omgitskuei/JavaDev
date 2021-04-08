package main.notes.regex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class regexFilterHTMLTags {

	public static void main(String[] args) {
		ArrayList<String> testCases = new ArrayList<String>();
		testCases.add("<a>");
		testCases.add("</a>");
		
		testCases.add("<A>");
		testCases.add("</A>");
		
		testCases.add("<1>");
		testCases.add("</1>");
		
//		testCases.add("<shift>");
//		testCases.add("</shift>");
//		
//		testCases.add("<ctrl>");
//		testCases.add("</ctrl>");
//		
//		testCases.add("<alt>");
//		testCases.add("</alt>");
//		
//		testCases.add("<space>");
//		testCases.add("</space>");
//		
//		testCases.add("<tab>");
//		testCases.add("</tab>");
//		
//		testCases.add("<enter>");
//		testCases.add("</enter>");
//		
//		testCases.add("<backspace>");
//		testCases.add("</backspace>");
		
		Pattern p = Pattern.compile(
				  "("
				  + "<"
				    + "("
				      + "[a-z]{1}|"
				      + "[0-9]{1}|"
				      + "[A-Z]{1}|"
				    + ")"
				  + ">"
				+ ")"
				+ "|"
				+ "("
				  + "</"
				    + "("
				      + "[a-z]{1}|"
				      + "[0-9]{1}|"
				      + "[A-Z]{1}"
				    + ")"
				  + ">"
				+ ")");//. represents single character  
		for(String test:testCases) {
			Matcher m = p.matcher(test);  
			boolean b = m.matches();
			System.out.println(b);
		}
	}

}
