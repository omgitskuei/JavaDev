package calculators;

public class Compounding {
	
	static double pv = 5000.0;
	static int t = 2;
	static double r = 0.03;
	
	public static void main(String[] args) {
		System.out.println("FV = PV * (1 + r)^t");
		System.out.println("FV = " + String.valueOf(pv) + " * " + "(1 + " + String.valueOf(r) + ")^" + String.valueOf(t));
		System.out.println("FV = " + String.valueOf(pv) + " * " + "(" + String.valueOf((1+r)) + ")^" + String.valueOf(t));
		System.out.println("FV = " + String.valueOf(pv) + " * " + String.valueOf(Math.pow((1+r), t)));
		System.out.println("FV = " + String.valueOf(pv*Math.pow((1+r), t)));
	}
}
