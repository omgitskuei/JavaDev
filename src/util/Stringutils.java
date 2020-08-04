package util;

import java.util.HashMap;
import java.util.Map;

public class Stringutils {

	public static String nullEmptyToTrimString(String checkThis) {
		return checkThis == null ? "" : checkThis.trim(); // trim() removes leading AND trailing spaces
	}

	public static boolean isNullEmptyOrAllSpaces(String checkThis) {
		return checkThis == null || "".equals(checkThis.trim());
	}
	
	public static String returnHashMapAsJson(HashMap<String, String> aMap) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\r\n");
		  for (HashMap.Entry<String,String> entry : aMap.entrySet()) {
			sb.append("  " + entry.getKey() + "=" + entry.getValue() + "\r\n");
		}
		sb.append("}");
		return sb.toString();
	}

	public static void main(String[] args) {
		// check null
		System.out.println("[" + nullEmptyToTrimString(null) + "]");
		// check spaces
		System.out.println("[" + nullEmptyToTrimString("       ") + "]");
		// check trailing spaces
		System.out.println("[" + nullEmptyToTrimString("A   ") + "]");
		// check opening spaces
		System.out.println("[" + nullEmptyToTrimString("    A") + "]");
		// check trailing and opening spaces
		System.out.println("[" + nullEmptyToTrimString("    A   ") + "]");

		System.out.println();
		
		// check null
		System.out.println("[" + isNullEmptyOrAllSpaces(null) + "]");
		// check spaces
		System.out.println("[" + isNullEmptyOrAllSpaces("       ") + "]");
		// check trailing spaces
		System.out.println("[" + isNullEmptyOrAllSpaces("A   ") + "]");
		// check opening spaces
		System.out.println("[" + isNullEmptyOrAllSpaces("    A") + "]");
		// check trailing and opening spaces
		System.out.println("[" + isNullEmptyOrAllSpaces("    A   ") + "]");
		
		System.out.println();
		
		HashMap<String, String> s = new HashMap<>();
		s.put("monkey", "tail");
		s.put("peacock", "feathers");
		System.out.println(returnHashMapAsJson(s));
		
	}

}
