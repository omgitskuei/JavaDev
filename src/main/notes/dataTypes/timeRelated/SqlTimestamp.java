package main.notes.dataTypes.timeRelated;

import java.sql.Timestamp;
import java.util.Date;

import main.time.Instant;

public class SqlTimestamp {
	private Timestamp timestamp;
	public static void main(String[] args) {
		SqlTimestamp instance = new SqlTimestamp();
		instance.timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(instance.timestamp);		// YYYY-MM-DD HH:mm:ss.fff
		
		//method 2 - via Date
        Date date = new Date();
        System.out.println(new Timestamp(date.getTime()));

        //return number of milliseconds since January 1, 1970, 00:00:00 GMT
        System.out.println("milliseconds since 01/01/1970 00:00:00 GMT: ["+instance.timestamp.getTime()+"]");

        //convert java.sql.Timestamp to java.time.Instant
        Instant instant = instance.timestamp.toInstant();
        System.out.println("convert timestamp to instance: ["+instance.timestamp+"] and ["+instant+"]");
        // Convert java.time.Instant to java.sql.Timestamp
        Timestamp tsFromInstant = Timestamp.from(instant);
        System.out.println(tsFromInstant.getTime());
	}

}
