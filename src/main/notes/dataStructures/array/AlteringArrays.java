package main.notes.dataStructures.array;

import java.util.Arrays;

public class AlteringArrays {

	public static void main(String[] args) {
		String[] cars = { "Volvo", "BMW", "Ford", "Mazda" };
		
		System.out.println(cars.toString());	// [Ljava.lang.String;@53bd815b

		cars[0] = "JEEP";
		
		System.out.println(cars.toString());	// [Ljava.lang.String;@53bd815b
		// ^ Notice the ID remains the same after changing an item

		System.out.println(Arrays.toString(cars));
	}

}
