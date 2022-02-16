package main.notes.dataStructures.collections.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortingDateStrings {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("2022-01-01");
		list.add("2021-10-04");
		list.add("2021-11-04");
		list.add("2021-10-05");

		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				System.out.println(o1);
				System.out.println(o2);
				System.out.println(o1.compareToIgnoreCase(o2));
				System.out.println(list);
				return o1.compareToIgnoreCase(o2);
				/*
				 * 2021-10-04
				 * 2022-01-01
				 * -1
				 * [2022-01-01, 2021-10-04, 2021-11-04, 2021-10-05]
				 * 
				 * 2021-11-04
				 * 2021-10-04
				 * 1
				 * [2022-01-01, 2021-10-04, 2021-11-04, 2021-10-05]
				 * 
				 * 2021-11-04
				 * 2022-01-01
				 * -1
				 * [2021-10-04, 2022-01-01, 2021-11-04, 2021-10-05]
				 * 2021-11-04
				 * 2021-10-04
				 * 1
				 * [2021-10-04, 2022-01-01, 2021-11-04, 2021-10-05]
				 * 2021-10-05
				 * 2021-11-04
				 * -1
				 * [2021-10-04, 2021-11-04, 2022-01-01, 2021-10-05]
				 * 2021-10-05
				 * 2021-10-04
				 * 1
				 * [2021-10-04, 2021-11-04, 2022-01-01, 2021-10-05]
				 */
			}
		});

		System.out.println(list);
		// [2021-10-04, 2021-10-05, 2021-11-04, 2022-01-01]
	}

}
