package main.exercises;

import java.util.Currency;
import java.util.Locale;

public class CurrencyData_WIP {
	// Local fields
	
	
	// Constructors
	public CurrencyData_WIP() {
		System.out.println("BEGIN: util.CurrencyData_WIP");
	}
	public static void main(String[] args) {
		
		// Currency declaration via Locale or String
		Currency thisC = Currency.getInstance(Locale.TAIWAN);  // Prints JPY
		//Currency thisC = Currency.getInstance("TWD");  
		
		System.out.println("Currency:       "+thisC);  // Takes String 4217 code, or Locale.COUNTRY, Prints USD
		 
		System.out.println("decimals:       "+thisC.getDefaultFractionDigits()); // Takes USD, Returns 2
		
		System.out.println("display name:   "+thisC.getDisplayName() );  // Takes USD, Returns "US Dollar"
		
		System.out.println("symbol:         "+thisC.getSymbol());  // Takes USD, Returns "$"
		
		System.out.println("ISO 4217 #:     "+thisC.getNumericCode());
		
		Locale locale = Locale.getDefault();
        System.out.println("Default Locale: "+locale);
	}
	
	// Methods
	public boolean validateCurrencyAmount() {
		try {
			return true;
		} catch (Exception e) {
			System.out.println("try FAILED");
		}
		return false;
	}
	

}
