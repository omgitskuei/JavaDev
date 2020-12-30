package main.exercises;

public class UsingSingletonDesign {
	// You want to create a single instance/object of a class
	// ... and that single object will be used by other classes
	// ... repeatedly without more than one ?˜new?? command.

	// Singleton design pattern requires:
	// 1) static member
	// 2) private constructor
	// 3) static factory method

	// Static member - Lazy Initialization
//	private static UsingSingletonDesign instance;
	// Static member - Eager Initialization
//	private static UsingSingletonDesign instance = new UsingSingletonDesign();

	// Local variables
	private int id = 0;
	public boolean valid = true;

	// [private] Constructor
	private UsingSingletonDesign() {
		System.out.println("Created a new singleton instance!");
	}

	// Getter/Setters
	public void setID(int newID) {
		SingletonHelper.instance.id = newID;
	}

	public int getID() {
		return SingletonHelper.instance.id;
	}

	// Bill Pugh Singleton Implementation
	private static class SingletonHelper{
		private static final UsingSingletonDesign instance = new UsingSingletonDesign();
	}
	
	public static UsingSingletonDesign getInstance() {
		// Factory Method - Lazy Initialization
//		if (instance == null) {
//			instance = new UsingSingletonDesign();
//		} 
		//  Bill Pugh Singleton Implementation doesn't require synchronization
		return SingletonHelper.instance;
	}
	// Factory Method - Eager Initialization
//	public static synchronized UsingSingletonDesign getInstance() {
//		return instance;
//	}
}

// Main class
class Main {
	// Entry Point
	public static void main(String[] args) {
		UsingSingletonDesign firstInstance = UsingSingletonDesign.getInstance(); // initially ID = 0
		firstInstance.setID(3); // ID now = 3
		System.out.println(firstInstance.getID());

		UsingSingletonDesign secondInstance = firstInstance; // 2 variables referencing same instance
		firstInstance.setID(2); // ID now = 2 FOR BOTH variables
		System.out.println(secondInstance.getID()); // ID is 2, is NOT 3

		System.out.println(UsingSingletonDesign.getInstance().getID()); // Without making new variable, same result ID =
																		// 2
		UsingSingletonDesign.getInstance().valid = false; // Editing its public variable
		System.out.println(secondInstance.valid); // valid = false

		System.out.println(firstInstance == secondInstance); // true
		System.out.println(firstInstance.equals(secondInstance)); // true

	}
}
