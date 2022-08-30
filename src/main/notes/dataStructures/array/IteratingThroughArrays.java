package main.notes.dataStructures.array;

import java.util.Arrays;

public class IteratingThroughArrays {

	public static void main(String[] args) {
		// ForEach loop only good for read-only looping with arrays
		// You could add a counter to keep track of the index within a ForEach loop,
		// but you might as well use a For loop
		
		int[] arrayInt = { 10, 20, 30, 40 };
		
		// ForEach
		for (int eachInt : arrayInt) {
			eachInt = eachInt + 1;
		}
		
		System.out.println(Arrays.toString(arrayInt));
		// ^ Ineffective, new data was NOT written into the array

		
		// For
		for(int i = 0; i < arrayInt.length; i++) {
			int eachInt = arrayInt[i];
			arrayInt[i] = eachInt + 1;
		}
		
		System.out.println(Arrays.toString(arrayInt));
		// ^ Note, the array can't be written without knowing the index (i) of the item you want to update
	}

}
