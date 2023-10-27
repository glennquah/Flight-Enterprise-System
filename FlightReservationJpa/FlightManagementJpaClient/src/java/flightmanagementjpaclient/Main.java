/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package flightmanagementjpaclient;

import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author admin
 */
public class Main {

    @EJB
    private static EmployeeSessionBeanRemote employeeSessionBean;

    @EJB
    private static CustomerSessionBeanRemote customerSessionBean;

    
    public static void main(String[] args) {
        MainApp mainApp = new MainApp(employeeSessionBean, customerSessionBean);
        mainApp.runApp();
    }
    
}
