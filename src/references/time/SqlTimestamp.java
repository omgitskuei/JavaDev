package references.time;

import java.sql.Timestamp;

public class SqlTimestamp {
	private Timestamp timestamp;
	public static void main(String[] args) {
		SqlTimestamp instance = new SqlTimestamp();
		instance.timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(instance.timestamp);		// YYYY-MM-DD HH:mm:ss.fff
	}

}
