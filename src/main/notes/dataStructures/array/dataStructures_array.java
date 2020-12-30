package main.notes.dataStructures.array;

public class dataStructures_array {
	// local fields
	private int[] arrayInt = { 10, 20, 30, 40 };
	String[] cars = { "Volvo", "BMW", "Ford", "Mazda" };

	// constructor

	// methods
	private int[] forEachExample(int[] arrayName) {
		for (int eachIndex : arrayName) {
			eachIndex = eachIndex+1;
		}
		return arrayName;
	}
	// entry point
	public static void Main(String args[]) {
		dataStructures_array instance = new dataStructures_array();
		System.out.println(instance.forEachExample(instance.arrayInt));
	}
}
