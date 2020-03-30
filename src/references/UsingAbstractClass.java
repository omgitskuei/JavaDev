package references;

public class UsingAbstractClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

// 'abstraction' enables the programmer to create a class that cannot be instantiated (cannot be new'd).
// an abstract class is a superclass for other classes to inherit from it, so that each subclass can share a common design
abstract class abstractClassName {
	abstract int abstractMethod(int x, int y);
}

// inheritance in OOP is a feature which enables a class to use the members of another class and embellish them with new capabilities
class className extends abstractClassName {

	@Override
	int abstractMethod(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}