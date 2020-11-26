package references.string;

/**
 * In Java, is there any difference between String.valueOf(Object) and Object.toString()? Is there a specific code convention for these?
 * @author i92008cc63
 *
 */

public class StringValueOf_VS_ToString {
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
        
        String str = null;
        System.out.println(String.valueOf(str));  // This will print a String equal to "null"
        try {
            System.out.println(str.toString()); // This will throw a NullPointerException
        } catch (NullPointerException e) {
            System.out.println("str.toString() threw NullPointerException");
        }
        
        
        
        Object obj = new Object();
        System.out.println(String.valueOf(obj));
        try {
            System.out.println(obj.toString()); // This will throw a NullPointerException
        } catch (NullPointerException e) {
            System.out.println("str.toString() threw NullPointerException");
        }
        
       
    } 
}
