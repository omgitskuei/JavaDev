package main.notes.variables;

import java.math.BigDecimal;

public class PrintVariableClass {
	
	
	// Constructors
	public PrintVariableClass () {
		System.out.println("BEGIN: References.Operators.Misc.UsingInstanceOf()");
	}
	
	public void printClass(Object var) {
		// Set local field to passed variable
		System.out.println("Passed ["+String.valueOf(var)+"],     var's class = "+var.getClass());
	}
	
	public static void main(String[] args) throws Throwable {
		PrintVariableClass thisClass = new PrintVariableClass();
		thisClass.printClass("300");		// string
		thisClass.printClass(300);			// int
		thisClass.printClass(300.0);		// double
		thisClass.printClass(300F);			// float
		thisClass.printClass(300L);			// long
		thisClass.printClass(new BigDecimal("300.0"));
	}

}
