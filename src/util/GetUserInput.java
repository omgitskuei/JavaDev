package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetUserInput {
	// Local Fields
	BufferedReader br;
	String s;
	
	// Constructors
	public GetUserInput() {
		// Local variables
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public String getUserInputBR() {
		try {
			s = br.readLine();
			System.out.println("You entered: "+s);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
}
