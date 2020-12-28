package main.java.notes.dataStructures.collections.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * The main difference between java.util.Collections.emptyList() and new List is immutability.
 * @param args
 */
public class EmptyListAndNewList {
	
	/**
	 * Fields
	 */
	
	private List<String> newList = new ArrayList<>();
	// retuns a list that can NOT be modified
	private List<String> emptyList = Collections.emptyList();
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		
		EmptyListAndNewList a = new EmptyListAndNewList();
		
		// Functionally, at first glance, the two are identical
		System.out.println(a.newList.size());			// prints 0
		System.out.println(a.newList.isEmpty());		// prints true
		
		System.out.println(a.emptyList.size());			// prints 0
		System.out.println(a.emptyList.isEmpty());		// prints true
		
		// But, through immutability, emptyList is created such that it'll 
		// always be empty.
		a.newList.add("test1");
		System.out.println(a.newList.get(0));			// prints "test1"
		
		try {
			a.emptyList.add("test2");					// throws UnsupportedOperationException
			System.out.println(a.emptyList.get(0));		// unreachable code
		} catch (Exception e) {
			System.out.println(e.getClass());
		}
	}

}
