package main.exercises;

public class PrintMultiplicationTable {

	public static void main(String[] args) {
//		for (int index = 1; index < 10; index++) {
//			for (int index1 = 1; index1 < 10; index1++) {
//					System.out.print(index + "*" + index1 + "=" + index * index1 + "\t");
//			}
//			System.out.println("");
//		}

		for (int index = 9; index > 0; index--) {
			for (int index1 = 1; index1 < 10; index1++) {
				if(index1>index) {
					break;
				}
					System.out.print(index + "*" + index1 + "=" + index * index1 + "\t");
			}
			System.out.println("");
		}
		
	}

}
