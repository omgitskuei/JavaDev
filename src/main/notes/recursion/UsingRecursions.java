package main.notes.recursion;

public class UsingRecursions {

	public static void main(String[] args) {
		int result = sum(5);
		System.out.println(result);
	}

	// Halting Condition
	// Just as loops can run into the problem of infinite looping,
	// recursive functions can run into the problem of infinite recursion. Infinite
	// recursion is when the function never stops calling itself. Every recursive
	// function should have a halting condition, which is the condition where the
	// function stops calling itself. In the previous example, the halting condition
	// is when the parameter k becomes 0.

	public static int sum(int k) {
		// When the sum() function is called, it adds parameter k to the sum of all
		// numbers smaller than k and returns the result. When k becomes 0, the function
		// just returns 0.
		// Since the function does not call itself when k is 0, the program stops there
		// and returns the result.

		System.out.println("Starting sum(int k), where k=" + k);
		if (k > 0) {
			int k1 = k;
			System.out.println("k1=" + k1);
			int k2 = sum(k - 1);			// does this 5 times
			System.out.println("k2=" + k2);	// before continuing
			int k3 = k1 + k2;
			System.out.println("k3=" + k3);
			return k3;	// recursion, it calls itself
		} else {
			return 0;
		}
		/**
		 * Starting sum(int k), where k=5
		 * k1=5
		 * Starting sum(int k), where k=4
		 * k1=4
		 * Starting sum(int k), where k=3
		 * k1=3
		 * Starting sum(int k), where k=2
		 * k1=2
		 * Starting sum(int k), where k=1
		 * k1=1
		 * Starting sum(int k), where k=0
		 * k2=0
		 * k3=1
		 * k2=1
		 * k3=3
		 * k2=3
		 * k3=6
		 * k2=6
		 * k3=10
		 * k2=10
		 * k3=15
		 * 15
		 */
	}

}
