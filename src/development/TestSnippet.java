package development;

import java.util.ArrayList;

public class TestSnippet {

	public TestSnippet() {
		
	}
	
	public static void main(String[] args) {
		//TestSnippet instance = new TestSnippet();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
	    numbers.add(5);
	    numbers.add(9);
	    numbers.add(8);
	    numbers.add(1);
	    numbers.forEach( (n) -> { System.out.println(n); } );
		
	}
	

	
}
