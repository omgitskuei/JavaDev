package main.notes.classes;

import java.util.ArrayList;
import java.util.List;

public class ClassHierarchy {

	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) {
		// ArrayList extends List, ArrayList is lower down the hierarchy
		
		// class1.isAssignableFrom(class2), where class1 is SUPER
		
		System.out.println(
			ArrayList.class.isAssignableFrom(List.class)		// false
		);
		
		System.out.println(
			List.class.isAssignableFrom(ArrayList.class)		// true
		);
		
		// same class
		System.out.println(
			ArrayList.class.isAssignableFrom(ArrayList.class)	// NOTE: true
		);
		
		// it's like saying (below) See how ArrayList can be ASSIGNED to List ?
		List a = new ArrayList();
		
		// see how same class can be assigned to same class
		ArrayList b = new ArrayList();
	
	}

}
