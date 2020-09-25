package dependenciesWorkspace.junit;

/**
 * This class prints the given message on console.
 */
public class ClassToBeTested {

    private String message;

    /**
     * Constructor
     * @param message to be printed
     */

    public ClassToBeTested(String message) {
        this.message = message;
    }

    /**
     * printMessage method
     * Sysouts this class's private variable message also returns it
     * @return message
     */
    public String printMessage() {
        System.out.println(message);
        return message;
    }
}