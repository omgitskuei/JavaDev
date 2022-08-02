package main.notes.dataStructures.array;

public class DeclaringArrays {

	public static void main(String[] args) {
		
		// Declaring an array with values
		String[] cars = { "Volvo", "BMW", "Ford", "Mazda" };
		
		System.out.println(cars.getClass());	// class [Ljava.lang.String;
		System.out.println(cars.toString());	// [Ljava.lang.String;@53bd815b
		System.out.println(cars.length);		// 4

		
		// Declaring an empty array then adding values
		int[] nums = new int[3];				// Is 3 exclusive (size) or inclusive (max index)?
		
		nums[0] = 1;
		nums[1] = 11;
		nums[2] = 111;
		// nums[3] = 1111;						// Try to give 3 as index, throws exception, so is 'size'

		System.out.println(new PrintingArrays().toString(nums));
	}

}
