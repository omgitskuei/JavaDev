package main.notes.dataTypes.timeRelated.MinguoDate;

import java.time.chrono.MinguoDate;

public class CreateMinguoDate {

	public static void main(String[] args) {
		
		MinguoDate md1 = MinguoDate.now();
		System.out.println(md1.toString() + "\r\n");
		// "Minguo ROC 111-07-10",
		
		MinguoDate m2 = MinguoDate.of(111, 01, 01);
		System.out.println(m2.toString() + "\r\n");
		// "Minguo ROC 111-01-01",
	}

}
