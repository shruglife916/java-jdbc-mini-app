import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException{

      EmployeeDao employeeDao = EmployeeDaoFactory.getEmployeeDao();
      Scanner sc = new Scanner(System.in);

      boolean flag = true;
      while (flag) {
          System.out.println("**************************************");
          System.out.println("Select form the options below");
          System.out.println("**************************************");
          System.out.println("PRESS 1: Add Employee");
          System.out.println("PRESS 2: Update Employee");
          System.out.println("PRESS 3: Delete Employee");
          System.out.println("PRESS 4: Get All Employees");
          System.out.println("PRESS 5: Get Employee By Id");
          System.out.println("PRESS 6: Exit");

          int input = sc.nextInt();
          switch(input) {
              case 1: {
                  // add
                  System.out.print("Enter Name: ");
                  String name = sc.nextLine();
                  System.out.print("Enter Email: ");
                  String email = sc.nextLine();
                  Employee employee = new Employee();
                  employee.setName(name);
                  employee.setEmail(email);
                  employeeDao.addEmployee(employee);
                  break;
              }
              case 2: {
                  // update
                  System.out.print("Enter Employee ID to Update : ");
                  int id = sc.nextInt();
                  System.out.print("Update Name: ");
                  String name = sc.nextLine();
                  System.out.print("Update Email: ");
                  String email = sc.nextLine();
                  Employee employeeToUpdate = new Employee();
                  employeeToUpdate.setName(name);
                  employeeToUpdate.setEmail(email);
                  employeeDao.updateEmployee(employeeToUpdate);
                  break;
              }
              case 3: {
                  // delete
                  System.out.print("Enter Employee ID to Delete : ");
                  int id = sc.nextInt();
                  employeeDao.deleteEmployee(id);
                  break;
              }
              case 4: {
                  // get all employees
                  List<Employee> employees = employeeDao.getEmployees();
                  System.out.println("All Employees:");
                  for (Employee employee : employees) {
                      System.out.println(employee);
                  }
                  break;
              }
              case 5: {
                  // get employee by id
                  System.out.println("Enter Employee ID to view ");
                  int id = sc.nextInt();
                  Employee retrievedEmployee = employeeDao.getEmployeeById(id);
                  System.out.println("Retrieved Employee: " + retrievedEmployee);
                  break;
              }
              case 6: {
                  // exit
                  System.out.println("Thank you");
                  System.out.println("Exiting...");
                  flag = false;
                  break;
              }
              default : {
                  System.out.println("Please Choose between 1 - 6");
                  break;
              }
          }
      }
    }
}