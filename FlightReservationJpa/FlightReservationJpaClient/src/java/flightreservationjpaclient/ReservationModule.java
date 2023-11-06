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
import util.exception.FlightDoesNotExist;

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
        Date returnDate = null;
        if (tripType == 2) {
            System.out.print("Enter Return Date (in the format YYYY-MM-DD)> ");
            returnDate = dateFormat.parse(sc.nextLine());
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
        if (tripType == 2) {
            System.out.println("*** FIRST FLIGHT ***");
        }
        if (flightType == 1) {
            bookingDirectFlight(sc, depAirport, destAirport, departureDate);
        } else {
            bookConnectingFlight(sc, depAirport, destAirport, departureDate);
        }
        
        if (tripType == 2) {
            System.out.println("*** RETURN FLIGHT ***");
            if (flightType == 1){
                bookingDirectFlight(sc, destAirport, depAirport, returnDate);
            } else {
                bookConnectingFlight(sc, destAirport, depAirport, returnDate);
            }
        }
    }
    
    public void bookConnectingFlight(Scanner sc, long depAirport, long destAirport, Date departureDate) throws FlightDoesNotExist {
        long hubId = 1;
        List<Flight> listOfFlightsToHub = flightSessionBeanRemote.retrieveFlightsThatHasDepAndDest(depAirport, hubId);
        List<Flight> listOfFlightsFromHub = flightSessionBeanRemote.retrieveFlightsThatHasDepAndDest(hubId, destAirport);
        List<FlightSchedulePlan> listOfFlightSchedulePlanToHub = flightSchedulePlanSessionBeanRemote.retrieveFlightSchedulePlanWithSameFlight(listOfFlightsToHub);
        List<FlightSchedulePlan> listOfFlightSchedulePlanFromHub = flightSchedulePlanSessionBeanRemote.retrieveFlightSchedulePlanWithSameFlight(listOfFlightsFromHub);
        int flightnum = 1;
        List<FlightSchedule> listOfFlightScheduleToHubSameDay = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWithSameTiming(listOfFlightSchedulePlanToHub, departureDate);
        
        System.out.println("\n*** FIRST, PICK FLIGHT GOING TO TAOYUAN AIRPORT (HUB) ***");
        System.out.println(String.format("\n*** %s FLIGHT ON THE SAME DAY ***\n", listOfFlightScheduleToHubSameDay.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightScheduleToHubSameDay, flightnum);
        
        //4. get list of flight schedule that is 3 days before
        List<FlightSchedule> listOfFlightScheduleHub3daysBefore = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWith3DaysBefore(listOfFlightSchedulePlanToHub, departureDate);
        System.out.println(String.format("\n*** %s FLIGHT 3 DAYS BEFORE ***\n", listOfFlightScheduleHub3daysBefore.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightScheduleHub3daysBefore, flightnum);
        
        
        //5. get list of flight schedule that is 3 days After
        List<FlightSchedule> listOfFlightScheduleHub3daysAfter = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWith3DaysAfter(listOfFlightSchedulePlanToHub, departureDate);
        System.out.println(String.format("\n*** %s FLIGHT 3 DAYS AFTER ***\n", listOfFlightScheduleHub3daysAfter.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightScheduleHub3daysAfter, flightnum);
        
        System.out.print("Enter Schedule ID of Flight You Want to Reserve> ");
        int flightSchedToHub = sc.nextInt();
        sc.nextLine();
        long flightSchedId = flightSchedToHub;
        Date dateOfFlightPicked = flightScheduleSessionBeanRemote.retrieveDateOfFlightPicked(flightSchedId);
        
        flightnum = 1;
        System.out.println("\n*** NEXT, PICK FLIGHT GOING OUT OF TAOYUAN AIRPORT (HUB) ***");
        List<FlightSchedule> listOfFlightSchedulesFromHubSameDay = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanAfterTiming(listOfFlightSchedulePlanFromHub, dateOfFlightPicked);
        System.out.println(String.format("\n*** %s FLIGHT ON THE SAME DAY ***", listOfFlightSchedulesFromHubSameDay.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightSchedulesFromHubSameDay, flightnum);
        
        //5. get list of flight schedule that is 3 days After
        List<FlightSchedule> listOfFlightScheduleFromHub3daysAfter = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWith3DaysAfter(listOfFlightSchedulePlanFromHub, dateOfFlightPicked);
        System.out.println(String.format("\n*** %s FLIGHT 3 DAYS AFTER ***", listOfFlightScheduleFromHub3daysAfter.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightScheduleFromHub3daysAfter, flightnum);
        
        System.out.print("Enter Schedule ID of Flight You Want to Reserve> ");
        int flightSchedFromHub = sc.nextInt();
        sc.nextLine();
    }
    
    public int printStatementForFlightSchedule(List<FlightSchedule> flightSchedules, int Number) {
        for(int i = 0; i < flightSchedules.size(); i ++) {
            System.out.println("No." + (Number++));
            System.out.println("Filght Schedule ID: " + flightSchedules.get(i).getFlightScheduleId());
            System.out.println("Filght Departure Date Time: " + flightSchedules.get(i).getDepartureDateTime());
            System.out.println("Filght Estimated Arrival Date Time: " + flightSchedules.get(i).getArrivalDateTime());
            System.out.println("Filght Estimated Time: " + flightSchedules.get(i).getEstimatedTime());
            System.out.println("");
        }
        return Number;
    }
    
    public void bookingDirectFlight(Scanner sc, long depAirport, long destAirport, Date departureDate) {
        List<Flight> listOfFlights = flightSessionBeanRemote.retrieveFlightsThatHasDepAndDest(depAirport, destAirport);
        //2. Get list of Flight shedule plan that has the same Flight number as the list of flights that we got
        List<FlightSchedulePlan> listOfFlightSchedulePlan = flightSchedulePlanSessionBeanRemote.retrieveFlightSchedulePlanWithSameFlight(listOfFlights);
        
        //3. get list of flight schedule that is on the same day
        int flightnum = 1;
        List<FlightSchedule> listOfFlightSchedule = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWithSameTiming(listOfFlightSchedulePlan, departureDate);
        System.out.println(String.format("\n*** %s FLIGHT ON THE SAME DAY ***", listOfFlightSchedule.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightSchedule, flightnum);
        
        //4. get list of flight schedule that is 3 days before
        List<FlightSchedule> listOfFlightSchedule3daysBefore = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWith3DaysBefore(listOfFlightSchedulePlan, departureDate);
        System.out.println(String.format("\n*** %s FLIGHT 3 DAYS BEFORE ***", listOfFlightSchedule3daysBefore.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightSchedule3daysBefore, flightnum);
        
        //5. get list of flight schedule that is 3 days After
        List<FlightSchedule> listOfFlightSchedule3daysAfter = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWith3DaysAfter(listOfFlightSchedulePlan, departureDate);
        System.out.println(String.format("\n*** %s FLIGHT 3 DAYS AFTER ***", listOfFlightSchedule3daysAfter.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightSchedule3daysAfter, flightnum);
        
        System.out.print("Enter Schedule ID You Want to Reserve> ");
    }
}
