package main.exercises.string;

import java.util.HashMap;

public class ContainsAndSubstring {

	public static void main(String[] args) {
		// Try to extract the POLICY_NO "12345",
		// PROD_ID "CY"
		// MSG "未成年無法開手術，健保法第107條，不給付"
		// And convert the data into a map format
		String aString = "保單號碼[12345]險別[CY]未成年無法開手術，健保法第107條，不給付";

		HashMap<String, String> aMap = new HashMap<String, String>();

		// Get POLICY_NO, which always follows "保單號碼" inside the next "[]"
		String POLICY_NO = (aString.contains("保單號碼"))
				? aString.substring(aString.indexOf("保單號碼") + 5, aString.indexOf("]", aString.indexOf("保單號碼") + 5))
				: "";

		// Get PROD_ID, which always follows "保單號碼" inside the next "[]"
		String PROD_ID = (aString.contains("險別"))
				? aString.substring(aString.indexOf("險別") + 3, aString.indexOf("]", aString.indexOf("險別") + 3))
				: "";

		// Note, the order of POLICY_NO and PROD_ID in the String can be reversed and
		// it'll still work
		// String aString = "險別[CY]保單號碼[12345]未成年無法開手術，健保法第107條，不給付";

		// Get MSG, which always appear at the end of the String after the last "]"
		StringBuilder sb = new StringBuilder(aString);
		String temp = sb.reverse().toString();
		int lengthOfMessage = temp.indexOf("]");
		String MSG = aString.substring(aString.length() - lengthOfMessage);

		aMap.put("POLICY_NO", POLICY_NO);
		aMap.put("PROD_ID", PROD_ID);
		aMap.put("MSG", MSG);

		System.out.println(aMap);
	}

}
