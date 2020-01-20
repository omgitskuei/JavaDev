package references;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputBufferedReader {

	public static void main(String[] args) {
		
		// Local variables
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		try {
			
			s = br.readLine();
			//int i = Integer.parseInt(s);
			
			System.out.println(s);
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
