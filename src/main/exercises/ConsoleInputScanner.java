package main.exercises;
import java.util.Scanner; 

public class ConsoleInputScanner {
	
	// Take one line of user input (using Scanner)
	// Print input out
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		//int i = scan.nextInt();
		
		System.out.println(s);
		scan.close();
	}
	
	
}
