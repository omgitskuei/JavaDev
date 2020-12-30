package main.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetRuntimeInput {
	// Local Fields
	private BufferedReader bReader;
	private String input;

	// Constructors
	// - Just opens reader
	public GetRuntimeInput() {
		System.out.println("BEGIN: util.GetRuntimeInput()");
		openReader();
	}
	// - Takes prompt, save user input, close reader
	public GetRuntimeInput(String prompt) {
		System.out.println("BEGIN: util.GetRuntimeInput(String)");
		try {
			openReader();
			// Print prompt
			System.out.println(prompt);
			// Input
			this.input = bReader.readLine();
			System.out.println("You entered: " + input);
			closeReader();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Methods
	private void openReader(){
		bReader = new BufferedReader(new InputStreamReader(System.in));
	}
	public void promptForInput(String prompt) {
		try {
			// Print prompt
			System.out.println(prompt);
			// Input
			this.input = bReader.readLine();
			
			System.out.println("You entered: " + this.input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String returnInput() {
		return this.input;
	}
	public void closeReader() {
		try {
			bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Executable
	public static void main(String args[]) throws IOException {
		// 2 ways to execute class:
		// 1) Only ask for input once
		GetRuntimeInput util1 = new GetRuntimeInput("Input String:");
		String input1 = util1.returnInput();
		util1.closeReader();

		// 2) Repeatedly ask for input with same reader, close reader later
		GetRuntimeInput util = new GetRuntimeInput();
		util.promptForInput("Input String:");
		String input = util.returnInput();
		util.closeReader();
	}
}
