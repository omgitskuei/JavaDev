package main.notes.variableArguments;

import java.util.HashMap;
import java.util.Map;

public class loopVarArgs {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		HashMap m = new HashMap();
		m.put("age", 23);
		m.put("name", "Bob");
		m.put("job", "Janitor");
		
		HashMap m1 = new HashMap();
		m1.put("age", 14);
		m1.put("name", "Patrick");
		m1.put("job", "Poop deck swashbuckler");
		
		// You could do this instead of passing List<Map> to loop over
		loopHashMaps(m, m1);
	}
	
	@SuppressWarnings("unused")
	private static void loopInts(int ... ints) {
		for (int i : ints) {
			System.out.print(i + " ");
		}
	}
	
	@SuppressWarnings("unused")
	private static void loopStrings(String ... strings) {
		for (String s : strings) {
			System.out.print(s + " ");
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static void loopHashMaps(HashMap ... maps) {
		for (Map s : maps) {
			System.out.println(s + " ");
		}
	}
}
