package main.notes.dataTypes.timeRelated.MinguoDate;

import java.time.LocalDate;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;

public class MinguoDateToLocalDate {

	public static void main(String[] args) {
		/*
		 * Converting a MinguoDate (to ISO and then) to LocalDate
		 * 
		 * String someText = md.format(dtf);
		 * LocalDate.parse(String someText, DateTimeFormatter dtf)
		 */
		LocalDate parsedDate = LocalDate.parse(
				MinguoDate.of(111, 01, 01).format(DateTimeFormatter.ISO_LOCAL_DATE),
				DateTimeFormatter.ISO_LOCAL_DATE);
		
		System.out.println(parsedDate + "\r\n");
		// "2022-07-10"
		
		
		LocalDate localDate = LocalDate.from(MinguoDate.of(111, 01, 01));
		
		System.out.println(localDate);
		// Minguo Date: Minguo ROC 111-01-01, ISO Date: 2022-01-01
	}

}
