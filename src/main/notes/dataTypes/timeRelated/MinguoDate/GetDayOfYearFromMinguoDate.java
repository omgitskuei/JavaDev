package main.notes.dataTypes.timeRelated.MinguoDate;

import java.time.LocalDate;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;

public class GetDayOfYearFromMinguoDate {

	// Three digits following dash is for the day-of-year, is pre-padded by zero
	// to ensure three digits.
	public static void main(String[] args) {

		MinguoDate mdNow = MinguoDate.now();

		System.out.println("ISO_ORDINAL_DATE =	" + mdNow.format(DateTimeFormatter.ISO_ORDINAL_DATE));
		// ISO_ORDINAL_DATE =	2022-198
		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_ORDINAL_DATE;
		String text = mdNow.format(formatter);
		LocalDate ld = LocalDate.parse(text, formatter);
		System.out.println(ld.getDayOfYear());
		
		
		System.out.println("2022-01-01 (ISO) =	"
				+ MinguoDate.of(111, 01, 01).format(DateTimeFormatter.ISO_ORDINAL_DATE) + "(ISO_ORDINAL)");
		// 2022-01-01 (ISO) =	2022-001(ISO_ORDINAL)
		
		String ordinal = MinguoDate.of(111, 01, 01).format(DateTimeFormatter.ISO_ORDINAL_DATE);
		System.out.println(ordinal.substring(ordinal.indexOf("-")+1));
		// 001
	}

}
