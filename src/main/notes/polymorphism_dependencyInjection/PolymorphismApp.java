package main.notes.polymorphism_dependencyInjection;

public class PolymorphismApp {

	public static void main(String[] args) {
//		Shape shape = new Triangle();
//		shape.draw();
		
		Triangle a = new Triangle();
		Drawing d = new Drawing();
		d.setShape(a);
		d.drawShape();
	}

	public void myDrawMethod(Shape shape) {
		shape.draw();
	}
}
