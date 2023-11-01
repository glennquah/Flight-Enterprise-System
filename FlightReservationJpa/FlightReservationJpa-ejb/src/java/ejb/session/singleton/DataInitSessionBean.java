/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ejb.session.singleton;

import ejb.session.stateless.AircraftSessionBeanRemote;
import ejb.session.stateless.AirportSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import ejb.session.stateless.FlightRoutesSessionBeanRemote;
import ejb.session.stateless.FlightScheduleSessionBeanRemote;
import entity.Aircraft;
import entity.Airport;
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

    @EJB
    private FlightRoutesSessionBeanRemote flightRoutesSessionBeanRemote;

    @EJB(name = "AircraftSessionBeanRemote")
    private AircraftSessionBeanRemote aircraftSessionBeanRemote;

    @EJB(name = "AirportSessionBeanRemote")
    private AirportSessionBeanRemote airportSessionBeanRemote;

    @EJB(name = "EmployeeSessionBeanRemote")
    private EmployeeSessionBeanRemote employeeSessionBeanRemote;

    @EJB(name = "CustomerSessionBeanRemote")
    private CustomerSessionBeanRemote customerSessionBeanRemote;
    
    @EJB
    private FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote;

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
        
        if(em.find(Airport.class, 1l) == null) {           
            Airport a1 = new Airport("Maplewood International Airport", "MPI", "Maplewood", "Ohio", "United States");
            Airport a2 = new Airport("Eagle's Nest Airport", "ENS", "Eagle's Nest", "Sydney", "Australia");
            Airport a3 = new Airport("Crimson Valley Airport", "CVA", "Crimson Valley", "London", "United Kingdom");
            Airport a4 = new Airport("Sapphire Harbor Airport", "SHA", "Sapphire Harbor", "Dublin", "Ireland");
            Airport a5 = new Airport("Golden Meadows Airport", "GMA", "Golden Meadows", "Vancouver", "Canada");
            Airport a6 = new Airport("Emerald Shores Airport", "ESA", "Emerald Shores", "Auckland", "New Zealand");
            Airport a7 = new Airport("Silverlake Regional Airport", "SLR", "Silverlake", "Tokyo", "Japan");
            Airport a8 = new Airport("Sunset Ridge Airport", "SRA", "Sunset Ridge", "Paris", "France");
            Airport a9 = new Airport("Greenfield Skyport", "GSP", "Greenfield", "Berlin", "Germany");
            Airport a10 = new Airport("Ambercrest International Airport", "AIA", "Ambercrest", "Rome", "Italy");
            airportSessionBeanRemote.createNewAirport(a1);
            airportSessionBeanRemote.createNewAirport(a2);
            airportSessionBeanRemote.createNewAirport(a3);
            airportSessionBeanRemote.createNewAirport(a4);
            airportSessionBeanRemote.createNewAirport(a5);
            airportSessionBeanRemote.createNewAirport(a6);
            airportSessionBeanRemote.createNewAirport(a7);
            airportSessionBeanRemote.createNewAirport(a8);
            airportSessionBeanRemote.createNewAirport(a9);
            airportSessionBeanRemote.createNewAirport(a10);
        }
        
        if(em.find(Aircraft.class, 1l) == null) {
            Aircraft ac1 = new Aircraft("Boeing 737", 180);
            Aircraft ac2 = new Aircraft("Airbus A320", 150);
            Aircraft ac3 = new Aircraft("Embraer E190", 100);
            Aircraft ac4 = new Aircraft("Boeing 787 Dreamliner", 250);
            Aircraft ac5 = new Aircraft("Airbus A350", 220);
            Aircraft ac6 = new Aircraft("Bombardier CRJ900", 90);
            Aircraft ac7 = new Aircraft("Boeing 777", 300);
            Aircraft ac8 = new Aircraft("Airbus A380", 500);
            Aircraft ac9 = new Aircraft("Embraer E145", 50);
            Aircraft ac10 = new Aircraft("Cessna 172", 4);
            aircraftSessionBeanRemote.createAircraft(ac1);
            aircraftSessionBeanRemote.createAircraft(ac2);
            aircraftSessionBeanRemote.createAircraft(ac3);
            aircraftSessionBeanRemote.createAircraft(ac4);
            aircraftSessionBeanRemote.createAircraft(ac5);
            aircraftSessionBeanRemote.createAircraft(ac6);
            aircraftSessionBeanRemote.createAircraft(ac7);
            aircraftSessionBeanRemote.createAircraft(ac8);
            aircraftSessionBeanRemote.createAircraft(ac9);
            aircraftSessionBeanRemote.createAircraft(ac10);
        }
        
    }
    
}
