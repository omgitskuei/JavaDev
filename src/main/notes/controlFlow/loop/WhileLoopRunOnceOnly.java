package main.notes.controlFlow.loop;

import java.util.ArrayList;
import java.util.Iterator;

public class WhileLoopRunOnceOnly {
	
	private static ArrayList<String> listStr = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		
		
		
		System.out.println(listStr.iterator().hasNext());
		System.out.println(listStr.iterator().next());
		
		ArrayList al = new ArrayList();
	      
	      // add elements to the array list
	      al.add("C");
	      al.add("A");
	      al.add("E");
	      al.add("B");
	      al.add("D");
	      al.add("F");

	      // Use iterator to display contents of al
	      System.out.print("Original contents of al: ");
	      Iterator itr = al.iterator();
	      
	      while(itr.hasNext()) {
	         Object element = itr.next();
	         System.out.print(element + " ");
	      }
	      System.out.println();
	}

}
