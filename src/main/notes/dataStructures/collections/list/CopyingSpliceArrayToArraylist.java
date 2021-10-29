package main.notes.dataStructures.collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CopyingSpliceArrayToArraylist {

	public static void main(String[] args) {
		// Variables
		String[] arr = {"dog", "cat", "pig", "bird", "hare"};
		ArrayList<String> arrList = new ArrayList<String>();
		
		// Add arr splice into arrList
		Collections.addAll(
				arrList, 
				Arrays.copyOfRange(arr, 2, arr.length)  // copyOfRange(array, startInd, endInd)
				                                        // startInd begins at 0
		);
		
		System.out.println(arrList);
		// [pig, bird, hare]
	}

}
