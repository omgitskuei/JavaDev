package dependenciesWorkspace.junit;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ATestCase {
    // Made a string to give to ClassToBeTested
    String message = "Hello World";
    // Made instance of ClassToBeTested, and passed var String message
    ClassToBeTested classToBeTested = new ClassToBeTested(message);

    // Tests if message == "Hello World", true
    @Test
    public void testPrintMessage() {
        assertEquals(message, classToBeTested.printMessage());
    }
    
    // Tests if "K3ll0 W0rLd" == "Hello World", false
    @Test
    public void testPrintMessage1() {
        assertEquals("K3ll0 W0rLd", classToBeTested.printMessage());
    }
}