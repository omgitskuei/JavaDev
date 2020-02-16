package util;

public class RepeatUntilCorrect {
	// Local fields
	
	
	// Constructors
	
	
	// Executable - Repeat prompt user until input is valid
	public static void main(String[] args) {
		
	}
	
	// Methods
	public void repeatTest(int maxRepeat) {
		try {
			GetRuntimeInput util = new GetRuntimeInput();
			boolean result=false;
			while (!(result) && maxRepeat>0) {
				// Prompt user
				util.promptForInput("Input String:");
				String input = util.returnInput();
				// Define what is "valid"
				//result = ;		// 'true' to break loop
				// Fail-safe to stop infinite-loops
				maxRepeat--;
				System.out.println("Tries left: "+maxRepeat);
			}
			util.closeReader();
			System.out.println("Finished While-Loop: "+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
