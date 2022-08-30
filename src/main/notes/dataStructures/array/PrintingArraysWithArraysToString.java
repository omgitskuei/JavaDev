package main.notes.dataStructures.array;

import java.util.Arrays;

public class PrintingArraysWithArraysToString {

	public static void main(String[] args) {

		/*
		 * Array of Strings
		 */
		String[] array = new String[] {"John", "Mary", "Bob"};
		System.out.println(Arrays.toString(array));
		// Output: [John, Mary, Bob]
		
		/*
		 * Nested Array of Strings
		 */
		String[][] deepArray = new String[][] {{"John", "Mary"}, {"Alice", "Bob"}};
		System.out.println(Arrays.toString(deepArray));
		// Output: [[Ljava.lang.String;@106d69c, [Ljava.lang.String;@52e922]
		System.out.println(Arrays.deepToString(deepArray));
		// Output: [[John, Mary], [Alice, Bob]]
		
		/*
		 * Array of Doubles
		 */
		double[] doubleArray = { 7.0, 9.0, 5.0, 1.0, 3.0 };
		System.out.println(Arrays.toString(doubleArray));
		// Output: [7.0, 9.0, 5.0, 1.0, 3.0 ]
		
		/*
		 * Array of ints
		 */
		int[] intArray = { 7, 9, 5, 1, 3 };
		System.out.println(Arrays.toString(intArray));
		// Output:[7, 9, 5, 1, 3 ]
	}
}
