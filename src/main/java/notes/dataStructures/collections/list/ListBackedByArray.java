package main.java.notes.dataStructures.collections.list;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class ListBackedByArray {

	public static void main(String[] args) {
		// Converting Array to ArrayList using these ways creates a List backed by the original
		// Array. This has 2 implications; List size can't be changed, 
		List<String> listFromArray1 = Arrays.asList(new String[] { "test1", "test2", "test3" });
		List<String> listFromArray2 = Arrays.asList("foo", "bar");
		
		// NOTE: The import shows that these two ArrayList are actually
		// java.util.Arrays$ArrayList, NOT java.util.ArrayList - see 
		// how java.util.ArrayList is an "unused" import".
		System.out.println(listFromArray1.getClass());
		System.out.println(listFromArray2.getClass());

		System.out.println(listFromArray1);
		System.out.println(listFromArray2);
		
		System.out.println(listFromArray1.contains("test1"));
		System.out.println(listFromArray2.contains("foo"));

		// 
		try {
			listFromArray1.add("test4");
		} catch (Exception e1) {
			System.out.println(e1.getClass());
		}

		
		try {
			listFromArray2.add("kas");
		} catch (Exception e) {
			System.out.println(e.getClass());
		}
	}

}
