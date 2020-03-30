package calculators;

public class LengthConversion {
	// Local fields
	private double temperature;
	
	// Constructors
	public LengthConversion() {
		System.out.println("");
	}
	
	// Methods
	// cm
	public double cmToKm(double cm) {
		double km = cm*1000;
		return km;
	}
	public double cmToM(double cm) {
		double m = cm*100;
		return m;
	}
	
	// getter/setter
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	// Entry point
	public static void main(String[] args) {
		double temperature = 100;
		LengthConversion thisClass = new LengthConversion();
		// celsius
		System.out.println(thisClass.celsiusToFahrenheit(temperature));
		System.out.println(thisClass.celsiusToKelvin(temperature));
		// fahrenheit
		System.out.println(thisClass.fahrenheitToCelsius(temperature));
		System.out.println(thisClass.fahrenheitToKelvin(temperature));
		// kelvin
		System.out.println(thisClass.kelvinToCelsius(temperature));
		System.out.println(thisClass.kelvinToFahrenheit(temperature));
	}

}
