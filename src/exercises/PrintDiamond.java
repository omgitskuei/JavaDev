package exercises;

public class PrintDiamond {

	public static void main(String[] args) {
		
		int row = 10;
		for (int index=0;index<row-1;index++) {
			
			// create increasingly wide triangle
			if (index<6) {
				System.out.println("    * * * * *     ".substring(index, 5 + 2*index));

			// create decreasingly wide triangle
			} else {
				int countdown = row-index;
				System.out.println("    * * * * *     ".substring(countdown-2,5+(2*countdown)-3));
			}
		}
	}
}
