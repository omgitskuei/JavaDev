package references.javaCoreFeatures;

class Animal {
	void eat() {
		System.out.println("Animal's eat()");
	}
	
	void evolve() {
		System.out.println("Animal's unique method evolve()");
	}
}

class Dog extends Animal {
	void eat() {
		System.out.println("Dog's eat()");
	}

	void bark() {
		System.out.println("barking...");
	}

	void superEat() {
		super.eat();
	}
}

public class UsingSuper {
	public static void main(String args[]) {
		Dog d = new Dog();				// Dog extends Animal. super = (class) Animal
		d.evolve();						// Dog can use Animal exclusive method
		d.eat();						// Dog can use Dog's methods
		d.superEat();					// Dog can use super's overloaded methods
		// d.super.eat();	this is wrong syntax, you have to re-wrap the super's method first
	}
}