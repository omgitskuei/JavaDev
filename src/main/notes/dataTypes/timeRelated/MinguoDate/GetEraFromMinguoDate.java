package main.notes.dataTypes.timeRelated.MinguoDate;

import java.time.LocalDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.MinguoEra;

public class GetEraFromMinguoDate {

	public static void main(String[] args) {
		// Source https://o7planning.org/13767/java-minguodate
		
		MinguoDate minguoDate = MinguoDate.of(50, 3, 15);
		MinguoEra era =    minguoDate.getEra();

		System.out.printf("Minguo Date: %s, ISO Date: %s%n", minguoDate, LocalDate.from(minguoDate));
		System.out.println(" > Era: " + era.name() +", value: " + era.getValue());
		// Minguo Date: Minguo ROC 50-03-15, ISO Date: 1961-03-15
		// > Era: ROC, value: 1

		minguoDate = MinguoDate.of(-10, 3, 15);  
		era =    minguoDate.getEra();
		System.out.printf("%nMinguo Date: %s, ISO Date: %s%n", minguoDate, LocalDate.from(minguoDate));
		System.out.println(" > Era: " + era.name() +", value: " + era.getValue());
		// Minguo Date: Minguo BEFORE_ROC 11-03-15, ISO Date: 1901-03-15
		// > Era: BEFORE_ROC, value: 0
	}

}
