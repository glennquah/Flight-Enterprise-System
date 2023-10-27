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
           MainApp mainApp = new MainApp(customerSessionBeanRemote, employeeSessionBeanRemote);
           mainApp.runApp();
    }
}
