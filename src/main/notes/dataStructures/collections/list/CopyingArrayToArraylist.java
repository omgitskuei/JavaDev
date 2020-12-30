package main.notes.dataStructures.collections.list;

import java.util.ArrayList;
import java.util.Arrays;

public class CopyingArrayToArraylist {

	public static void main(String[] args) {
		String a[] = {"as", "sd", "df", "fg", "gh", "hj", "jk"};
		CopyingArrayToArraylist ab = new CopyingArrayToArraylist();
		
		ArrayList<String> result = ab.arrStrToArrayList(a);
		
		System.out.println(result);
	}
	
	/**
	 * A helper method that converts String[] stringArray to ArrayList<String>
	 * @param String array[]
	 * @return ArrayList<String> arrList
	 */
	private ArrayList<String> arrStrToArrayList(String array[]) {
		ArrayList<String> arrList = new ArrayList<String>();
		arrList.addAll(Arrays.asList(array));
		return arrList;
	}
}
