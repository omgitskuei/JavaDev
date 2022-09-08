package main.notes.dataStructures.array;

import java.util.Arrays;

public class PrintingArraysWithArraysAsList {

	public static void main(String[] args) {
		Object[] objs = new Object[] {"12", "string", 45, 3.4};
		System.out.println(Arrays.asList(objs));
		// Output: [12, string, 45, 3.4]
	}

}
