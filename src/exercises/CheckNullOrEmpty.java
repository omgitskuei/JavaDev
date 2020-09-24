package exercises;

public class CheckNullOrEmpty {

    public static String nullEmptyToTrimString(String checkThis) {
        return checkThis == null ? "" : checkThis.trim(); // trim() removes leading AND trailing spaces
    }

    public static boolean isNullEmptyOrAllSpaces(String checkThis) {
        return checkThis == null || "".equals(checkThis.trim());
    }
    
    public static void main(String[] args) {
        // RETURNS STRING
        System.out.println("[" + nullEmptyToTrimString(null) + "]");
        // check spaces
        System.out.println("[" + nullEmptyToTrimString("       ") + "]");
        // check trailing spaces
        System.out.println("[" + nullEmptyToTrimString("A   ") + "]");
        // check opening spaces
        System.out.println("[" + nullEmptyToTrimString("    A") + "]");
        // check trailing and opening spaces
        System.out.println("[" + nullEmptyToTrimString("    A   ") + "]");

        System.out.println();
        
        // RETURNS BOOLEAN
        System.out.println("[" + isNullEmptyOrAllSpaces(null) + "]");
        // check spaces
        System.out.println("[" + isNullEmptyOrAllSpaces("       ") + "]");
        // check trailing spaces
        System.out.println("[" + isNullEmptyOrAllSpaces("A   ") + "]");
        // check opening spaces
        System.out.println("[" + isNullEmptyOrAllSpaces("    A") + "]");
        // check trailing and opening spaces
        System.out.println("[" + isNullEmptyOrAllSpaces("    A   ") + "]");
        
        System.out.println();
    }

}
