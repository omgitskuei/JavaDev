package references.dataStructures.collections.list;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * LinkedList Notes
 * @author Kuei-Feng Tung
 * @see www.github.com/omgitskuei
 * @version 2.0
 * @category Notes
 * @since Sept 26 2020
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
        
        // Creating object of the class linked list 
        LinkedList<String> linkedList = new LinkedList<String>();
        
        /**
         *  ADDING
         */
        
        // .add(Object)
        linkedList.add("A");            // NOTE: Add an element at the END of the LinkedList.
        linkedList.add("B");            // [A]                  -->             [A, B]
        linkedList.addLast("C");        // [A, B]               -->             [A, B, C]
        linkedList.addFirst("D");       // [A, B, C]            -->             [D, A, B, C]
        // .add(index, value)
        linkedList.add(2, "E");         // [D, A, B, C]         -->             [D, A, E, B, C]
        System.out.println("1) " + linkedList); // prints [D, A, E, B, C]
        
        
        /**
         *  REMOVING
         */
        
        // .remove(Object)
        linkedList.remove("B");         // [D, A, E, B, C]      -->             [D, A, E, C]
        // .remove(int)
        linkedList.remove(3);           // [D, A, E, C]         -->             [D, A, E]
        linkedList.removeFirst();       // [D, A, E]            -->             [A, E]
        linkedList.removeLast();        // [A, E]               -->             [A]
        System.out.println("2) " + linkedList);                 // prints [A]
        // .removeAll(Collection)
        linkedList.add("B");            // [A]                  -->             [A, B]
        linkedList.add("C");            // [A, B]               -->             [A, B, C]
        linkedList.addFirst("B");       // [A, B, C]            -->             [B, A, B, C]
        System.out.println("3) " + linkedList);                 // prints [B, A, B, C]
        ArrayList<String> al = new ArrayList<>();
        al.add("A");                    // [A]
        al.add("B");                    // [A, B]
        linkedList.removeAll(al);       // [B, A, B, C]         -->             [C]
        System.out.println("4) " + linkedList);                 // prints [C]
        
        
        /**
         *  NOTES
         */
        
        linkedList.addFirst("B");       // [B, A]
        // NO GAPS ALLOWED
        // ll.add(3, "B");      // [B, A, ??, B]    <-- this would lead to [java.lang.IndexOutOfBoundsException: Index: 3, Size: 2]
        linkedList.add(2, "B");
        linkedList.add(3, "B");
        linkedList.addLast("b");        // [B, A, B, B, b]
        // NOTE: This only removes the first "B" it finds, NOT all "B"s.
        linkedList.remove("B");         // [B, A, B, B, b]      -->             [A, B, B, b]
        System.out.println("5) " + linkedList); // prints [A, B, B, b]
    }
}
