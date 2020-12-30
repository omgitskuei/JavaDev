package main.notes.dataStructures.collections.list;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class ListBackedByArray {
	
	// See CopyingArrayToArrayList class for how to make an ArrayList with values of an array.
	
	public static void main(String[] args) {
		// Converting Array to ArrayList using these ways creates a List backed by the original
		// Array. This has 2 implications; List size can't be changed, mutations are also done
		// to the original array[]
		
		String[] anArray = new String[] { "test1", "test2", "test3" };
		List<String> listFromArray1 = Arrays.asList(anArray);
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

		// Adding new elements will throw UnsupportedOperationException
		// because !arrays! (not ArrayList) can't dynamically increase in size/length
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
		
		// Removing an element will throw UnsupportedOperationException
		// because that technically counts as changing its size/length
		try {
			// Before
			System.out.println(listFromArray1);
			// After
			listFromArray1.remove(0);
			System.out.println(listFromArray1);
		} catch (Exception e) {
			System.out.println(e.getClass());
		}
		
		// Replacing an element is OK though, since size/length is unaffected
		listFromArray1.set(0, "newString");
		
		// ArrayList listFromArray1 is backed by String[] anArray
		// This means changes occur to original array[] as well!
		System.out.println(listFromArray1);
		System.out.print("[");
		for (int i = 0; i < anArray.length; i++) {
			if(i!=0) {
				System.out.print(", ");
			}
			System.out.print(anArray[i]);
		}
		System.out.println("]");
	}

}
