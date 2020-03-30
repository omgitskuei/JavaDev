package references;

public class UsingCustomizedExceptions {

	public static void main(String[] args) {
		A a = new A1();
		try {
			System.out.println(a.methodName(25, 8));
			System.out.println(a.methodName(20, 101));
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}
	}
}

abstract class A {
	abstract int methodName(int x, int y) throws MyException;
}

class A1 extends A {
	protected int methodName(int x, int y) throws MyException {
		System.out.println("x"+x);
		System.out.println("y"+y);
		if (x > 99 || y > 99) {
			throw new MyException("Wow");
		}
		System.out.println("x*y="+x*y);
		return x * y;
	}
}

class MyException extends Exception {
	String message;

	MyException(String str) {
		message = str;
	}

	public String getMessage() {
		return message;
	}
}
