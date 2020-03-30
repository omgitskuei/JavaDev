package references;

class Animal {
	void eat() {
		System.out.println("Animal's eat()");
	}
}

class Dog extends Animal {
	void eat() {
		System.out.println("Dog's eat()");
	}

	void bark() {
		System.out.println("barking...");
	}

	void work() {
		super.eat();
		bark();
	}
}

public class UsingSuper {
	public static void main(String args[]) {
		Dog d = new Dog();
		// Dog extends Animal's work() uses Animal's eat method
		d.work();					// super = Animal, super.eat() outputs "Animal's eat()"
	}
}