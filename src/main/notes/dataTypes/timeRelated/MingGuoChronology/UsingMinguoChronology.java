package main.notes.dataTypes.timeRelated.MingGuoChronology;

import java.time.LocalDate;
import java.time.chrono.MinguoChronology;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Locale;

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
	
//	private static final MinguoChronology mgC = MinguoChronology.INSTANCE;
	
	// Assuming today's date was 2022-07-10, ...
	public static void main(String[] args) {
		// MingGuoChronology mgc = new MinguoChronology();						// cannot declare using 'new'
		MinguoChronology mgChronology = MinguoChronology.INSTANCE;				// Singleton only
		System.out.println(mgChronology);
		// prints "Minguo"
		
		MinguoDate mdNow1 = MinguoChronology.INSTANCE.dateNow();
		System.out.println(mdNow1.toString());
		// "Minguo ROC 111-07-10",
		
		MinguoDate mdNow2 = mgChronology.dateNow();
		
		MinguoDate md1 = mgChronology.date(111, 01, 01);
		MinguoDate md2 = mgChronology.dateYearDay(111, 1);	// ORDINAL
		System.out.println(md1.isEqual(md2));
		
		System.out.println(mgChronology.eras());
		// [BEFORE_ROC, ROC]
		
		System.out.println(mgChronology.getCalendarType());
		// roc
		
		System.out.println(mgChronology.isLeapYear(111));	// takes int Minguo years (eg, 2022 (Gregorian) is 111)
		// false
		MinguoDate md3 = MinguoDate.of(111, 01, 01);
		System.out.println(md3.isLeapYear());
		// false
	}

}
