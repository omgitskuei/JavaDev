package main.notes.dataStructures.collections.iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class iterateArrayList {

	private static ArrayList<String> listStr = new ArrayList<String>();

	public static void main(String[] args) {
		listStr.add("str1");
		listStr.add("str2");
		listStr.add("str3");

		// Must declare Iterator prior to looping
		Iterator<String> itr = listStr.iterator();

		while (itr.hasNext()) {
			String eachElement = itr.next();
			System.out.println(eachElement);			// Prints 3 times
		}
		
		System.out.println();
		
		while (itr.hasNext()) {							// This DOES NOT RUN, itr already iterated to the end
			String eachElement = itr.next();
			System.out.println(eachElement);
			break;										// BREAK HERE - This always executes Either ZERO or ONE time;
		}
		
//		while (listStr.iterator().hasNext()) {			// This is an infinite while loop
//			System.out.println("yay");
//		}
	}

}
