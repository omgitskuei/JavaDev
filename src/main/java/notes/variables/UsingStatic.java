package main.java.notes.variables;

import java.util.HashMap;

/**
 * 'static' means that the static object belongs specifically to the class
 * ITSELF, instead of INSTANCES of that class.
 * 
 * Variables, methods, and nested classes can be static. A static class is
 * really a class within a class, often called static nested classes.
 * 
 * Applications: Eg.1 For example, my Tour class has a list of possible
 * destinations from the DB. These choices change depending on what the tour
 * company is offering that year.
 * 
 * 
 * 
 * Eg.2
 * 
 * 
 * @author Chris
 *
 */

public class UsingStatic {

	// Constructor
	public UsingStatic() {
		Tours.destinations = new HashMap<String, String>();
	}

	// A static nested class
	public static class Tours {
		private static HashMap<String, String> destinations;

		// replace code with label
		private static String codeIsPresent(String key, HashMap<String, String> map) {

			// check if map is initialized
			if (map == null) {
				System.out.println("Passed map was null, initializing map");
				// initialize map
				map = new HashMap<String, String>();
				// populate this code map
				map.put("KR", "Korea");
				map.put("JP", "Japan");
				map.put("CN", "China");
			}
			// check if code is in map
			String label = key;
			if (map.containsKey(key)) {
				System.out.println("Match found");
				// code is in map
				label = map.get(key);
			} else {
				System.out.println("Match not found");
			}
			return label;

		}
	}

	public static void main(String[] args) {
		Tours t = new Tours();
		
		System.out.println((Tours.destinations == null) ? "It's null" : "It's empty"); // "null" will trigger

		String b = Tours.codeIsPresent("TW", Tours.destinations); // null, initializing, match not found; "TW"
		System.out.println(b);

		/**
		 * Notice; null will trigger even though I initialized inside the codeIsPresent
		 * method meaning initializing inside a method only has life cycle inside method
		 * Outside of method, it's still null.
		 */
		System.out.println((Tours.destinations == null) ? "It's null" : "It's empty"); // "null" will trigger

		b = Tours.codeIsPresent("KR", Tours.destinations); // null, initializing, match found; "Korea"
		System.out.println(b);

		// Notice how the static class Tours does not need to be initialized with 'new'
		// initialized outside methods
		Tours.destinations = new HashMap<String, String>();

		// Notice; empty will trigger, meaning changes stayed
		System.out.println((Tours.destinations == null) ? "It's null" : "It's empty");
		
		// Notice; the method doesn't return Passed map was null
		b = Tours.codeIsPresent("KR", Tours.destinations);
		System.out.println(b);
	}

}
