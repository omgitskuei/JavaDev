package dependenciesWorkspace.junit.oneExample;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

// Can run as JUnit test
public class TestEmployeeDetails {
   EmpBusinessLogic empBusinessLogic = new EmpBusinessLogic();
   EmployeeDetails employee = new EmployeeDetails();

   //test to check appraisal
   @Test
   public void testCalculateAppriasal() {
      employee.setName("Kuei");
      employee.setAge(25);
      employee.setMonthlySalary(8000);
        
      double appraisal = empBusinessLogic.calculateAppraisal(employee);
      assertEquals(500, appraisal, 0.0);
   }

   // test to check yearly salary
   @Test
   public void testCalculateYearlySalary() {
      employee.setName("Feng");
      employee.setAge(25);
      employee.setMonthlySalary(7000); // was 8000 and correct
        
      double salary = empBusinessLogic.calculateYearlySalary(employee);
      assertEquals(96000, salary, 0.0);
   }
}