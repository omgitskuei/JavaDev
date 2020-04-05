package calculators;

public class LengthConversion {
	// Local fields
	private double length;
	private static LengthConversion instance;
	
	// Constructors
	private LengthConversion() {
		System.out.println("Created an instance of LengthConversion");
	}
	public static synchronized LengthConversion getInstance() {
		if (instance == null) {
			instance = new LengthConversion();
		}
		return instance;
	}
	
	// Methods
	// cm
	public double cmToKm(double cm) {
		double km = cm/100000;
		return km;
	}
	public double cmToM(double cm) {
		double m = cm/100;
		return m;
	}
	public double cmToInch(double cm) {
		double inch = cm/2.54;
		return inch;
	}
	// m
	public double mToKm(double m) {
		double km = m/1000;
		return km;
	}
	public double mToCm(double m) {
		double cm = m*100;
		return cm;
	}
	public double mToInch(double m) {
		double inch = m*39.37;
		return inch;
	}
	// km
	public double kmToCm(double km) {
		double cm = km*100000;
		return cm;
	}
	public double kmToM(double km) {
		double m = km*1000;
		return m;
	}
	public double kmToInch(double km) {
		double inch = km*39370;
		return inch;
	}
	// inch
	public double inchToKm(double inch) {
		double km = inch/39370;
		return km;
	}
	public double inchToM(double inch) {
		double m = inch*39.37;
		return m;
	}
	public double inchToCm(double inch) {
		double cm = inch*2.54;
		return cm;
	}
	
	// getter/setter
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}

	// Entry point
	public static void main(String[] args) {
		double length = 100;
		LengthConversion thisClass = LengthConversion.getInstance();
		thisClass.setLength(length);
		double result = thisClass.inchToM(thisClass.getLength());
		System.out.println(result);
	}

}
