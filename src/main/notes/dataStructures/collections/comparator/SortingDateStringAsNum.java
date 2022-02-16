package main.notes.dataStructures.collections.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortingDateStringAsNum {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("2000-01-01");
		list.add("2010-10-04");
		list.add("2020-11-04");
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				o1 = o1.replace("-", ""); // Changes eg. 2001-01-13 into 20010113
				System.out.println(o1);

				o2 = o2.replace("-", "");
				System.out.println(o2);

				System.out.println(Integer.parseInt(o1) < Integer.parseInt(o2) ? -1 : 1);
				System.out.println(o1.compareToIgnoreCase(o2));
				
				try {
					return Integer.parseInt(o1) < Integer.parseInt(o2) ? -1 : 1;
				} catch (NumberFormatException e) {
					return o1.compareToIgnoreCase(o2);
				}
				/*
				 * 20101004
				 * 20000101
				 * 1
				 * 1
				 * 
				 * 20201104
				 * 20101004
				 * 1
				 * 1
				 */
			}
		});
		System.out.println(list);
		// [2000-01-01, 2010-10-04, 2020-11-04]
	}

}
