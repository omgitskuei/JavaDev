package references;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsingStaticFinal {

	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		String currentFormat = "";
		String formattedDate = "";
		
		currentFormat = DateFormatLibrary.dateFormatDashes;
		formattedDate = date.format(DateTimeFormatter.ofPattern(currentFormat));
		System.out.println(formattedDate);
		
		currentFormat = DateFormatLibrary.dateFormatDots;
		formattedDate = date.format(DateTimeFormatter.ofPattern(currentFormat));
		System.out.println(formattedDate);
	}
}

class DateFormatLibrary {
	public static final java.lang.String dateFormatSlashes = "yyyy/MM/dd";
	public static final java.lang.String dateFormatDashes = "yyyy-MM-dd";
	public static final java.lang.String dateFormatDots = "yyyy.MM.dd";
}
