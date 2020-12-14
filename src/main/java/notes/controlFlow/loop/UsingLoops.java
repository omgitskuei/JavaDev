package main.java.notes.controlFlow.loop;

public class UsingLoops {

	public static void main(String[] args) {
		int count = 0;
		// for loop
		// is an Entry Control Loop
		// consumes the initialization, condition, and increment/decrement in one line
		for (int index=0;index<5;index++) {
			count++;
			System.out.println("For loop x"+count);
		}
		
		// enhanced for loop
		// provides a simpler way to iterate through elements of a collection or array
		// Note: inflexible, must iterate in sequential manner, cannot know the current index
		// Thus, should be used only when you want sequential without references
		count=0;
		int[] array = {1, 2, 3, 4, 5};
		for (int x : array) {
			count++;
			System.out.println("Enhanced for loop x"+count);
			if (x%2==0) {
				System.out.print(x+" is even ");
			} else {
				System.out.print(x+" is odd ");
			} 
		}
		
		// while loop
		// is an Entry Control Loop
		// can be thought of as a repeating if statement
		count=0; 
		int index = 0;
		while (index<5) {
			count++;
			index++;
			System.out.println("While loop x"+count);
		}
		
		// do-while loop
		// is an Exit Control Loop
		// notice how it's still executed ONCE even though its condition [index=6, (index<5)] fails
		count=0;
		index = 6;
		do {
			count++;
			index++;
			System.out.println("Do-While loop x"+count);
		} while (index<5);

	}

}
