package references.javaCoreFeatures;

// 'abstraction' enables the programmer to create a class that cannot be instantiated (cannot be new'd).
// there's two ways to achieve abstraction;
//    1) abstract class
//    2) interface

// an abstract class is a superclass for other classes to inherit from it

// why do this?
// ^ subclass can share a common design
// ^ security - hide certain details and only show the important details of an object.

abstract class AbstractClassName {
	abstract int abstractMethod(int x, int y);

	int regularMethod(int x, int y) {
		return x - y;
	}
}

// inheritance in OOP is a feature which enables a class to use the members of another class and embellish them with new capabilities
class ClassName extends AbstractClassName {
	@Override
	int abstractMethod(int x, int y) { // ClassName must override and implement AbstractClassName's abstractMethod here
		return x * y;
	}
}

public class UsingAbstractClass {

	public static void main(String[] args) {
		// AbstractClassName b = new AbstractClassName(); // this leads to error

		ClassName a = new ClassName();
		int s = a.abstractMethod(5, 2);
		System.out.println(s);		// returns 10

		s = a.regularMethod(5, 2);	// regular Class extending abstract class can use abstract class's regular methods
		System.out.println(s);		// returns 3
	}

}