package references;

import java.util.ArrayList;
import java.util.List;

public class UsingAutoboxing {
	// Autoboxing is the automatic conversion that the Java compiler makes between
	// the primitive types and their corresponding object wrapper classes. For
	// example, converting an int to an Integer, a double to a Double, and so on. If
	// the conversion goes the other way, this is called unboxing.

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Boolean - boolean
		boolean result = Boolean.TRUE;
		Boolean result1 = true;
		Boolean result2 = Boolean.valueOf(true);

		// Integer - int
		int int1 = 0;
		Integer int2 = 0;
		
		// Character - char
		char char1 = 'a';
		Character ch = 'a';
		
		
		// no compile-time error is issued despite adding int to Integer - autoboxing 
		List<Integer> li = new ArrayList<>();
		for (int i = 1; i < 50; i += 2)
		    li.add(i);
		
		Integer s = sumEven(li);
		
	}
	
	public static int sumEven(List<Integer> li) {
	    int sum = 0;
	    for (Integer i: li)
	    	// the remainder (%) and unary plus (+=) operators do not apply to Integer objects
	    	if (i % 2 == 0)
	            sum += i;
	    	// but no compile-time error is issued because at runtime i is converted to i.intValue()
	        return sum;
	}
	
	public static int sumEvenAtRuntime(List<Integer> li) {
	    int sum = 0;
	    for (Integer i : li)
	    	// i at runtime;
	        if (i.intValue() % 2 == 0)
	            sum += i.intValue();
	        return sum;
	}

}
