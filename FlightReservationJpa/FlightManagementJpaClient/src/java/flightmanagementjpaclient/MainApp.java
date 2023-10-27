/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightmanagementjpaclient;

import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author admin
 */
public class MainApp {
    private EmployeeSessionBeanRemote employeeSessionBean;
    private CustomerSessionBeanRemote customerSessionBean;

    public MainApp() {
    }

    public MainApp(EmployeeSessionBeanRemote employeeSessionBean, CustomerSessionBeanRemote customerSessionBean) {
        this.employeeSessionBean = employeeSessionBean;
        this.customerSessionBean = customerSessionBean;
    }
    
    public void runApp() {
        //Login logic
        
        ManagementModule managementModule = new ManagementModule(employeeSessionBean, customerSessionBean);
        managementModule.adminLoginPage();
    }
    
}
