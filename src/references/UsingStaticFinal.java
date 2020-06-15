package references;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Final is a non-access modifier, only applicable to variable, method, and
 * class for various effects; 1. final Variables; applied to create Constant
 * Variables 2. final Methods; applied to prevent Method Overriding 3. final
 * Class; applied to prevent Inheritance
 * 
 * Static is ...
 * 
 * @author Kuei-Feng Tung
 */

public class UsingStaticFinal {

	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		String currentFormat = "";
		String formattedDate = "";

		// using a direct initialized static variable
		currentFormat = DateFormatLibrary.dateFormatDashes; // static
		formattedDate = date.format(DateTimeFormatter.ofPattern(currentFormat));
		System.out.println(formattedDate);

		// using a direct initialized static final variable
		currentFormat = DateFormatLibrary.dateFormatDots; // static final
		formattedDate = date.format(DateTimeFormatter.ofPattern(currentFormat));
		System.out.println(formattedDate);
	}
}

class DateFormatLibrary {
	public static final java.lang.String dateFormatSlashes = "yyyy/MM/dd";
	public static java.lang.String dateFormatDashes = "yyyy-MM-dd";
	public static final java.lang.String dateFormatDots = "yyyy.MM.dd";
}
