package util;

import java.util.HashMap;

public class Stringutils_WIP {

	
	
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
		
		
		HashMap<String, String> s = new HashMap<>();
		s.put("monkey", "tail");
		s.put("peacock", "feathers");
		System.out.println(returnHashMapAsJson(s));
		
	}

}
