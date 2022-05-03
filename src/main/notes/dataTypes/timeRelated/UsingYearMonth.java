package main.notes.dataTypes.timeRelated;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class UsingYearMonth {

	public static void main(String[] args) {
		
		// Instantiating YearMonth object
		YearMonth ymNow = YearMonth.now();
		System.out.println("1-------------------");
		System.out.println(ymNow);	// prints "2022-05" if system time was 2022 May
		
		YearMonth ym202202 =  YearMonth.of(2022, 2);
		System.out.println("2-------------------");
		System.out.println(ym202202);	// prints "2022-02"
		
		// getting String value of YearMonth in a custom format
		String ymWithSlash = ym202202.format(DateTimeFormatter.ofPattern("MM / yyyy"));
		System.out.println("3-------------------");
		System.out.println(ymWithSlash); // prints "02 / 2022"
		
		// Extracting month from YearMonth
		Month month1 = ym202202.getMonth();
		System.out.println("4-------------------");
		System.out.println(month1);	// prints "FEBRUARY"
		System.out.println(month1.getDisplayName(TextStyle.FULL, Locale.CHINA));	// prints 二月
		System.out.println(month1.getDisplayName(TextStyle.FULL_STANDALONE, Locale.CHINA));	// prints 二月
		System.out.println(month1.getDisplayName(TextStyle.NARROW, Locale.CHINA));	// prints 二月
		
		
		int month2 = ym202202.getMonthValue();
		System.out.println("5-------------------");
		System.out.println(month2);	// prints "2"
		
		// comparing the month of YearMonth with another month
		int compare = month1.compareTo(Month.APRIL);
		System.out.println("6-------------------");
		System.out.println(
			compare > 0 ? "greater than APRIL" : 
			compare == 0 ? "equal to APRIL" : 
			compare < 0 ? "less than APRIL" : "impossible case"); // when m = MAY, prints "less than" because FEB > APRIL
		System.out.println(month2 > 4);
		
		// get last day of a YearMonth
		LocalDate ld = ym202202.atEndOfMonth();
		System.out.println("7-------------------");
		System.out.println(ld.getDayOfMonth()); // prints "28" for 2022 FEB
		
		int year2 = ym202202.getYear();
		System.out.println("8-------------------");
		System.out.println(year2);	// prints "2022"
		
		// check if is leap year
		System.out.println("9-------------------");
		System.out.println(ym202202.isLeapYear());	// prints false for 2022
		
		// validate date
		System.out.println("10-------------------");
		System.out.println(ym202202.isValidDay(29));	// prints false
		System.out.println(ym202202.isValidDay(28));	// prints true
		
		// get length of month, between 28 and 31
		int ym202205monthlength = YearMonth.of(2022, 05).lengthOfMonth();
		System.out.println("11-------------------");
		System.out.println(ym202205monthlength);	// prints 31
	}

}
