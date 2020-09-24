package exercises;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListExtended {

	/**
	 * 
	 */
	public ArrayList<String> convertStringArrayAsArrayList(String array[]) {
		ArrayList<String> arrList = new ArrayList<String>();
		arrList.addAll(Arrays.asList(array));
		return arrList;
		
	}
	
	public static void main(String[] args) {
		String a[] = {"as", "sd", "df", "fg", "gh", "hj", "jk"};
		ArrayListExtended ab = new ArrayListExtended();
		
		ArrayList<String> result = ab.convertStringArrayAsArrayList(a);
		
		System.out.println(result);
	}

}
