package main.notes.annotations;

import java.util.ArrayList;
import java.util.List;

public class Deprecation {

	public static void main(String[] args) {
		String yyyyddStr = "20201";
		
		List usingOldMethod = getYYYYDD(yyyyddStr);
		System.out.println(usingOldMethod);
		
		List usingNewMethod = getYYYYDD(yyyyddStr, true);
		System.out.println(usingNewMethod);
	}
	
	
	
	// Helper methods
	
	/**
	 * Newer method, notice it placed before the older method
	 * @param yyyyddStr
	 * @param fillZero
	 * @return ArrayList<String>
	 */
	private static List<String> getYYYYDD(String yyyyddStr, boolean fillZero) {
		List<String> arr = new ArrayList<String>();
		arr.add(yyyyddStr.substring(0, 4));
		if (fillZero) {
			if (yyyyddStr.substring(4, yyyyddStr.length()).length() == 1) {
				arr.add("0"+yyyyddStr.substring(4, yyyyddStr.length()));
			}
		} else {
			arr.add(yyyyddStr.substring(4, yyyyddStr.length()));
		}
		return arr;
	}
	
	/**
	 * Older method, deprecated, IBM's eclipse knock-off called RAD will
	 * throw an "is not applicable for the arguments (..., etc)" error 
	 * on line 11 by matching the first method with matching name (the newer one) 
	 * to BOTH method calls on line 11 and line 14.
	 * 
	 * But Eclipse 2020-12 won't have this problem.
	 * 
	 * @param yyyyddStr
	 * @return ArrayList<String>
	 */
	@Deprecated
	private static List<String> getYYYYDD(String yyyyddStr) {
		List<String> arr = new ArrayList<String>();
		arr.add(yyyyddStr.substring(0, 4));
		arr.add(yyyyddStr.substring(4, yyyyddStr.length()));
		return arr;
	}

}
