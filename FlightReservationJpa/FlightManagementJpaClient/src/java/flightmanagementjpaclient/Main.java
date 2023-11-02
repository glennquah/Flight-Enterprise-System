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
import ejb.session.stateless.FlightSchedulePlanSessionBeanRemote;
import ejb.session.stateless.FlightScheduleSessionBeanRemote;
import ejb.session.stateless.FlightSessionBeanRemote;
import javax.ejb.EJB;
import util.exception.AirportDoesNotExistException;
import util.exception.InvalidInputException;

/**
 *
 * @author admin
 */
public class Main {

    @EJB(name = "FlightSessionBeanRemote")
    private static FlightSessionBeanRemote flightSessionBeanRemote;

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
    
    @EJB(name = "FlightScheduleSessionBeanRemote")
    private static FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote;

    @EJB(name = "FlightSchedulePlanSessionBeanRemote")
    private static FlightSchedulePlanSessionBeanRemote flightSchedulePlanSessionBeanRemote;
    
    public static void main(String[] args) throws Exception {
        MainApp mainApp = new MainApp(employeeSessionBeanRemote, customerSessionBeanRemote, 
                    aircraftSessionBeanRemote, aircraftConfigurationSessionBeanRemote, 
                    cabinCustomerSessionBeanRemote, flightRoutesSessionBeanRemote, 
                    airportSessionBeanRemote, flightSessionBeanRemote, flightScheduleSessionBeanRemote,
                    flightSchedulePlanSessionBeanRemote);
        mainApp.runApp();
    }
    
}
