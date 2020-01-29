package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DatatypeConversion {
	// Local fields

	// Constructors
	public DatatypeConversion() {
		System.out.println("BEGIN: util.DatatypeConversion");
	}
	
	// Methods
	// Convert String to ...
	public int stringToInt(String convertThis) {
		return Integer.parseInt(convertThis);
	}

	public float stringToFloat(String convertThis) {
		return Float.parseFloat(convertThis);
	}

	public LocalDate stringToLocalDate(String convertThis) {
		LocalDate result = LocalDate.parse(convertThis);
		return result;
	}

	public Date stringToDate(String convertThis) throws ParseException {
//		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("MM dd, yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); 
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		Date result = formatter.parse(convertThis); 
		return result;
	}

	public long stringToLong(String convertThis) {
		long result = Long.parseLong(convertThis);
		return result;
	}

	public boolean stringToBoolean(String convertThis) {
		// "true", "TRue" will all convert to true
		// "ok" will convert to false
		Boolean result = Boolean.parseBoolean(convertThis);
		return result;
	}
	
	
	// Convert int to ...
	public String intToString(int convertThis) {
		String result = String.valueOf(convertThis);
		return result;
	}

	
	// Convert long to ...
	public String longToString(long convertThis) {
		String result = String.valueOf(convertThis);
		return result;
	}

	
	// Convert float to ...
	public String floatToString(float convertThis) {
		String result = String.valueOf(convertThis);
		return result;
	}
	
	
	// Convert date to ...
	public String dateToString(Date convertThis) {
//		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("MM dd, yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); 
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		String result = formatter.format(convertThis);
		return result;
	}
	
	public Timestamp dateToTimestamp(Date convertThis) {
		Timestamp ts=new Timestamp(convertThis.getTime());   
		return ts;
	}
	
	
	// Convert boolean to ...
	public String booleanToString(boolean convertThis) {
		// 2 ways;
		//String result = String.valueOf(convertBoolean);
		String result = Boolean.toString(convertThis);
		return result;
	}
	
	
	// Convert timestamp to ...
	public Date timestampToDate(Timestamp convertThis) {
		Date result=new Date(convertThis.getTime());
		return result;
	}
}
