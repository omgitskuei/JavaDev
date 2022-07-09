package main.notes.dataTypes.timeRelated.MingGuoChronology;

import java.time.LocalDate;
import java.time.chrono.MinguoChronology;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.UnsupportedTemporalTypeException;

public class UsingMinguoChronology {
	/*
	 * The Minguo calendar system. This chronology defines the rules of the Minguo
	 * calendar system. This calendar system is primarily used in the Republic of
	 * China, often known as Taiwan. Dates are aligned such that 0001-01-01 (Minguo)
	 * is 1912-01-01 (ISO).
	 * 
	 * era - There are two eras, the current 'Republic' (ERA_ROC) and the previous
	 * era (ERA_BEFORE_ROC).
	 * 
	 * year-of-era - The year-of-era for the current era increases uniformly from
	 * the epoch at year one. For the previous era the year increases from one as
	 * time goes backwards. The value for the current era is equal to the ISO
	 * proleptic-year minus 1911.
	 * 
	 * proleptic-year - The proleptic year is the same as the year-of-era for the
	 * current era. For the previous era, years have zero, then negative values. The
	 * value is equal to the ISO proleptic-year minus 1911.
	 * 
	 * month-of-year - The Minguo month-of-year exactly matches ISO.
	 * 
	 * day-of-month - The Minguo day-of-month exactly matches ISO.
	 * 
	 * day-of-year - The Minguo day-of-year exactly matches ISO. leap-year - The
	 * Minguo
	 * 
	 * leap-year pattern exactly matches ISO, such that the two calendars are never
	 * out of step.
	 * 
	 * https://docs.oracle.com/javase/8/docs/api/java/time/chrono/MinguoChronology.
	 * html
	 */
	
//	public static final MinguoChronology mgC = MinguoChronology.INSTANCE;
	
	
	public static void main(String[] args) {
		
		// Constructor not visible - cannot declare using 'new'
		// MingGuoChronology mgc = new MinguoChronology();
		
		// To access MinguoChronology, must use Singleton style
		MinguoChronology mgC = MinguoChronology.INSTANCE;
		
		// Assuming today's date was 2022-07-10, ...
		
		/*
		 *  Today's Minguo date (YYYY-MM-DD)
		 */
		MinguoDate mdNow = mgC.dateNow();
		System.out.println(mdNow);						// "Minguo ROC 111-07-10", 
//		System.out.println(mdNow.toString());			// same thing, "Minguo ROC 111-07-10"
		
		System.out.println(mdNow.format(DateTimeFormatter.BASIC_ISO_DATE));			// 20220710
		System.out.println(mdNow.format(DateTimeFormatter.ISO_LOCAL_DATE));			// 2022-07-10
		System.out.println(mdNow.format(DateTimeFormatter.ISO_ORDINAL_DATE));		// 2022-191
		
		
		
		
		try {
			// example ISO_OFFSET_DATE >'2011-12-03+01:00'
			System.out.println(mdNow.format(DateTimeFormatter.ISO_OFFSET_DATE));
			// Unsupported field: OffsetSeconds will be thrown
		} catch (UnsupportedTemporalTypeException e) {
			System.out.println(e);	// Exception will be thrown, ISO_OFFSET_DATE requires OffsetSeconds.
			// OffsetSeconds sounds like rare 
			// war badges which Minguo date can't provide
		}
		
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
		String text = mdNow.format(dtf);
		LocalDate parsedDate = LocalDate.parse(text, dtf);
		System.out.println(parsedDate);												// "2022-07-10"
		
		/*
		 * Comparing two Minguo Dates
		 */
		MinguoDate mdOther = mgC.date(2022, 07, 11);
		System.out.println(mdNow.isBefore(mdOther));								// true
		
		System.out.println(mdNow.isBefore(mgC.date(2022, 07, 11)));
	}

}
