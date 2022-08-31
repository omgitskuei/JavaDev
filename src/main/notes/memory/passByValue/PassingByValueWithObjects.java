package main.notes.memory.passByValue;

public class PassingByValueWithObjects {
	
	/*
	 * Passing an object to a method is technically passing the object's reference, 
	 * not the object itself.
	 * 
	 * Because Java is pass-by-value, the object's reference remains unchanged despite
	 * changes made to the object inside the method.
	 * 
	 * So, the method successfully modified the object without an explicit return statement
	 * and then overwriting the same variable with the returned object like so;
	 * 
	 * Cat bob = new Cat("Bob", 3);
	 * bob = testing.ageCat(bob, 2);
	 * 
	 * This achieves the pass-by-reference effect despite Java still being always pass-by-value.
	 */
	public static void main(String[] args) {
		PassingByValueWithObjects testing = new PassingByValueWithObjects();
		
		Cat bob = new Cat("Bob", 3);
		testing.ageCat(bob, 2);
		
		System.out.println(bob);	// Cat:{Name="Bob",Age=5}, age did change!
		
		/* 
		 * This is still pass by value because the Object because the object
		 * bob of class Cat still refers to a location in memory, not the
		 * object itself.
		 * 
		 * The value (physical memory location) is passed to the method!
		 */
	}
	
	private void ageCat(Cat aCat, int extraAge) {
		aCat.age = aCat.age + extraAge;
	}
}

class Cat {
	String name = "";
	int age = 0;
	
	// Constructor
	Cat(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Cat:{Name=\"" + this.name + "\",Age=" + this.age + "}";
	}
}