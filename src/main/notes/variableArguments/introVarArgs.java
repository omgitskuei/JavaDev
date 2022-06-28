package main.notes.variableArguments;

public class introVarArgs {

	public static void main(String[] args) {
		/*
		 *  what's advantageous with using varargs/"variable args" is 
		 *  allowing the method higher up the call hierarchy (in this case,
		 *  this would be the public static void main(...etc)) to call the method 
		 *  printPassedInts and pass a various number of parameters each 
		 *  time.
		 *  another advantage is foregoing the need to declare an instance 
		 *  of int array, wrapping the data, then passing it to 
		 *  printAllIntsPassed(...)
		 */
		printPassedInts(1, 23, 4, 5, 6);
		System.out.println();
		
		printPassedInts(2, 3, 5);
		System.out.println();
		/**
		 *  There are limitations to this; the parameters must be the same type (int)
		 */
		
//		printPassedInts(1, 2, "as"); this is a syntax error
		
	}
	
	private static void printPassedInts(int ... arrayOfInts) {
		/**
		 *  The variable arguments "varargs" arrayOfInts syntax is equivalent
		 *  to writing printAllIntsPassed(int[] arrayOfInts) from the
		 *  perspective of the private method
		 */
		
		System.out.println(arrayOfInts instanceof int[]);		// true
		
		// Prints out id of an int[] array like so if directly accessed;
		System.out.println(arrayOfInts);				// [I@5d22bbb7
		
		for (int eachInt : arrayOfInts) {
			System.out.print(eachInt + " ");
		}
		
		System.out.println();
	}

	// Syntax error:
//	private static void printMixedParams(int ... arrayOfInts, String ...strings) {
//		// ...
//	}
}
