package dependenciesWorkspace.junit;

import dependenciesWorkspace.junit.otherTestCases.SecondTestCase;
import dependenciesWorkspace.junit.otherTestCases.ThirdTestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;


public class ATestSuite {
   public static void main(String[] a) {
      // add the test's in the suite
      TestSuite suite = new TestSuite(ATestCase.class, SecondTestCase.class, ThirdTestCase.class );
      TestResult result = new TestResult();
      suite.run(result);
      System.out.println("Number of test cases = " + result.runCount());
   }
}