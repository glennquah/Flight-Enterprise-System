/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightmanagementjpaclient;

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
import entity.Aircraft;
import entity.AircraftConfiguration;
import entity.Airport;
import entity.Cabin;
import entity.Fare;
import entity.Flight;
import entity.FlightRoute;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import entity.ReservationDetails;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import util.enumeration.EmployeeAccessRightEnum;
import util.enumeration.FlightRouteStatusEnum;
import util.enumeration.FlightStatusEnum;
import util.exception.AirportDoesNotExistException;
import util.exception.FareBasisCodeAlreadyExistException;
import util.exception.FlightDisabledException;
import util.exception.FlightDoesNotExistException;
import util.exception.FlightNumberAlreadyExistException;
import util.exception.FlightRouteAlreadyExistException;
import util.exception.FlightRouteDoesNotExistException;
import util.exception.FlightScheduleDoesNotExistException;

/**
 *
 * @author admin
 */
public class ManagementModule {

    private Long employeeId;
    private EmployeeSessionBeanRemote employeeSessionBean;
    private CustomerSessionBeanRemote customerSessionBean;
    private AircraftSessionBeanRemote aircraftSessionBeanRemote;
    private AircraftConfigurationSessionBeanRemote aircraftConfigurationSessionBeanRemote;
    private CabinCustomerSessionBeanRemote cabinCustomerSessionBeanRemote;
    private FlightRoutesSessionBeanRemote flightRoutesSessionBeanRemote;
    private AirportSessionBeanRemote airportSessionBeanRemote;
    private FlightSessionBeanRemote flightSessionBeanRemote;
    private FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote;
    private FlightSchedulePlanSessionBeanRemote flightSchedulePlanSessionBeanRemote;
    private FareSessionBeanRemote fareSessionBeanRemote;
    private ReservationDetailsSessionBeanRemote reservationDetailsSessionBeanRemote;

    public ManagementModule() {
    }

    public ManagementModule(Long employeeId,
            EmployeeSessionBeanRemote employeeSessionBean,
            CustomerSessionBeanRemote customerSessionBean,
            AircraftSessionBeanRemote aircraftSessionBeanRemote,
            AircraftConfigurationSessionBeanRemote aircraftConfigurationSessionBeanRemote,
            CabinCustomerSessionBeanRemote cabinCustomerSessionBeanRemote,
            FlightRoutesSessionBeanRemote flightRoutesSessionBeanRemote,
            AirportSessionBeanRemote airportSessionBeanRemote,
            FlightSessionBeanRemote flightSessionBeanRemote,
            FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote,
            FlightSchedulePlanSessionBeanRemote flightSchedulePlanSessionBeanRemote,
            FareSessionBeanRemote fareSessionBeanRemote,
            ReservationDetailsSessionBeanRemote reservationDetailsSessionBeanRemote) {
        this.employeeId = employeeId;
        this.employeeSessionBean = employeeSessionBean;
        this.customerSessionBean = customerSessionBean;
        this.aircraftSessionBeanRemote = aircraftSessionBeanRemote;
        this.aircraftConfigurationSessionBeanRemote = aircraftConfigurationSessionBeanRemote;
        this.cabinCustomerSessionBeanRemote = cabinCustomerSessionBeanRemote;
        this.flightRoutesSessionBeanRemote = flightRoutesSessionBeanRemote;
        this.airportSessionBeanRemote = airportSessionBeanRemote;
        this.flightSessionBeanRemote = flightSessionBeanRemote;
        this.flightScheduleSessionBeanRemote = flightScheduleSessionBeanRemote;
        this.flightSchedulePlanSessionBeanRemote = flightSchedulePlanSessionBeanRemote;
        this.fareSessionBeanRemote = fareSessionBeanRemote;
        this.reservationDetailsSessionBeanRemote = reservationDetailsSessionBeanRemote;
    }

    //=================================================ADMIN PAGE================================================================
    public void adminLoginPage(Long employeeId) throws Exception {
        EmployeeAccessRightEnum employeeRights = employeeSessionBean.retrieveRole(employeeId);
        Scanner sc = new Scanner(System.in);
        
        if (employeeRights.equals(EmployeeAccessRightEnum.FLEETMANAGER)) {
            fleetManagerOptions(sc);
        } else if (employeeRights.equals(EmployeeAccessRightEnum.ROUTEPLANNER)) {
            routePlannerOptions(sc);
        } else if (employeeRights.equals(EmployeeAccessRightEnum.SCHEDULEMANAGER)) {
            scheduleManagerOptions(sc);
        } else if (employeeRights.equals(EmployeeAccessRightEnum.SALESMANAGER)) {
            salesManagerOptions(sc);
        }
    }
    
    //=================================================FLEET MANAGER=================================================
    public void fleetManagerOptions(Scanner sc) throws Exception {
        System.out.println("*** YOU ARE LOGGED IN AS A FLEET MANAGER ***\n");
        Integer response;
        while (true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create Aircraft Configuration ");
            System.out.println("2: View All Aircraft Configuration");
            System.out.println("3: Logout\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            System.out.println("");
            if (response == 1) {
                createFlightConfiguration(sc);
            } else if (response == 2) {
                viewAllFlightConfiguration(sc);
            } else if (response == 3) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }

    //Create Flight Config
    public void createFlightConfiguration(Scanner sc) throws Exception {
        System.out.println("*** YOU HAVE PICKED CREATE AIRCRAFT CONFIGURATION ***");
        System.out.println("*** PICK AIRCRAFT TYPE ***\n");
        List<Aircraft> listOfAircrafts = aircraftSessionBeanRemote.retrieveAllAircrafts();
        
        System.out.println("AIRCRAFT NAME | NUMBER | SEATS ");
        for (int i = 0; i < listOfAircrafts.size(); i++) {
            System.out.println(String.format("%13s | %6s | %5s ", listOfAircrafts.get(i).getAircraftName(), i + 1, listOfAircrafts.get(i).getNumOfSeats()));
        }
        System.out.print("Enter Aircraft Number> ");
        Long aircraftNumLong = sc.nextLong();
        sc.nextLine();
        System.out.println("");
        
        System.out.print("Enter Aircraft Configuration Name> ");
        String AircraftConfigName = sc.nextLine().trim();
        Long aircraftConfigId = aircraftConfigurationSessionBeanRemote.createAircraftConfiguration(new AircraftConfiguration(AircraftConfigName), aircraftNumLong);

        System.out.print("Enter Number of Cabin Class (1 to 4)> ");
        int numCabinClass = sc.nextInt();
        sc.nextLine();
        if (numCabinClass < 1 || numCabinClass > 4) {
            System.out.println("ERROR Please select from 1 - 4");
            createFlightConfiguration(sc);
        }

        for (int i = 0; i < numCabinClass; i++) {
            System.out.println("\n*** TYPES OF CLASSES ***");
            System.out.println("First Class: F");
            System.out.println("Business Class: J");
            System.out.println("Premium Economy Class: W");
            System.out.println("Economy Class: Y");
            System.out.print(String.format("\nEnter Cabin Class> ", i + 1));
            String cabinName = sc.nextLine().trim();
            System.out.print(String.format("Enter Number of isles for No.%s Cabin (Max 2)> ", i + 1));
            int numOfIsles = sc.nextInt();
            sc.nextLine();
            System.out.print(String.format("Enter Number of Rows for No.%s Cabin> ", i + 1));
            int numOfRows = sc.nextInt();
            sc.nextLine();
            System.out.print(String.format("Enter Seating Configuraiton for No.%s Cabin (Format should be in 1 or 1-2 or 1-2-1)> ", i + 1));
            String seatingConfig = sc.nextLine().trim();
            String[] sconfig = seatingConfig.split("-");
            Integer[] sconfigInt = new Integer[numOfIsles + 1];
            for (int j = 0; j < sconfig.length; j++) {
                sconfigInt[j] = Integer.parseInt(sconfig[j]);
            }
            Cabin cab = new Cabin(cabinName, numOfIsles, numOfRows, sconfigInt);
            Long cabinId = cabinCustomerSessionBeanRemote.createCabin(cab, aircraftConfigId);
            System.out.println(String.format("\nNo.%s cabin created", i + 1));
        }
        System.out.println("New Flight Configuration Created!");
        System.out.println("Flight Configuration ID = " + aircraftConfigId);
        System.out.println("");
    }

    public void viewAllFlightConfiguration(Scanner sc) throws Exception {
        List<AircraftConfiguration> listOfac = aircraftConfigurationSessionBeanRemote.retrieveAllAircraftConfigurations();
        //List<Aircraft> listOfAircraft = aircraftSessionBeanRemote.retrieveAllAircrafts();
        System.out.println("*** YOU HAVE PICKED VIEW ALL AIRCRAFT CONFIGURATION ***\n");
        
        System.out.println("AIRCRAFT ID | AIRCRAFT TYPE | AIRCRAFT NAME ");
        for (int i = 0; i < listOfac.size(); i++) {
            System.out.println(String.format("%11s | %13s | %13s ", listOfac.get(i).getAircraftConfigurationId(), listOfac.get(i).getAircraft().getAircraftName(), listOfac.get(i).getAircraftConfigName()));
        }
        
        System.out.println("");
        System.out.print("Enter ID for more details> ");
//        String aircraftName = sc.nextLine().trim();
        Long aircraftNum = sc.nextLong();
        sc.nextLine();

        List<Cabin> listOfCabins = aircraftConfigurationSessionBeanRemote.retrieveCabinsWithId(aircraftNum);
        System.out.println("\n*** AIRCRAFT CONFIGURATION DETAILS ***");
        for (int i = 0; i < listOfCabins.size(); i++) {
            System.out.println(String.format("\nCabin Class No.%s Name: ", i + 1) + listOfCabins.get(i).getCabinClassName());
            System.out.println("Number of Isles: " + listOfCabins.get(i).getNumOfIsles());
            System.out.println("Number of Rows: " + listOfCabins.get(i).getNumOfRows());
            System.out.print("Seating Configuration: ");
            for (int j = 0; j < listOfCabins.get(i).getSeatingConfiguration().length; j++) {
                System.out.print(listOfCabins.get(i).getSeatingConfiguration()[j]);
                if (j != listOfCabins.get(i).getSeatingConfiguration().length - 1) {
                    System.out.print("-");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    //=================================================ROUTE PLANNER================================================================
    public void routePlannerOptions(Scanner sc) {
        System.out.println("*** YOU ARE LOGGED IN AS A ROUTE PLANNER ***\n");
        Integer response;
        while (true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create New Flight Route ");
            System.out.println("2: View All Flight Route");
            System.out.println("3: Delete Flight Route");
            System.out.println("4: Logout\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            System.out.println("");
            if (response == 1) {
                createFlightRoute(sc);
            } else if (response == 2) {
                viewFlightRoutes(sc);
            } else if (response == 3) {
                deleteFlightRoute(sc);
            } else if (response == 4) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }

    public void createFlightRoute(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED CREATE FLIGHT ROUTE ***\n");
        Integer response;
        while (true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create new Flight Route ");
            System.out.println("2: Create new Flight Route with Return Route");
            System.out.println("3: Back\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if (response == 1) {
                System.out.println("*** PLEASE ENTER THE ORIGIN AND DESTINATION AIRPORT NAME ***\n");
                List<Airport> listOfAirports = airportSessionBeanRemote.retrieveAllAirports();
                
                System.out.println("AIRPORT ID | AIRPORT NAME | AIRPORT CODE ");
                for (int i = 0; i < listOfAirports.size(); i++) {
                    System.out.println(String.format("%10s | %12s | %12s ", listOfAirports.get(i).getAirportId(), listOfAirports.get(i).getName(), listOfAirports.get(i).getAirportCode()));
                }
                System.out.println("");
                
                System.out.print("Enter Origin Airport ID> ");
                Long originAirport = sc.nextLong();
                System.out.print("Enter Destination Airport ID> ");
                Long destAirport = sc.nextLong();
                try {
                    Long flightRouteId = flightRoutesSessionBeanRemote.createNewFlightRoute(originAirport, destAirport);
                    System.out.println("Successfully created Flight Route with ID: " + flightRouteId + "!");
                    System.out.println("");
                } catch (AirportDoesNotExistException ex) {
                    System.out.println(ex.getMessage());
                } catch (FlightRouteAlreadyExistException ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (response == 2) {
                System.out.println("*** PLEASE ENTER THE ORIGIN AND DESTINATION AIRPORT NAME ***\n");
                List<Airport> listOfAirports = airportSessionBeanRemote.retrieveAllAirports();
                
                System.out.println("AIRPORT ID | AIRPORT NAME ");
                for (int i = 0; i < listOfAirports.size(); i++) {
                    System.out.println(String.format("%10s | %12s ", listOfAirports.get(i).getAirportId(), listOfAirports.get(i).getName()));
                }
                System.out.println("");
                
                System.out.print("Enter Origin Airport ID> ");
                Long originAirport = sc.nextLong();
                System.out.print("Enter Destination Airport ID> ");
                Long destAirport = sc.nextLong();

                try {
                    Long flightRouteId = flightRoutesSessionBeanRemote.createNewFlightRouteWithReturn(originAirport, destAirport);
                    System.out.println("Successfully created Flight Route with ID: " + flightRouteId + "!");
                } catch (AirportDoesNotExistException ex) {
                    System.out.println(ex.getMessage());
                } catch (FlightRouteAlreadyExistException ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (response == 3) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
     
    public void viewFlightRoutes(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED VIEW FLIGHT ROUTE ***\n");
        List<FlightRoute> listOfFlightRoutes = flightRoutesSessionBeanRemote.retrieveAllFlightRoutes();
        List<FlightRoute> printedFlightRoutes = new ArrayList<FlightRoute>();
        
        if (listOfFlightRoutes.size() == 0) {
            System.out.println("There are no existing Flight Routes!");
        } else {
            
            System.out.println("FLIGHT ROUTE ID | ORIGIN AIRPORT | DESTINATION AIRPORT ");
            for (FlightRoute f:listOfFlightRoutes) {
                if (!printedFlightRoutes.contains(f) && f.getFlightRouteStatus() == FlightRouteStatusEnum.ACTIVE) {
                    System.out.println(String.format("%15s | %14s | %19s ", f.getFlightRouteId(), f.getOrigin().getName(), f.getDestination().getName()));
                    printedFlightRoutes.add(f);

                    if (f.getComplementaryRoute()) {
                        for (int i = 0; i < listOfFlightRoutes.size(); i++) {
                            if (listOfFlightRoutes.get(i).getOrigin() == f.getDestination() && listOfFlightRoutes.get(i).getDestination() == f.getOrigin() && listOfFlightRoutes.get(i).getFlightRouteStatus() == FlightRouteStatusEnum.ACTIVE) {
                                System.out.println(String.format("%15s | %14s | %19s ", listOfFlightRoutes.get(i).getFlightRouteId(), listOfFlightRoutes.get(i).getOrigin().getName(), listOfFlightRoutes.get(i).getDestination().getName()));
                                printedFlightRoutes.add(listOfFlightRoutes.get(i));
                                break;
                            } 
                        }

                    } 
                }
            }
            System.out.println("");
        }
        
    }
     
    public void deleteFlightRoute(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED DELETE FLIGHT ROUTE ***\n");
        Integer response;
        while (true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Delete Flight Route ");
            System.out.println("2: Back\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if (response == 1) {
                System.out.println("*** PLEASE ENTER THE ORIGIN AND DESTINATION AIRPORT NAME ***\n");
                List<FlightRoute> listOfFlightRoutes = flightRoutesSessionBeanRemote.retrieveAllFlightRoutes();
                List<FlightRoute> printedFlightRoutes = new ArrayList<FlightRoute>();

                System.out.println("FLIGHT ROUTE ID | ORIGIN AIRPORT | DESTINATION AIRPORT ");
                for (FlightRoute f:listOfFlightRoutes) {
                    if (!printedFlightRoutes.contains(f) && f.getFlightRouteStatus() == FlightRouteStatusEnum.ACTIVE) {
                        System.out.println(String.format("%15s | %14s | %19s ", f.getFlightRouteId(), f.getOrigin().getName(), f.getDestination().getName()));
                        printedFlightRoutes.add(f);

                        if (f.getComplementaryRoute()) {
                            for (int i = 0; i < listOfFlightRoutes.size(); i++) {
                                if (listOfFlightRoutes.get(i).getOrigin() == f.getDestination() && listOfFlightRoutes.get(i).getDestination() == f.getOrigin() && listOfFlightRoutes.get(i).getFlightRouteStatus() == FlightRouteStatusEnum.ACTIVE) {
                                    System.out.println(String.format("%15s | %14s | %19s ", listOfFlightRoutes.get(i).getFlightRouteId(), listOfFlightRoutes.get(i).getOrigin().getName(), listOfFlightRoutes.get(i).getDestination().getName()));
                                    printedFlightRoutes.add(listOfFlightRoutes.get(i));
                                    break;
                                } 
                            }

                        } 
                    }
                }
                
                System.out.println("");
                
                System.out.print("Enter Flight Route ID> ");
                Long flightRouteId = sc.nextLong();
                try {
                    flightRoutesSessionBeanRemote.deleteFlightRoute(flightRouteId);
                } catch (FlightRouteDoesNotExistException ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (response == 2) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }

    //=================================================SCHEDULE MANAGER================================================================
    public void scheduleManagerOptions(Scanner sc) throws Exception {
        System.out.println("*** YOU ARE LOGGED IN AS A SCHEDULE MANAGER ***\n");
        Integer response;
        while (true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create Flight ");
            System.out.println("2: View All Flight");
            System.out.println("3: Update Flight");
            System.out.println("4: Delete Flight");
            System.out.println("5: Create New Flight Schedule Plan ");
            System.out.println("6: View Flight Schedule Plans");
            System.out.println("7: Update Flight Schedule Plan");
            System.out.println("8: Delete Flight Schedule Plan");
            System.out.println("9: Logout\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            System.out.println("");
            if (response == 1) {
                createFlight(sc);
            } else if (response == 2) {
                viewAllFlights(sc);
            } else if (response == 3) {
                updateFlight(sc);
            } else if (response == 4) {
                deleteFlights(sc);
            } else if (response == 5) {
                createFlightSchedulePlan(sc, 1);
            } else if (response == 6) {
                viewFlightSchedulePlans(sc);
            } else if (response == 7) {
                updateFlightSchedulePlan(sc);
            } else if (response == 8) {
                deleteFlightSchedulePlan(sc);
            } else if (response == 9) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }

    public void createFlight(Scanner sc) throws Exception {
        System.out.println("*** YOU HAVE PICKED CREATE FLIGHT ***");
        System.out.println("*** FILL UP FLIGHT DETAILS ***\n");
        System.out.println("*** SELECT AIRLINE ***");
        System.out.println("1. Merline Airlines : IATA\n");
        System.out.print("Enter Airline Number> ");
        int airlineNum = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Flight Number> ");
        Integer flightNum = sc.nextInt();
        sc.nextLine();
        System.out.println("");
        System.out.println("*** SELECT FLIGHT ROUTE ***\n");
        List<FlightRoute> listOfFlightRoutes = flightRoutesSessionBeanRemote.retrieveAllFlightRoutes();
        List<FlightRoute> printedFlightRoutes = new ArrayList<FlightRoute>();
        
        if (listOfFlightRoutes.size() == 0) {
            System.out.println("There are no existing Flight Routes!");
        } else {
            System.out.println("FLIGHT ROUTE ID | ORIGIN AIRPORT | DESTINATION AIRPORT ");
            for (FlightRoute f:listOfFlightRoutes) { 
                if (!printedFlightRoutes.contains(f) && f.getFlightRouteStatus() == FlightRouteStatusEnum.ACTIVE) {
                    System.out.println(String.format("%15s | %14s | %19s ", f.getFlightRouteId(), f.getOrigin().getName(), f.getDestination().getName()));
                    printedFlightRoutes.add(f);

                    if (f.getComplementaryRoute()) {
                        for (int i = 0; i < listOfFlightRoutes.size(); i++) {
                            if (listOfFlightRoutes.get(i).getOrigin() == f.getDestination() && listOfFlightRoutes.get(i).getDestination() == f.getOrigin() && listOfFlightRoutes.get(i).getFlightRouteStatus() == FlightRouteStatusEnum.ACTIVE) {
                                System.out.println(String.format("%15s | %14s | %19s ", listOfFlightRoutes.get(i).getFlightRouteId(), listOfFlightRoutes.get(i).getOrigin().getName(), listOfFlightRoutes.get(i).getDestination().getName()));
                                printedFlightRoutes.add(listOfFlightRoutes.get(i));
                                break;
                            } 
                        }

                    } 
                }
            } 
            System.out.println("");
        }
            
        
        System.out.print("Enter Flight Route ID> ");
        Long flightRouteId = sc.nextLong();
        sc.nextLine();
        
        FlightRoute chosenFlightRoute = flightRoutesSessionBeanRemote.getFlightRouteWithId(flightRouteId);
        
        if (chosenFlightRoute.getComplementaryRoute()) {
            System.out.print("Do you want to create a complementary return flight (Y/N)> ");
        }
        
        String haveReturn = sc.nextLine().toUpperCase().trim();
        Integer returnFlightNumber = 0;
        if (haveReturn.equals("Y")) {
            System.out.print("Enter Complementary Flight Number> ");
            returnFlightNumber = sc.nextInt();
        }
        
//        retrieve flight route and check for complementary then prompt user to input
//         flight to store complementary flight number now

        System.out.println("\n*** SELECT AIRCRAFT CONFIGURATION ***\n");
        List<AircraftConfiguration> aircraftConfigurations = aircraftConfigurationSessionBeanRemote.retrieveAllAircraftConfigurations();

        System.out.println("AIRCRAFT ID | AIRCRAFT TYPE | AIRCRAFT NAME ");
        for (int i = 0; i < aircraftConfigurations.size(); i++) {
           System.out.println(String.format("%11s | %13s | %13s ", aircraftConfigurations.get(i).getAircraftConfigurationId(), aircraftConfigurations.get(i).getAircraft().getAircraftName(), aircraftConfigurations.get(i).getAircraftConfigName()));
        }
        System.out.println("");
        
        System.out.print("Enter Flight Configuration Id> ");
        Long aircraftConfigId = sc.nextLong();

        sc.nextLine();
        
        Flight flight = new Flight(flightNum);
        
        Long flightId;
        try {
           if (haveReturn.equals("Y")) {
                Flight returnFlight = new Flight(returnFlightNumber);
                Long returnFlightRouteId = flightRoutesSessionBeanRemote.getReturnFlightRouteId(flightRouteId);
                flightId = flightSessionBeanRemote.createNewFlight(flight, flightRouteId, aircraftConfigId);
                Long returnFlightId = flightSessionBeanRemote.createNewFlight(returnFlight, returnFlightRouteId, aircraftConfigId);

                flightSessionBeanRemote.setReturnFlight(flightId, returnFlightId);
                flightSessionBeanRemote.setReturnFlight(returnFlightId, flightId);  
            } else {
                flightId = flightSessionBeanRemote.createNewFlight(flight, flightRouteId, aircraftConfigId);
            }
           
            System.out.println("");
            System.out.println("Flight Successfully Created!");
            System.out.println("Flight Id = " + flightId);
            System.out.println("");
        } catch (FlightNumberAlreadyExistException e) {
            System.out.println("This Flight Number already exist!");
            System.out.println("");
        }
        
        
    }

    public void deleteFlights(Scanner sc) throws Exception {
        System.out.println("*** YOU HAVE PICKED DELETE FLIGHTS ***\n");
        //print all of the flights
        List<Flight> listOfFlights = flightSessionBeanRemote.retrieveAllFlights();
        
        System.out.println("FLIGHT ID | AIRCRAFT CONFIGURATION NAME | AIRCRAFT ROUTE ID ");
        for (Flight f: listOfFlights) { 
            if (f.getFlightStatus() == FlightStatusEnum.ACTIVE) {
                System.out.println(String.format("%9s | %27s | %17s ", f.getFlightId(), f.getAircraftConfig().getAircraftConfigName(), f.getFlightRoute().getFlightRouteId()));
            }
        }
        System.out.println("");
        
        System.out.print("Enter ID of Flight to be deleted> ");
        Long flightIdNum = sc.nextLong();
        sc.nextLine();

        Long delFlightId = flightSessionBeanRemote.removeFlight(flightIdNum);
        System.out.println(String.format("Flight ID = %s has been Removed", delFlightId));
        System.out.println("");
    }

    public void viewAllFlights(Scanner sc) throws Exception {
        List<Flight> listOfFlights = flightSessionBeanRemote.retrieveAllFlights();
        System.out.println("*** YOU HAVE PICKED VIEW ALL FLIGHTS ***\n");
        
        if (listOfFlights.size() == 0) {
            System.out.println("There are no existing Flights!");
        } else {
            System.out.println("FLIGHT ID | AIRCRAFT CONFIGURATION NAME | FLIGHT NUMBER ");
            for (Flight f: listOfFlights) { 
                if (f.getFlightStatus() == FlightStatusEnum.ACTIVE) {
                    System.out.println(String.format("%9s | %27s | %13s ", f.getFlightId(), f.getAircraftConfig().getAircraftConfigName(), "ML" + f.getFlightNumber()));
                }
            }
            System.out.println("");

            System.out.print("Enter ID for more details> ");
            Long flightIdNum = sc.nextLong();
            sc.nextLine();

            Flight flight = flightSessionBeanRemote.getFlightWithId(flightIdNum);
            List<Cabin> listOfCabins = aircraftConfigurationSessionBeanRemote.retrieveCabinsWithId(flight.getAircraftConfig().getAircraftConfigurationId());
            System.out.println("\n*** FLIGHT DETAILS ***");
            System.out.println("FLIGHT NUMBER | TOTAL SEATS | AVAILABLE SEATS | RESERVED SEATS ");
            
            Integer totalSeats = flightSessionBeanRemote.getTotalSeats(flightIdNum);
            Integer reservedSeats = flightSessionBeanRemote.getReservedSeats(flightIdNum);
             
            System.out.println(String.format("%13s | %11s | %15s | %14s ", "ML" + flight.getFlightNumber(), totalSeats, (totalSeats - reservedSeats), reservedSeats));

            System.out.println("\n*** FLIGHT ROUTE DETAILS ***");
            System.out.println("FLIGHT ROUTE ID | ORIGIN AIRPORT | DESTINATION AIRPORT ");
            FlightRoute f = flightRoutesSessionBeanRemote.getFlightRouteWithId(flight.getFlightRoute().getFlightRouteId());
            System.out.println(String.format("%15s | %14s | %19s ", f.getFlightRouteId(), f.getOrigin().getName(), f.getDestination().getName()));

            if (f.getComplementaryRoute()) {
                System.out.println(String.format("%15s | %14s | %19s ", "COMPLEMENTARY", f.getDestination().getName(), f.getOrigin().getName()));
            }

            System.out.println("\n*** CABIN DETAILS ***");
            for (int i = 0; i < listOfCabins.size(); i++) {
                System.out.println(String.format("Cabin Class No.%s Name: ", i + 1) + listOfCabins.get(i).getCabinClassName());
                System.out.println("Number of Isles: " + listOfCabins.get(i).getNumOfIsles());
                System.out.println("Number of Rows: " + listOfCabins.get(i).getNumOfRows());
                System.out.print("Seating Configuration: ");
                for (int j = 0; j < listOfCabins.get(i).getSeatingConfiguration().length; j++) {
                    System.out.print(listOfCabins.get(i).getSeatingConfiguration()[j]);
                    if (j != listOfCabins.get(i).getSeatingConfiguration().length - 1) {
                        System.out.print("-");
                    }
                }
                System.out.println("\nCabin Total Seats: " + listOfCabins.get(i).getTotalSeats());
                System.out.println("Cabin Available Seats: " + listOfCabins.get(i).getAvailableSeats());
                System.out.println("Cabin Reserved Seats: " + listOfCabins.get(i).getReservedSeats());
                System.out.println("");
            }
        }
           
    }
    
    public void updateFlight(Scanner sc) throws Exception {
        System.out.println("*** YOU HAVE PICKED UPDATE FLIGHT ***\n");
        List<Flight> listOfFlights = flightSessionBeanRemote.retrieveAllFlights();
        
        if (listOfFlights.size() > 0) {
            System.out.println("FLIGHT ID | AIRCRAFT CONFIGURATION NAME | ROUTE ID ");
            for (Flight f: listOfFlights) { 
                if (f.getFlightStatus() == FlightStatusEnum.ACTIVE) {
                    System.out.println(String.format("%9s | %27s | %8s ", f.getFlightId(), f.getAircraftConfig().getAircraftConfigName(), f.getFlightRoute().getFlightRouteId()));
                }
            }
            System.out.println("");
        
            System.out.print("Enter Flight ID to Update> ");
            Long flightIdNum = sc.nextLong();
            sc.nextLine();
            
            Flight flight = flightSessionBeanRemote.getFlightWithId(flightIdNum);

            if (flight.getFlightStatus() == FlightStatusEnum.ACTIVE) {
               System.out.println("*** UPDATE DETAILS FOR FLIGHT ***\n");
                System.out.print("Change Flight Number? (Y/N)> ");
                if (sc.nextLine().trim().equalsIgnoreCase("Y")) {
                    System.out.print("Input New Flight Number> ");
                    Integer newFlightNum = sc.nextInt();
                    sc.nextLine();
                    Long fNumId = flightSessionBeanRemote.changeFlightNumber(flightIdNum, newFlightNum);
                    System.out.println("*** FLIGHT NUMBER CHANGED! ***\n");
                }

                System.out.print("Change Flight Route? (Y/N)> ");
                if (sc.nextLine().trim().equalsIgnoreCase("Y")) {
                    System.out.print("Enter New Flight Route ID> ");
                    Long newFlightRouteId = sc.nextLong();
                    sc.nextLine(); 
                    Long fRId = flightSessionBeanRemote.changeFlightRoute(flightIdNum, newFlightRouteId, flight.getFlightRoute().getFlightRouteId());
                    System.out.println("*** FLIGHT ROUTE CHANGED! ***\n");
                }

                System.out.print("Change Flight Configuration? (Y/N)> ");
                if (sc.nextLine().trim().equalsIgnoreCase("Y")) {
                    System.out.print("Enter New Flight Configuration ID> ");
                    Long newFlightConfigId = sc.nextLong();
                    sc.nextLine();
                    
                    Long fcId = flightSessionBeanRemote.changeFlightConfig(flightIdNum, newFlightConfigId, flight.getAircraftConfig().getAircraftConfigurationId());
                    System.out.println("*** FLIGHT CONFIGURATION CHANGED! ***\n");
                }

                System.out.println("UPDATING DONE!\n"); 
            } else {
                throw new FlightDisabledException("This Flight Route is Disabled!");
            }
        } else {
            System.out.println("There are no existing Flights!");
        }
        
        
    }
     
    public void createFlightSchedulePlan(Scanner sc, int option) throws Exception {
        if (option == 1) {
            System.out.println("*** YOU HAVE PICKED CREATE FLIGHT SCHEDULE PLAN ***\n");
        } else {
            System.out.println("*** YOU HAVE PICKED UPDATE FLIGHT SCHEDULE PLAN ***\n");
        }
        
        Integer response;
        while(true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Single Flight Schedule ");
            System.out.println("2: Multiple Flight Schedules ");
            System.out.println("3: Recurrent Flight Schedules Every N Days ");
            System.out.println("4: Recurrent Flight Schedules Every Week ");
            
            if (option == 1) {
                System.out.println("5: Back\n");
            }
            
            System.out.print("> ");
            response = sc.nextInt();
  
            if(response == 1) {
                System.out.println("*** PLEASE ENTER THE FLIGHT NUMBER, DEPARTURE DETAILS AND FLIGHT DURATION ***\n");
                System.out.print("Enter Flight Number> ");
                Integer flightNumber = sc.nextInt();
                
                try {
                    Boolean complementaryReturn = flightSessionBeanRemote.haveComplementaryFlight(flightNumber);
                    Integer returnFlightNumber = 0;
                    if (complementaryReturn) {
                        returnFlightNumber = flightSessionBeanRemote.returnFlightNumber(flightNumber);
                    }
                
                    System.out.print("Enter DEPARTURE DATE AND TIME (yyyy-MM-dd HH:mm)> ");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    sc.nextLine();
                    Date departureDateTime = dateFormat.parse(sc.nextLine());
                    System.out.print("Enter DURATION in Hours> ");
                    double duration = sc.nextDouble();
                    
                    System.out.print("Enter the LAYOVER DURATION in Hours> ");
                    double layover = sc.nextDouble();
                    
                    String haveReturn = "";
                    if (complementaryReturn) {
                        System.out.print("Is there a return flight(Y/N)> ");
                        sc.nextLine();
                        haveReturn = sc.nextLine().trim().toUpperCase();
                    }
                    
                    flightScheduleSessionBeanRemote.checkForConflictingFlights(flightNumber, departureDateTime, duration, layover);
                    FlightSchedule flightSchedule = flightScheduleSessionBeanRemote.createNewFlightSchedule(flightNumber, new FlightSchedule(departureDateTime, duration, layover));
                    
                    FlightSchedulePlan flightSchedulePlan = new FlightSchedulePlan(flightNumber);
                    List<Cabin> cabins = flightSchedule.getListOfCabins();
                    cabins.size();
                    
                    Long flightSchedulePlanId = flightSchedulePlanSessionBeanRemote.createSingleFlightSchedulePlan(flightSchedulePlan, flightSchedule.getFlightScheduleId());
                    Long flightSchedulePlanReturnId = 0l;
                    
                    if (haveReturn.equals("Y")) {
                        Instant instant = flightSchedule.getArrivalDateTime().toInstant();
                        Date returnDepartureDateTime =  Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
                        flightScheduleSessionBeanRemote.checkForConflictingFlights(returnFlightNumber, returnDepartureDateTime, duration, layover);
                        FlightSchedule flightScheduleReturn = flightScheduleSessionBeanRemote.createNewFlightSchedule(returnFlightNumber, new FlightSchedule(returnDepartureDateTime, duration, layover));
                        flightSchedulePlanReturnId = flightSchedulePlanSessionBeanRemote.createSingleFlightSchedulePlan(flightSchedulePlan, flightScheduleReturn.getFlightScheduleId());
                    } 
                    
                    for (int i = 0; i < cabins.size(); i++) {
                        System.out.print(String.format("Enter the Number of Fares for Cabin Class %s> ", cabins.get(i).getCabinClassName()));
                        
                        int numFares = sc.nextInt();
                        ArrayList<String> fareBasisCodes = new ArrayList<>();
                        ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                        
                        sc.nextLine();
                        for (int j = 0; j < numFares; j++) {
                            System.out.print("Enter the Fare Basis Code> ");
                            String fareBasisCode = sc.nextLine();
                            
                            if (fareBasisCodes.contains(fareBasisCode)) {
                                throw new FareBasisCodeAlreadyExistException("There should not be any duplicate fare basis code!");
                            } else {
                                fareBasisCodes.add(fareBasisCode);
                            }

                            System.out.print("Enter the Fare Amount> ");
                            fareAmounts.add(new BigDecimal(sc.nextLine()));
                        }
                        
                        flightSchedulePlanSessionBeanRemote.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                        if (haveReturn.equals("Y")) {
                            flightSchedulePlanSessionBeanRemote.createFare(flightSchedulePlanReturnId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                        }
                    }  
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date and time format. Please use yyyy-MM-dd HH:mm.");
                    System.out.println("");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format. Please enter numeric values for hours and minutes.");
                    System.out.println("");
                }  catch (FlightDoesNotExistException e) {
                    System.out.println("This Flight Number does not exist!");
                    System.out.println("");
                }
            } else if (response == 2) {
                System.out.println("*** PLEASE ENTER THE NUMBER OF SCHEDULES YOU WISH TO CREATE ***\n");
                System.out.print("Enter the Number Of Schedules> ");
                int numSchedules = sc.nextInt();
                List<Long> flightScheduleIds = new ArrayList<>();
                List<Long> returnFlightScheduleIds = new ArrayList<>();
                List<Date> departureDates = new ArrayList<>();
                List<Double> durations = new ArrayList<>();
                List<Double> layovers = new ArrayList<>();
                List<String> returnFlights = new ArrayList<>();
                
                System.out.println("");
                System.out.println("*** PLEASE ENTER THE FLIGHT NUMBER, DEPARTURE DETAILS AND FLIGHT DURATION ***\n");
                System.out.print("Enter Flight Number> ");
                Integer flightNumber = sc.nextInt();
                try {
                    Boolean complementaryReturn = flightSessionBeanRemote.haveComplementaryFlight(flightNumber);

                    Integer returnFlightNumber = 0;
                    if (complementaryReturn) {
                        returnFlightNumber = flightSessionBeanRemote.returnFlightNumber(flightNumber);
                    }
                
                    sc.nextLine(); 
                    
                    for (int i = 0 ; i < numSchedules; i++) {   
                        System.out.println(String.format("ENTER DETAILS FOR FLIGHT SCHEDULE %s ", i + 1));
                        System.out.print("Enter DEPARTURE DATE AND TIME (yyyy-MM-dd HH:mm)> ");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date departureDateTime = dateFormat.parse(sc.nextLine());
                        departureDates.add(departureDateTime);
                        
                        System.out.print("Enter DURATION in Hours> ");
                        double duration = sc.nextDouble();
                        durations.add(duration);
                        
                        System.out.print("Enter the LAYOVER DURATION in Hours> ");
                        double layover = sc.nextDouble();
                        layovers.add(layover);
                        
                        if (complementaryReturn) {
                            System.out.print("Is there a return flight(Y/N)> ");
                            sc.nextLine();
                            String haveReturn = sc.nextLine().trim().toUpperCase();
                            returnFlights.add(haveReturn);
                        }
                        
                        System.out.println("");
                    }
                    
                    flightScheduleSessionBeanRemote.checkForConflictingFlights(flightNumber, departureDates, durations, layovers, returnFlights);
                    FlightSchedule flightSchedule = new FlightSchedule();
                    for (int i = 0; i < departureDates.size(); i++) { 
                        Date departureDateTime = departureDates.get(i);
                        double duration = durations.get(i);
                        double layover = layovers.get(i);
                        String returnFlight = returnFlights.get(i);
                        flightSchedule = flightScheduleSessionBeanRemote.createNewFlightSchedule(flightNumber, new FlightSchedule(departureDateTime, duration, layover));
                        flightScheduleIds.add(flightSchedule.getFlightScheduleId());
                        
                        if (returnFlight.equals("Y")) {
                            Instant instant = flightSchedule.getArrivalDateTime().toInstant();
                            Date returnDepartureDateTime =  Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
                            flightSchedule = flightScheduleSessionBeanRemote.createNewFlightSchedule(returnFlightNumber, new FlightSchedule(returnDepartureDateTime, duration, layover));
                            returnFlightScheduleIds.add(flightSchedule.getFlightScheduleId());
                        }
                    }
                    
                    FlightSchedulePlan flightSchedulePlan = new FlightSchedulePlan(flightNumber);
//                    List<Cabin> cabins = flightSessionBeanRemote.getCabin(flightNumber);
                    List<Cabin> cabins = flightSchedule.getListOfCabins();
                    cabins.size();
                    Long flightSchedulePlanId = flightSchedulePlanSessionBeanRemote.createMultipleFlightSchedulePlan(flightSchedulePlan, flightScheduleIds);
                    Long returnFlightSchedulePlanId = 0l;
                    
                    if (returnFlightScheduleIds.size() > 0) {
                        FlightSchedulePlan returnFlightSchedulePlan = new FlightSchedulePlan(returnFlightNumber);
                        returnFlightSchedulePlanId = flightSchedulePlanSessionBeanRemote.createMultipleFlightSchedulePlan(returnFlightSchedulePlan, returnFlightScheduleIds);
                    }
                    
                    for (int i = 0; i < cabins.size(); i++) {
                        System.out.print(String.format("Enter the Number of Fares for Cabin Class %s>", cabins.get(i).getCabinClassName()));
                        
                        int numFares = sc.nextInt();
                        ArrayList<String> fareBasisCodes = new ArrayList<>();
                        ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                        
                        sc.nextLine();
                        for (int j = 0; j < numFares; j++) {
                            System.out.print("Enter the Fare Basis Code> ");
                            String fareBasisCode = sc.nextLine();
                            
                            if (fareBasisCodes.contains(fareBasisCode)) {
                                throw new FareBasisCodeAlreadyExistException("There should not be any duplicate fare basis code!");
                            } else {
                                fareBasisCodes.add(fareBasisCode);
                            }

                            System.out.print("Enter the Fare Amount> ");
                            fareAmounts.add(new BigDecimal(sc.nextLine()));
                        }
                        System.out.println("");
                        
                        flightSchedulePlanSessionBeanRemote.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                        
                        if (returnFlightScheduleIds.size() > 0) {
                            flightSchedulePlanSessionBeanRemote.createFare(returnFlightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                        }
                    } 
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date and time format. Please use yyyy-MM-dd HH:mm.");
                    System.out.println("");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format. Please enter numeric values for hours and minutes.");
                    System.out.println("");
                } catch (FlightDoesNotExistException e) {
                    System.out.println("This Flight Number does not exist!");
                    System.out.println("");
                }
            } else if (response == 3) {
                List<Long> flightScheduleIds = new ArrayList<>();
                List<Long> returnFlightScheduleIds = new ArrayList<>();
                List<Date> departureDates = new ArrayList<>();
                List<Double> durations = new ArrayList<>();
                List<Double> layovers = new ArrayList<>();
                
                System.out.println("");
                System.out.println("*** PLEASE ENTER THE FLIGHT NUMBER, DEPARTURE DETAILS AND FLIGHT DURATION ***\n");
                System.out.print("Enter Flight Number> ");
                Integer flightNumber = sc.nextInt();
                try {
                    Boolean complementaryReturn = flightSessionBeanRemote.haveComplementaryFlight(flightNumber);

                    Integer returnFlightNumber = 0;
                    if (complementaryReturn) {
                        returnFlightNumber = flightSessionBeanRemote.returnFlightNumber(flightNumber);
                    }
                
                    
                    System.out.println("ENTER DETAILS FOR THE FLIGHT SCHEDULE ");
                    System.out.print("Enter DEPARTURE DATE AND TIME (yyyy-MM-dd HH:mm)> ");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    sc.nextLine();
                    Date departureDateTime = dateFormat.parse(sc.nextLine());

                    System.out.print("Enter DURATION in Hours> ");
                    double duration = sc.nextDouble();

                    System.out.print("Enter the number of days which the flight schedule will recur> ");
                    int days = sc.nextInt();
                    
                    System.out.print("Enter END DATE (yyyy-MM-dd HH:mm)> ");
                    sc.nextLine();
                    Date endDate = dateFormat.parse(sc.nextLine());
                    
                    System.out.print("Enter the LAYOVER DURATION in Hours> ");
                    double layover = sc.nextDouble();
                    layovers.add(layover);
                    
                    String haveReturn = "";
                    if (complementaryReturn) {
                        System.out.print("Is there a return flight(Y/N)> ");
                        sc.nextLine();
                        haveReturn = sc.nextLine().trim().toUpperCase();
                    }
                    
                    while(!departureDateTime.equals(endDate) || endDate.after(departureDateTime)) {
                        departureDates.add(departureDateTime);
                        durations.add(duration);
                        layovers.add(layover);
                        
                        departureDateTime = new Date(departureDateTime.getTime() + (days * 1000 * 60 * 60 * 24));
                        
                        if (endDate.before(departureDateTime)) {
                            break;
                        }
                    }
                    
                    flightScheduleSessionBeanRemote.checkForConflictingFlights(flightNumber, departureDates, durations, layovers, haveReturn.equals("Y"));
                    FlightSchedule flightSchedule = new FlightSchedule();
                    
                     
                    for (int i = 0; i < departureDates.size(); i++) { 
                        Date departureDate = departureDates.get(i);
                        flightSchedule = flightScheduleSessionBeanRemote.createNewFlightSchedule(flightNumber, new FlightSchedule(departureDate, duration, layover));
                        flightScheduleIds.add(flightSchedule.getFlightScheduleId());
                        
                        if (haveReturn.equals("Y")) {
                            Instant instant = flightSchedule.getArrivalDateTime().toInstant();
                            Date returnDepartureDateTime =  Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
                            flightSchedule = flightScheduleSessionBeanRemote.createNewFlightSchedule(returnFlightNumber, new FlightSchedule(returnDepartureDateTime, duration, layover));
                            returnFlightScheduleIds.add(flightSchedule.getFlightScheduleId());
                        }
                    }
                    
                    FlightSchedulePlan flightSchedulePlan = new FlightSchedulePlan(flightNumber);          
//                    List<Cabin> cabins = flightSessionBeanRemote.getCabin(flightNumber);
                    List<Cabin> cabins = flightSchedule.getListOfCabins();
                    cabins.size();
                    Long flightSchedulePlanId = flightSchedulePlanSessionBeanRemote.createMultipleFlightSchedulePlan(flightSchedulePlan, flightScheduleIds);
                    
                    Long returnFlightSchedulePlanId = 0l;
                    if (haveReturn.equals("Y")) {
                        FlightSchedulePlan returnFlightSchedulePlan = new FlightSchedulePlan(returnFlightNumber);
                        returnFlightSchedulePlanId = flightSchedulePlanSessionBeanRemote.createMultipleFlightSchedulePlan(returnFlightSchedulePlan, returnFlightScheduleIds);
                    }
                    
                    for (int i = 0; i < cabins.size(); i++) {
                        System.out.print(String.format("Enter the Number of Fares for Cabin Class %s> ", cabins.get(i).getCabinClassName()));
                        
                        int numFares = sc.nextInt();
                        ArrayList<String> fareBasisCodes = new ArrayList<>();
                        ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                        
                        sc.nextLine();
                        for (int j = 0; j < numFares; j++) {
                            System.out.print("Enter the Fare Basis Code> ");
                            String fareBasisCode = sc.nextLine();
                            
                            if (fareBasisCodes.contains(fareBasisCode)) {
                                throw new FareBasisCodeAlreadyExistException("There should not be any duplicate fare basis code!");
                            } else {
                                fareBasisCodes.add(fareBasisCode);
                            }

                            System.out.print("Enter the Fare Amount> ");
                            fareAmounts.add(new BigDecimal(sc.nextLine()));
                        }
                        
                        flightSchedulePlanSessionBeanRemote.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                        if (haveReturn.equals("Y")) {
                            flightSchedulePlanSessionBeanRemote.createFare(returnFlightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                        }
                    } 
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date and time format. Please use yyyy-MM-dd HH:mm.");
                    System.out.println("");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format. Please enter numeric values for hours and minutes.");
                    System.out.println("");
                } catch (FlightDoesNotExistException e) {
                    System.out.println("This Flight Number does not exist!");
                    System.out.println("");
                }
            } else if (response == 4) {
                List<Long> flightScheduleIds = new ArrayList<>();
                List<Long> returnFlightScheduleIds = new ArrayList<>();
                List<Date> departureDates = new ArrayList<>();
                List<Double> durations = new ArrayList<>();
                List<Double> layovers = new ArrayList<>();
                
                System.out.println("");
                System.out.println("*** PLEASE ENTER THE FLIGHT NUMBER, DEPARTURE DETAILS AND FLIGHT DURATION ***\n");
                System.out.print("Enter Flight Number> ");
                Integer flightNumber = sc.nextInt();
                
                try {
                    Boolean complementaryReturn = flightSessionBeanRemote.haveComplementaryFlight(flightNumber);

                    Integer returnFlightNumber = 0;
                    if (complementaryReturn) {
                        returnFlightNumber = flightSessionBeanRemote.returnFlightNumber(flightNumber);
                    }    
                    System.out.println("ENTER DETAILS FOR THE FLIGHT SCHEDULE ");
                    System.out.print("Enter DEPARTURE DATE AND TIME (yyyy-MM-dd HH:mm)> ");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    sc.nextLine();
                    Date departureDateTime = dateFormat.parse(sc.nextLine());

                    System.out.print("Enter the Day of The Week (1 -> Sunday , 7 -> Saturday)> ");
                    int dayOfWeek = sc.nextInt();
                            
                    System.out.print("Enter DURATION in Hours> ");
                    double duration = sc.nextDouble();
                    sc.nextLine();
                    
                    System.out.print("Enter END DATE (yyyy-MM-dd HH:mm)> ");
                    Date endDate = dateFormat.parse(sc.nextLine());
                    
                    System.out.print("Enter the LAYOVER DURATION in Hours> ");
                    double layover = sc.nextDouble();
                    
                    String haveReturn = "";
                    if (complementaryReturn) {
                        System.out.print("Is there a return flight(Y/N)> ");
                        sc.nextLine();
                        haveReturn = sc.nextLine().trim().toUpperCase();
                    }
                    
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(departureDateTime);
                    int dayOfWeekNow = calendar.get(Calendar.DAY_OF_WEEK);
                    
                    while(calendar.get(Calendar.DAY_OF_WEEK) != dayOfWeek) {
                        departureDateTime = new Date(departureDateTime.getTime() + 1000 * 60 * 60 * 24);
                        calendar.setTime(departureDateTime);
                    }
                    
                    while(!departureDateTime.equals(endDate)) {
                        departureDates.add(departureDateTime);
                        durations.add(duration);
                        layovers.add(layover);

                        departureDateTime = new Date(departureDateTime.getTime() + (7 * 1000 * 60 * 60 * 24));
                        
                        if (endDate.before(departureDateTime)) {
                            break;
                        }
                    }
                    
                    flightScheduleSessionBeanRemote.checkForConflictingFlights(flightNumber, departureDates, durations, layovers, haveReturn.equals("Y"));
                    FlightSchedule flightSchedule = new FlightSchedule();
                    for (int i = 0; i < departureDates.size(); i++) { 
                        Date departureDate = departureDates.get(i);
                        flightSchedule = flightScheduleSessionBeanRemote.createNewFlightSchedule(flightNumber, new FlightSchedule(departureDate, duration, layover));
                        flightScheduleIds.add(flightSchedule.getFlightScheduleId());
                        
                        if (haveReturn.equals("Y")) {
                            Instant instant = flightSchedule.getArrivalDateTime().toInstant();
                            Date returnDepartureDateTime =  Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
                            flightSchedule = flightScheduleSessionBeanRemote.createNewFlightSchedule(returnFlightNumber, new FlightSchedule(returnDepartureDateTime, duration, layover));
                            returnFlightScheduleIds.add(flightSchedule.getFlightScheduleId());
                        }
                    }
                    
                    FlightSchedulePlan flightSchedulePlan = new FlightSchedulePlan(flightNumber);
//                    List<Cabin> cabins = flightSessionBeanRemote.getCabin(flightNumber);
                    List<Cabin> cabins = flightSchedule.getListOfCabins();
                    cabins.size();
                    Long flightSchedulePlanId = flightSchedulePlanSessionBeanRemote.createMultipleFlightSchedulePlan(flightSchedulePlan, flightScheduleIds);
                    
                    Long returnFlightSchedulePlanId = 0l;
                    if (haveReturn.equals("Y")) {
                        FlightSchedulePlan returnFlightSchedulePlan = new FlightSchedulePlan(returnFlightNumber);
                        returnFlightSchedulePlanId = flightSchedulePlanSessionBeanRemote.createMultipleFlightSchedulePlan(returnFlightSchedulePlan, returnFlightScheduleIds);
                    }
                    
                    for (int i = 0; i < cabins.size(); i++) {
                        System.out.print(String.format("Enter the Number of Fares for Cabin Class %s> ", cabins.get(i).getCabinClassName()));
                        
                        int numFares = sc.nextInt();
                        ArrayList<String> fareBasisCodes = new ArrayList<>();
                        ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                        
                        sc.nextLine();
                        for (int j = 0; j < numFares; j++) {
                            System.out.print("Enter the Fare Basis Code> ");
                            String fareBasisCode = sc.nextLine();
                            
                            if (fareBasisCodes.contains(fareBasisCode)) {
                                throw new FareBasisCodeAlreadyExistException("There should not be any duplicate fare basis code!");
                            } else {
                                fareBasisCodes.add(fareBasisCode);
                            }

                            System.out.print("Enter the Fare Amount> ");
                            fareAmounts.add(new BigDecimal(sc.nextLine()));
                        }
                        
                        flightSchedulePlanSessionBeanRemote.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                        
                        if (haveReturn.equals("Y")) {
                            flightSchedulePlanSessionBeanRemote.createFare(returnFlightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                        }
                    } 
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date and time format. Please use yyyy-MM-dd HH:mm.");
                    System.out.println("");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format. Please enter numeric values for hours and minutes.");
                    System.out.println("");
                } catch (FlightDoesNotExistException e) {
                    System.out.println("This Flight Number does not exist!");
                    System.out.println("");
                }
            } else if (response == 5) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
    
    public void viewFlightSchedulePlans(Scanner sc) throws Exception {
        System.out.println("*** YOU HAVE PICKED VIEW FLIGHT SCHEDULE PLANS ***\n");
        Integer response;
        while(true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: View All Flight Schedule Plans ");
            System.out.println("2: Back\n ");

            System.out.print("> ");
            response = sc.nextInt();
            
            if (response == 1) {
                viewAllFLightSchedulePlan(sc);
            } else if (response == 2) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
  
    }
    
    public void viewAllFLightSchedulePlan(Scanner sc) throws Exception {
        List<FlightSchedulePlan> listOfFlightSchedulePlan = flightSchedulePlanSessionBeanRemote.retrieveAllFlightSchedulePlans();
        
        if (listOfFlightSchedulePlan.size() == 0) {
            System.out.println("There are no existing Flight Schedule Plans!");
            System.out.println("");
        } else {
            System.out.println("*** YOU HAVE PICKED VIEW ALL FLIGHT SCHEDULE PLANS ***\n");
            System.out.println("FLIGHT SCHEDULE PLAN ID | FLIGHT NUMBER ");
            for (int i = 0; i < listOfFlightSchedulePlan.size(); i++) { 
                System.out.println(String.format("%23s | %13s ", listOfFlightSchedulePlan.get(i).getFlightSchedulePlanId(), listOfFlightSchedulePlan.get(i).getFlightNumber()));
            }

            System.out.print("Enter ID for more details> ");
            int flightSchedulePlanId = sc.nextInt();
            sc.nextLine();

            FlightRoute flightRoute  = flightRoutesSessionBeanRemote.getFlightRouteWithFS((long) flightSchedulePlanId);
            System.out.println("The Flight Route is:");
            System.out.println("Flight has Origin Aiport: " + flightRoute.getOrigin());
            System.out.println("Flight has Destination Aiport: " + flightRoute.getDestination());
            System.out.println("");

            List<FlightSchedule> flightSchedules = flightScheduleSessionBeanRemote.getFlightSchedulesWithId((long) flightSchedulePlanId);
            System.out.println("The Flight Schedules are:");
            for (FlightSchedule f : flightSchedules) {
                System.out.println(f);
            }
            System.out.println("");

            List<Fare> fares = flightSchedulePlanSessionBeanRemote.getFlightSchedulePlanFares((long) flightSchedulePlanId);
            System.out.println("The Flight Fares are:");
            for (Fare f : fares) {
                System.out.println(f);
            }
            System.out.println("");
        }  
    }
    
    public void updateFlightSchedulePlan(Scanner sc) throws Exception {
        System.out.println("*** YOU HAVE PICKED UPDATE FLIGHT SCHEDULE PLAN ***\n");
        
        List<FlightSchedulePlan> listOfFlightSchedulePlan = flightSchedulePlanSessionBeanRemote.retrieveAllFlightSchedulePlans();
        for (int i = 0; i < listOfFlightSchedulePlan.size(); i++) { 
            System.out.println("Flight Schedule Plan ID: " + listOfFlightSchedulePlan.get(i).getFlightSchedulePlanId());
            System.out.println("Flight Schedule Plan for Flight Number: " + listOfFlightSchedulePlan.get(i).getFlightNumber());
            System.out.println("");
        }
        
        System.out.print("Enter Flight Schedule Plan ID to Update> ");
        int flightSchedulePlanId = sc.nextInt();
        sc.nextLine();
        
        System.out.println("*** UPDATE DETAILS FOR FLIGHT SCHEDULE ***\n");
        System.out.print("Delete Flight Schedule? (Y/N)> ");
        if (sc.nextLine().trim().equalsIgnoreCase("Y")) {
            List<FlightSchedule> flightSchedules = flightSchedulePlanSessionBeanRemote.retrieveFlightSchedule((long) flightSchedulePlanId);
            for (FlightSchedule f: flightSchedules) {
                System.out.println(f);
            }
            
            System.out.print("Input the Flight Schedule ID you wish to delete> ");
            Integer flightScheduleId = sc.nextInt();
            
            Long Id = flightScheduleSessionBeanRemote.deleteFlightSchedule((long) flightScheduleId);
            System.out.println("*** FLIGHT SCHEDULE DELETED! ***\n");
        }
        
        System.out.print("Change Flight Schedule? (Y/N)> ");
        sc.nextLine();
        if (sc.nextLine().trim().equalsIgnoreCase("Y")) {
            List<FlightSchedule> flightSchedules = flightSchedulePlanSessionBeanRemote.retrieveFlightSchedule((long) flightSchedulePlanId);
            for (FlightSchedule f: flightSchedules) {
                System.out.println(f);
            }
            
            System.out.print("Input the Flight Schedule ID you wish to update> ");
            Integer flightScheduleId = sc.nextInt();
            System.out.print("Enter DEPARTURE DATE AND TIME (yyyy-MM-dd HH:mm)> ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            sc.nextLine();
            Date departureDateTime = dateFormat.parse(sc.nextLine());

            System.out.print("Enter DURATION in Hours> ");
            double duration = sc.nextDouble();
            
            Long fScheduleId = flightScheduleSessionBeanRemote.changeFlightScheduleDateTime((long) flightScheduleId, departureDateTime, duration);
            System.out.println("*** FLIGHT SCHEDULE CHANGED! ***\n");
        }
        
        System.out.print("Change Fare? (Y/N)> ");
        if (sc.nextLine().trim().equalsIgnoreCase("Y")) {
            List<Fare> fares = flightSchedulePlanSessionBeanRemote.getFlightSchedulePlanFares((long) flightSchedulePlanId);
            for (Fare f : fares) {
                System.out.println(f);
            }
            
            System.out.print("Input the Fare ID you wish to update> ");
            Integer fareId = sc.nextInt();
            sc.nextLine();
            
            System.out.print("Input the updated Fare Basis Code> ");
            String fareBasisCode = sc.nextLine();
            
            System.out.print("Input the updated Fare Amount> ");
            BigDecimal fareAmount = new BigDecimal(sc.nextLine());
            
            fareSessionBeanRemote.updateFare((long) fareId, fareBasisCode, fareAmount);
            
            System.out.println("*** FARE CHANGED! ***\n");
        }
        
        System.out.println(String.format("UPDATING DONE FOR FLIGHT SCHEDULE PLAN %s!\n", flightSchedulePlanId));
    }
    
    public void deleteFlightSchedulePlan(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED DELETE FLIGHT SCHEDULE PLAN ***\n");
        
        List<FlightSchedulePlan> listOfFlightSchedulePlan = flightSchedulePlanSessionBeanRemote.retrieveAllFlightSchedulePlans();
        for (int i = 0; i < listOfFlightSchedulePlan.size(); i++) { 
            System.out.println("Flight Schedule Plan ID: " + listOfFlightSchedulePlan.get(i).getFlightSchedulePlanId());
            System.out.println("Flight Schedule Plan for Flight Number: " + listOfFlightSchedulePlan.get(i).getFlightNumber());
            System.out.println("");
        }
        
        System.out.print("Enter Flight Schedule Plan ID to Delete> ");
        int flightScheduleId = sc.nextInt();
        sc.nextLine();
        
        flightSchedulePlanSessionBeanRemote.deleteFlightSchedulePlan((long) flightScheduleId);
    }
    
     //=================================================SALES MANAGER========================================================
    public void salesManagerOptions(Scanner sc) throws FlightScheduleDoesNotExistException {
        System.out.println("*** YOU ARE LOGGED IN AS A SALES MANAGER ***\n");
        Integer response;
        while (true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: View Seat Inventory ");
            System.out.println("2: View Flight Reservations");
            System.out.println("3: Logout\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            System.out.println("");
            if (response == 1) {
                viewSeatInventory(sc);
            } else if (response == 2) {
                viewFlightReservation(sc);
            } else if (response == 3) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
    
    public void viewSeatInventory(Scanner sc) throws FlightScheduleDoesNotExistException {
        System.out.println("*** YOU HAVE PICKED VIEW SEAT INVENTORY ***\n");
        System.out.print("Enter Flight Number> ");
        int flightNum = sc.nextInt();
        sc.nextLine();
        List<FlightSchedule> listOfFlightSchedules = flightScheduleSessionBeanRemote.getFlightSchedulesUsingFlightNum(flightNum);
        System.out.println("FLIGHT SCHEDULE ID | DEPARTURE DATE TIME | ORIGIN | DESTINATION ");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (FlightSchedule fs : listOfFlightSchedules) {
            System.out.println(String.format("%18s | %19s | %6s | %11s ", fs.getFlightScheduleId(), dateFormat.format(fs.getDepartureDateTime()), fs.getFlightSchedulePlan().getFlight().getFlightRoute().getOrigin().getAirportCode(), fs.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportCode()));
        }
        
        System.out.print("Enter Flight Schedule Id> ");
        long fsId = sc.nextLong();
        sc.nextLine();
        FlightSchedule fs = flightScheduleSessionBeanRemote.getFlightSchedulesWithFsId(fsId);
        System.out.println("");
        System.out.println("*** CABIN DETAILS FOR FLIGHT SCHEDULE ***");
        List<Cabin> cabins = flightScheduleSessionBeanRemote.getCabins(fsId);
        
        System.out.println("CABIN CLASS | TOTAL SEATS | REMAINING SEATS | FARE PER TICKET ");
        for (Cabin c : cabins) {
            long lowestFareid = cabinCustomerSessionBeanRemote.getLowestFareIdInCabin(c.getCabinId());
            //System.out.println(lowestFareid);
            BigDecimal lowestFare = fareSessionBeanRemote.getFareUsingId(lowestFareid);
            System.out.println(String.format("%11s | %11s | %15s | %15s ", c.getCabinClassName(), c.getTotalSeats(), (c.getTotalSeats() - c.getReservedSeats()), lowestFare));
        }
        System.out.print("Enter Cabin to see Seat Inventory> ");
        String cabin = sc.nextLine().trim();
        char[][] cabinSeatingPlan = flightScheduleSessionBeanRemote.getCabinSeats(fsId, cabin);
        Integer[] islesPlan = flightScheduleSessionBeanRemote.getIslesPlan(fsId, cabin);
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
        System.out.println("");
    }
    
    //=================================================VIEW FLIGHT RESERVATION=====================================================
    public void viewFlightReservation(Scanner sc) throws FlightScheduleDoesNotExistException {
        System.out.println("*** YOU HAVE PICKED VIEW FLIGHT RESERVATION ***\n");
        System.out.print("Enter Flight Number> ");
        int flightNum = sc.nextInt();
        sc.nextLine();
        List<FlightSchedule> listOfFlightSchedules = flightScheduleSessionBeanRemote.getFlightSchedulesUsingFlightNum(flightNum);
        System.out.println("FLIGHT SCHEDULE ID | DEPARTURE DATE TIME | ORIGIN | DESTINATION ");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (FlightSchedule fs : listOfFlightSchedules) {
            System.out.println(String.format("%18s | %19s | %6s | %11s ", fs.getFlightScheduleId(), dateFormat.format(fs.getDepartureDateTime()), fs.getFlightSchedulePlan().getFlight().getFlightRoute().getOrigin().getAirportCode(), fs.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportCode()));
        }
        
        System.out.print("Enter Flight Schedule Id> ");
        long fsId = sc.nextLong();
        sc.nextLine();
        
        FlightSchedule fs = flightScheduleSessionBeanRemote.getFlightSchedulesWithFsId(fsId);
        List<ReservationDetails> resDetails = reservationDetailsSessionBeanRemote.getReservationsWithFsId(fsId);
        
        System.out.println("CABIN CLASS | FIRST NAME | LAST NAME | SEAT | FARE ");
        for (ReservationDetails rd : resDetails) {
            System.out.println(String.format("%11s | %10s | %9s | %4s | %4s ", rd.getFare().getCabin().getCabinClassName(), rd.getFirstName(), rd.getLastName(), rd.getRowNum() + rd.getSeatLetter(), rd.getFare().getFareAmount()));
        }
    }
}
