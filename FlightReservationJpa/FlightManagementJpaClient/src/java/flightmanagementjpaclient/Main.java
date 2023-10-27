/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package flightmanagementjpaclient;

import ejb.session.stateless.AircraftConfigurationSessionBeanRemote;
import ejb.session.stateless.AircraftSessionBeanRemote;
import ejb.session.stateless.AirportSessionBeanRemote;
import ejb.session.stateless.CabinCustomerSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import ejb.session.stateless.FlightRoutesSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author admin
 */
public class Main {

    @EJB(name = "FlightRoutesSessionBeanRemote")
    private static FlightRoutesSessionBeanRemote flightRoutesSessionBeanRemote;

    @EJB(name = "EmployeeSessionBeanRemote")
    private static EmployeeSessionBeanRemote employeeSessionBeanRemote;

    @EJB(name = "CustomerSessionBeanRemote")
    private static CustomerSessionBeanRemote customerSessionBeanRemote;

    @EJB(name = "AirportSessionBeanRemote")
    private static AirportSessionBeanRemote airportSessionBeanRemote;

    @EJB(name = "AircraftConfigurationSessionBeanRemote")
    private static AircraftConfigurationSessionBeanRemote aircraftConfigurationSessionBeanRemote;

    @EJB(name = "CabinCustomerSessionBeanRemote")
    private static CabinCustomerSessionBeanRemote cabinCustomerSessionBeanRemote;

    @EJB(name = "AircraftSessionBeanRemote")
    private static AircraftSessionBeanRemote aircraftSessionBeanRemote;
    

    public static void main(String[] args) {
        MainApp mainApp = new MainApp(employeeSessionBeanRemote, customerSessionBeanRemote, 
                    aircraftSessionBeanRemote, aircraftConfigurationSessionBeanRemote, 
                    cabinCustomerSessionBeanRemote, flightRoutesSessionBeanRemote, 
                    airportSessionBeanRemote);
        mainApp.runApp();
    }
    
}
