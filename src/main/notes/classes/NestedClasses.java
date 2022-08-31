package main.notes.classes;

public class NestedClasses {
	
	// Fields
	int outerClassInt = 3;
	
	class InnerClass {
		// Fields
		int innerClassInt = 4;
	}
	
	static class StaticInnerClass {
		int stInnerClassInt = 5;
	}
	
	public static void main(String[] args) {
		NestedClasses outerClass = new NestedClasses();

		NestedClasses.InnerClass innerClass = outerClass.new InnerClass();
		
		// Accessing information inside nested classes
		System.out.println(outerClass.outerClassInt);	// 3
		System.out.println(innerClass.innerClassInt);	// 4
		
		NestedClasses.StaticInnerClass stInnerClass = new StaticInnerClass();
		System.out.println(stInnerClass.stInnerClassInt);	// 5
	}
}
