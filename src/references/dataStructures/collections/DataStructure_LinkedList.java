package references.dataStructures.collections;

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
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        
        // Creating object of the class linked list 
        LinkedList<String> ll = new LinkedList<String>();

        // Adding elements to the linked list 
        ll.add("A");            // NOTE: Add an element at the END of the LinkedList.
        ll.add("B");            // [A, B]
        ll.addLast("C");        // [A, B, C]
        ll.addFirst("D");       // [D, A, B, C]
        ll.add(2, "E");         // [D, A, E, B, C]

        System.out.println(ll); // prints [D, A, E, B, C]

        ll.remove("B");         // [D, A, E, C]
        ll.remove(3);           // [D, A, E]
        ll.removeFirst();       // [A, E]
        ll.removeLast();        // [A]

        System.out.println(ll); // prints [A]

        ll.addFirst("B");       // [B, A]
        // ll.add(3, "B");      // [B, A, ??, B]    <-- this would lead to [java.lang.IndexOutOfBoundsException: Index: 3, Size: 2]
        ll.add(2, "B");
        ll.add(3, "B");
        ll.addLast("b");        // [B, A, B, B, b]
        ll.remove("B");         // NOTE: This only removes the first "B" it finds, NOT all "B"s.
        
        System.out.println(ll); // prints [A, B, B, b]

        @SuppressWarnings("rawtypes")
        ArrayList al = new ArrayList<>();
        al.add("B");
        ll.removeAll(al);
        
        System.out.println(ll); // prints [A, b]
        
        /**
         * 
         * TO-DO REWRITE THIS EXPLANATION
         * 
         * After adding the elements, if we wish to change the element, it can be done using the set() method.
         * Since a LinkedList is indexed, the element which we wish to change is referenced by the index of the element.
         * Therefore, this method takes an index and the updated element which needs to be inserted at that index.
         */
        
        LinkedList<String> ll2 = new LinkedList<>();  
        
        ll2.add("Geeks");  
        ll2.add("Geeks");  
        ll2.add(1, "Geeks");  
    
        System.out.println("Initial LinkedList " + ll2);  
    
        ll2.set(1, "For");  
    
        System.out.println("Updated LinkedList " + ll2);  
        
        /**
         * 
         * TO-DO REWRITE THIS EXPLANATION
         * 
         * 3. Removing Elements: In order to remove an element from a LinkedList, we can use the remove() method. 
         * This method is overloaded to perform multiple operations based on different parameters.
         * They are:
         *  remove(Object): This method is used to simply remove an object from the LinkedList.
         *      If there are multiple such objects, then the first occurrence of the object is removed.
         *  remove(int index): Since a LinkedList is indexed, this method takes an integer value which simply removes the element present at that specific index in the LinkedList. 
         *      After removing the element, all the elements are moved to the left to fill the space and the indices of the objects are updated.
         */
        
        LinkedList<String> ll3 = new LinkedList<>();  
        
        ll3.add("Geeks");  
        ll3.add("Geeks");  
        ll3.add(1, "For");  
    
        System.out.println(  
            "Initial LinkedList " + ll3);  
    
        ll3.remove(1);  
    
        System.out.println(  
            "After the Index Removal " + ll3);  
    
        ll3.remove("Geeks");  
    
        System.out.println(  
            "After the Object Removal " + ll3);  
    }
}
