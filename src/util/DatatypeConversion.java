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
		System.out.println("BEGIN: util.DatatypeConversion()");
	}

	public static void main(String args[]) {
		DatatypeConversion util = new DatatypeConversion();
		Date s = new Date();
		String e = util.dateToString(s);
		System.out.println(e);
	}

	// Methods
	
	// byte <-> hex
	public String byteToHex(byte num) {
		char[] hexDigits = new char[2];
		hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
		hexDigits[1] = Character.forDigit((num & 0xF), 16);
		return new String(hexDigits);
	}
	public byte hexToByte(String hexString) {
		int firstDigit = Character.digit(hexString.charAt(0), 16);
		if (firstDigit == -1) {
			throw new IllegalArgumentException("Invalid Hexadecimal Character: " + hexString.charAt(0));
		}
		int secondDigit = Character.digit(hexString.charAt(1), 16);
		if (secondDigit == -1) {
			throw new IllegalArgumentException("Invalid Hexadecimal Character: " + hexString.charAt(1));
		}
		return (byte) ((firstDigit << 4) + secondDigit);
	}

	public int hexChartoInt(char hexChar) {
		int digit = Character.digit(hexChar, 16);
		if (digit == -1) {
			throw new IllegalArgumentException("Invalid Hexadecimal Character: " + hexChar);
		}
		return digit;
	}
	
	// Convert byte[] <-> hexString (String)
	public String byteArrayToHexstring(byte[] byteArray) {
		StringBuffer hexStringBuffer = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			char[] hexDigits = new char[2];
			hexDigits[0] = Character.forDigit((byteArray[i] >> 4) & 0xF, 16);
			hexDigits[1] = Character.forDigit((byteArray[i] & 0xF), 16);
			hexStringBuffer.append(new String(hexDigits));
		}
		return hexStringBuffer.toString();
	}

	public byte[] hexstringToByteArray(String hexString) {
		if (hexString.length() % 2 == 1) {
			throw new IllegalArgumentException("Invalid hexadecimal String supplied.");
		}
		byte[] bytes = new byte[hexString.length() / 2];
		for (int i = 0; i < hexString.length(); i += 2) {
			int firstDigit = Character.digit(hexString.substring(i, i + 2).charAt(0), 16);
			if (firstDigit == -1) {
				throw new IllegalArgumentException(
						"Invalid Hexadecimal Character: " + hexString.substring(i, i + 2).charAt(0));
			}
			int secondDigit = Character.digit(hexString.substring(i, i + 2).charAt(1), 16);
			if (secondDigit == -1) {
				throw new IllegalArgumentException(
						"Invalid Hexadecimal Character: " + hexString.substring(i, i + 2).charAt(1));
			}
			bytes[i / 2] = (byte) ((firstDigit << 4) + secondDigit);
		}
		return bytes;
	}

	// Convert String <-> Int
	public int stringToInt(String convertThis) {
		return Integer.parseInt(convertThis);
	}

	public String intToString(int convertThis) {
		// Two ways
		// String result = Integer.toString(convertThis);
		String result = String.valueOf(convertThis);
		return result;
	}

	// Convert String <-> Float
	public float stringToFloat(String convertThis) {
		return Float.parseFloat(convertThis);
	}

	public String floatToString(float convertThis) {
		String result = String.valueOf(convertThis);
		return result;
	}

	// Convert String <-> LocalDate
	public LocalDate stringToLocalDate(String convertThis) {
		LocalDate result = LocalDate.parse(convertThis);
		return result;
	}

	// Convert String <-> Date
	public Date stringToDate(String convertThis) throws ParseException {
//		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("MM dd, yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date result = formatter.parse(convertThis);
		return result;
	}

	public String dateToString(Date convertThis) {
//		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("MM dd, yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd yyyy");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");  
//	    SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String result = formatter.format(convertThis);
		return result;
	}

	// Convert String <-> Long
	public long stringToLong(String convertThis) {
		long result = Long.parseLong(convertThis);
		return result;
	}

	public String longToString(long convertThis) {
		String result = String.valueOf(convertThis);
		return result;
	}

	// Convert String <-> Boolean
	public boolean stringToBoolean(String convertThis) {
		// "true", "TRue" will all convert to true
		// "ok" will convert to false
		Boolean result = Boolean.parseBoolean(convertThis);
		return result;
	}

	public String booleanToString(boolean convertThis) {
		// 2 ways;
		// String result = String.valueOf(convertBoolean);
		String result = Boolean.toString(convertThis);
		return result;
	}

	// Convert Date to Timestamp
	public Timestamp dateToTimestamp(Date convertThis) {
		Timestamp ts = new Timestamp(convertThis.getTime());
		return ts;
	}

	public Date timestampToDate(Timestamp convertThis) {
		Date result = new Date(convertThis.getTime());
		return result;
	}
}
