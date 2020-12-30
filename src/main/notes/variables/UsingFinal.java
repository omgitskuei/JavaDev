package main.notes.variables;

import java.util.ArrayList;

public class UsingFinal {

	/**
	 * Final Variables; Variables with the final keyword can't be modified so they
	 * must be initialized. If the final variable is a reference, this means that
	 * the variable cannot reference another object, THAT SAID, internal state of
	 * the object pointed by that reference variable can be changed i.e. you can add
	 * or remove elements from final array or final collection. It is good practice
	 * to represent final variables in all UPPERCASE_CASE, using underscore to
	 * separate words. We must initialize a final variable, otherwise compiler will
	 * throw compile-time error. A final variable can only be initialized once.
	 **/

	/**
	 * When to use a final variable :
	 * 
	 * The only difference between a normal variable and a final variable is that we
	 * can re-assign value to a normal variable but we cannot change the value of a
	 * final variable once assigned. Hence final variables must be used only for the
	 * values that we want to remain constant throughout the execution of program.
	 */

	/**
	 * @see Non-transivity
	 * 
	 *      As you know that a final variable cannot be re-assign. But in case of a
	 *      reference final variable, internal state of the object pointed by that
	 *      reference variable can be changed. Note that this is not re-assigning.
	 *      The non-transitivity property also applies to arrays. This property of
	 *      final is called non-transitivity.
	 */

	/**
	 * Differences between C++ & Java
	 * const variables in C++ must be assigned a value when declared. For final
	 * variables in Java, it is not necessary as we see in above examples. A Java final
	 * variable can be assigned value later, but only once.
	 */
	
	
	public static void main(String[] args) {
		UsingFinalSubclass anInstance = new UsingFinalSubclass(2);
		System.out.println("using a blank final variable initialized via 1st constructor:\n" + anInstance.PI);

		System.out.println("using a final variable initialized directly:\n" + anInstance.directPI);

		UsingFinalSubclass anotherInstance = new UsingFinalSubclass("3");
		System.out.println("using a blank final variable initialized via 2nd constructor:\n" + anotherInstance.PI);

		System.out.println(
				"using a blank static final variable initialized via static block:\n" + UsingFinalSubclass.staticPI);

		/**
		 * Non-Transivity
		 */

		// a final reference variable sb
		final StringBuilder sb = new StringBuilder("Geeks");
		System.out.println(sb);

		// changing internal state of object reference by final reference variable sb
		sb.append("ForGeeks");
		System.out.println(sb);
		
		
		// What happens when you create a 'final' object but that object is mutable?
		final ArrayList<String> list = new ArrayList<String>();
		list.add("asd");
		System.out.println(list);			// prints [asd], still mutable
		// list = new ArrayList<String>();	// THIS FAILS, cannot redeclare as it's final
	}
}

class UsingFinalSubclass {
	/*
	 * Three ways to initialize a final variable: 1. direct initialize - You can
	 * initialize a final variable when it is declared.This approach is the most
	 * common. 2. initialize inside constructor 3. initialize inside static block
	 */

	// direct initialize;
	public final double directPI = 3.0;

	/**
	 * A final variable is called blank final variable, if it is not initialized
	 * while declaration.
	 */

	// blank final variable
	public final double PI;
	public static final double staticPI;

	/*
	 * A blank final variable can be initialized inside constructor OR inside static
	 * block. If you have more than one constructor then it must be initialized in
	 * all of them
	 */

	// constructors
	public UsingFinalSubclass(int decimals) {
		if (2 == decimals) {
			PI = 3.14;
		} else {
			PI = 3.1;
		}
	}

	public UsingFinalSubclass(String decimals) {
		if ("3".equals(decimals)) {
			PI = 3.141;
		} else {
			PI = 3.1;
		}
	}

	// static block
	static {
		staticPI = 3.1415;
	}
}
