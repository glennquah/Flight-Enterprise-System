/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ejb.session.stateless.singleton;

import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import entity.Account;
import entity.Customer;
import entity.Employee;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.EmployeeAccessRightEnum;

/**
 *
 * @author Lenovo
 */
@Singleton
@LocalBean
@Startup

public class DataInitSessionBean {

    @EJB(name = "EmployeeSessionBeanRemote")
    private EmployeeSessionBeanRemote employeeSessionBeanRemote;

    @EJB(name = "CustomerSessionBeanRemote")
    private CustomerSessionBeanRemote customerSessionBeanRemote; 

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @PostConstruct
    public void postConstruct() {
        if(em.find(Customer.class, 1l) == null) {
            //if data is empty, inject 2 accounts
            
            Customer c1 = new Customer("Glenn", "Quah", "quah.glenn@gmail.com", "password", "87534510", "Kent Ridge");
            Customer c2 = new Customer("Ryan", "Tang", "ryan@gmail.com", "password", "999", "RVRC");
            customerSessionBeanRemote.createNewAccount(c1);
            customerSessionBeanRemote.createNewAccount(c2);
        }
        
        if(em.find(Employee.class, 1l) == null) {
            //if data is empty, inject 2 accounts
            Employee em1 = new Employee("Glenn", "Quah", "quah.glenn@gmail.com", "password", EmployeeAccessRightEnum.MANAGER);
            Employee em2 = new Employee("Ryan", "Tang", "ryan@gmail.com", "password", EmployeeAccessRightEnum.STAFF);
            employeeSessionBeanRemote.createNewAccount(em1);
            employeeSessionBeanRemote.createNewAccount(em2);
        }
    }
    
}
