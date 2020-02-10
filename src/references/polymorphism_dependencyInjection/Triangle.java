package references.polymorphism_dependencyInjection;

public class Triangle implements Shape{

	@Override
	public void draw() {
		System.out.println("I drew a triangle.");		
	}
	
	public void area() {
		System.out.println("This is the area: "+"y");
	}
}
