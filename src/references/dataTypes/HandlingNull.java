package references.dataTypes;

public class HandlingNull {

	public static void main(String[] args) {
		String e = null;
		Object a = null;
		String f = String.valueOf(a);
		System.out.println(f);
		try {
			// No null point exception triggered
			if ("wow".equals(f)) {

			}
			// null point exception triggered
			if (f.equals("wow")) {

			}

		} catch (NullPointerException e1) {
			e1.printStackTrace();
		}
	}

	/*
	 * Example 2
	 * 
	 * As with C++, a null class can be instantiated in situations where a reference
	 * to an Animal object is required, but there is no appropriate object
	 * available. This is 'public interface Animal'. A null Animal object is
	 * possible (Animal myAnimal = null;) and could be useful as a place-holder, but
	 * may not be used for calling a method.
	 * 
	 * In this example, Animal myAnimal = null; myAnimal.makeSound(); // throws a
	 * NullPointerException.
	 * 
	 * Therefore, additional code may be necessary to test for null objects. The
	 * null object pattern solves this problem by providing a special NullAnimal
	 * class which can be instantiated as an object of type Animal.
	 */
	
	public interface Animal {
		void makeSound();		// null
	}

	public class Dog implements Animal {
		public void makeSound() {
			System.out.println("woof!");
		}
	}

	// Null object pattern;
	public class NullAnimal implements Animal {
		public void makeSound() {
			// Empty...
		}
	}
	// This pattern should be used carefully as it can make errors/bugs appear as normal program execution.

}
