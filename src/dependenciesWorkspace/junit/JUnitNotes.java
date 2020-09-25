package dependenciesWorkspace.junit;
/**
 * Apache JUnit4 Notes
 * @author Kuei-Feng Tung
 * @see www.github.com/omgitskuei
 * @version 1.0
 * @category Notes
 * @since Sept 25 2020
 */
public class JUnitNotes {
    /**
     * NOTE:
     * 
     * This package and its associated classes, notes, examples pertain to JUnit4
     * As of now, there's already JUnit5 (but that needs Java 11 or higher).
     * Check pom.xml to make sure you have a compatible version to your Java.
     * 
     * 
     * SOURCES:
     * 
     * FAQ on JUnit4
     * https://github.com/junit-team/junit4/wiki/FAQ
     * 
     * 
     * To use javac compiler, setup environment variables:
     *      - Set up classpath:
     *          - CLASSPATH to %CLASSPATH%;%JUNIT_HOME%\junit4.12.jar;.;
     *      - Set the JUNIT_HOME:
     *          - JUNIT_HOME to C:\JUNIT (or where JUNIT jar is)
     * https://www.tutorialspoint.com/junit/junit_environment_setup.htm
     * 
     * 
     * JUnit is a Regression Testing Framework used by developers to implement unit testing
     * Unit test framework provides the following important features:
     * - Fixtures
     *      - a fixed state of a set of objects used as a baseline for running tests
     *      - purpose is to ensure that environment in which tests are run is FIXED
     *        so that results are repeatable
     *      - includes:
     *          - setUp() method, which runs before every test invocation
     *          - tearDown() method, which runs after every test method
     * - Test suites
     *      - bundles Unit Test Cases and runs them together.
     *      - In JUnit, both @RunWith and @Suite annotation are used to run the suite test.
     * - Test runners
     *      - used for executing the test cases
     * - JUnit classes
     *      - Assert − Contains a set of assert methods.
     *      - TestCase − Contains a test case that defines the fixture to run multiple tests.
     *      - TestResult − Contains methods to collect the results of executing a test case.
     * https://www.tutorialspoint.com/junit/junit_test_framework.htm
     * 
     * 
     * Step-by-step Create a single test
     *     1) Pick a ClassToBeTested
     *     2) Create ATestCase
     *     3) Create ATestRunner
     *     4) Run ATestCase as 'JUnit Test' OR Run ATestRunner as Java Application
     * https://www.tutorialspoint.com/junit/junit_basic_usage.htm
     * 
     * 
     * junit.framework Classes, and their methods
     *  - Assert
     *      - void assertEquals(primitiveOrObject expected, primitiveOrObject actual)
     *      - void assertFalse(boolean condition)
     *      - void assertNotNull(Object object)
     *      - void assertNull(Object object)
     *      - void assertTrue(boolean condition)
     *  - TestCase
     *      - int countTestCases() - Counts the number of test cases executed by run(TestResult result)
     *      - TestResult createResult()
     *      - TestResult run(), or void run(TestResult result)
     *      - String toString() - Returns a string representation of the test case.
     *  - TestResult
     *      - 
     *  - TestSuite
     *      - void addTest(Test test) - Adds a test to the suite.
     *      - void addTestSuite(Class<? extends TestCase> testClass) - Adds the tests from the given class to the suite.
     *      - int countTestCases() - Counts the number of test cases that will be run by this test.
     *      - String getName() - Returns the name of the suite.
     *      - void run(TestResult result) - Runs the tests and collects their result in a TestResult.
     *      - void setName(String name) - Sets the name of the suite.
     *      - Test testAt(int index) - Returns the test at the given index.
     *      - int testCount() - Returns the number of tests in this suite.
     *      - static Test warning(String message) - Returns a test which will fail and log a warning message.
     */
    
    
}