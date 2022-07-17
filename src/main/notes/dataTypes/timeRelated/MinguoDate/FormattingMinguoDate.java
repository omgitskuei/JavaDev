package main.notes.dataTypes.timeRelated.MinguoDate;

import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;

public class FormattingMinguoDate {

	public static void main(String[] args) {
		MinguoDate mdNow = MinguoDate.now();
		System.out.println("BASIC_ISO_DATE =	" + mdNow.format(DateTimeFormatter.BASIC_ISO_DATE));
		// 20220710
		System.out.println("ISO_DATE =			" + mdNow.format(DateTimeFormatter.ISO_DATE));
		// 2022-07-10
		System.out.println("ISO_LOCAL_DATE =	" + mdNow.format(DateTimeFormatter.ISO_LOCAL_DATE));
		// 2022-07-10 (if locale is Taiwan)
	}

}
