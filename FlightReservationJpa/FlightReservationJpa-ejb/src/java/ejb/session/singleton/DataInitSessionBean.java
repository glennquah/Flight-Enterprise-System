/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ejb.session.singleton;

import ejb.session.stateless.AircraftSessionBeanLocal;
import ejb.session.stateless.AirportSessionBeanLocal;
import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.EmployeeSessionBeanLocal;
import ejb.session.stateless.PartnerSessionBeanLocal;
import entity.Aircraft;
import entity.Airport;
import entity.Customer;
import entity.Employee;
import entity.Partner;
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

    @EJB(name = "CustomerSessionBeanLocal")
    private CustomerSessionBeanLocal customerSessionBeanLocal;

    @EJB(name = "EmployeeSessionBeanLocal")
    private EmployeeSessionBeanLocal employeeSessionBeanLocal;

    @EJB(name = "AirportSessionBeanLocal")
    private AirportSessionBeanLocal airportSessionBeanLocal;

    @EJB(name = "AircraftSessionBeanLocal")
    private AircraftSessionBeanLocal aircraftSessionBeanLocal;
    
    @EJB(name = "PartnerSessionBeanLocal")
    private PartnerSessionBeanLocal partnerSessionBeanLocal;

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @PostConstruct
    public void postConstruct() {
        if(em.find(Customer.class, 1l) == null) {
            //if data is empty, inject 2 accounts
            
            Customer c1 = new Customer("Glenn", "Quah", "quah.glenn@gmail.com", "password", "87534510", "Kent Ridge");
            Customer c2 = new Customer("Ryan", "Tang", "ryan@gmail.com", "password", "999", "RVRC");
            customerSessionBeanLocal.createNewAccount(c1);
            customerSessionBeanLocal.createNewAccount(c2);
        }
        
        if(em.find(Employee.class, 1l) == null) {
            //if data is empty, inject 2 accounts
            Employee em1 = new Employee("Fleet", "Manager", "fleetmanager@mlair.com.sg", "password", EmployeeAccessRightEnum.FLEETMANAGER);
            Employee em2 = new Employee("Route", "Planner", "routeplanner@mlair.com.sg", "password", EmployeeAccessRightEnum.ROUTEPLANNER);
            Employee em3 = new Employee("Schedule", "Manager", "schedulemanager@mlair.com.sg", "password", EmployeeAccessRightEnum.SCHEDULEMANAGER);
            Employee em4 = new Employee("Sales", "Manager", "salesmanager@mlair.com.sg", "password", EmployeeAccessRightEnum.SALESMANAGER);
            employeeSessionBeanLocal.createNewAccount(em1);
            employeeSessionBeanLocal.createNewAccount(em2);
            employeeSessionBeanLocal.createNewAccount(em3);
            employeeSessionBeanLocal.createNewAccount(em4);
        }
        
        if(em.find(Airport.class, 1l) == null) {     
            Airport a1 = new Airport("Changi", "SIN", "Singapore", "Singapore", "Singapore");
            Airport a2 = new Airport("Hong Kong", "HKG", "Chek Lap Kok", "Hong Kong", "China");
            Airport a3 = new Airport("Taoyuan", "TPE", "Taoyuan", "Taipei", "Taiwan R.O.C.");
            Airport a4 = new Airport("Narita", "NRT", "Narita", "Chiba", "Japan");
            Airport a5 = new Airport("Sydney", "SYD", "Sydney", "New South Wales", "Australia");
     
            airportSessionBeanLocal.createNewAirport(a1);
            airportSessionBeanLocal.createNewAirport(a2);
            airportSessionBeanLocal.createNewAirport(a3);
            airportSessionBeanLocal.createNewAirport(a4);
            airportSessionBeanLocal.createNewAirport(a5);
        }
        
        if(em.find(Aircraft.class, 1l) == null) {
            Aircraft ac1 = new Aircraft("Boeing 737", 200);
            Aircraft ac2 = new Aircraft("Airbus 747", 400);
            aircraftSessionBeanLocal.createAircraft(ac1);
            aircraftSessionBeanLocal.createAircraft(ac2);
        }
        
        if(em.find(Partner.class, 1l) == null) {
            Partner em1 = new Partner("Holiday.com", "mlair@holiday.com", "password");
            partnerSessionBeanLocal.createNewAccount(em1);
        }
    }
    
}
