package references.dataStructures.collections;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class DataStructure_Map {
	// Local Fields
	// - Hashtable is synchronized, in contrast to HashMap.
	// - - It has an overhead for synchronization.
	// - - doesn't allow two identical elements ('identical' means same KEY AND
	// VALUE)
	// - - - eg. hashMap.put("Wow", 3) and ("Wow", 2) are NOT EQUAL;
	// - - -
	private Hashtable<String, String> hTable;

	// - LinkedHashMap preserves the insertion order
	// - - insert order
	// - - inherits the features of HashMap
	private LinkedHashMap<String, String> linkedHMap;

	// - HashMap is implemented as a hash table,
	// - - no ordering on keys or values.
	// - - roughly equivalent to Hashtable
	// - - unsynchronized and permits nulls.
	private HashMap<String, String> hMap;

	// - TreeMap is implemented based on red-black tree structure,
	// - - ordered by keys.
	private TreeMap<String, String> tMap;

	// Constructors
	public DataStructure_Map() {
		System.out.println("BEGIN: JavaTestEnvironment.src.references.DataStructure_Map");
	}

	// Executable
	public static void main(String[] args) {
		DataStructure_Map thisClass = new DataStructure_Map();
//		thisClass.runTestHashMap();
		
		Map<String, Object> person = new HashMap<String, Object>();
		person.put("name", "Chris");
		person.put("favorite color", "red");
		person.put("age", "22");
		Map<String, Object> skills = new HashMap<String, Object>();
		skills.put("cooking", "expert");
		skills.put("singing", "novice");
		Map<String, Object> languages = new HashMap<String, Object>();
		languages.put("java", "expert");
		languages.put("python", "novice");
		skills.put("coding", languages);
		
		person.put("skills", skills);
		System.out.println(person);
	}

	// Methods
	class Dog {
		String color;

		Dog(String c) {
			color = c;
		}

		public boolean equals(Object o) {
			return ((Dog) o).color.equals(this.color);
		}

		public int hashCode() {
			return color.length();
		}

		public String toString() {
			return color + " dog";
		}
	}

	public void runTestHashMap() {
		HashMap<Dog, Integer> hashMap = new HashMap<Dog, Integer>();
		hashMap.put(new Dog("red"), 10);
		hashMap.put(new Dog("black"), 15);
		hashMap.put(new Dog("white"), 5);
		hashMap.put(new Dog("white"), 20);

		// print size
		System.out.println(hashMap.size());

		// loop HashMap
		for (Entry<Dog, Integer> entry : hashMap.entrySet()) {
			System.out.println(entry.getKey().toString() + " - " + entry.getValue());
		}
	}

	class Cat implements Comparable<Cat> {
		// The reason is that TreeMap now uses compareTo() method to compare keys.
		// Different sizes make different dogs!
		String color;
		int size;

		Cat(String c, int s) {
			color = c;
			size = s;
		}

		public String toString() {
			return color + " cat";
		}

		@Override
		public int compareTo(Cat o) {
			return o.size - this.size;
		}
	}

	public void runTestTreeMap() {
		// Since TreeMaps are sorted by keys, the object for key has to be able to
		// compare with each other, that'UsingSuper why it has to implement Comparable interface.
		// For example, you use String as key, because String implements Comparable
		// interface.
		// Let'UsingSuper change the Dog, and make it comparable.

		Cat d1 = new Cat("red", 30);
		Cat d2 = new Cat("black", 20);
		Cat d3 = new Cat("white", 10);
		Cat d4 = new Cat("white", 10);

		TreeMap<Cat, Integer> treeMap = new TreeMap<Cat, Integer>();
		treeMap.put(d1, 10);
		treeMap.put(d2, 15);
		treeMap.put(d3, 5);
		treeMap.put(d4, 20);

		for (Entry<Cat, Integer> entry : treeMap.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	}

	class Fish {
		String color;

		Fish(String c) {
			color = c;
		}

		public boolean equals(Object o) {
			return ((Fish) o).color.equals(this.color);
		}

		public int hashCode() {
			return color.length();
		}

		public String toString() {
			return color + " fish";
		}
	}

	public void runTestLinkedHashMap() {
		Fish d1 = new Fish("red");
		Fish d2 = new Fish("black");
		Fish d3 = new Fish("white");
		Fish d4 = new Fish("white");

		LinkedHashMap<Fish, Integer> linkedHashMap = new LinkedHashMap<Fish, Integer>();
		linkedHashMap.put(d1, 10);
		linkedHashMap.put(d2, 15);
		linkedHashMap.put(d3, 5);
		linkedHashMap.put(d4, 20);

		for (Entry<Fish, Integer> entry : linkedHashMap.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	}

	
	public void runTestHashtable() {

	}
}
