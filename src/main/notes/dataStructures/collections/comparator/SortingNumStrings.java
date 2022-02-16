package main.notes.dataStructures.collections.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortingNumStrings {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("5");
		list.add("3");
		list.add("3");
		list.add("2");

		/*
		 * Comparator
		 * ----------
		 * A comparison function, which imposes a total ordering on some collection of
		 * objects. Comparators can be passed to a sort method (such as Collections.sort
		 * or Arrays.sort) to allow precise control over the sort order. Comparators can
		 * also be used to control the order of certain data structures (such as sorted
		 * sets or sorted maps), or to provide an ordering for collections of objects
		 * that don't have a natural ordering.
		 * 
		 * The ordering imposed by a comparator c on a set of elements S is said to be
		 * consistent with equals if and only if c.compare(e1, e2)==0 has the same
		 * boolean value as e1.equals(e2) for every e1 and e2 in S.
		 * 
		 * Caution should be exercised when using a comparator capable of imposing
		 * an ordering inconsistent with equals to order a sorted set (or sorted
		 * map).
		 * 
		 * [Because Set and Map keys uses .equals() to figure out if a new item is
		 * unique, and thus should be added, Comparator establishing custom equals() for
		 * sorting may interfere with the Set or Map uniqueness checking.]
		 * For example, suppose one adds two elements a1 and b1 such that;
		 * 		(a1.equals(b1) && c.compare(a1, b1) != 0)
		 * to an empty TreeSet with comparator c.
		 * The second c.compare(a1, b1) operation will return true and the size of the tree
		 * set will increase), even though this is contrary to the specification of the
		 * Set.add method.
		 * 
		 * Note: It is generally a good idea for comparators to also implement
		 * java.io.Serializable, in order for the data structure to
		 * serialize successfully, the comparator (if provided) must implement
		 * Serializable.
		 * 
		 * Unlike Comparable, a comparator may optionally permit comparison of null
		 * arguments, while maintaining the requirements for an equivalence relation.
		 * 
		 * This interface is a member of the Java Collections Framework.
		 * Since:1.2
		 * Author:Josh BlochNeal GafterSee
		 * Also:Comparable, java.io.Serializable
		 */

		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				System.out.println(o1);
				System.out.println(o2);
				System.out.println(o1.compareToIgnoreCase(o2));
				System.out.println(list);
				return o1.compareToIgnoreCase(o2);
				/*
				 * 3 // i=2
				 * 5 // i=1
				 * -2
				 * [5, 3, 3, 2]
				 * 
				 * 3 // i=3
				 * 3 // i=2
				 * 0
				 * [5, 3, 3, 2]
				 * 
				 * 3 // i=3
				 * 5 // i=2
				 * -2
				 * [3, 5, 3, 2]
				 * 
				 * 3 // i=3
				 * 3 // i=1
				 * 0
				 * [3, 5, 3, 2]
				 * 
				 * 2 // i=4
				 * 3 // i=1
				 * -1
				 * [3, 3, 5, 2]
				 * 
				 * 2 // i=4
				 * 3 // i=2
				 * -1
				 * [3, 3, 5, 2]
				 */
			}
		});

		System.out.println(list);
		// [2, 3, 3, 5]
	}

}
