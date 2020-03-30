package calculators;

public class TemperatureConversion {
	// Local fields
	private double temperature;
	
	// Constructors
	public TemperatureConversion() {
		System.out.println("");
	}
	
	// Methods
	// celsius
	public double celsiusToFahrenheit(double celsius) {
		double fahrenheit = (celsius*9/5)+32;
		return fahrenheit;
	}
	public double celsiusToKelvin(double celsius) {
		double kelvin = celsius + 273.15;
		return kelvin;
	}
	// fahrenheit
	public double fahrenheitToCelsius(double fahrenheit) {
		double celsius = (fahrenheit-32)*5/9;
		return celsius;
	}
	public double fahrenheitToKelvin(double fahrenheit) {
		double kelvin = (fahrenheit-32) * 5/9 + 273.15;
		return kelvin;
	}
	// kelvin
	public double kelvinToCelsius(double kelvin) {
		double celsius = kelvin-273.15;
		return celsius;
	}
	public double kelvinToFahrenheit(double kelvin) {
		double fahrenheit = (kelvin-273.15)*9/5 + 32;
		return fahrenheit;
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
		TemperatureConversion thisClass = new TemperatureConversion();
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
