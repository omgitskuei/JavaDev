package references;
import java.util.Scanner; 

public class ConsoleInputScanner {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		//int i = scan.nextInt();
		
		System.out.println(s);
		scan.close();
	}
	
	
}
