package main.exercises;

public class CollatzSequence {

	/**
	 * Write a function named collatz() that has one parameter named number.
	 * 
	 * If number is even, then collatz() should print (number/2) and return this
	 * value.
	 * If number is odd, then collatz() should print and return (3*number+1).
	 * 
	 * Then write a program that lets the user type in an integer and that keeps
	 * calling collatz() on that number until the function returns the value 1.
	 * 
	 * (Amazingly enough, this sequence actually works for any integer —
	 * sooner or later, using this sequence, you’ll arrive at 1!
	 * 
	 * Even mathematicians aren’t sure why.
	 * Your program is exploring what’s called the Collatz sequence, sometimes
	 * called 'the simplest impossible math problem.')
	 * 
	 * Hint: An integer number is even if number % 2 == 0,
	 * and it’s odd if number % 2 == 1.
	 * 
	 * The output of this program could look something like this:
	 * Enter number: 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
	 * 
	 * This exercise was originally a Python problem from <Automate the Boring Stuff
	 * with Python> page 77 by Al Sweigart
	 * 
	 * @author omgitskuei
	 * @category exercise
	 * @param int number
	 * @return int number
	 * @since 2021/05/07 (YYYY/MM/DD)
	 */
	private int collatz(int number) {
		if (number % 2 == 0) {
			// is Even
			return number / 2;
		} else {
			// is Odd
			return 3 * number + 1;
		}

	}

	public static void main(String[] args) {
		CollatzSequence cS = new CollatzSequence();
		int number = 3;
		System.out.println(number);		// prints the initial number
		while (number != 1) {
			number = cS.collatz(number);
			System.out.println(number);	// prints other iterations
		}
	}

}
