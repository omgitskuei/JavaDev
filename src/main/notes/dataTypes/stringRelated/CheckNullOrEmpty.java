package main.notes.dataTypes.stringRelated;

public class CheckNullOrEmpty {

    public static String nullEmptySpacesToTrimString(String checkThis) {
        return checkThis == null ? "" : checkThis.trim(); // trim() removes leading AND trailing spaces
    }

    public static boolean isNullEmptySpaces(String checkThis) {
        return checkThis == null || "".equals(checkThis.trim());
    }
    
    public static void main(String[] args) {
    	
    	// TEST CASES
    	String case1 = null;
    	String case2 = "       ";
    	String case3 = "a      ";
    	String case4 = "      a";
    	String case5 = "   a   ";
    	
        // RETURNS STRING
        System.out.println("[" + nullEmptySpacesToTrimString(case1) + "]");
        // check spaces
        System.out.println("[" + nullEmptySpacesToTrimString(case2) + "]");
        // check trailing spaces
        System.out.println("[" + nullEmptySpacesToTrimString(case3) + "]");
        // check opening spaces
        System.out.println("[" + nullEmptySpacesToTrimString(case4) + "]");
        // check trailing and opening spaces
        System.out.println("[" + nullEmptySpacesToTrimString(case5) + "]");

        System.out.println();
        
        // RETURNS BOOLEAN
        System.out.println("[" + isNullEmptySpaces(case1) + "]");
        // check spaces
        System.out.println("[" + isNullEmptySpaces(case2) + "]");
        // check trailing spaces
        System.out.println("[" + isNullEmptySpaces(case3) + "]");
        // check opening spaces
        System.out.println("[" + isNullEmptySpaces(case4) + "]");
        // check trailing and opening spaces
        System.out.println("[" + isNullEmptySpaces(case5) + "]");
    }
}
