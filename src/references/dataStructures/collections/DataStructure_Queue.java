package references.dataStructures.collections;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class DataStructure_Queue {
	// fields
	/**
	 * @category LinkedList
	 * Linked List are linear data structures where elements are not stored in contiguous locations;
	 * every element is a separate object with a data part and address part
	 * elements (aka 'nodes') are linked using pointers and addresses.
	 * Due to the dynamicity and ease of insertions and deletions, they are preferred over the arrays. 
	 * It also has few disadvantages like the nodes cannot be accessed directly,
	 * instead we need to start from the head and follow through the link to reach to a node we wish to access.
	 * To store the elements in a linked list we use a doubly linked list which provides a linear data structure 
	 * and also used to inherit an abstract class and implement list and dequeue interfaces.
	 * In Java, LinkedList class implements the list interface; also consists of various constructors and methods like other java collections.
	 */
	private LinkedList<String> lQueue = new LinkedList<String>();
	
	/**
	 * @category PriorityQueue
	 * 
	 * 
	 */
	private Queue<String> pQueue  = new PriorityQueue<>();
	
	/**
	 * @category ArrayBlockingQueue
	 * 
	 * ArrayBlockingQueue class is a 'bounded blocking' queue; the size of the Queue is fixed/cannot be changed after creation.
	 * It does not allow NULL objects.
	 * ArrayBlockingQueue is thread safe.
	 * 
	 * [Adding to a full queue] or [Taking an element from empty queue] will result in the operation blocking.
	 * This queue orders elements FIFO (first-in-first-out). Head of queue = oldest, Tail of queue = newest.
	 * Can be initialized by passing:
	 * 		#1 int: capacity of queue (mandatory, others optional)
	 * 		#2 boolean: specified access policy
	 * 		#3 Collection: initially containing elements of another Collection
	 */
	private int capacity = 10;
	private Queue<String> bQueue = new ArrayBlockingQueue<>(capacity, true, pQueue);
	
	
	
	// constructors
	public DataStructure_Queue() {
		System.out.println("BEGIN: JavaTestEnvironment.src.references.DataStructure_List");
	}
	
	// methods
	
	// executable
	public static void main(String[] args) {
		DataStructure_Queue instance = new DataStructure_Queue();
		/*
		 * LinkedList
		 */
        // Adding elements to the linked list 
		instance.lQueue.add("A"); 
		instance.lQueue.add("B"); 		// A, B
		instance.lQueue.addLast("C"); 	// A, B, C
		instance.lQueue.addFirst("D"); 	// D, A, B, C
		instance.lQueue.add(2, "E"); 	// index starts at 0; D, A, E, B, C
		instance.lQueue.add("F"); 		// D, A, E, B, C, F
		instance.lQueue.add("G"); 		// D, A, E, B, C, F, G
        System.out.println("Linked list : " + instance.lQueue); 
  
        // Removing elements from the linked list 
        instance.lQueue.remove("B"); 	// D, A, E, C, F, G
        instance.lQueue.remove(3); 		// D, A, E, F, G
        instance.lQueue.removeFirst(); 	// A, E, F, G
        instance.lQueue.removeLast();	// A, E, F
        System.out.println("Linked list after deletion: " + instance.lQueue); 
  
        // Finding elements in the linked list 
        System.out.println("List contains the element 'E'? "+instance.lQueue.contains("E")); 
  
        // Number of elements in the linked list 
        System.out.println("Size of linked list = " + instance.lQueue.size()); 
  
        // Get and set elements from linked list 
        Object element = instance.lQueue.get(2); 
        System.out.println("Element returned by get(2) : " + element); 
        System.out.println("Element at index 2 set to 'Y'. "
        		+ "Previous value at index was; "+instance.lQueue.set(2, "Y"));	// returns the previous element at the index 
        System.out.println("Linked list after change : " + instance.lQueue); 
	}

}
