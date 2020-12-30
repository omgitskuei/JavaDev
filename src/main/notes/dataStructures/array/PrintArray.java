package main.notes.dataStructures.array;

public class PrintArray {

	public static void main(String[] args) {
		String[] anArray = new String[] { "test1", "test2", "test3" };
		
		PrintArray thisClass = new PrintArray();
		
		thisClass.print(anArray);
		
		System.out.println(thisClass.toString(anArray));
	}
	
	public String toString(String anArray[]) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < anArray.length; i++) {
			if(i!=0) {
				sb.append(", ");
			}
			sb.append(anArray[i]);
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
