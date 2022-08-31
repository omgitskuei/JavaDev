package main.notes.memory.passByValue;

public class PassingByValueWithMethods {
	
	/*
	 * Pass by Reference - when you call fillCup(cup1, "coffee"), you are actually passing
	 * the Original Cup object cup1 to the method.
	 * Whatever changes made to the Cup object passed to fillCup(...) will also change cup1
	 * outside the method (WITHOUT the need for a return statement and an explicit
	 * overwriting of cup1 with the returned results).
	 * 
	 * Pass by Value - when you call fillCup(cup1, "coffee"), you pass a copy of cup1.
	 * Changes made to the Cup object passed to fillCup(...) will not change cup1
	 * outside the method (UNLESS you explicitly overwrite cup1 with the returned results,
	 * assuming fullCup(...) has a return statement).
	 */
	
	private int aFieldInt = 7;
	
	public static void main(String[] args) {
		int num1 = 2;	// Variables provide comfort to the programmer allowing them to let
		int num2 = 3;	// the OS handle where data is physically located in memory
		PassingByValueWithMethods testing = new PassingByValueWithMethods();
		
		testing.noReturnSum(num1, num2);
		System.out.println(num1);	// 2, unchanged
		
		testing.noReturnSum(testing.aFieldInt, num2);
		System.out.println(testing.aFieldInt);	// 7, unchanged
		
		/*
		 * If Java was a pass-by-reference language, num1 would be 5 and aFieldInt would be 7
		 */
	}
	
	private void noReturnSum(int firstNum, int secNum) {
		firstNum = firstNum + secNum;
		// no return statement
	}
	
}
