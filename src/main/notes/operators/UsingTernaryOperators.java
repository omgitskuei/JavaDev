/**
 * 
 */
package main.notes.operators;

/**
 * @author Kuei-Feng Tung
 *
 */
public class UsingTernaryOperators {
	private String logic="Apple";
	static String answerTrue;
	static String answerFalse;
	
	public UsingTernaryOperators() {
		System.out.println("UsingTernaryOperators");
		answerTrue="apple";
		answerFalse="orange";
	}
	
	@SuppressWarnings("unused")
    public static void main(String[] args) {
		UsingTernaryOperators instance = new UsingTernaryOperators();
		
		// Both Method #1 and #2 do the same thing
		
		// Method #1 - if-else statement
		String answer="";
		if (instance.logic.equals("Apple")) {
			answer = answerTrue;
		} else {
			answer = answerFalse;
		}
		System.out.println("\"Apple\".equals(\"Apple\") == true, so answer = "+answer);   // answer = "apple"
		
		// Method #2 - ternary operation 
		answer=instance.logic.equals("Dog") ? answerTrue : answerFalse;
		System.out.println("\"Apple\".equals(\"Dog\") == false, so answer = "+answer); // answer = "orange"
		
		// More examples;
		int intAnswer= (3>4) ? 2 : 3;
		System.out.println("3 > 4 == false, so answer = "+intAnswer);	// intAnswer = 3
		 
		char x = 'X';
		int i = 0;
		System.out.println(true ? x : 0);		// prints unicode character "X" (char)
		System.out.println(false ? i : x);		// prints decimal int 88 (int) ...
												// ... because Java has to pick one of many overloaded .print methods;
												// ... Java went with PrintStream.print(int) because the first variable i ...
												// ... is an int (i=0)
	}

}
