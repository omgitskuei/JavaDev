package main.notes.dataStructures.collections.vector;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

public class UsingVector {
	/*
	 * The Vector class implements a growable array of objects. Vectors fall in
	 * legacy classes, but now it is fully compatible with collections. It is found
	 * in java.util package and implement the List interface
	 * 
	 * - 2022/05/13, https://www.geeksforgeeks.org/java-util-vector-class-java/
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		
		// Vector Constructors:
		// 1. Vector(): Creates a default vector of the initial capacity is 10.
		Vector<String> v1 = new Vector<String>();
		
		// 2. Vector(int size): Creates a vector whose initial capacity is specified by size.
		Vector<String> v2 = new Vector<String>(5);
		
		// 3. Vector(int size, int incr): Creates a vector whose initial capacity is specified by size and increment is specified by incr. 
		// It specifies the number of elements to allocate each time a vector is resized upward.
		Vector<String> v3 = new Vector<String>(3, 1);
		
		// 4. Vector(Collection c): Creates a vector that contains the elements of collection c.
		Vector<String> v4 = new Vector<String>(v3);
		
		
		// Examples:
		Vector v = new Vector();
		v.add("aString");
		v.add(1);
		v.add(String.valueOf(new Timestamp(System.currentTimeMillis())));
		v.add(1, 3); // Note placing a int 3 at index 1 DOES NOT OVERWRITE the int 1 at index 1
		System.out.println(v); // "[aString, 3, 1, 2022-05-13 09:00:07.626]"

		ArrayList a = new ArrayList();
		a.add("aString");
		a.add(1);
		a.add(String.valueOf(new Timestamp(System.currentTimeMillis())));
		a.add(1, 3); // same here, no overwriting
		System.out.println(a); // [aString, 3, 1, 2022-05-13 09:01:45.228]
		// ^ the two output the same result

		// Whats the difference?
		/*
		 * They are very similar to ArrayList, but Vector is synchronized and has some
		 * legacy methods that the collection framework does not contain. It also
		 * maintains an insertion order like an ArrayList. Still, it is rarely used in a
		 * non-thread environment as it is synchronized, and due to this, it gives a
		 * poor performance in adding, searching, deleting, and updating its elements.
		 * The Iterators returned by the Vector class are fail-fast. In the case of
		 * concurrent modification, it fails and throws the
		 * ConcurrentModificationException.
		 * 
		 * Important points regarding the Increment of vector capacity are as follows:
		 * 
		 * If the increment is specified, Vector will expand according to it in each
		 * allocation cycle. Still, if the increment is not specified, then the vector’s
		 * capacity gets doubled in each allocation cycle. Vector defines three
		 * protected data members:
		 * 
		 * int capacityIncreament: Contains the increment value. int elementCount:
		 * Number of elements currently in vector stored in it. Object elementData[]:
		 * Array that holds the vector is stored in it. Common Errors in the declaration
		 * of Vectors are as follows:
		 * 
		 * Vector throws an IllegalArgumentException if the InitialSize of the vector
		 * defined is negative. If the specified collection is null, It throws
		 * NullPointerException.
		 */
	}

}
