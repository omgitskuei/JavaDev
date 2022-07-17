package main.notes.dataTypes.timeRelated.MinguoDate;

import java.time.chrono.MinguoDate;

public class ComparingMinguoDates {

	public static void main(String[] args) {
		/*
		 * Comparing two Minguo Dates
		 */
		MinguoDate mdNow = MinguoDate.now();
		MinguoDate mdOther = MinguoDate.of(111, 07, 11);
		
		System.out.println(mdNow + "	isBefore	" + mdOther + "		=	" + mdNow.isBefore(mdOther));
		// false
		System.out.println(mdNow + "	isAfter		" + mdOther + "		=	" + mdNow.isAfter(mdOther));
		// true
		System.out.println(mdNow + "	isAfter		" + mdNow + "		=	" + mdNow.isAfter(mdNow)); // same date!
		// false
		System.out.println(mdNow + "	isEqual		" + mdNow + "		=	" + mdNow.isEqual(mdNow)); // same date!
		// true
		
		// x.compareTo(y), x>y where greater='more recent'
		System.out.println(mdNow.compareTo(mdOther));
		// 1
		System.out.println(mdOther.compareTo(mdNow));
		// -1
		System.out.println(mdNow.compareTo(mdNow));
		// 0
		
		new ComparingMinguoDates().printCompareTo(mdNow, mdOther);
		// md1 is more recent
		
		
	}
	
	private void printCompareTo(MinguoDate md1, MinguoDate md2) {
		int result = md1.compareTo(md2);
		switch (result) {
		case 0:
			System.out.println("md1 and md2 are equal");
			break;
		case 1:
			System.out.println("md1 is more recent");
			break;
		case -1:
			System.out.println("md2 is more recent");
			break;
		default:
			break;
		}
	}
}
