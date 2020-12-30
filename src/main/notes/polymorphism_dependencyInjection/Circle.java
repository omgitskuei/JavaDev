package main.notes.polymorphism_dependencyInjection;

public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("I drew a circle.");
	}
	
	public void circumference() {
		System.out.println("Here'UsingSuper the circumference: "+"x");
	}
}
