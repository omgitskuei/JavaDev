package main.notes.dataStructures.array;

public class PrintingArrays {

	public static void main(String[] args) {
		String[] anArray = new String[] { "test1", "test2", "test3" };
		
		PrintingArrays thisClass = new PrintingArrays();
		
		// both do the same thing
		System.out.println(thisClass.toString(anArray));
		thisClass.print(anArray);
	}
	
	// Overloading
	public String toString(int anArray[]) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < anArray.length; i++) {
			if(i!=0) {
				sb.append(", ");
			}
			sb.append(String.valueOf(anArray[i]));
		}
		sb.append("]");
		return sb.toString();
	}
	// Overloading
	public String toString(Object anArray[]) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < anArray.length; i++) {
			if(i!=0) {
				sb.append(", ");
			}
			sb.append(String.valueOf(anArray[i]));
		}
		sb.append("]");
		return sb.toString();
	}
	
	
	private void print(String anArray[]) {
		System.out.print("[");
		for (int i = 0; i < anArray.length; i++) {
			if(i!=0) {
				System.out.print(", ");
			}
			System.out.print(anArray[i]);
		}
		System.out.println("]");
	}

}
