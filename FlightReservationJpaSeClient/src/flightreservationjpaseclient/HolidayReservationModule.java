/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightreservationjpaseclient;

import entity.Airport;
import entity.Flight;
import entity.FlightRoute;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import util.exception.FlightScheduleDoesNotExistException;
import ws.partner.FlightDoesNotExistException_Exception;
import ws.partner.FlightSchedule;
import ws.partner.FlightScheduleDoesNotExistException_Exception;
import ws.partner.FlightSchedulePlan;
import ws.partner.InvalidLoginCredentialException_Exception;

/**
 *
 * @author admin
 */
public class HolidayReservationModule {

    private long partnerId;
    
    public HolidayReservationModule() {
    }
    
    public HolidayReservationModule(long partnerId) {
        this.partnerId = partnerId;
    }
    
     // =========================================LOGIN SCREEN=====================================================
    public void partnerLoginPage() throws Exception {
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
        List<ws.partner.Airport> listOfAirports = retrieveAllAirports();
        printAirport(listOfAirports);
        System.out.print("Enter Departure Airport ID> ");
        Long depAirport = sc.nextLong();
        sc.nextLine();
        System.out.print("Enter Destination Airport ID> ");
        Long destAirport = sc.nextLong();
        sc.nextLine();
        System.out.print("Enter Departure Date (in the format YYYY-MM-DD)> ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date departureDate = dateFormat.parse(sc.nextLine());

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
    
    public void printAirport(List<ws.partner.Airport> listOfAirports) {
        System.out.println("ID | CODE | AIRPORT NAME");
        for (ws.partner.Airport a : listOfAirports) {
            String aCode = getAirportCodeWithAirportId(a.getAirportId());
            //a.getCode() not working???
            System.out.printf("%2s | %4s | %s", a.getAirportId(), aCode, a.getName());
            System.out.println("");
        }
    }
    
    public BigDecimal searchConnectingFlight(int flightType, Scanner sc, long depAirport, long destAirport, Date departureDate, int numOfSeats, BigDecimal fare, boolean connectedFlight) throws Exception {
        
        List<Long> listOfHubsId = new ArrayList<>();
        if (flightType == 2) {
            listOfHubsId = getListOfHubsIdConnecting(destAirport);
        } else if (flightType == 3) {
            listOfHubsId = getListOfHubsId();
        }
        
        
        if (flightType == 2) {
            System.out.print("\n*** FIRST, PICK FLIGHT GOING TO HUB ***");
        } else {
            System.out.print("\n*** PICK FIRST FLIGHT ***");
        }
        int flightnum = 1;
        List<ws.partner.FlightSchedule> listOfFlightScheduleToHubSameDay = retrieveFlightSchedulePlanWithSameTimingConnecting(depAirport, listOfHubsId, departureDate);
        System.out.println("");
        System.out.println("FLIGHT SCHEDULE ID | ORIGIN AIRPORT CODE | DESTINATION AIRPORT CODE | FLIGHT DEPARTURE DATE TIME   | FLIGHT ESTIMATED ARRIVAL DATETIME | FLIGHT DURATION ");
        System.out.println(String.format("====================================================================%s FLIGHT ON THE SAME DAY======================================================================", listOfFlightScheduleToHubSameDay.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightScheduleToHubSameDay, flightnum);
        
        //4. get list of flight schedule that is 3 days before
        List<ws.partner.FlightSchedule> listOfFlightScheduleHub3daysBefore = retrieveFlightSchedulePlanWith3DaysBeforeConnecting(depAirport, listOfHubsId, departureDate);
        System.out.println(String.format("=====================================================================%s FLIGHT 3 DAYS BEFORE=======================================================================", listOfFlightScheduleHub3daysBefore.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightScheduleHub3daysBefore, flightnum);
        
        
        //5. get list of flight schedule that is 3 days After
        List<ws.partner.FlightSchedule> listOfFlightScheduleHub3daysAfter = retrieveFlightSchedulePlanWith3DaysAfterConnecting(depAirport, listOfHubsId, departureDate);
        System.out.println(String.format("=====================================================================%s FLIGHT 3 DAYS AFTER=======================================================================", listOfFlightScheduleHub3daysAfter.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightScheduleHub3daysAfter, flightnum);
        
        int schedId = -2;
        while(schedId != 0 && schedId != -1) {
            System.out.println("Enter 0 to Reserve Flight");
            System.out.println("Enter -1 to Go back Flight");
            System.out.print("\nEnter Schedule ID to see more details > ");
            schedId = sc.nextInt();
            sc.nextLine();
            if (schedId != 0 && schedId != -1) {
                checkFlightDetails(sc, schedId, numOfSeats);
            }
            if (schedId == -1) {
                partnerLoginPage();
            }
        }
        
        sc.nextLine();
        System.out.print("Enter Flight Schedule ID to reserve> ");
        int confirmId = sc.nextInt();
        sc.nextLine();
        System.out.println("*** YOU HAVE SELECTED " + confirmId + " ***");
        System.out.print("Press Y to confirm N to restart> ");
        String next = sc.nextLine().trim();
        if (next.equalsIgnoreCase("N")) {
            partnerLoginPage();
        } else {
            fare = fare.add(reserveFlight(confirmId, sc, numOfSeats, false, fare));
        }
        
        long flightSchedId = confirmId;
        Boolean isDirectFlight = checkIfFlightSchedIdIsDirect(flightSchedId, destAirport);
        if (!isDirectFlight) {
            Date dateOfFlightPicked = toDate(retrieveDateOfFlightPicked(flightSchedId));

            long pickedAirportId = getAirportIdWithFlightScheduleId(flightSchedId);

            flightnum = 1;
            System.out.println("\n*** NEXT, PICK FLIGHT GOING OUT OF HUB ***");
            System.out.println("FLIGHT SCHEDULE ID | ORIGIN AIRPORT CODE | DESTINATION AIRPORT CODE | FLIGHT DEPARTURE DATE TIME   | FLIGHT ESTIMATED ARRIVAL DATETIME | FLIGHT DURATION ");
            List<FlightSchedule> listOfFlightSchedulesFromHubSameDay = retrieveFlightSchedulePlanAfterTimingReturnConnecting(pickedAirportId, destAirport, dateOfFlightPicked);
            System.out.println(String.format("====================================================================%s FLIGHT ON THE SAME DAY======================================================================", listOfFlightSchedulesFromHubSameDay.size()));
            flightnum = printStatementForFlightSchedule(listOfFlightSchedulesFromHubSameDay, flightnum);

            //5. get list of flight schedule that is 3 days After
            List<FlightSchedule> listOfFlightScheduleFromHub1dayAfter = retrieveFlightSchedulePlanWith1DayAfterReturnConnecting(pickedAirportId, destAirport, dateOfFlightPicked);
            System.out.println(String.format("====================================================================%s FLIGHT ONE DAY AFTER=======================================================================", listOfFlightScheduleFromHub1dayAfter.size()));
            flightnum = printStatementForFlightSchedule(listOfFlightScheduleFromHub1dayAfter, flightnum);

            schedId = -2;
            while(schedId != 0 && schedId != -1) {
                System.out.println("Enter 0 to Reserve Flight");
                System.out.println("Enter -1 to Go back Flight");
                System.out.print("\nEnter Schedule ID to see more details > ");
                schedId = sc.nextInt();
                sc.nextLine();
                if (schedId != 0 && schedId != -1) {
                    checkFlightDetails(sc, schedId, numOfSeats);
                }
                if (schedId == -1) {
                    partnerLoginPage();
                }
            }

            sc.nextLine();
            System.out.print("Enter Flight Schedule ID to reserve> ");
            confirmId = sc.nextInt();
            sc.nextLine();
            System.out.println("*** YOU HAVE SELECTED " + confirmId + " ***");
            System.out.print("Press Y to confirm N to restart> ");
            next = sc.nextLine().trim();
            if (next.equalsIgnoreCase("N")) {
                partnerLoginPage();
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
    
    public int printStatementForFlightSchedule(List<ws.partner.FlightSchedule> flightSchedules, int Number) {
        for(int i = 0; i < flightSchedules.size(); i ++) {
            ws.partner.FlightSchedule fs = flightSchedules.get(i);
            Airport og = getAirportOrigin(fs.getFlightScheduleId());
            Airport dest = getAirportDest(fs.getFlightScheduleId());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            double duration = fs.getEstimatedTime();
            int hours = (int) duration;
            double fractionalHours = duration - hours;
            int minutes = (int) (fractionalHours * 60);
            String formattedTime = String.format("%02d:%02d", hours, minutes);
            System.out.println(String.format("%18s | %19s | %24s | %25s | %33s | %15s ", fs.getFlightScheduleId(),
                                                                                         og.getAirportCode(),
                                                                                         dest.getAirportCode(),
                                                                                         dateFormat.format(fs.getDepartureDateTime().toGregorianCalendar().getTime()),
                                                                                         dateFormat.format(fs.getArrivalDateTime().toGregorianCalendar().getTime()),
                                                                                         formattedTime));
        }
        return Number;
    }
    
    public BigDecimal searchDirectFlight(Scanner sc, long depAirport, long destAirport, Date departureDate, int numOfSeats, BigDecimal fare, Boolean lastFlight) throws Exception {
        int flightnum = 1;
        List<ws.partner.FlightSchedule> listOfFlightSchedule = retrieveFlightSchedulePlanWithSameTimingPartner(departureDate, depAirport, destAirport);
        System.out.println(String.format("\n*** %s FLIGHT ON THE SAME DAY ***", listOfFlightSchedule.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightSchedule, flightnum);
        
        //4. get list of flight schedule that is 3 days before
        List<ws.partner.FlightSchedule> listOfFlightSchedule3daysBefore = retrieveFlightSchedulePlanWith3DaysBeforePartner(departureDate, depAirport, destAirport);
        System.out.println(String.format("\n*** %s FLIGHT 3 DAYS BEFORE ***", listOfFlightSchedule3daysBefore.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightSchedule3daysBefore, flightnum);
        
        //5. get list of flight schedule that is 3 days After
        List<ws.partner.FlightSchedule> listOfFlightSchedule3daysAfter = retrieveFlightSchedulePlanWith3DaysAfterPartner(departureDate, depAirport, destAirport);
        System.out.println(String.format("\n*** %s FLIGHT 3 DAYS AFTER ***", listOfFlightSchedule3daysAfter.size()));
        flightnum = printStatementForFlightSchedule(listOfFlightSchedule3daysAfter, flightnum);
        
        int schedId = -1;
        while(schedId != 0) {
            System.out.print("\nEnter Schedule ID to see more details (Enter 0 to Reserve Flight)> ");
            schedId = sc.nextInt();
            if (schedId != 0) {
                checkFlightDetails(sc, schedId, numOfSeats);
            }
        }
        
        sc.nextLine();
        System.out.print("Enter Flight Schedule ID to reserve> ");
        int confirmId = sc.nextInt();
        sc.nextLine();
        System.out.println("*** YOU HAVE SELECTED " + confirmId + " ***");
        System.out.print("Press Y to confirm N to restart> ");
        String next = sc.nextLine().trim();
        if (next.equalsIgnoreCase("N")) {
            partnerLoginPage();
        } else {
            if (lastFlight) {
                fare = fare.add(reserveFlight(confirmId, sc, numOfSeats, true, fare));
            } else {
                fare = fare.add(reserveFlight(confirmId, sc, numOfSeats, false, fare));
            }
            
        }
        return fare;
    }
    
    public void checkFlightDetails(Scanner sc, long scheduleId, int numOfSeats) throws FlightScheduleDoesNotExistException_Exception {
        //STOPPED HERE
        System.out.println(String.format("\n*** DETAILS FOR FLIGHT SCHEDULE %s ***", scheduleId));
        ws.partner.FlightSchedule fs = getFlightScheduleWithId(scheduleId);
        System.out.println("Filght Schedule ID: " + fs.getFlightScheduleId());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println("Filght Departure Date Time: " + dateFormat.format(fs.getDepartureDateTime().toGregorianCalendar().getTime()));
        System.out.println("Filght Estimated Arrival Date Time: " + dateFormat.format(fs.getArrivalDateTime().toGregorianCalendar().getTime()));
        double duration = fs.getEstimatedTime();       
        int hours = (int) duration;
        double fractionalHours = duration - hours;
        int minutes = (int) (fractionalHours * 60);
        String formattedTime = String.format("%02d:%02d", hours, minutes);        
        System.out.println("Filght Estimated Time: " + formattedTime);
        System.out.println("\n*** CABIN DETAILS ***");
        List<ws.partner.Cabin> cabins = getCabins(scheduleId);
        for (ws.partner.Cabin c : cabins) {
            System.out.println("Cabin Class : " + c.getCabinClassName());
            System.out.println("Total Seats: " + c.getTotalSeats());
            System.out.println("Remaining Seats: " + (c.getTotalSeats() - c.getReservedSeats()));
            long highestFareId = getHighestFareIdInCabin(c.getCabinId());
            BigDecimal lowestFare = getFareUsingId(highestFareId);
            System.out.println("Fare per Ticket: $" + lowestFare);
            System.out.println("Total Fare: $" + (lowestFare.multiply(BigDecimal.valueOf(numOfSeats))));
            System.out.println("");
        }
    }
    
    public BigDecimal reserveFlight(long flightScheduleId, Scanner sc, int numOfSeats, Boolean payment, BigDecimal existingFare) throws FlightScheduleDoesNotExistException, FlightScheduleDoesNotExistException_Exception {
        checkFlightDetails(sc, flightScheduleId, numOfSeats);
        System.out.print("Enter Cabin you want to Reserve> ");
        String cabin = sc.nextLine().trim();
        List<String> cabinSeatingPlanList = getCabinSeatsList(flightScheduleId, cabin);
        List<Integer> islesPlan = getIslesPlan(flightScheduleId, cabin);
        System.out.println("*** SEATING CONFIGURATION *** ");
        System.out.print("LETTER ");
        char seatNum = 'A';
        int count = 0;
        int c = 0;
        
        char[][] cabinSeatingPlan = convertListToCharArray(cabinSeatingPlanList);
        for (int i = 0; i < cabinSeatingPlan[0].length; i++) {
            System.out.print(seatNum);
            seatNum++;
            count++;
            if (count == islesPlan.get(c) && c != islesPlan.size() - 1) {
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
                if (count == islesPlan.get(c) && c != islesPlan.size() - 1) {
                    System.out.print("|");
                    c++;
                    count = 0;
                }
            }
            System.out.println("");
        }
        
        long highestFareId = getHighestFareUsingCabinName(cabin, flightScheduleId);
        //System.out.println("HIGHEST FARE ID= " + highestFareId);
        for (int i = 0; i < numOfSeats; i ++) {
            System.out.println(String.format("Enter No. %s Seat you wan to reserve: ", (i + 1)));
            System.out.print("Enter Row Number> ");
            int rowNum = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Seat Letter> ");
            String seat = sc.nextLine().trim().toUpperCase();
            char letter = seat.charAt(0);
            Boolean seatAvailable = checkSeatIfAvailable(flightScheduleId, cabin, rowNum, letter);
            if (seatAvailable) {
                bookSeat(flightScheduleId, cabin, rowNum, letter);
            } else {
                System.out.println("SEAT IS UNAVAILABLE, PLEASE TRY AGAIN");
                reserveFlight(flightScheduleId, sc, numOfSeats, payment, existingFare);
            }
            System.out.print("Enter First Name Of Customer> ");
            String firstName = sc.nextLine().trim();
            System.out.print("Enter Last Name Of Customer> ");
            String lastName = sc.nextLine().trim();
            System.out.print("Enter Passport Number Of Customer> ");
            String passport = sc.nextLine().trim();
             
            ws.partner.ReservationDetails rd = new ws.partner.ReservationDetails();
            rd.setFirstName(firstName);
            rd.setLastName(lastName);
            rd.setPassportNumber(passport);
            rd.setRowNum(rowNum);
            rd.setSeatLetter(letter);
            Long reservId = createReservationDetailsForPartner(rd, this.partnerId, flightScheduleId, highestFareId);
            System.out.println("Reservation Created!");
            System.out.println("Reservation ID = " + reservId);
            System.out.println("*** SEAT BOOKED ***");
            System.out.println("");
        }
        
        
//        BigDecimal lowestFare = flightScheduleSessionBeanRemote.getLowestFareUsingCabinName(cabin, flightScheduleId);
        //System.out.println("Price per Ticket: " + lowestFare);
        BigDecimal highestFare = getFareUsingId(highestFareId);
        BigDecimal fare = (highestFare.multiply(BigDecimal.valueOf(numOfSeats)));
        
        if (payment) {
            BigDecimal totalFare = fare.add(existingFare);
            System.out.println("Total Price: $" + totalFare);
            System.out.print("Enter Credit Card Details> ");
            String ccd = sc.nextLine().trim();
            linkCreditCard(this.partnerId, ccd);
            System.out.println("Credit Card Details Set!");
            System.out.println("*** FLIGHT RESERVATION DONE ***");
        }
        linkFlightSchedule(this.partnerId, flightScheduleId);
        return fare;
    }
    
    // =========================================VIEW MY FLIGHT RESERVATION=====================================================
    public void viewFlightReservation(Scanner sc) throws FlightScheduleDoesNotExistException_Exception {
        System.out.println("\n*** YOU HAVE SLECTED VIEW ALL FLIGHT RESERVATION ***\n");
        List<ws.partner.FlightSchedule> listOfFlightSchedules = getFlightSchedules(this.partnerId);
        for (ws.partner.FlightSchedule fs : listOfFlightSchedules) {
            System.out.println("Flight Schedule ID: " + fs.getFlightScheduleId());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            System.out.println("Flight Departure Date Time: " + dateFormat.format(fs.getDepartureDateTime().toGregorianCalendar().getTime()));
            ws.partner.FlightRoute fr = getFRUsingFSId(fs.getFlightScheduleId());
            
            double duration = fs.getEstimatedTime();
            int hours = (int) duration;
            double fractionalHours = duration - hours;
            int minutes = (int) (fractionalHours * 60);
            
            String formattedTime = String.format("%02d:%02d", hours, minutes);
            System.out.println("Flight Estimate Duration: " + formattedTime + "H");
            System.out.println("Flight Origin: " + fr.getOrigin().getName());
            System.out.println("Flight Destination: " + fr.getDestination().getName());
            System.out.println("");
        }
        
        System.out.print("Enter Flight Schedule ID for more Details> ");
        long flightScheduleId = sc.nextLong();
        viewFlightReservationDetails(sc, flightScheduleId);
    }
    
    public void viewFlightReservationDetails(Scanner sc, long flightScheduleId) throws FlightScheduleDoesNotExistException_Exception {
        System.out.println("\n*** VIEW MORE FLIGHT RESERVATION DETAILS ***\n");
        List<ws.partner.ReservationDetails> listOfReservationDetails = getReservationDetails(flightScheduleId, this.partnerId);
        listOfReservationDetails.size();
        BigDecimal fare = BigDecimal.ZERO;
        for (ws.partner.ReservationDetails rd : listOfReservationDetails) {
            //System.out.println("Cabin: " + rd.getFare().getCabin().getCabinClassName());
            System.out.println("First Name: " + rd.getFirstName());
            System.out.println("Last Name: " + rd.getLastName());
            System.out.println("Seat: " + rd.getRowNum() + rd.getSeatLetter());
            //System.out.println("Fare: $" + rd.getFare().getFareAmount());
            fare = rd.getFare().getFareAmount();
            System.out.println("");
        }
        System.out.println("Total Amount Paid: $" + fare.multiply(BigDecimal.valueOf(listOfReservationDetails.size())));
        System.out.println();
    }
    
    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) 
    { 
        GregorianCalendar gCalendar = new GregorianCalendar(); 
        gCalendar.setTime(date); 
        XMLGregorianCalendar xmlCalendar = null; 
        try { 
            xmlCalendar = DatatypeFactory.newInstance() .newXMLGregorianCalendar(gCalendar); 
        } 
        catch (DatatypeConfigurationException ex) { 
            System.out.println(ex); 
        } 
        return xmlCalendar; 
    } 
  
    public static Date toDate(XMLGregorianCalendar calendar) 
    { 
        if (calendar == null) { 
            return null; 
        } 
        return calendar.toGregorianCalendar().getTime(); 
    } 
    
    public static char[][] convertListToCharArray(List<String> seatList) {
        if (seatList == null || seatList.isEmpty() || seatList.get(0).isEmpty()) {
            return new char[0][0]; 
        }

        int numCols = Integer.parseInt(seatList.get(0));
        seatList.remove(0);
        int numRows = seatList.size() / numCols;
        char[][] charArray = new char[numRows][numCols];

        int count  = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                charArray[i][j] = seatList.get(count).charAt(0);
                count++;
            }
        }

        return charArray;
    }

    
    private static Long getPartnerId(java.lang.String email) throws InvalidLoginCredentialException_Exception {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getPartnerId(email);
    }
    
    private static List<ws.partner.Airport> retrieveAllAirports() {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.retrieveAllAirports();
    }
    
    private static ws.partner.FlightSchedule getFlightScheduleWithId(java.lang.Long id) throws FlightScheduleDoesNotExistException_Exception {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getFlightScheduleWithId(id);
    }
    
    private static List<ws.partner.FlightSchedule> retrieveFlightSchedulePlanWithSameTimingPartner(Date departureDate, java.lang.Long depAirport, java.lang.Long destAirport) throws DatatypeConfigurationException {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(departureDate);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        return port.retrieveFlightSchedulePlanWithSameTimingPartner(xmlDate, depAirport, destAirport);
    }
    
    private static List<ws.partner.FlightSchedule> retrieveFlightSchedulePlanWith3DaysBeforePartner(Date departureDate, java.lang.Long depAirport, java.lang.Long destAirport) throws DatatypeConfigurationException {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(departureDate);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        return port.retrieveFlightSchedulePlanWith3DaysBeforePartner(xmlDate, depAirport, destAirport);
    }
    
    private static List<ws.partner.FlightSchedule> retrieveFlightSchedulePlanWith3DaysAfterPartner(Date departureDate, java.lang.Long depAirport, java.lang.Long destAirport) throws DatatypeConfigurationException {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(departureDate);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        return port.retrieveFlightSchedulePlanWith3DaysAfterPartner(xmlDate, depAirport, destAirport);
    }
    
    private static List<ws.partner.FlightSchedule> retrieveFlightSchedulePlanWithSameTimingConnecting(java.lang.Long depAirport, List<java.lang.Long> listOfHubIds, Date departureDate) throws DatatypeConfigurationException {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(departureDate);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        return port.retrieveFlightSchedulePlanWithSameTimingConnecting(depAirport, listOfHubIds, xmlDate);
    }
    
    private static List<ws.partner.FlightSchedule> retrieveFlightSchedulePlanWith3DaysBeforeConnecting(java.lang.Long depAirport, List<java.lang.Long> listOfHubIds, Date departureDate) throws DatatypeConfigurationException {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(departureDate);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        return port.retrieveFlightSchedulePlanWith3DaysBeforeConnecting(depAirport, listOfHubIds, xmlDate);
    }
    
    private static List<ws.partner.FlightSchedule> retrieveFlightSchedulePlanWith3DaysAfterConnecting(java.lang.Long depAirport, List<java.lang.Long> listOfHubIds, Date departureDate) throws DatatypeConfigurationException {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(departureDate);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        return port.retrieveFlightSchedulePlanWith3DaysAfterConnecting(depAirport, listOfHubIds, xmlDate);
    }
    
    private static XMLGregorianCalendar retrieveDateOfFlightPicked(java.lang.Long id) throws FlightDoesNotExistException_Exception {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.retrieveDateOfFlightPicked(id);
    }
    
    private static List<ws.partner.Cabin> getCabins(java.lang.Long id) throws  FlightScheduleDoesNotExistException_Exception {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getCabins(id);
    }
    
    private static List<String> getCabinSeatsList(java.lang.Long id, java.lang.String cabName) throws FlightScheduleDoesNotExistException_Exception {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getCabinSeatsList(id, cabName);
    }

    private static List<Integer> getIslesPlan(java.lang.Long id, java.lang.String cabName) throws FlightScheduleDoesNotExistException_Exception {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getIslesPlan(id, cabName);
    }
    
    private static Long bookSeat(java.lang.Long id, java.lang.String cabName, java.lang.Integer seat, java.lang.Character letter) throws FlightScheduleDoesNotExistException_Exception {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.bookSeat(id, cabName, seat, letter);
    }
    
    private static Long getHighestFareUsingCabinName(java.lang.String cabName, java.lang.Long id) throws FlightScheduleDoesNotExistException_Exception {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getHighestFareUsingCabinName(cabName, id);
    }
    
    private static Boolean checkSeatIfAvailable(java.lang.Long flightSchedId, java.lang.String cabinName, java.lang.Integer rowNum, java.lang.Character seat) {    
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.checkSeatIfAvailable(flightSchedId, cabinName, rowNum, seat);
    }
    
    private static List<ws.partner.ReservationDetails> getReservationDetails(long flightScheduleId, long partnerId) throws  FlightScheduleDoesNotExistException_Exception {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getReservationDetailsPartner(flightScheduleId, partnerId);
    }
    
    private static Long getHighestFareIdInCabin(java.lang.Long cabinId) {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getHighestFareIdInCabin(cabinId);
    }
    
    private static BigDecimal getFareUsingId(java.lang.Long fareId) {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getFareUsingId(fareId);
    }
    
    private static Long createReservationDetailsForPartner(ws.partner.ReservationDetails reservationDetails,
                                                           java.lang.Long partnerId,
                                                           java.lang.Long flightScheduleId,
                                                           java.lang.Long highestFareId) {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.createReservationDetailsForPartner(reservationDetails, partnerId, flightScheduleId, highestFareId);
    }
    
    private static Long linkCreditCard(java.lang.Long partnerId, java.lang.String ccd) {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.linkCreditCard(partnerId, ccd);
    }
    
    private static Long linkFlightSchedule(java.lang.Long partnerId, java.lang.Long fsId) {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.linkFlightSchedule(partnerId, fsId);
    }
    
    private static List<ws.partner.FlightSchedule> getFlightSchedules(java.lang.Long partnerId) {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getFlightSchedules(partnerId);
    }
    
    private static List<Long> getListOfHubsId() {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getListOfHubsId();
    }
    
    private static Long getAirportIdWithFlightScheduleId(java.lang.Long flightSchedId) {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getAirportIdWithFlightScheduleId(flightSchedId);
    }
    
    private static List<ws.partner.FlightSchedule> retrieveFlightSchedulePlanAfterTimingReturnConnecting(java.lang.Long pickedAirport, java.lang.Long destAirport, Date dateOfFlightPicked) throws DatatypeConfigurationException {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dateOfFlightPicked);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        return port.retrieveFlightSchedulePlanAfterTimingReturnConnecting(pickedAirport, destAirport, xmlDate);
    }
    
    private static List<ws.partner.FlightSchedule> retrieveFlightSchedulePlanWith1DayAfterReturnConnecting(java.lang.Long pickedAirport, java.lang.Long  destAirport, Date dateOfFlightPicked) throws DatatypeConfigurationException {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dateOfFlightPicked);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        return port.retrieveFlightSchedulePlanWith1DayAfterReturnConnecting(pickedAirport, destAirport, xmlDate);
    }   
    
    private static ws.partner.FlightRoute getFRUsingFSId(java.lang.Long flightSchedId) {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getFRUsingFSId(flightSchedId);
    }
 
    public static Boolean checkIfFlightSchedIdIsDirect(java.lang.Long flightSchedId, java.lang.Long destAirportId) {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.checkIfFlightSchedIdIsDirect(flightSchedId, destAirportId);
    }
    
    private static List<Long> getListOfHubsIdConnecting(java.lang.Long destAirportId) {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getListOfHubsIdConnecting();
    }
    
    private static Airport getAirportOrigin(java.lang.Long fsId) {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getAirportOrigin();
    }
    
    private static Airport getAirportDest(java.lang.Long fsId) {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getAirportDest();
    }
    
    private static String getAirportCodeWithAirportId(java.lang.Long airportId) {
        ws.partner.PartnerWebService_Service service = new ws.partner.PartnerWebService_Service();
        ws.partner.PartnerWebService port = service.getPartnerWebServicePort();
        return port.getAirportCodeWithAirportId();
    }

}

