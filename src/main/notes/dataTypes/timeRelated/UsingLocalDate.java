package main.notes.dataTypes.timeRelated;

import main.time.LocalDate;

public class UsingLocalDate {

	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
//		LocalDate date0 = LocalDate.now("TW");
		LocalDate date1 = LocalDate.of(2020, 12, 31);
	}

}
