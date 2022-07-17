package main.notes.dataTypes.timeRelated.MinguoDate;

import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.UnsupportedTemporalTypeException;

public class IncompatibleFormattingMinguoDate {

	public static void main(String[] args) {
		
		MinguoDate mdNow = MinguoDate.now();
		// Some DateTimeFormatter such as DateTimeFormatter.ISO_OFFSET_DATE
		// will not work with MinguoDate, because of unsupported fields
		try {
			// ISO_OFFSET_DATE >'2011-12-03+01:00'
			System.out.println(mdNow.format(DateTimeFormatter.ISO_OFFSET_DATE));
			// throws
		} catch (UnsupportedTemporalTypeException e) {
			System.out.println("try ISO_OFFSET_DATE,	threw due to " + e.getMessage());
			// ISO_OFFSET_DATE requires OffsetSeconds. MinguoDate doesn't have
			// OffsetSeconds.
		}
		try {
			System.out.println(mdNow.format(DateTimeFormatter.ISO_TIME));
			// throws
		} catch (Exception e) {
			System.out.println("try ISO_TIME,			threw due to " + e.getMessage());
			// ISO_TIME requires HourOfDay. MinguoDate doesn't have HourOfDay.
		}
		try {
			System.out.println(mdNow.format(DateTimeFormatter.ISO_INSTANT));
			// throws
		} catch (Exception e) {
			System.out.println("try ISO_INSTANT,		threw due to " + e.getMessage());
			// ISO_INSTANT requires InstantSeconds. MinguoDate doesn't have InstantSeconds.
		}
	}
}
