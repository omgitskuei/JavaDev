package calculators;

public class Prime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// checks whether an int is prime or not.
	boolean isPrime(int number) {
		for (int index = 2; index < number; index++) {
			if (number % index == 0)
				return false;
		}
		return true;
	}
}
