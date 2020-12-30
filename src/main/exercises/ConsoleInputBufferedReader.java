package main.exercises;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputBufferedReader {

	// Take one line of user input (using BufferedReader)
	// Print input out
	public static void main(String[] args) {
		
		// Local variables
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		try {
			
			s = br.readLine();
			//int i = Integer.parseInt(UsingSuper);
			
			System.out.println(s);
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
