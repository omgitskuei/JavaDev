package main.java.notes.dataStructures.collections.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataStructure_List {
	// fields
	private List<Object> list;
	private List<Integer> listInt;
	private List<String> listString;

	private ArrayList<Object> arrayList = new ArrayList<Object>();
	private ArrayList<Integer> arrayListInt = new ArrayList<Integer>();
	private ArrayList<String> arrayListString = new ArrayList<String>();

	private LinkedList<Object> linkedList = new LinkedList<Object>();

	/**
	 * In Java 7, you can use the diamond operator <> in construction to forgo
	 * typing the List's type:
	 */
	private List<Map<String, String>> newList = new ArrayList<>();

	// constructors
	public DataStructure_List() {
		System.out.println("BEGIN: JavaTestEnvironment.src.references.DataStructure_List");
	}

	// methods

	// executable
	public static void main(String[] args) {
		DataStructure_List instance = new DataStructure_List();
		
		/**
		 * Collections can be converted to and from each other; eg. HashSet to ArrayList
		 */
		// Create a HashSet and Add elements to HashSet
		HashSet<String> hset = new HashSet<String>();
		hset.add("Steve");
		hset.add("John");
		hset.add("Tommy");
		// Displaying HashSet elements
		System.out.println("HashSet contains: " + hset);
		// Creating a List of HashSet elements / Convert from HashSet to ArrayList
		instance.listString = new ArrayList<String>(hset);
		// Displaying ArrayList elements
		System.out.println("ArrayList contains: " + instance.listString);

		
		int[] a = { 3, 3, 1, 2, 6, 85, 4 };
//		String inClause = instance.listString.stream().map(a-> String.valueOf(a)).collect(toStringJoiner(","));
//		String sql = "select name from tbl where name in (" + inClause + ")";
	}

}
