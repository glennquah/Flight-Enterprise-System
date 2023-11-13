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
            Employee em1 = new Employee("Glenn", "Quah", "quah.glenn@gmail.com", "password", EmployeeAccessRightEnum.MANAGER);
            Employee em2 = new Employee("Ryan", "Tang", "ryan@gmail.com", "password", EmployeeAccessRightEnum.STAFF);
            employeeSessionBeanLocal.createNewAccount(em1);
            employeeSessionBeanLocal.createNewAccount(em2);
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


            airportSessionBeanLocal.createNewAirport(a1);
            airportSessionBeanLocal.createNewAirport(a2);
            airportSessionBeanLocal.createNewAirport(a3);
            airportSessionBeanLocal.createNewAirport(a4);
            airportSessionBeanLocal.createNewAirport(a5);
            airportSessionBeanLocal.createNewAirport(a6);
            airportSessionBeanLocal.createNewAirport(a7);
            airportSessionBeanLocal.createNewAirport(a8);
            airportSessionBeanLocal.createNewAirport(a9);
            airportSessionBeanLocal.createNewAirport(a10);
            airportSessionBeanLocal.createNewAirport(a11);
            airportSessionBeanLocal.createNewAirport(a12);
            airportSessionBeanLocal.createNewAirport(a13);
            airportSessionBeanLocal.createNewAirport(a14);
            airportSessionBeanLocal.createNewAirport(a15);
            airportSessionBeanLocal.createNewAirport(a16);
            airportSessionBeanLocal.createNewAirport(a17);
            airportSessionBeanLocal.createNewAirport(a18);
            airportSessionBeanLocal.createNewAirport(a19);
            airportSessionBeanLocal.createNewAirport(a20);
            airportSessionBeanLocal.createNewAirport(a21);
            airportSessionBeanLocal.createNewAirport(a22);
            airportSessionBeanLocal.createNewAirport(a23);
            airportSessionBeanLocal.createNewAirport(a24);
            airportSessionBeanLocal.createNewAirport(a25);
            airportSessionBeanLocal.createNewAirport(a26);
            airportSessionBeanLocal.createNewAirport(a27);
            airportSessionBeanLocal.createNewAirport(a28);
            airportSessionBeanLocal.createNewAirport(a29);
            airportSessionBeanLocal.createNewAirport(a30);
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
            aircraftSessionBeanLocal.createAircraft(ac1);
            aircraftSessionBeanLocal.createAircraft(ac2);
            aircraftSessionBeanLocal.createAircraft(ac3);
            aircraftSessionBeanLocal.createAircraft(ac4);
            aircraftSessionBeanLocal.createAircraft(ac5);
            aircraftSessionBeanLocal.createAircraft(ac6);
            aircraftSessionBeanLocal.createAircraft(ac7);
            aircraftSessionBeanLocal.createAircraft(ac8);
            aircraftSessionBeanLocal.createAircraft(ac9);
            aircraftSessionBeanLocal.createAircraft(ac10);
            aircraftSessionBeanLocal.createAircraft(ac11);
            aircraftSessionBeanLocal.createAircraft(ac12);
            aircraftSessionBeanLocal.createAircraft(ac13);
            aircraftSessionBeanLocal.createAircraft(ac14);
            aircraftSessionBeanLocal.createAircraft(ac15);
            aircraftSessionBeanLocal.createAircraft(ac16);
            aircraftSessionBeanLocal.createAircraft(ac17);
            aircraftSessionBeanLocal.createAircraft(ac18);
            aircraftSessionBeanLocal.createAircraft(ac19);
            aircraftSessionBeanLocal.createAircraft(ac20);
            aircraftSessionBeanLocal.createAircraft(ac21);
            aircraftSessionBeanLocal.createAircraft(ac22);
            aircraftSessionBeanLocal.createAircraft(ac23);
            aircraftSessionBeanLocal.createAircraft(ac24);
            aircraftSessionBeanLocal.createAircraft(ac25);
            aircraftSessionBeanLocal.createAircraft(ac26);
            aircraftSessionBeanLocal.createAircraft(ac27);
            aircraftSessionBeanLocal.createAircraft(ac28);
            aircraftSessionBeanLocal.createAircraft(ac29);
            aircraftSessionBeanLocal.createAircraft(ac30);
        }
        
        if(em.find(Partner.class, 1l) == null) {
            Partner em1 = new Partner("Holiday.com", "mlair@holiday.com", "password");
            partnerSessionBeanLocal.createNewAccount(em1);
        }
    }
    
}
