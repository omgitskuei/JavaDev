package util;

public class Stringutils {

	public static String s(String checkThis) {
		
		String string = checkThis == null ? "" : checkThis.trim();		// trim() removes leading AND trailing spaces
		return string;
		// check trailing spaces
		
	}
	
	public static void main(String[] args) {
		// check null
		System.out.println("["+s(null)+"]");
		// check spaces
		System.out.println("["+s("       ")+"]");
		// check trailing spaces
		System.out.println("["+s("A   ")+"]");
		// check opening spaces
		System.out.println("["+s("    A")+"]");
		// check trailing and opening spaces
		System.out.println("["+s("    A   ")+"]");
	}

}
