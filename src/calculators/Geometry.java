package calculators;

public class Geometry {
	//
	
	public double pythagorasTheorum(double a, double b, double c) {
		// Provided 2 sides, calculate any 3rd side of a right-angle triangle
		// a^2 + b^2 = c^2
		if (c<=0 && a>0 && b>0) {
			c = Math.pow(a, 2) + Math.pow(b, 2);
			c = Math.sqrt(c);
			return c;
		} else if (a<=0 && c>0 && b>0) {
			a = Math.pow(c, 2) - Math.pow(b, 2);
			a = Math.sqrt(a);
			return a;
		} else if (b<=0 && c>0 && a>0) {
			b = Math.pow(c, 2) - Math.pow(a, 2);
			b = Math.sqrt(b);
			return b;
		} else {
			System.out.println("Method was not provided two valid sides.");
			return 0;
		}
	}
	
	// area
	public double areaTriangle(double base, double height) {
		double area = base*height/2;
		return area;
	}
	public double areaRectangle(double width, double height) {
		double area = width*height;
		return area;
	}
	public double areaSquare(double side) {
		double area = Math.pow(side, 2);
		return area;
	}
	public double areaParellelogram(double base, double height) {
		double area = base*height;
		return area;
	}
	public double areaEllipse(double semiMajorAxis, double semiMinorAxis) {
		// ellipse is ‘橢圓形’ tuoyuanxing, oval, a circle is a special kind of ellipse
		// semiMajorAxis is the horizontal/vertical line (whichever longer) from center to curve line
		// semiMinorAxis is the horizontal/vertical line (whichever shorter) from center to curve line
		double area = Math.PI*semiMajorAxis*semiMinorAxis;
		return area;
	}
	public double areaCircle(double radius) {
		// circle is actually a type of ellipse, where the ellipse's 2 focal points are the same at the center,
		// meaning no eccentricity
		double area = Math.PI*Math.pow(radius, 2);
		return area;
	}
	
	// Entry Point
	public static void main(String[] args) {
		Geometry e = new Geometry();
		System.out.println(e.pythagorasTheorum(3, 4, 0));
	}
	
}
