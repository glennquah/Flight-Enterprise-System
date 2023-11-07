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
            Airport a1 = new Airport("Taoyuan International Airport", "TPE", "Taoyuan", "Taipei", "Taiwan");
            Airport a2 = new Airport("Eagle's Nest Airport", "ENS", "Eagle's Nest", "Sydney", "Australia");
            Airport a3 = new Airport("Crimson Valley Airport", "CVA", "Crimson Valley", "London", "United Kingdom");
            Airport a4 = new Airport("Sapphire Harbor Airport", "SHA", "Sapphire Harbor", "Dublin", "Ireland");
            Airport a5 = new Airport("Golden Meadows Airport", "GMA", "Golden Meadows", "Vancouver", "Canada");
            Airport a6 = new Airport("Emerald Shores Airport", "ESA", "Emerald Shores", "Auckland", "New Zealand");
            Airport a7 = new Airport("Silverlake Regional Airport", "SLR", "Silverlake", "Tokyo", "Japan");
            Airport a8 = new Airport("Sunset Ridge Airport", "SRA", "Sunset Ridge", "Paris", "France");
            Airport a9 = new Airport("Greenfield Skyport", "GSP", "Greenfield", "Berlin", "Germany");
            Airport a10 = new Airport("Ambercrest International Airport", "AIA", "Ambercrest", "Rome", "Italy");
            Airport a11 = new Airport("Pinecrest International Airport", "PIC", "Pinecrest", "California", "United States");
            Airport a12 = new Airport("Oceanview Airport", "OVA", "Oceanview", "Perth", "Australia");
            Airport a13 = new Airport("Highland Skies Airport", "HSA", "Highland Skies", "Edinburgh", "United Kingdom");
            Airport a14 = new Airport("Coral Harbor Airport", "CHA", "Coral Harbor", "Nassau", "Bahamas");
            Airport a15 = new Airport("Maple Leaf Airport", "MLA", "Maple Leaf", "Toronto", "Canada");
            Airport a16 = new Airport("Rainforest Oasis Airport", "ROA", "Rainforest Oasis", "Auckland", "New Zealand");
            Airport a17 = new Airport("Sakura Valley Airport", "SVA", "Sakura Valley", "Kyoto", "Japan");
            Airport a18 = new Airport("Chateau Heights Airport", "CHA", "Chateau Heights", "Paris", "France");
            Airport a19 = new Airport("Eternal City Airport", "ECA", "Eternal City", "Rome", "Italy");
            Airport a20 = new Airport("Maplewood International Airport", "MPI", "Maplewood", "Ohio", "United States");
            Airport a21 = new Airport("Golden Shores Airport", "GSA", "Golden Shores", "Sydney", "Australia");
            Airport a22 = new Airport("Emerald Valley Airport", "EVA", "Emerald Valley", "London", "United Kingdom");
            Airport a23 = new Airport("Ruby Cove Airport", "RCA", "Ruby Cove", "Dublin", "Ireland");
            Airport a24 = new Airport("Diamond Harbor Airport", "DHA", "Diamond Harbor", "Vancouver", "Canada");
            Airport a25 = new Airport("Amethyst Peak Airport", "APA", "Amethyst Peak", "Auckland", "New Zealand");
            Airport a26 = new Airport("Silver Sands Airport", "SSA", "Silver Sands", "Tokyo", "Japan");
            Airport a27 = new Airport("Sunset Bay Airport", "SBA", "Sunset Bay", "Paris", "France");
            Airport a28 = new Airport("Crystal Waters Airport", "CWA", "Crystal Waters", "Berlin", "Germany");
            Airport a29 = new Airport("Topaz Heights Airport", "THA", "Topaz Heights", "Rome", "Italy");
            Airport a30 = new Airport("Ruby Valley Airport", "RVA", "Ruby Valley", "Ohio", "United States");


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
            airportSessionBeanRemote.createNewAirport(a11);
            airportSessionBeanRemote.createNewAirport(a12);
            airportSessionBeanRemote.createNewAirport(a13);
            airportSessionBeanRemote.createNewAirport(a14);
            airportSessionBeanRemote.createNewAirport(a15);
            airportSessionBeanRemote.createNewAirport(a16);
            airportSessionBeanRemote.createNewAirport(a17);
            airportSessionBeanRemote.createNewAirport(a18);
            airportSessionBeanRemote.createNewAirport(a19);
            airportSessionBeanRemote.createNewAirport(a20);
            airportSessionBeanRemote.createNewAirport(a21);
            airportSessionBeanRemote.createNewAirport(a22);
            airportSessionBeanRemote.createNewAirport(a23);
            airportSessionBeanRemote.createNewAirport(a24);
            airportSessionBeanRemote.createNewAirport(a25);
            airportSessionBeanRemote.createNewAirport(a26);
            airportSessionBeanRemote.createNewAirport(a27);
            airportSessionBeanRemote.createNewAirport(a28);
            airportSessionBeanRemote.createNewAirport(a29);
            airportSessionBeanRemote.createNewAirport(a30);
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
            Aircraft ac11 = new Aircraft("Airbus A330", 280);
            Aircraft ac12 = new Aircraft("Boeing 747", 400);
            Aircraft ac13 = new Aircraft("Embraer E175", 80);
            Aircraft ac14 = new Aircraft("Airbus A321", 170);
            Aircraft ac15 = new Aircraft("Bombardier Dash 8", 70);
            Aircraft ac16 = new Aircraft("McDonnell Douglas MD-80", 130);
            Aircraft ac17 = new Aircraft("Boeing 767", 220);
            Aircraft ac18 = new Aircraft("Airbus A340", 320);
            Aircraft ac19 = new Aircraft("Cessna Citation", 6);
            Aircraft ac20 = new Aircraft("Embraer E145XR", 60);
            Aircraft ac21 = new Aircraft("Airbus A220", 110);
            Aircraft ac22 = new Aircraft("Boeing 737 MAX", 200);
            Aircraft ac23 = new Aircraft("Bombardier CRJ200", 50);
            Aircraft ac24 = new Aircraft("Airbus A380 Plus", 550);
            Aircraft ac25 = new Aircraft("Boeing 757", 180);
            Aircraft ac26 = new Aircraft("Embraer E195", 120);
            Aircraft ac27 = new Aircraft("Airbus A319", 140);
            Aircraft ac28 = new Aircraft("Cessna 208", 10);
            Aircraft ac29 = new Aircraft("Boeing 777X", 350);
            Aircraft ac30 = new Aircraft("Airbus A310", 240);
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
            aircraftSessionBeanRemote.createAircraft(ac11);
            aircraftSessionBeanRemote.createAircraft(ac12);
            aircraftSessionBeanRemote.createAircraft(ac13);
            aircraftSessionBeanRemote.createAircraft(ac14);
            aircraftSessionBeanRemote.createAircraft(ac15);
            aircraftSessionBeanRemote.createAircraft(ac16);
            aircraftSessionBeanRemote.createAircraft(ac17);
            aircraftSessionBeanRemote.createAircraft(ac18);
            aircraftSessionBeanRemote.createAircraft(ac19);
            aircraftSessionBeanRemote.createAircraft(ac20);
            aircraftSessionBeanRemote.createAircraft(ac21);
            aircraftSessionBeanRemote.createAircraft(ac22);
            aircraftSessionBeanRemote.createAircraft(ac23);
            aircraftSessionBeanRemote.createAircraft(ac24);
            aircraftSessionBeanRemote.createAircraft(ac25);
            aircraftSessionBeanRemote.createAircraft(ac26);
            aircraftSessionBeanRemote.createAircraft(ac27);
            aircraftSessionBeanRemote.createAircraft(ac28);
            aircraftSessionBeanRemote.createAircraft(ac29);
            aircraftSessionBeanRemote.createAircraft(ac30);
        }
        
    }
    
}
