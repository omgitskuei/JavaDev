package main.java.notes.dataTypes.timeRelated;

import java.util.Calendar;

public class UsingCalendar {

	private void printYYYYMMDD(Calendar a) {
		String year = String.valueOf(a.get(Calendar.YEAR));
		String month = String.valueOf(a.get(Calendar.MONTH)+1);
		String day = String.valueOf(a.get(Calendar.DAY_OF_MONTH));
		String symbol = "/";
		System.out.println(year + symbol + month + symbol + day);
	}
	public static void main(String[] args) {
		// Set a specific date
		Calendar a = Calendar.getInstance();
		// Note DD = 0 means LAST DAY_OF_MONTH, eg 28/29 for Feb, 31 for Dec
		// Note MM set to 12 IS DECEMBER, as expected
		a.set(2020, 12, 0);
		// but after setting month, when you get it, it's now 11!! UNEXPECTED!
		System.out.println(a.get(Calendar.MONTH));
		
		new UsingCalendar().printYYYYMMDD(a);		
		
		
		System.out.println("--------------------------");
		String CALC_YM = "202002";
		
		Calendar calCALC_YM = Calendar.getInstance();
		int yearCALC_YM = Integer.valueOf(CALC_YM.substring(0, 4));	// parse year
		int monthCALC_YM = Integer.valueOf(CALC_YM.substring(4));	// parse month
		calCALC_YM.set(yearCALC_YM, monthCALC_YM, 0); 		// .set(YYYY, MM, DD where 0 == last day of month)
		new UsingCalendar().printYYYYMMDD(calCALC_YM);
		
		System.out.println("--------------------------");
		
		
		
		// Today
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1);
		String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String symbol = "/";
		System.out.println(year + symbol + month + symbol + day);
		
		// The properties of a Calendar object
		Calendar c = Calendar.getInstance();
		System.out.println(c.getCalendarType());
		System.out.println(c);
		System.out.println(c.get(0));  // prints 1, i=0 means ERA
		System.out.println("YEAR = " + c.get(Calendar.YEAR));
		System.out.println(c.get(1));  // prints 2020, i=1 means YEAR
		System.out.println("MONTH = " + c.get(Calendar.MONTH));
		System.out.println(c.get(2));  // prints 11, i=2 means MONTH
		System.out.println(c.get(3));  // prints 52, i=3 means WEEK OF YEAR
		System.out.println(c.get(4));  // prints 4, i=4 means WEEK_OF_MONTH
		System.out.println("DATE = " + c.get(Calendar.DATE)); // Same as DAY_OF_MONTH
		System.out.println("DAY_OF_MONTH = " + c.get(Calendar.DAY_OF_MONTH));
		System.out.println(c.get(5));  // prints 22, i=5 means DAY_OF_MONTH
		System.out.println(c.get(6));  // prints 357, i=6 means DAY_OF_YEAR
		System.out.println(c.get(7));  // prints 3, i=7 means DAY_OF_WEEK
		System.out.println(c.get(8));  // prints 4, i=8 means DAY_OF_WEEK_IN_MONTH
		System.out.println("AM_PM = " + c.get(Calendar.AM_PM));  // prints 0, i=9 means AM_PM
		System.out.println("AM = " + c.get(Calendar.AM));	// Returns same as AM_PM
		System.out.println("PM = " + c.get(Calendar.PM));	// Bugged, returns YEAR
		System.out.println(c.get(9));  // prints 0, i=9 means AM_PM
		System.out.println(c.get(10));
		System.out.println(c.get(10)); // prints 11, i=10 means HOUR
		System.out.println(c.get(11)); // prints 11, i 11 means HOUR_OF_DAY
		System.out.println(c.get(12)); // prints 49, i 12 means MINUTE
		System.out.println(c.get(13)); // means SECOND
		
		// Get last day of the year
		System.out.println(c.getActualMaximum(Calendar.DATE));

	}

}
