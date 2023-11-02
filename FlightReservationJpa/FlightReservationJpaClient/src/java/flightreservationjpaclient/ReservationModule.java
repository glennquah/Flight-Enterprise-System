/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightreservationjpaclient;

import ejb.session.stateless.AircraftConfigurationSessionBeanRemote;
import ejb.session.stateless.AircraftSessionBeanRemote;
import ejb.session.stateless.AirportSessionBeanRemote;
import ejb.session.stateless.CabinCustomerSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import ejb.session.stateless.FlightRoutesSessionBeanRemote;
import ejb.session.stateless.FlightSessionBeanRemote;
import entity.Airport;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 *
 * @author Lenovo
 */
public class ReservationModule {

    private Long customerId;
    private EmployeeSessionBeanRemote employeeSessionBean;
    private CustomerSessionBeanRemote customerSessionBean;
    private AircraftSessionBeanRemote aircraftSessionBeanRemote;
    private AircraftConfigurationSessionBeanRemote aircraftConfigurationSessionBeanRemote;
    private CabinCustomerSessionBeanRemote cabinCustomerSessionBeanRemote;
    private FlightRoutesSessionBeanRemote flightRoutesSessionBeanRemote;
    private AirportSessionBeanRemote airportSessionBeanRemote;
    private FlightSessionBeanRemote flightSessionBeanRemote;

    public ReservationModule() {
    }

    public ReservationModule(Long customerId,
            EmployeeSessionBeanRemote employeeSessionBean,
            CustomerSessionBeanRemote customerSessionBean,
            AircraftSessionBeanRemote aircraftSessionBeanRemote,
            AircraftConfigurationSessionBeanRemote aircraftConfigurationSessionBeanRemote,
            CabinCustomerSessionBeanRemote cabinCustomerSessionBeanRemote,
            FlightRoutesSessionBeanRemote flightRoutesSessionBeanRemote,
            AirportSessionBeanRemote airportSessionBeanRemote,
            FlightSessionBeanRemote flightSessionBeanRemote) {
        this.customerId = customerId;
        this.employeeSessionBean = employeeSessionBean;
        this.customerSessionBean = customerSessionBean;
        this.aircraftSessionBeanRemote = aircraftSessionBeanRemote;
        this.aircraftConfigurationSessionBeanRemote = aircraftConfigurationSessionBeanRemote;
        this.cabinCustomerSessionBeanRemote = cabinCustomerSessionBeanRemote;
        this.flightRoutesSessionBeanRemote = flightRoutesSessionBeanRemote;
        this.airportSessionBeanRemote = airportSessionBeanRemote;
        this.flightSessionBeanRemote = flightSessionBeanRemote;
    }
    
    public void customerLoginPage() {
        Scanner sc = new Scanner(System.in);
        Integer response;
        while(true) {
            System.out.println("*** YOU HAVE SUCCESSFULLY LOGIN ***\n");
            System.out.println("Please select the following options \n");
            System.out.println("1: Reserve Flight");
            System.out.println("2: View My Flight Reservation");
            System.out.println("3: Log Out");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                reserveFlight(sc);
            } else if (response == 2) {
                System.out.println("2");
            } else if (response == 3) {
                System.out.println("*** SUCCESSFULLY LOGOUT ***");
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
    
    // **************************************** RESERVE FLIGHT ***************************************************
    public void reserveFlight(Scanner sc) {
        System.out.println("\n*** YOU HAVE PICKED RESERVE FLIGHT ***");
        System.out.println("*** ENTER FLIGHT DETAILS ***\n");
        System.out.println("Trip Type: ");
        System.out.println("1: Return Flight");
        System.out.println("2: Round Trip");
        System.out.print("Enter Trip Type> ");
        int tripType = sc.nextInt();
        sc.nextLine();
        System.out.print("All Aiport: ");
        List<Airport> listOfAirports = airportSessionBeanRemote.retrieveAllAiports();
        for (int i = 0; i < listOfAirports.size(); i++) {
            System.out.println(String.format("%s: Airport Name: ", i + 1) + listOfAirports.get(i).getName());
            System.out.println("ID: " + listOfAirports.get(i).getAirportId());
            System.out.println("");
        }
        System.out.print("Enter Departure Airport ID> ");
        Long depAirport = sc.nextLong();
        sc.nextLine();
        System.out.print("Enter Destination Airport ID> ");
        Long destAirport = sc.nextLong();
        sc.nextLine();
        System.out.print("Enter Departure Date (in the format YYYY-MM-DD HH:MM)> ");
        LocalDateTime departureDateTime = LocalDateTime.parse(sc.nextLine());

        System.out.println("Departure Date> " + departureDateTime);
    
        if (tripType == 2) {
            System.out.print("Enter Return Date (in the format YYYY-MM-DD)> ");
            LocalDateTime returnDateTime = LocalDateTime.parse(sc.nextLine());
        }
        
        System.out.print("Enter number of Passengers> ");
        int numOfPassengers = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Pick Flight Type: ");
        System.out.println("1: Direct Flight");
        System.out.println("2: Connecting Flight");
        System.out.println("Enter Flight Type > ");
        int flightType = sc.nextInt();
        sc.nextLine();
        
        //PICK CABIN TYPE
    }
    
    
}
