package references.dataStructures.collections;

import java.util.LinkedList;

/**
 * LinkedList Notes
 * @author Kuei-Feng Tung
 * @see www.github.com/omgitskuei
 * @version 1.0
 * @category Notes
 * @since Sept 25 2020
 */
public class DataStructure_LinkedList {
    /**
     * LinkedList Intro
     * 
     * LinkedList is a linear data structure where the elements are not stored in contiguous locations
     * and every element is a separate object with a data part and address part.
     * The elements are linked using pointers and addresses.
     * Each element is known as a node.
     * The nodes cannot be accessed directly instead we need to start from the head and follow through the link to reach to a node we wish to access.
     */
    public static void main(String[] args) {
        // Creating object of the 
        // class linked list 
        LinkedList<String> ll = new LinkedList<String>();

        // Adding elements to the linked list 
        ll.add("A");
        ll.add("B");            // [A, B]
        ll.addLast("C");        
        ll.addFirst("D");
        ll.add(2, "E");

        System.out.println(ll); // prints [D, A, E, B, C]

        ll.remove("B");
        ll.remove(3);
        ll.removeFirst();
        ll.removeLast();

        System.out.println(ll);
    }

}
