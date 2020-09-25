package dependenciesWorkspace.junit.otherTestCases;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SecondTestCase {
    // Method to be tested
    public int sumThisString(String expression) {
        int sum = 0;
        for (String summand : expression.split("\\+"))
            sum += Integer.valueOf(summand);
        return sum;
    }

    @Test
    public void testEvalSum() {
        int sumResult = sumThisString("1+2+3");
        int myGuess = 5;
        assertEquals(myGuess, sumResult); // false - if it fails here, it will STOP
        myGuess = 6;
        assertEquals(myGuess, sumResult); // true - Ln[11] already failed so this code wasn't asserted
        myGuess = 7;
        assertEquals(sumResult, myGuess); // false - Ln[11] already failed so this code wasn't asserted
    }

    @Test
    public void testTwoStringsEqual() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fin", str); // failed - this WILL run even though it failed on Ln[11], because different @Test(s)
    }
}