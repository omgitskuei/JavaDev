package main.notes.dataTypes.stringRelated;

/**
 * In Java, is there any difference between String.valueOf(Object) and Object.toString()? Is there a specific code convention for these?
 * @author i92008cc63
 *
 */

public class CompareStringValueOfAndToString {
    @SuppressWarnings("null")
    public static void main(String args[]) {  
        /**
         * String.valueOf():
         * if the argument is null, then a string equal to "null"; otherwise, the value of obj.toString() is returned.
         */
        
        /**
         * Object.toString():
         * if the instance is null, a NullPointerException will be thrown, so arguably, it's less safe.
         */
        
        String nullStr = null;
        System.out.println(String.valueOf(nullStr));  				// prints "null"
        try {
            System.out.println(nullStr.toString()); 			// threw NullPointerException
        } catch (Exception e) {									// triggered
            System.out.println(e.getClass());						// prints
        }
        
        System.out.println();
        
        Object obj = new Object();
        Object objNull = null;
        System.out.println(String.valueOf(obj));					// prints Object ID
        System.out.println(String.valueOf(objNull));				// prints "null"
        try {
            System.out.println(obj.toString()); 					// prints Object ID, no throw
            System.out.println(objNull.toString());				// threw NullPointerException
        } catch (Exception e) {									// triggered
            System.out.println(e.getClass());						// prints
        }
        
       
    } 
}
