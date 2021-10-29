package main.notes.dataStructures.collections.list;

import java.util.ArrayList;
import java.util.Arrays;

public class CopyingEntireArrayToArraylist {

	public static void main(String[] args) {
		String a[] = {"as", "sd", "df", "fg", "gh", "hj", "jk"};
		CopyingEntireArrayToArraylist ab = new CopyingEntireArrayToArraylist();
		
		ArrayList<String> result = ab.arrToArrayList(a);
		
		System.out.println(result);
	}
	
	/**
	 * A helper method that converts String[] stringArray to ArrayList<String>
	 * @param String array[]
	 * @return ArrayList<String> arrList
	 */
	private ArrayList<String> arrToArrayList(String array[]) {
		ArrayList<String> arrList = new ArrayList<String>();
		arrList.addAll(Arrays.asList(array));
		return arrList;
	}
}
