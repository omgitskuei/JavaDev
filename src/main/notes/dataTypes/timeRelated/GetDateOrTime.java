package main.notes.dataTypes.timeRelated;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.time.LocalDate;
import main.time.ZoneId;

public class GetDateOrTime {
	// Local fields

	// Constructors
	public GetDateOrTime() {
		System.out.println("BEGIN: util.GetDateOrTime()");
	}
	
	// Executable
	public static void main(String args[]) {
		GetDateOrTime u = new GetDateOrTime();
		LocalDate s = u.convertDateToLocalDate(new Date());
		System.out.println(s);
		
		u.generateDate();
		u.generateLocalDate();
		u.convertLocalDateToDate(u.generateLocalDate());
		u.convertDateToLocalDate(u.generateDate());
	}

	// Methods
	// --Generate current date
	public Date generateDate() {
		// Generate current date
		Date newDate = new Date();
		// Format the new date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat dateFormat3 = new SimpleDateFormat("MM-dd-yyyy");
		DateFormat dateFormat4 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		// Console trail
		System.out.println("GetDateOrTime generated: " + dateFormat.format(newDate)); // prints out current Date
		System.out.println("GetDateOrTime generated: " + dateFormat2.format(newDate));
		System.out.println("GetDateOrTime generated: " + dateFormat3.format(newDate));
		System.out.println("GetDateOrTime generated: " + dateFormat4.format(newDate));
		return newDate;
	}

	// --Generate current local date
	public LocalDate generateLocalDate() {
		// Generate current local date based on system clock
		LocalDate generatedLocalDate = LocalDate.now();
		// Format the new local date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// Console trail
		System.out.println("GetDateOrTime generated: " + generatedLocalDate); // prints out current
																									// Date
		return generatedLocalDate;
	}

	// --Take the passed LocalDate and convert it to Date, return Date type
	public Date convertLocalDateToDate(LocalDate thisLocalDate) {
		Date convertedDate = Date.from(thisLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		// Console trail
		System.out.println("Local Date (type) converted to Date (type)");
		System.out.println("Result: " + convertedDate);
		return convertedDate;
	}

	// --Take the passed date and convert it to LocalDate, return LocalDate type
	public LocalDate convertDateToLocalDate(Date thisDate) {
		LocalDate convertedLocalDate = thisDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		// Console trail
		System.out.println("Date (type) converted to Local Date (type)");
		System.out.println("Result: " + convertedLocalDate);
		return convertedLocalDate;
	}
}
