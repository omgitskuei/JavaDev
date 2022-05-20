package main.notes.exceptions;

public class MultipleExceptionsInOneCatch {

	public static void main(String[] args) {
		String a = null;
		int b;
		
		try {
			b = Integer.parseInt(a);
			System.out.println(b);
		} catch (NullPointerException | NumberFormatException e) {
			System.out.println("An exception was thrown: " + e.getClass().getSimpleName());
			// prints "An exception was thrown: NumberFormatException"
		}
	}
}
