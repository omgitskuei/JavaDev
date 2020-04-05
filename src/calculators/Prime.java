package calculators;

public class Prime {
	// ******** Bill Pugh Singleton Implementation ********
	private static class SingletonHelper {
		private static final Prime instance = new Prime();
	}

	public static Prime getInstance() {
		return SingletonHelper.instance;
	}

	// ******************* Constructor *******************
	private Prime() {
		System.out.println("New instance of Prime created");
	}

	// ******************* Entry Point *******************
	public static void main(String[] args) {
		Prime prime = Prime.getInstance();
		// System.out.println(prime.isPrime(10));
		boolean result = prime.fermatTwoSquareTheorem(29);
		System.out.println(result);

		result = prime.fermatTwoSquareTheorem4kplus1(29);
		System.out.println(result);
	}

	// ********************* Methods *********************
	// some primes can be written as the sum of two integers squares
	// eg.1. 5 = 1^2 + 2^2 = 1 + 4
	// eg.2. 29 = 2^2 + 5^2 = 4 + 25
	// let fermat's theorem be;
	// prime = square + difference,
	// so, difference = prime - square,
	// if difference is a perfect square, then true
	// there is exactly one pair of squares that sum up into those primes
	boolean fermatTwoSquareTheorem(int prime) {
		System.out.println("BEGIN: fermatTwoSquareTheorem(int prime)");
		int index = 1;
		double difference = 0;
		boolean showMath = true;
		while (Math.pow(index, 2) < prime) {
			difference = (prime - Math.pow(index, 2));
			double indexSq = Math.pow(index, 2);
			double diffSqrt = Math.sqrt(difference);
			if (showMath == true) {
				System.out.print(prime + " = " + index + "^2 + " + difference + " = " + indexSq + " + " + difference);
				System.out.println(" => Is " + difference + " a perfect square? root(" + difference + ") = "
						+ (double) Math.round(diffSqrt * 100) / 100 + " " + (diffSqrt - Math.floor(diffSqrt) == 0));
			}
			// check whether the sqrt of difference has any remainders
			if (diffSqrt - Math.floor(diffSqrt) == 0) {
				if (showMath == true) {
					System.out.println("Returning true");
				}
				return true;
			}
			++index;
			if (showMath == true) {
				System.out.println("Continue? While loop (" + Math.pow(index, 2) + " < " + prime + "); "
						+ (Math.pow(index, 2) < prime));
			}
		}
		if (showMath == true) {
			System.out.println("Returning false");
		}
		return false;
	}
	// also, all primes able to be written as prime=4k+1 CAN be written as sum of
	// two integers squares
	// prime = 4k + 1
	// k = (prime-1)/4
	// if k is an integer (no remainders), then true
	boolean fermatTwoSquareTheorem4kplus1(int prime) {
		System.out.println("BEGIN: fermatTwoSquareTheorem4kplus1(int prime)");
		boolean showMath = true;
		if (showMath == true) {
			System.out.println(prime+" = "+"4k + 1");
			System.out.println("k = ("+prime+"-1)/4 = "+(prime-1)+"/4");
			System.out.println("k = "+(prime-1)/4);
			System.out.println("Is k an integer (no remainders)? "+((prime - 1) % 4 == 0));
		}
		// If (prime-1)/4 have a remainder of 0
		if ((prime - 1) % 4 == 0) {
			if (showMath == true) {
				System.out.println("Returning true");
			}
			return true;
		}
		if (showMath == true) {
			System.out.println("Returning false");
		}
		return false;
	}

	// checks whether the argument int number is a prime or not
	boolean isPrime(int number) {
		for (int index = 2; index < number; index++) {
			// System.out.println(number+" divided by "+index+" gives remainder
			// "+number%index);
			if (number % index == 0)
				return false;
		}
		return true;
	}
}
