package references.polymorphism_dependencyInjection;

public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("I drew a circle.");
	}
	
	public void circumference() {
		System.out.println("Here's the circumference: "+"x");
	}
}
