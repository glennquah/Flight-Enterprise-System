/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package flightreservationjpaclient;

import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import entity.Account;
import entity.Customer;
import entity.Employee;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Lenovo
 */
public class Main {

    @EJB(name = "CustomerSessionBeanRemote")
    private static CustomerSessionBeanRemote customerSessionBeanRemote;

    @EJB(name = "EmployeeSessionBeanRemote")
    private static EmployeeSessionBeanRemote employeeSessionBeanRemote;
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Look up session bean
        List<Customer> retrieveAllAccounts = customerSessionBeanRemote.retrieveAllAccounts();
        for (Customer cust: retrieveAllAccounts) {
            System.out.println("cust ID: " + cust.getAccountId());
            System.out.println("cust Name: " + cust.getFirstName());
            System.out.println("cust UserName: " + cust.getEmail());
            System.out.println("cust Password: " + cust.getPassword()+ "\n\n");
        }
        
        List<Employee> retrieveEmp = employeeSessionBeanRemote.retrieveAllAccounts();
        for (Employee emp: retrieveEmp) {
            System.out.println("cust ID: " + emp.getAccountId());
            System.out.println("cust Name: " + emp.getLastName());
            System.out.println("cust UserName: " + emp.getEmail());
            System.out.println("cust Password: " + emp.getLastName()+ "\n\n");
        }
    }
    
}
