package main.java.notes.controlFlow.tryCatch;


public class UsingNestedTryCatch {

	public static void main(String[] args) {
		
		System.out.println("Example 1: Outer catch won't trigger if Inner catch have caught all thrown Exceptions");
		try {
			
			try {
				String a = null;
				String b = a.trim(); 				// NullPointerException thrown
			} catch (NullPointerException e) {		// caught
				System.out.println("Inner catch: Caught " + e.getClass());
			}
			
			try {
				String c = "4C23!";
				Integer d = Integer.parseInt(c);	// NumberFormatException thrown
			} catch (NumberFormatException e) {		// caught
				System.out.println("Inner catch: Caught " + e.getClass());
			}
			
		} catch (Exception e) {						// NOTE: all Exceptions caught already so outer catch didn't trigger
			System.out.println("Outer catch: Caught " + e.getClass());
		}
		System.out.println();
		
		
		System.out.println("Example 2: Outer catch triggers if Inner catch did not catch all thrown Exceptions");
		try {
			
			try {
				String a = null;
				String b = a.trim(); 				// NullPointerException thrown
			} catch (NullPointerException e) {		// caught
				System.out.println("Inner catch: Caught " + e.getClass());
			}
			
			String c = "4C23!";
			Integer d = Integer.parseInt(c);		// NumberFormatException thrown
													// ! uncaught !
			
		} catch (Exception e) {						// NOTE: outer catch triggers
			System.out.println("Outer catch: Caught " + e.getClass());
		}
		System.out.println();
		
		
		System.out.println("Example 3: Both Inner and Outer catch will trigger if Inner catch throws new Exception");
		try {
			
			try {
				String a = null;
				String b = a.trim(); 				// NullPointerException thrown
			} catch (NullPointerException e) {		// caught
				System.out.println("Inner catch: Caught " + e.getClass());
				throw new Exception();				// threw a new one
			}
			
		} catch (Exception e) {						// NOTE: outer catch triggers
			System.out.println("Outer catch: Caught " + e.getClass());
		}
		System.out.println();
		
		
		System.out.println("Example 4: Impossible to throw multiple new Exceptions");
//		try {
//			
//			try {
//				String a = null;
//				String b = a.trim(); 				// NullPointerException thrown
//			} catch (NullPointerException e) {		// caught
//				System.out.println("Inner catch: Caught " + e.getClass());
//				throw new Exception();				// threw multiple new exceptions
//				throw new IOException();			// ! impossible, unreachable code !
//			}
//			
//		} catch (Exception e) {						// NOTE: outer catch triggers
//			System.out.println("Outer catch: Caught " + e.getClass());
//		}
		System.out.println();
		
		
		System.out.println("Example 5: Only the First uncaught Exception thrown is caught by any one Catch");
		try {
			
			String a = null;
			String b = a.trim(); 					// NullPointerException thrown
													// caught, code escapes to catch-block
													// later lines of try-block are unreachable
			String c = "4C23!";
			Integer d = Integer.parseInt(c);		// NumberFormatException thrown
													// ! uncaught !
			
		} catch (Exception e) {						// NOTE: this catch triggers
			System.out.println("Outer catch: Caught " + e.getClass());
		}
		System.out.println();
		
		
		System.out.println("Example 6: One Try can have Multiple Catch-blocks, but must respect hierarchy of Exceptions' scopes");
		try {
			
			String a = null;
			String b = a.trim(); 					// NullPointerException thrown
													// caught, code escapes to catch-block
													// later lines of try-block are unreachable
			String c = "4C23!";
			Integer d = Integer.parseInt(c);		// NumberFormatException thrown
													// ! uncaught !
			
		} catch (NumberFormatException e) {			// NOTE: this catch DOES NOT trigger, thrown NullPointerException != NumberFormatException
			System.out.println("Outer catch1: Caught " + e.getClass());
		} catch (NullPointerException e) {			// NOTE: this catch triggers
			System.out.println("Outer catch2: Caught " + e.getClass());
		} catch (Exception e) {						// NOTE: this catch DOES NOT trigger, thrown exception already caught
			System.out.println("Outer catch3: Caught " + e.getClass());
		}
		System.out.println();

		
		System.out.println("Example 7: Multiple Catch-blocks must respect hierarchy of Exceptions' scopes");
		try {
			
			String a = null;
			String b = a.trim(); 					// NullPointerException thrown
													// caught, code escapes to catch-block
													// later lines of try-block are unreachable
			String c = "4C23!";
			Integer d = Integer.parseInt(c);		// NumberFormatException thrown
													// ! uncaught !
			
		} catch (NumberFormatException e) {			// NOTE: this catch DOES NOT trigger, thrown NullPointerException != NumberFormatException
			System.out.println("Outer catch1: Caught " + e.getClass());
		} catch (Exception e) {						// NOTE: this catch DOES triggers, Exception scope includes NullPointerException
			System.out.println("Outer catch2: Caught " + e.getClass());
		} 
//		catch (NullPointerException e) {			// NOTE: this catch NEVER triggers because of above catch (Exception e)
//			System.out.println("Outer catch3: Caught " + e.getClass());
//		}
		System.out.println();
	}
}
