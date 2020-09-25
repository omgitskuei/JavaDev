package dependenciesWorkspace.junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ATestRunner {
    public static void main(String[] args) {
        // Use runClasses method to run the test case ATestCase, store as Result.
        Result result = JUnitCore.runClasses(ATestCase.class);
        
        // Get failure(s)
        for (Failure failure : result.getFailures()) {
           System.out.println("failure.toString()" + failure.toString());
        }
        // Get Success
        System.out.println("result.wasSuccessful() = " + result.wasSuccessful());
     }
}
