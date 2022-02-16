package main.notes.dataStructures.collections.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortingCharsString {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("b");
		list.add("a");
		list.add("c");

		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				System.out.println(o1);
				System.out.println(o2);
				System.out.println(o1.compareToIgnoreCase(o2));
				System.out.println(list);
				return o1.compareToIgnoreCase(o2);
				/*
				 * a
				 * b
				 * -1
				 * [b, a, c]
				 * 
				 * c
				 * a
				 * 2
				 * [b, a, c]
				 * 
				 * c
				 * b
				 * 1
				 * [a, b, c]
				 */
			}
		});

		System.out.println(list);
		// [a, b, c]
	}

}
