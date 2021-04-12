package main.projects.timeElapse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class TestCalcTimeDiff {
	
	
	public static void main(String args[]) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.ENGLISH);
		Calendar start = Calendar.getInstance();
		
		
		String input = "2021-04-12 04:12 PM";
		start.setTime(sdf.parse(input));
		System.out.println(start);
		
		// theres no 29th, only 28
		input = "2021-02-29 04:12 PM";
		start.setTime(sdf.parse(input));
		System.out.println(start);
		
		// theres no 29th, only 28
		input = "2020-02-29 04:12 PM";
		start.setTime(sdf.parse(input));
		System.out.println(start);
		
		System.out.println();
		
		int year = 2022;
		int month = 1; //feb
		int day = 29;
		start.set(year, month, day);
		System.out.println(start);
	}
}
