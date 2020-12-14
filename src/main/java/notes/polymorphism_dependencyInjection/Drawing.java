package main.java.notes.polymorphism_dependencyInjection;

public class Drawing {
	private Shape shapeProperty;
	
	public void setShape(Shape passedShape) {
		this.shapeProperty = passedShape;
	}
	
	public void drawShape() {
		this.shapeProperty.draw();
	}
}
