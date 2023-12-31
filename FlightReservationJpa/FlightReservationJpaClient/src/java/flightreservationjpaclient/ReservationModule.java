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
import ejb.session.stateless.FareSessionBeanRemote;
import ejb.session.stateless.FlightRoutesSessionBeanRemote;
import ejb.session.stateless.FlightSchedulePlanSessionBeanRemote;
import ejb.session.stateless.FlightScheduleSessionBeanRemote;
import ejb.session.stateless.FlightSessionBeanRemote;
import ejb.session.stateless.ReservationDetailsSessionBeanRemote;
import entity.Airport;
import entity.Cabin;
import entity.Fare;
import entity.Flight;
import entity.FlightRoute;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import entity.ReservationDetails;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import util.exception.FlightScheduleDoesNotExistException;

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
    private ReservationDetailsSessionBeanRemote reservationDetailsSessionBeanRemote;
    private FareSessionBeanRemote fareSessionBeanRemote;

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
            FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote,
            ReservationDetailsSessionBeanRemote reservationDetailsSessionBeanRemote,
            FareSessionBeanRemote fareSessionBeanRemote) {
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
        this.reservationDetailsSessionBeanRemote = reservationDetailsSessionBeanRemote;
        this.fareSessionBeanRemote = fareSessionBeanRemote;
    }
    
    // =========================================LOGIN SCREEN=====================================================
    public void customerLoginPage() throws Exception {
        Scanner sc = new Scanner(System.in);
        Integer response;
        System.out.println("*** YOU HAVE SUCCESSFULLY LOGIN ***\n");
        while(true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION *** \n");
            System.out.println("1: Search or Reserve Flight");
            System.out.println("2: View My Flight Reservation");
            System.out.println("3: Log Out");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                searchFlight(sc);
            } else if (response == 2) {
                viewFlightReservation(sc);
            } else if (response == 3) {
                System.out.println("*** SUCCESSFULLY LOGOUT ***");
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
    
    // =========================================RESERVE FLIGHT=====================================================
    public void searchFlight(Scanner sc) throws Exception{
        System.out.println("\n*** YOU HAVE PICKED SEARCH FLIGHT ***");
        System.out.println("*** ENTER FLIGHT DETAILS ***\n");
        System.out.println("Trip Type: ");
        System.out.println("1: One Way Flight");
        System.out.println("2: Round Trip");
        System.out.print("Enter Trip Type> ");
        int tripType = sc.nextInt();
        if (tripType != 1 && tripType != 2) {
            System.out.println("Wrong input, try again");
            searchFlight(sc);
        }
        sc.nextLine();
        List<Airport> listOfAirports = airportSessionBeanRemote.retrieveAllAirports();
        printAirport(listOfAirports);
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
        System.out.println("3: No Preference");
        System.out.print("Enter Flight Type> ");
        int flightType = sc.nextInt();
        if (flightType != 1 && flightType != 2 && flightType != 3) {
            System.out.println("Wrong input, try again");
            searchFlight(sc);
        }
        sc.nextLine();
        List<Flight> listOfFlights = new ArrayList<>();
        //1. Get list of flights that have origin to destination the same as input
        if (tripType == 2) {
            System.out.println("\n*** FIRST FLIGHT BOOKING ***");
        }
        BigDecimal fare = BigDecimal.ZERO;
        if (flightType == 1) {
            if (tripType == 2) {
                fare = fare.add(searchDirectFlight(sc, depAirport, destAirport, departureDate, numOfPassengers, fare, false));
            } else {
                fare = fare.add(searchDirectFlight(sc, depAirport, destAirport, departureDate, numOfPassengers, fare, true));
            }
        } else {
            if (tripType == 2) {
                fare = fare.add(searchConnectingFlight(flightType, sc, depAirport, destAirport, departureDate, numOfPassengers, fare, false));
            } else {
                fare = fare.add(searchConnectingFlight(flightType, sc, depAirport, destAirport, departureDate, numOfPassengers, fare, true));
            }  
        }
        
        if (tripType == 2) {
            System.out.println("\n*** RETURN FLIGHT BOOKING ***");
            if (flightType == 1){
                fare = fare.add(searchDirectFlight(sc, destAirport, depAirport, returnDate, numOfPassengers, fare, true));
            } else {
                fare = fare.add(searchConnectingFlight(flightType, sc, destAirport, depAirport, returnDate, numOfPassengers, fare, true));
            }
        }
    }
    
    public BigDecimal searchConnectingFlight(int flightType, Scanner sc, long depAirport, long destAirport, Date departureDate, int numOfSeats, BigDecimal fare, boolean connectedFlight) throws Exception {
        //change this
        List<Long> listOfHubsId = new ArrayList<>();
        if (flightType == 2) {
            listOfHubsId = airportSessionBeanRemote.getListOfHubsIdConnecting(destAirport);
        } else if (flightType == 3) {
            listOfHubsId = airportSessionBeanRemote.getListOfHubsId();
        }
  
        List<Flight> listOfFlightsToHub = new ArrayList<>();
        
        for (Long hubId : listOfHubsId) {
            List<Flight> listToHub = flightSessionBeanRemote.retrieveFlightsThatHasDepAndDest(depAirport, hubId);
            for (Flight f : listToHub) {
                listOfFlightsToHub.add(f);
            }
        }
        List<FlightSchedulePlan> listOfFlightSchedulePlanToHub = flightSchedulePlanSessionBeanRemote.retrieveFlightSchedulePlanWithSameFlight(listOfFlightsToHub);
        
        int flightnum = 1;
        List<FlightSchedule> listOfFlightScheduleToHubSameDay = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWithSameTimingConnecting(listOfFlightSchedulePlanToHub, departureDate, destAirport);
        if (flightType == 2) {
            System.out.print("\n*** FIRST, PICK FLIGHT GOING TO HUB ***");
        } else {
            System.out.print("\n*** PICK FIRST FLIGHT ***");
        }
        System.out.println("");
        System.out.println("FLIGHT SCHEDULE ID | ORIGIN AIRPORT CODE | DESTINATION AIRPORT CODE | FLIGHT DEPARTURE DATE TIME   | FLIGHT ESTIMATED ARRIVAL DATETIME | FLIGHT DURATION ");
        System.out.println(String.format("====================================================================%s FLIGHT ON THE SAME DAY======================================================================", listOfFlightScheduleToHubSameDay.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightScheduleToHubSameDay, flightnum);
            
        //4. get list of flight schedule that is 3 days before
        List<FlightSchedule> listOfFlightScheduleHub3daysBefore = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWith3DaysBeforeConnecting(listOfFlightSchedulePlanToHub, departureDate, destAirport);
        System.out.println(String.format("=====================================================================%s FLIGHT 3 DAYS BEFORE=======================================================================", listOfFlightScheduleHub3daysBefore.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightScheduleHub3daysBefore, flightnum);
        
        
        //5. get list of flight schedule that is 3 days After
        List<FlightSchedule> listOfFlightScheduleHub3daysAfter = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWith3DaysAfterConnecting(listOfFlightSchedulePlanToHub, departureDate, destAirport);
        System.out.println(String.format("=====================================================================%s FLIGHT 3 DAYS AFTER=======================================================================", listOfFlightScheduleHub3daysAfter.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightScheduleHub3daysAfter, flightnum);
        
        int schedId = -2;
        while(schedId != 0 && schedId != -1) {
            System.out.println("Enter 0 to Reserve Flight"); 
            System.out.println("Enter -1 to Go back Flight");
            System.out.print("Enter Schedule ID to see more details > ");
            schedId = sc.nextInt();
            sc.nextLine();
            if (schedId != 0 && schedId != -1) {
                checkFlightDetails(sc, schedId, numOfSeats);
            }
            if (schedId == -1) {
                customerLoginPage();
            }
        }
        
        System.out.print("Enter Flight Schedule ID to reserve> ");
        int confirmId = sc.nextInt();
        sc.nextLine();
        System.out.println("*** YOU HAVE SELECTED " + confirmId + " ***");
        System.out.print("Press Y to confirm N to restart> ");
        String next = sc.nextLine().trim();
        if (next.equalsIgnoreCase("N")) {
            customerLoginPage();
        } else {
            fare = fare.add(reserveFlight(confirmId, sc, numOfSeats, false, fare));
        }
        
        
        long flightSchedId = confirmId;
        Boolean isDirectFlight = flightScheduleSessionBeanRemote.checkIfFlightSchedIdIsDirect(flightSchedId, destAirport);
        
        if (!isDirectFlight) {
            Date dateOfFlightPicked = flightScheduleSessionBeanRemote.retrieveDateOfFlightPicked(flightSchedId);

            long pickedAirportId = flightScheduleSessionBeanRemote.getAirportIdWithFlightScheduleId(flightSchedId);


            List<Flight> listFromHub = flightSessionBeanRemote.retrieveFlightsThatHasDepAndDest(pickedAirportId, destAirport);
            List<FlightSchedulePlan> listOfFlightSchedulePlanFromHub = flightSchedulePlanSessionBeanRemote.retrieveFlightSchedulePlanWithSameFlight(listFromHub);

            flightnum = 1;
            System.out.println("\n*** NEXT, PICK FLIGHT GOING OUT OF HUB ***");
            System.out.println("FLIGHT SCHEDULE ID | ORIGIN AIRPORT CODE | DESTINATION AIRPORT CODE | FLIGHT DEPARTURE DATE TIME   | FLIGHT ESTIMATED ARRIVAL DATETIME | FLIGHT DURATION ");
            List<FlightSchedule> listOfFlightSchedulesFromHubSameDay = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanAfterTiming(listOfFlightSchedulePlanFromHub, dateOfFlightPicked);
            System.out.println(String.format("====================================================================%s FLIGHT ON THE SAME DAY======================================================================", listOfFlightSchedulesFromHubSameDay.size()));
            flightnum = printStatementForFlightSchedule(listOfFlightSchedulesFromHubSameDay, flightnum);
            
            //5. get list of flight schedule that is 3 days After
            List<FlightSchedule> listOfFlightScheduleFromHub1dayAfter = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWith1DayAfter(listOfFlightSchedulePlanFromHub, dateOfFlightPicked);
             System.out.println(String.format("====================================================================%s FLIGHT ONE DAY AFTER=======================================================================", listOfFlightScheduleFromHub1dayAfter.size()));
            flightnum = printStatementForFlightSchedule(listOfFlightScheduleFromHub1dayAfter, flightnum);

            schedId = -2;
            while(schedId != 0 && schedId != -1) {
                System.out.println("Enter 0 to Reserve Flight");
                System.out.println("Enter -1 to Go back Flight");
                System.out.print("Enter Schedule ID to see more details > ");
                schedId = sc.nextInt();
                sc.nextLine();
                if (schedId != 0 && schedId != -1) {
                    checkFlightDetails(sc, schedId, numOfSeats);
                }
                if (schedId == -1) {
                    customerLoginPage();
                }
            }

            System.out.print("Enter Flight Schedule ID to reserve> ");
            confirmId = sc.nextInt();
            sc.nextLine();
            System.out.println("*** YOU HAVE SELECTED " + confirmId + " ***");
            System.out.print("Press Y to confirm N to restart> ");
            next = sc.nextLine().trim();
            if (next.equalsIgnoreCase("N")) {
                customerLoginPage();
            } else {
                if (connectedFlight) {
                    fare = fare.add(reserveFlight(confirmId, sc, numOfSeats, true, fare));
                } else {
                    fare = fare.add(reserveFlight(confirmId, sc, numOfSeats, false, fare));
                }
            }
        }
        return fare;
    }
    
    public void printAirport(List<Airport> listOfAirports) {
        System.out.println("ID | CODE | AIRPORT NAME");
        for (int i = 0; i < listOfAirports.size(); i++) {
            System.out.printf("%2s | %4s | %s", listOfAirports.get(i).getAirportId(), listOfAirports.get(i).getAirportCode(), listOfAirports.get(i).getName());
            System.out.println("");
        }
    }
    
    public int printStatementForFlightSchedule(List<FlightSchedule> flightSchedules, int Number) {
        for(int i = 0; i < flightSchedules.size(); i ++) {
            FlightSchedule fs = flightSchedules.get(i);
            double duration = fs.getEstimatedTime();
            int hours = (int) duration;
            double fractionalHours = duration - hours;
            int minutes = (int) (fractionalHours * 60);
            String formattedTime = String.format("%02d:%02d", hours, minutes);
            System.out.println(String.format("%18s | %19s | %24s | %25s | %33s | %15s ", fs.getFlightScheduleId(), fs.getFlightSchedulePlan().getFlight().getFlightRoute().getOrigin().getAirportCode(), fs.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportCode(), fs.getDepartureDateTime(), fs.getArrivalDateTime(), formattedTime));
        }
        return Number;
    }
    
    public BigDecimal searchDirectFlight(Scanner sc, long depAirport, long destAirport, Date departureDate, int numOfSeats, BigDecimal fare, Boolean lastFlight) throws Exception {
        List<Flight> listOfFlights = flightSessionBeanRemote.retrieveFlightsThatHasDepAndDest(depAirport, destAirport);
        List<FlightSchedulePlan> listOfFlightSchedulePlan = flightSchedulePlanSessionBeanRemote.retrieveFlightSchedulePlanWithSameFlight(listOfFlights);
        
        int flightnum = 1;
        List<FlightSchedule> listOfFlightSchedule = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWithSameTiming(listOfFlightSchedulePlan, departureDate);
        System.out.println("FLIGHT SCHEDULE ID | ORIGIN AIRPORT CODE | DESTINATION AIRPORT CODE | FLIGHT DEPARTURE DATE TIME   | FLIGHT ESTIMATED ARRIVAL DATETIME | FLIGHT DURATION ");
        System.out.println(String.format("====================================================================%s FLIGHT ON THE SAME DAY======================================================================", listOfFlightSchedule.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightSchedule, flightnum);
        
        //4. get list of flight schedule that is 3 days before
        List<FlightSchedule> listOfFlightSchedule3daysBefore = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWith3DaysBefore(listOfFlightSchedulePlan, departureDate);
        System.out.println(String.format("=====================================================================%s FLIGHT 3 DAYS BEFORE=======================================================================", listOfFlightSchedule3daysBefore.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightSchedule3daysBefore, flightnum);
        
        //5. get list of flight schedule that is 3 days After
        List<FlightSchedule> listOfFlightSchedule3daysAfter = flightScheduleSessionBeanRemote.retrieveFlightSchedulePlanWith3DaysAfter(listOfFlightSchedulePlan, departureDate);
        System.out.println(String.format("=====================================================================%s FLIGHT 3 DAYS AFTER=======================================================================", listOfFlightSchedule3daysAfter.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightSchedule3daysAfter, flightnum);
        
        int schedId = -2;
        
        while(schedId != 0 && schedId != -1) {
            System.out.println("Enter 0 to Reserve Flight");
            System.out.println("Enter -1 to Go back Flight");
            System.out.print("Enter Schedule ID to see more details > ");
            schedId = sc.nextInt();
            sc.nextLine();
            if (schedId != 0 && schedId != -1) {
                checkFlightDetails(sc, schedId, numOfSeats);
            }
            if (schedId == -1) {
                customerLoginPage();
            }
        }
        
        System.out.print("Enter Flight Schedule ID to reserve> ");
        int confirmId = sc.nextInt();
        sc.nextLine();
        System.out.println("*** YOU HAVE SELECTED " + confirmId + " ***");
        System.out.print("Press Y to confirm N to restart> ");
        String next = sc.nextLine().trim();
        if (next.equalsIgnoreCase("N")) {
            customerLoginPage();
        } else {
            if (lastFlight) {
                fare = fare.add(reserveFlight(confirmId, sc, numOfSeats, true, fare));
            } else {
                fare = fare.add(reserveFlight(confirmId, sc, numOfSeats, false, fare));
            }
            
        }
        return fare;
    }
    
    public void checkFlightDetails(Scanner sc, long scheduleId, int numOfSeats) throws FlightScheduleDoesNotExistException {
        System.out.println(String.format("\n*** DETAILS FOR FLIGHT SCHEDULE %s ***", scheduleId));
        FlightSchedule fs = flightScheduleSessionBeanRemote.getFlightScheduleWithId(scheduleId);
        System.out.println("Filght Schedule ID: " + fs.getFlightScheduleId());
        System.out.println("Filght Departure Date Time: " + fs.getDepartureDateTime());
        System.out.println("Filght Estimated Arrival Date Time: " + fs.getArrivalDateTime());
        double duration = fs.getEstimatedTime();
        int hours = (int) duration;
        double fractionalHours = duration - hours;
        int minutes = (int) (fractionalHours * 60);
        
        String formattedTime = String.format("%02d:%02d", hours, minutes);
        System.out.println("Filght Estimated Time: " + formattedTime);
        System.out.println("\n*** CABIN DETAILS ***");
        List<Cabin> cabins = flightScheduleSessionBeanRemote.getCabins(scheduleId);
        System.out.printf("Cabin Class | Total Seats | Remaining Seats | Fare Per Ticket | Total Fare \n");
        for (Cabin c : cabins) {
            long lowestFareid = cabinCustomerSessionBeanRemote.getLowestFareIdInCabin(c.getCabinId());
            BigDecimal lowestFare = fareSessionBeanRemote.getFareUsingId(lowestFareid);
            System.out.printf("%11s | %11s | %15s | %15s |  $%9.2f", c.getCabinClassName(), c.getTotalSeats(), (c.getTotalSeats() - c.getReservedSeats()), lowestFare, (lowestFare.multiply(BigDecimal.valueOf(numOfSeats))));
            System.out.println("");
        }
    }
    
    public BigDecimal reserveFlight(long flightScheduleId, Scanner sc, int numOfSeats, Boolean payment, BigDecimal existingFare) throws FlightScheduleDoesNotExistException, Exception {
        checkFlightDetails(sc, flightScheduleId, numOfSeats);
        System.out.println("1: Pick Cabin");
        System.out.println("2: No Preference for Cabin");
        System.out.print("> ");
        int option = sc.nextInt();
        sc.nextLine();
        String cabin = "";
        if (option == 1) {
            System.out.print("Enter Cabin you want to Reserve> ");
            cabin = sc.nextLine().trim();
        } else if (option == 2) {
            cabin = flightScheduleSessionBeanRemote.getFirstAvailableCabin(flightScheduleId, numOfSeats);
            if (cabin == null) {
                System.out.println("*** There is not enough seats, please select different cabins ***");
            }
        } else {
            System.out.println("Please Pick the right option!");
            reserveFlight(flightScheduleId, sc, numOfSeats, payment, existingFare);
        }
        char[][] cabinSeatingPlan = flightScheduleSessionBeanRemote.getCabinSeats(flightScheduleId, cabin);
        Integer[] islesPlan = flightScheduleSessionBeanRemote.getIslesPlan(flightScheduleId, cabin);
        System.out.printf("*** SEATING CONFIGURATION FOR CABIN %s ***\n", cabin);
        System.out.println("======================SEATING CONFIGURATION======================");
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
        System.out.println("======================SEATING CONFIGURATION======================");
        long lowestFareId = flightScheduleSessionBeanRemote.getLowestFareUsingCabinName(cabin, flightScheduleId);
        //System.out.println("LOWEST FARE ID= " + lowestFareId);
        for (int i = 0; i < numOfSeats; i ++) {
            System.out.println(String.format("Enter No. %s Seat you wan to reserve: ", (i + 1)));
            System.out.print("Enter Row Number> ");
            int rowNum = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Seat Letter> ");
            String seat = sc.nextLine().trim().toUpperCase();
            char letter = seat.charAt(0);
            Boolean seatAvailable = flightScheduleSessionBeanRemote.checkSeatIfAvailable(flightScheduleId, cabin, rowNum, letter);
            if (seatAvailable) {
                flightScheduleSessionBeanRemote.bookSeat(flightScheduleId, cabin, rowNum, letter);
            } else {
                System.out.println("SEAT IS UNAVAILABLE, PLEASE TRY AGAIN");
                searchFlight(sc);
            }
            System.out.print("Enter First Name Of Customer> ");
            String firstName = sc.nextLine().trim();
            System.out.print("Enter Last Name Of Customer> ");
            String lastName = sc.nextLine().trim();
            System.out.print("Enter Passport Number Of Customer> ");
            String passport = sc.nextLine().trim();
            ReservationDetails reservationDetails = new ReservationDetails(firstName, lastName, passport, rowNum, letter);   
            Long reservId = reservationDetailsSessionBeanRemote.createReservationDetails(reservationDetails, this.customerId, flightScheduleId, lowestFareId);
            System.out.println("Reservation Created!");
            System.out.println("Reservation ID = " + reservId);
            System.out.println("*** SEAT BOOKED ***");
            System.out.println("");
            
        }
        BigDecimal lowestFare = fareSessionBeanRemote.getFareUsingId(lowestFareId);
        BigDecimal fare = (lowestFare.multiply(BigDecimal.valueOf(numOfSeats)));
        
        if (payment) {
            BigDecimal totalFare = fare.add(existingFare);
            System.out.println("Total Price: $" + totalFare);
            System.out.print("Enter Credit Card Details> ");
            String ccd = sc.nextLine().trim();
            customerSessionBean.linkCreditCard(this.customerId, ccd);
            System.out.println("Credit Card Details Set!");
            System.out.println("*** FLIGHT RESERVATION DONE ***");
        }
        customerSessionBean.linkFlightSchedule(this.customerId, flightScheduleId);
        return fare;
    }
    
    // =========================================VIEW MY FLIGHT RESERVATION=====================================================
    public void viewFlightReservation(Scanner sc) throws FlightScheduleDoesNotExistException {
        //change this?
        System.out.println("\n*** YOU HAVE SLECTED VIEW ALL FLIGHT RESERVATION ***\n");
        List<FlightSchedule> listOfFlightSchedules = customerSessionBean.getFlightSchedules(this.customerId);
        System.out.println("Flight Schedule ID |   Flight Departure Date Time | Flight Estimated Duration | Flight Origin | Flight Destination");
        
        for (FlightSchedule fs : listOfFlightSchedules) {
            FlightRoute fr = fs.getFlightSchedulePlan().getFlight().getFlightRoute();
            double duration = fs.getEstimatedTime();
            int hours = (int) duration;
            double fractionalHours = duration - hours;
            int minutes = (int) (fractionalHours * 60);
            String formattedTime = String.format("%02d:%02d", hours, minutes);
            System.out.printf("%18s | %26s | %24sH | %13s | %18s\n", fs.getFlightScheduleId(), fs.getDepartureDateTime(), formattedTime, fr.getOrigin().getName(), fr.getDestination().getName());
        }
        
        System.out.print("Enter Flight Schedule ID for more Details> ");
        long flightScheduleId = sc.nextLong();
        viewFlightReservationDetails(sc, flightScheduleId);
    }
    
    public void viewFlightReservationDetails(Scanner sc, long flightScheduleId) throws FlightScheduleDoesNotExistException {
        System.out.println("\n*** VIEW MORE FLIGHT RESERVATION DETAILS ***\n");
        List<ReservationDetails> listOfReservationDetails = flightScheduleSessionBeanRemote.getReservationDetails(flightScheduleId, this.customerId);
        listOfReservationDetails.size();
        BigDecimal fare = BigDecimal.ZERO;
        System.out.println(" Cabin |           First Name |            Last Name | Seat |    Fare ");
        for (ReservationDetails rd : listOfReservationDetails) {
            System.out.printf(" %5s | %20s | %20s | %4s | $%5.2f \n", rd.getFare().getCabin().getCabinClassName(), rd.getFirstName(), rd.getLastName(), rd.getRowNum() + rd.getSeatLetter(), rd.getFare().getFareAmount());
            fare = rd.getFare().getFareAmount();
        }
        System.out.println("Total Amount Paid: $" + fare.multiply(BigDecimal.valueOf(listOfReservationDetails.size())));
        //add fare entity into reservation details
        System.out.println();
    }
    
}
