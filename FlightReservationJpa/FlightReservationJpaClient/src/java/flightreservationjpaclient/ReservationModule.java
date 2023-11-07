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
import entity.Cabin;
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
                searchFlight(sc);
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
    public void searchFlight(Scanner sc) throws Exception{
        System.out.println("\n*** YOU HAVE PICKED RESERVE FLIGHT ***");
        System.out.println("*** ENTER FLIGHT DETAILS ***\n");
        System.out.println("Trip Type: ");
        System.out.println("1: Return Flight");
        System.out.println("2: Round Trip");
        System.out.print("Enter Trip Type> ");
        int tripType = sc.nextInt();
        if (tripType != 1 && tripType != 2) {
            System.out.println("Wrong input, try again");
            searchFlight(sc);
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
            searchFlight(sc);
        }
        sc.nextLine();
        List<Flight> listOfFlights = new ArrayList<>();
        //1. Get list of flights that have origin to destination the same as input
        if (tripType == 2) {
            System.out.println("\n*** FIRST FLIGHT BOOKING ***");
        }
        if (flightType == 1) {
            searchDirectFlight(sc, depAirport, destAirport, departureDate, numOfPassengers);
        } else {
            searchConnectingFlight(sc, depAirport, destAirport, departureDate);
        }
        
        if (tripType == 2) {
            System.out.println("\n*** RETURN FLIGHT BOOKING ***");
            if (flightType == 1){
                searchDirectFlight(sc, destAirport, depAirport, returnDate, numOfPassengers);
            } else {
                searchConnectingFlight(sc, destAirport, depAirport, returnDate);
            }
        }
    }
    
    public void searchConnectingFlight(Scanner sc, long depAirport, long destAirport, Date departureDate) throws Exception {
        long hubId = 1;
        List<Flight> listOfFlightsToHub = flightSessionBeanRemote.retrieveFlightsThatHasDepAndDest(depAirport, hubId);
        List<Flight> listOfFlightsFromHub = flightSessionBeanRemote.retrieveFlightsThatHasDepAndDest(hubId, destAirport);
        List<FlightSchedulePlan> listOfFlightSchedulePlanToHub = flightSchedulePlanSessionBeanRemote.retrieveFlightSchedulePlanWithSameFlight(listOfFlightsToHub);
        List<FlightSchedulePlan> listOfFlightSchedulePlanFromHub = flightSchedulePlanSessionBeanRemote.retrieveFlightSchedulePlanWithSameFlight(listOfFlightsFromHub);
        int flightnum = 1;
        List<FlightSchedule> listOfFlightScheduleToHubSameDay = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWithSameTiming(listOfFlightSchedulePlanToHub, departureDate);
        
        System.out.print("\n*** FIRST, PICK FLIGHT GOING TO TAOYUAN AIRPORT (HUB) ***");
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
        
        int schedId = -1;
        while(schedId != 0) {
            System.out.println("\n(Enter 0 to Reserve Flight)");
            System.out.print("Enter Schedule ID to see more details> ");
            schedId = sc.nextInt();
            if (schedId != 0) {
                checkFlightDetails(sc, schedId);
            }
        }
        
        sc.nextLine();
        System.out.print("Enter Flight Schedule Id to reserve> ");
        int confirmId = sc.nextInt();
        sc.nextLine();
        System.out.println("*** YOU HAVE SELECTED " + confirmId + " ***");
        System.out.println("Press Y to confirm N to restart");
        String next = sc.nextLine().trim();
        if (next.equalsIgnoreCase("N")) {
            customerLoginPage();
        } else {
            System.out.println("BOOKED FIRST FLIGHT");
        }
        
        long flightSchedId = confirmId;
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
        
        schedId = -1;
        while(schedId != 0) {
            System.out.println("\n(Enter 0 to Reserve Flight)");
            System.out.print("Enter Schedule ID to see more details> ");
            schedId = sc.nextInt();
            if (schedId != 0) {
                checkFlightDetails(sc, schedId);
            }
        }
        
        sc.nextLine();
        System.out.print("Enter Flight Schedule Id to reserve> ");
        confirmId = sc.nextInt();
        sc.nextLine();
        System.out.println("*** YOU HAVE SELECTED " + confirmId + " ***");
        System.out.println("Press Y to confirm N to restart");
        next = sc.nextLine().trim();
        if (next.equalsIgnoreCase("N")) {
            customerLoginPage();
        } else {
            System.out.println("BOOKED FIRST FLIGHT");
        }
    }
    
    public int printStatementForFlightSchedule(List<FlightSchedule> flightSchedules, int Number) {
        for(int i = 0; i < flightSchedules.size(); i ++) {
            //System.out.println("No." + (Number++));
            FlightSchedule fs = flightSchedules.get(i);
            System.out.println("Filght Schedule ID: " + fs.getFlightScheduleId());
            System.out.println("Filght Departure Date Time: " + fs.getDepartureDateTime());
            System.out.println("Filght Estimated Arrival Date Time: " + fs.getArrivalDateTime());
            System.out.println("Filght Estimated Time: " + fs.getEstimatedTime());
        }
        return Number;
    }
    
    public void searchDirectFlight(Scanner sc, long depAirport, long destAirport, Date departureDate, int numOfSeats) throws Exception {
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
        
        int schedId = -1;
        while(schedId != 0) {
            System.out.println("\n(Enter 0 to Reserve Flight)");
            System.out.print("Enter Schedule ID to see more details> ");
            schedId = sc.nextInt();
            if (schedId != 0) {
                checkFlightDetails(sc, schedId);
            }
        }
        
        sc.nextLine();
        System.out.print("Enter Flight Schedule Id to reserve> ");
        int confirmId = sc.nextInt();
        sc.nextLine();
        System.out.println("*** YOU HAVE SELECTED " + confirmId + " ***");
        System.out.println("Press Y to confirm N to restart");
        String next = sc.nextLine().trim();
        if (next.equalsIgnoreCase("N")) {
            customerLoginPage();
        } else {
            reserveFlight(confirmId, sc, numOfSeats);
            System.out.println("BOOKED FIRST FLIGHT");
        }
    }
    
    public void checkFlightDetails(Scanner sc, long scheduleId) {
        System.out.println(String.format("\n*** DETAILS FOR FLIGHT SCHEDULE %s ***", scheduleId));
        FlightSchedule fs = flightScheduleSessionBeanRemote.getFlightScheduleWithId(scheduleId);
        System.out.println("Filght Schedule ID: " + fs.getFlightScheduleId());
        System.out.println("Filght Departure Date Time: " + fs.getDepartureDateTime());
        System.out.println("Filght Estimated Arrival Date Time: " + fs.getArrivalDateTime());
        System.out.println("Filght Estimated Time: " + fs.getEstimatedTime());
        System.out.println("\n*** CABIN DETAILS ***");
        List<Cabin> cabins = flightScheduleSessionBeanRemote.getCabins(scheduleId);
        for (Cabin c : cabins) {
            System.out.println("Cabin Class : " + c.getCabinClassName());
            System.out.println("Total Seats: " + c.getTotalSeats());
            System.out.println("Remaining Seats: " + (c.getTotalSeats() - c.getReservedSeats()));
            System.out.println("");
            //print out price here as well
        }
    }
    
    public void reserveFlight(long flightScheduleId, Scanner sc, int numOfSeats) {
        checkFlightDetails(sc, flightScheduleId);
        System.out.print("Enter Cabin You wan to Reserve> ");
        String cabin = sc.nextLine().trim();
        char[][] cabinSeatingPlan = flightScheduleSessionBeanRemote.getCabinSeats(flightScheduleId, cabin);
        Integer[] islesPlan = flightScheduleSessionBeanRemote.getIslesPlan(flightScheduleId, cabin);
        System.out.println("*** SEATING CONFIGURATION *** ");
        System.out.print("LETTER ");
        char seatNum = 'A';
        int count = 0;
        int c = 0;
        for (int i = 0; i < cabinSeatingPlan[0].length; i++) {
            System.out.print(seatNum);
            seatNum++;
            count++;
            if (count == islesPlan[c] && c != islesPlan.length - 1) {
                System.out.print("|");
                c++;
                count = 0;
            }
        }
        System.out.println("");
        for (int i = 0; i < cabinSeatingPlan.length; i++) {
            if (i < 9) {
                System.out.print("ROW  " + (i + 1) + " ");
            } else {
                System.out.print("ROW " + (i + 1) + " ");
            }
            count = 0;
            c = 0;
            for (int j = 0; j < cabinSeatingPlan[0].length; j++) {
                System.out.print(cabinSeatingPlan[i][j]);
                count++;
                if (count == islesPlan[c] && c != islesPlan.length - 1) {
                    System.out.print("|");
                    c++;
                    count = 0;
                }
            }
            System.out.println("");
        }
        
        for (int i = 0; i < numOfSeats; i ++) {
            System.out.println(String.format("Enter No. %s Seat you wan to reserve: ", (i + 1)));
            System.out.print("Enter Row Number> ");
            int rowNum = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Seat Letter> ");
            String seat = sc.nextLine().trim();
            char letter = seat.charAt(0);
            flightScheduleSessionBeanRemote.bookSeat(flightScheduleId, cabin, rowNum, letter);
            System.out.print("Enter First Name Of Customer> ");
            String firstName = sc.nextLine().trim();
            System.out.print("Enter Last Name Of Customer> ");
            String lastName = sc.nextLine().trim();
            System.out.print("Enter Passport Number Of Customer> ");
            String passport = sc.nextLine().trim();
            System.out.println("*** SEAT BOOKED ***");
            System.out.println("");
        }
        
    }
}
