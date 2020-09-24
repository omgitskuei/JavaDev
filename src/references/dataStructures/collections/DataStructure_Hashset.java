package references.dataStructures.collections;

import java.util.HashSet;
import java.util.Iterator;

public class DataStructure_Hashset {
	private HashSet<String> hashsetStrings = new HashSet<String>();
	private HashSet<Integer> hashsetInts = new HashSet<Integer>();
	// private HashSet<String> hashsetStrings = new HashSet<String>();

	DataStructure_Hashset() {
		System.out.println();
	}

	public DataStructure_Hashset(HashSet<String> hashsetStrings, HashSet<Integer> hashsetInts) {
		super();
		System.out.println();
		this.hashsetStrings = hashsetStrings;
		this.hashsetInts = hashsetInts;
	}

	public static void main(String[] args) {
		DataStructure_Hashset instance = new DataStructure_Hashset();

		/**
		 * Collections can be converted to and from each other; eg. ArrayList to HashSet
		 */
		// Creating an empty HashSet
		instance.hashsetStrings = new HashSet<String>();
		// Use add() method to add elements into the HashSet
		// Remember; Repeats are ignored
		instance.hashsetStrings.add("Beoop");
		instance.hashsetStrings.add("To");
		instance.hashsetStrings.add("Geeks");
		instance.hashsetStrings.add("For");
		instance.hashsetStrings.add("Geeks");
		// Displaying the HashSet
		System.out.println("The HashSet: " + instance.hashsetStrings);
		// Creating the array and using toArray()
		Object[] arr = instance.hashsetStrings.toArray();
		System.out.println("The array is:");
		for (String element : instance.hashsetStrings) {
            System.out.println(element);
        }

		System.out.println(" * * * * * * * * * * * * * * * * ");
		/**
		 * It's possible to iterate through a HashSet but why would you without
		 * converting it to ArrayList first? Remember that HashSets are
		 * unordered/unsorted.
		 */
		// Creating an empty HashSet
		instance.hashsetStrings = new HashSet<String>();
		// Use add() method to add elements into the Set
		instance.hashsetStrings.add("Welcome");
		instance.hashsetStrings.add("To");
		instance.hashsetStrings.add("Geeks");
		instance.hashsetStrings.add("4");
		instance.hashsetStrings.add("Geeks");
		// Displaying the HashSet
		System.out.println("HashSet: " + instance.hashsetStrings);
		// Creating an iterator
		Iterator<String> value = instance.hashsetStrings.iterator();
		// Displaying the values after iterating through the set
		System.out.println("The iterator values are: ");
		while (value.hasNext()) {
			System.out.println(value.next());
		}
	}

}
