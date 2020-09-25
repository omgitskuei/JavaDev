package dependenciesWorkspace.junit.example;

/**
 * EmpBusinessLogic class is used for calculating −
 *      the yearly salary of an employee.
 *      the appraisal amount of an employee.
 * @author Kuei-Feng Tung
 * @see www.github.com/omgitskuei
 * @version 1.0
 * @category Notes
 * @since Sept 25 2020
 */
public class EmpBusinessLogic {
    // Calculate the yearly salary of employee
    public double calculateYearlySalary(EmployeeDetails employeeDetails) {
       return employeeDetails.getMonthlySalary() * 12;
    }
     
    // Calculate the appraisal amount of employee
    public double calculateAppraisal(EmployeeDetails employeeDetails) {
       double appraisal = 0;
         
       if(employeeDetails.getMonthlySalary() < 10000){
          appraisal = 500;
       }else{
          appraisal = 1000;
       }
         
       return appraisal;
    }
 }