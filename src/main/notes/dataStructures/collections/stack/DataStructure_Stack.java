package main.notes.dataStructures.collections.stack;

import java.util.*;

public class DataStructure_Stack {
// Stack is a subclass of Vector that implements a standard last-in, first-out stack (LIFO).
// Apart from the methods inherited from its parent class Vector, Stack defines the following methods;
	// boolean empty() - Tests if this stack is empty. Returns true if empty, false
	// if stack contains elements.
	// Object peek() - Returns the element on the top of the stack, but DOES NOT
	// REMOVE it.
	// Object pop() - Returns the element on the top of the stack, and REMOVE it.
	// Object push(Object element) - Pushes the element onto the stack. Element is
	// also returned.
	// int search(Object element) - Searches for element in the stack.
	// - If FOUND, its offset from the top of the stack is returned.
	// - If NOT FOUND, -1 is returned.

	// Local fields

	// Constructors
	public DataStructure_Stack() {
		System.out.println("BEGIN: util.DataStructure_Stack()");
	}

	@SuppressWarnings("rawtypes")
	public static void main(String args[]) {
		// Create new empty Stack
		Stack thisStack = new Stack();

		DataStructure_Stack n = new DataStructure_Stack(); // Methods not static, so need to instantiate Class

		// Add 3 numbers to the stack
		System.out.println("stack: " + thisStack);
		n.showpush(thisStack, 42);
		n.showpush(thisStack, 66);
		n.showpush(thisStack, 99);
		System.out.println();

		// Look at top of stack
		System.out.println("peek: " + thisStack.peek());
		System.out.println();

		// Search(Object element)
		int search0 = thisStack.search(66); // Searches stack for num 66, returns 2, 2nd index from top
		System.out.println("searh(66):" + search0);
		int search1 = thisStack.search(33333); // Doesn't exist in stack, returns -1
		System.out.println("search(33333):" + search1);
		System.out.println();

		// Remove all 3 numbers from stack
		System.out.println("stack: " + thisStack);
		n.showpop(thisStack);
		n.showpop(thisStack);
		n.showpop(thisStack);
		System.out.println();

		// Check if stackName.empty() == true, false for notEmpty
		System.out.println(n.checkEmpty(thisStack));
		System.out.println();

		try {
			n.showpop(thisStack);
		} catch (EmptyStackException e) {
			System.out.println("empty stack");
		}
	}

	
	// Methods
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int showpush(Stack thisStack, int thisInt) {
		// Object push(Object element)
		System.out.println("push(" + thisInt + ")");
		System.out.println("stack: " + thisStack);

		return (int) thisStack.push(new Integer(thisInt)); // It'UsingSuper returned as a rawtype, so need to (typeCast) it
	}

	@SuppressWarnings("rawtypes")
	public int showpop(Stack thisStack) {
		// Object pop()

		System.out.println("pop -> " + thisStack.pop());
		System.out.println("stack: " + thisStack);

		return (Integer) thisStack.pop(); // See how it'UsingSuper (typeCast) as Integer
	}

	@SuppressWarnings("rawtypes")
	public boolean checkEmpty(Stack thisStack) {
		return thisStack.empty();
	}
}