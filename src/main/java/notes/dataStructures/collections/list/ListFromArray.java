package main.java.notes.dataStructures.collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListFromArray {
	
	/**
	 * Fields
	 */
	private List<String> listFromArray = Arrays.asList(
			new String[] {"test1", "test2", "test3"});
	
	private List<String> newList = new ArrayList<>();
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		ListFromArray a = new ListFromArray();
		System.out.println(a.listFromArray);
		
		System.out.println(a.newList);
	}

}
