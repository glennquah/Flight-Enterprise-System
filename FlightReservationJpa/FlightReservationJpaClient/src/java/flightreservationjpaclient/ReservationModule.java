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
import ejb.session.stateless.FlightSchedulePlanSessionBeanRemote;
import ejb.session.stateless.FlightScheduleSessionBeanRemote;
import ejb.session.stateless.FlightSessionBeanRemote;
import entity.Airport;
import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    private FlightSchedulePlanSessionBeanRemote flightSchedulePlanSessionBeanRemote;
    private FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote;

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
            FlightSessionBeanRemote flightSessionBeanRemote,
            FlightSchedulePlanSessionBeanRemote flightSchedulePlanSessionBeanRemote,
            FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote) {
        this.customerId = customerId;
        this.employeeSessionBean = employeeSessionBean;
        this.customerSessionBean = customerSessionBean;
        this.aircraftSessionBeanRemote = aircraftSessionBeanRemote;
        this.aircraftConfigurationSessionBeanRemote = aircraftConfigurationSessionBeanRemote;
        this.cabinCustomerSessionBeanRemote = cabinCustomerSessionBeanRemote;
        this.flightRoutesSessionBeanRemote = flightRoutesSessionBeanRemote;
        this.airportSessionBeanRemote = airportSessionBeanRemote;
        this.flightSessionBeanRemote = flightSessionBeanRemote;
        this.flightSchedulePlanSessionBeanRemote = flightSchedulePlanSessionBeanRemote;
        this.flightScheduleSessionBeanRemote = flightScheduleSessionBeanRemote;
    }
    
    public void customerLoginPage() throws Exception {
        Scanner sc = new Scanner(System.in);
        Integer response;
        System.out.println("*** YOU HAVE SUCCESSFULLY LOGIN ***\n");
        while(true) {
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
    public void reserveFlight(Scanner sc) throws Exception{
        System.out.println("\n*** YOU HAVE PICKED RESERVE FLIGHT ***");
        System.out.println("*** ENTER FLIGHT DETAILS ***\n");
        System.out.println("Trip Type: ");
        System.out.println("1: Return Flight");
        System.out.println("2: Round Trip");
        System.out.print("Enter Trip Type> ");
        int tripType = sc.nextInt();
        if (tripType != 1 && tripType != 2) {
            System.out.println("Wrong input, try again");
            reserveFlight(sc);
        }
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
        System.out.print("Enter Departure Date (in the format YYYY-MM-DD)> ");
        //LocalDateTime departureDateTime = LocalDateTime.parse(sc.nextLine());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date departureDate = dateFormat.parse(sc.nextLine());
        
        //check
        //System.out.println("Departure Date> " + departureDate);
    
        if (tripType == 2) {
            System.out.print("Enter Return Date (in the format YYYY-MM-DD)> ");
            Date returnDate = dateFormat.parse(sc.nextLine());
        }
        
        System.out.print("Enter number of Passengers> ");
        int numOfPassengers = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Pick Flight Type: ");
        System.out.println("1: Direct Flight");
        System.out.println("2: Connecting Flight");
        System.out.print("Enter Flight Type> ");
        int flightType = sc.nextInt();
        if (flightType != 1 && flightType != 2) {
            System.out.println("Wrong input, try again");
            reserveFlight(sc);
        }
        sc.nextLine();
        List<Flight> listOfFlights = new ArrayList<>();
        //1. Get list of flights that have origin to destination the same as input
        if (flightType == 1) {
        //1a. If this is direct flight
            listOfFlights = flightSessionBeanRemote.retrieveFlightsThatHasDepAndDest(depAirport, destAirport);
        } else {
            listOfFlights = flightSessionBeanRemote.retrieveFlightsThatHasDepAndDestConnectingFlight(depAirport, destAirport);
        }
        System.out.println("SIZE IS =" + listOfFlights.size());

        //2. Get list of Flight shedule plan that has the same Flight number as the list of flights that we got
        List<FlightSchedulePlan> listOfFlightSchedulePlan = flightSchedulePlanSessionBeanRemote.retrieveFlightSchedulePlanWithSameFlight(listOfFlights);
        
        //3. get list of flight schedule that is on the same day
        int flightnum = 1;
        List<FlightSchedule> listOfFlightSchedule = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWithSameTiming(listOfFlightSchedulePlan, departureDate);
        System.out.println(String.format("\n*** %s FLIGHT ON THE SAME DAY ***\n", listOfFlightSchedule.size()));
        for(int i = 0; i < listOfFlightSchedule.size(); i ++) {
            System.out.println("Number: " + (flightnum++));
            System.out.println("Filght Schedule ID: " + listOfFlightSchedule.get(i).getFlightScheduleId());
            System.out.println("Filght Departure Date Time: " + listOfFlightSchedule.get(i).getDepartureDateTime());
            System.out.println("Filght Estimated Arrival Date Time: " + listOfFlightSchedule.get(i).getArrivalDateTime());
            System.out.println("Filght Estimated Time: " + listOfFlightSchedule.get(i).getEstimatedTime());
            System.out.println("");
        }
        
        //4. get list of flight schedule that is 3 days before
        List<FlightSchedule> listOfFlightSchedule3daysBefore = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWith3DaysBefore(listOfFlightSchedulePlan, departureDate);
        System.out.println(String.format("\n*** %s FLIGHT 3 DAYS BEFORE ***\n", listOfFlightSchedule3daysBefore.size()));
        for(int i = 0; i < listOfFlightSchedule3daysBefore.size(); i ++) {
            System.out.println("Number : " + (flightnum++));
            System.out.println("Filght Schedule ID: " + listOfFlightSchedule3daysBefore.get(i).getFlightScheduleId());
            System.out.println("Filght Departure Date Time: " + listOfFlightSchedule3daysBefore.get(i).getDepartureDateTime());
            System.out.println("Filght Estimated Arrival Date Time: " + listOfFlightSchedule3daysBefore.get(i).getArrivalDateTime());
            System.out.println("Filght Estimated Time: " + listOfFlightSchedule3daysBefore.get(i).getEstimatedTime());
            System.out.println("");
        }
        
        //5. get list of flight schedule that is 3 days After
        List<FlightSchedule> listOfFlightSchedule3daysAfter = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWith3DaysAfter(listOfFlightSchedulePlan, departureDate);
        System.out.println(String.format("\n*** %s FLIGHT 3 DAYS AFTER ***\n", listOfFlightSchedule3daysAfter.size()));
        for(int i = 0; i < listOfFlightSchedule3daysAfter.size(); i ++) {
            System.out.println("Number: " + (flightnum++));
            System.out.println("Filght Schedule ID: " + listOfFlightSchedule3daysAfter.get(i).getFlightScheduleId());
            System.out.println("Filght Departure Date Time: " + listOfFlightSchedule3daysAfter.get(i).getDepartureDateTime());
            System.out.println("Filght Estimated Arrival Date Time: " + listOfFlightSchedule3daysAfter.get(i).getArrivalDateTime());
            System.out.println("Filght Estimated Time: " + listOfFlightSchedule3daysAfter.get(i).getEstimatedTime().toString());
            System.out.println("");
        }
        
        System.out.println("Enter Schedule ID You Want to Reserve> ");
    }
    
    
}
