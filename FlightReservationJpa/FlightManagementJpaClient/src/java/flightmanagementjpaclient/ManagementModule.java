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
import entity.Aircraft;
import entity.AircraftConfiguration;
import entity.Airport;
import entity.Cabin;
import entity.Fare;
import entity.Flight;
import entity.FlightRoute;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import util.enumeration.FlightRouteStatusEnum;
import util.enumeration.FlightStatusEnum;
import util.exception.AirportDoesNotExistException;
import util.exception.FareBasisCodeAlreadyExistException;
import util.exception.FlightDisabledException;
import util.exception.FlightRouteAlreadyExistException;
import util.exception.FlightRouteDoesNotExistException;
import util.exception.InvalidInputException;

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
            FareSessionBeanRemote fareSessionBeanRemote) {
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
    }

    //=================================================ADMIN PAGE================================================================
    public void adminLoginPage() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("*** SUCCESSFULLY LOGIN! *** \n");
        Integer response;
        while (true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Aircraft Configuration ");
            System.out.println("2: Flight Route");
            System.out.println("3: Flight");
            System.out.println("4: Flight Schedule Plan");
            System.out.println("5: Logout\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if (response == 1) {
                aircraftConfigurationOptions(sc);
            } else if (response == 2) {
                flightRoute(sc);
            } else if (response == 3) {
                flightOptions(sc);
            } else if (response == 4) {
                flightSchedule(sc);
            } else if (response == 5) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }

    //=================================================FLIGHT ROUTE================================================================
    public void flightRoute(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED FLIGHT ROUTE ***\n");
        Integer response;
        while (true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create New Flight Route ");
            System.out.println("2: View All Flight Route");
            System.out.println("3: Delete Flight Route");
            System.out.println("4: Back\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
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
                for (int i = 0; i < listOfAirports.size(); i++) {
                    System.out.println(String.format("%s: Airport Name: ", i + 1) + listOfAirports.get(i).getName());
                    System.out.println("ID: " + listOfAirports.get(i).getAirportId());
                    System.out.println("");
                }
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
                for (int i = 0; i < listOfAirports.size(); i++) {
                    System.out.println(String.format("%s: Airport Name: ", i + 1) + listOfAirports.get(i).getName());
                    System.out.println("ID: " + listOfAirports.get(i).getAirportId());
                    System.out.println("");
                }
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
            for (FlightRoute f:listOfFlightRoutes) {
                if (!printedFlightRoutes.contains(f) && f.getFlightRouteStatus() == FlightRouteStatusEnum.ACTIVE) {
                    System.out.println("Flight Route ID: " + f.getFlightRouteId());
                    System.out.println("Origin Airport: " + f.getOrigin().getName());
                    System.out.println("Destination Airport: " + f.getDestination().getName());
                    System.out.println("");
                    printedFlightRoutes.add(f);

                    if (f.getComplementaryRoute()) {
                        for (int i = 0; i < listOfFlightRoutes.size(); i++) {
                            if (listOfFlightRoutes.get(i).getOrigin() == f.getDestination() && listOfFlightRoutes.get(i).getDestination() == f.getOrigin() && listOfFlightRoutes.get(i).getFlightRouteStatus() == FlightRouteStatusEnum.ACTIVE) {
                                System.out.println("Flight Route ID: " + listOfFlightRoutes.get(i).getFlightRouteId() + " (Complementary Flight Route)");
                                System.out.println("Origin Airport: " + listOfFlightRoutes.get(i).getOrigin().getName());
                                System.out.println("Destination Airport: " + listOfFlightRoutes.get(i).getDestination().getName());
                                System.out.println("");

                                printedFlightRoutes.add(listOfFlightRoutes.get(i));
                                break;
                            } 
                        }

                    } 
                }
            }
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

                for (FlightRoute f:listOfFlightRoutes) {
                    if (!printedFlightRoutes.contains(f) && f.getFlightRouteStatus() == FlightRouteStatusEnum.ACTIVE) {
                        System.out.println("Flight Route ID: " + f.getFlightRouteId());
                        System.out.println("Origin Airport: " + f.getOrigin().getName());
                        System.out.println("Destination Airport: " + f.getDestination().getName());
                        System.out.println("");
                        printedFlightRoutes.add(f);

                        if (f.getComplementaryRoute()) {
                            for (int i = 0; i < listOfFlightRoutes.size(); i++) {
                                if (listOfFlightRoutes.get(i).getOrigin() == f.getDestination() && listOfFlightRoutes.get(i).getDestination() == f.getOrigin() && listOfFlightRoutes.get(i).getFlightRouteStatus() == FlightRouteStatusEnum.ACTIVE) {
                                    System.out.println("Flight Route ID: " + listOfFlightRoutes.get(i).getFlightRouteId() + " (Complementary Flight Route)");
                                    System.out.println("Origin Airport: " + listOfFlightRoutes.get(i).getOrigin().getName());
                                    System.out.println("Destination Airport: " + listOfFlightRoutes.get(i).getDestination().getName());
                                    System.out.println("");

                                    printedFlightRoutes.add(listOfFlightRoutes.get(i));
                                    break;
                                } 
                            }

                        } 
                    }
                }
                
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

    //=================================================AIRCRAFT CONFIGURATION=================================================
    public void aircraftConfigurationOptions(Scanner sc) throws Exception {
        System.out.println("*** YOU HAVE PICKED AIRCRAFT CONFIGURATION ***\n");
        Integer response;
        while (true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create Aircraft Configuration ");
            System.out.println("2: View All Aircraft Configuration");
            System.out.println("3: Back\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
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
        for (int i = 0; i < listOfAircrafts.size(); i++) {
            System.out.println(String.format("%s: Aircraft Name: ", i + 1) + listOfAircrafts.get(i).getAircraftName());
            System.out.println("Number of Seats: " + listOfAircrafts.get(i).getNumOfSeats());
            System.out.println("");
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
        System.out.println("Flight Configuration Id = " + aircraftConfigId);
        System.out.println("");
    }

    public void viewAllFlightConfiguration(Scanner sc) throws Exception {
        List<AircraftConfiguration> listOfac = aircraftConfigurationSessionBeanRemote.retrieveAllAircraftConfigurations();
        //List<Aircraft> listOfAircraft = aircraftSessionBeanRemote.retrieveAllAircrafts();
        System.out.println("*** YOU HAVE PICKED VIEW ALL AIRCRAFT CONFIGURATION ***\n");
        for (int i = 0; i < listOfac.size(); i++) {
            System.out.println("ID: " + listOfac.get(i).getAircraftConfigurationId());
            System.out.println("Aircraft Type: " + listOfac.get(i).getAircraft().getAircraftName());
            System.out.println("Aircraft Configuration Name: " + listOfac.get(i).getAircraftConfigName());
            System.out.println("");
        }

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
    }

    //=================================================FLIGHT================================================================
    public void flightOptions(Scanner sc) throws Exception {
        System.out.println("*** YOU HAVE PICKED FLIGHT ***\n");
        Integer response;
        while (true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create Flight ");
            System.out.println("2: View All Flight");
            System.out.println("3: Update Flight");
            System.out.println("4: Delete Flight");
            System.out.println("5: Back\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if (response == 1) {
                createFlight(sc);
            } else if (response == 2) {
                viewAllFlights(sc);
            } else if (response == 3) {
                updateFlight(sc);
            } else if (response == 4) {
                deleteFlights(sc);
            } else if (response == 5) {
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
        System.out.print("Enter 4 Digit Flight Number> ");
        Integer flightNum = sc.nextInt();
        sc.nextLine();
        System.out.println("");
        System.out.println("*** SELECT FLIGHT ROUTE ***\n");
        List<FlightRoute> listOfFlightRoutes = flightRoutesSessionBeanRemote.retrieveAllFlightRoutes();
        List<FlightRoute> printedFlightRoutes = new ArrayList<FlightRoute>();
        
        if (listOfFlightRoutes.size() == 0) {
            System.out.println("There are no existing Flight Routes!");
        } else {
            for (FlightRoute f:listOfFlightRoutes) {
                if (!printedFlightRoutes.contains(f) && f.getFlightRouteStatus() == FlightRouteStatusEnum.ACTIVE) {
                    System.out.println("Flight Route ID: " + f.getFlightRouteId());
                    System.out.println("Origin Airport: " + f.getOrigin().getName());
                    System.out.println("Destination Airport: " + f.getDestination().getName());
                    System.out.println("");
                    printedFlightRoutes.add(f);

                    if (f.getComplementaryRoute()) {
                        for (int i = 0; i < listOfFlightRoutes.size(); i++) {
                            if (listOfFlightRoutes.get(i).getOrigin() == f.getDestination() && listOfFlightRoutes.get(i).getDestination() == f.getOrigin() && listOfFlightRoutes.get(i).getFlightRouteStatus() == FlightRouteStatusEnum.ACTIVE) {
                                System.out.println("Flight Route ID: " + listOfFlightRoutes.get(i).getFlightRouteId() + " (Complementary Flight Route)");
                                System.out.println("Origin Airport: " + listOfFlightRoutes.get(i).getOrigin().getName());
                                System.out.println("Destination Airport: " + listOfFlightRoutes.get(i).getDestination().getName());
                                System.out.println("");

                                printedFlightRoutes.add(listOfFlightRoutes.get(i));
                                break;
                            } 
                        }

                    } 
                }
            } 
        }
            
        
        System.out.print("Enter Flight Route ID> ");
        Long flightRouteId = sc.nextLong();
        sc.nextLine();
        
        FlightRoute chosenFlightRoute = flightRoutesSessionBeanRemote.getFlightRouteWithId(flightRouteId);
        
        if (chosenFlightRoute.getComplementaryRoute()) {
            System.out.print("Do you want to create a complementary return flight> ");
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

        for (int i = 0; i < aircraftConfigurations.size(); i++) {
            System.out.println("ID: " + aircraftConfigurations.get(i).getAircraftConfigurationId());
            System.out.println("Aircraft Type: " + aircraftConfigurations.get(i).getAircraft().getAircraftName());
            System.out.println("Aircraft Configuration Name: " + aircraftConfigurations.get(i).getAircraftConfigName());
            System.out.println("");
        }
        System.out.print("Enter Flight Configuration Id> ");
        Long aircraftConfigId = sc.nextLong();

        sc.nextLine();
        
        Flight flight = new Flight(flightNum);
        
        Long flightId;
        if (haveReturn.equals("Y")) {
            Flight returnFlight = new Flight(returnFlightNumber);
            flightId = flightSessionBeanRemote.createNewFlightWithReturn(flight, returnFlight, flightRouteId, aircraftConfigId);
        } else {
            flightId = flightSessionBeanRemote.createNewFlight(flight, flightRouteId, aircraftConfigId);
        }
        System.out.println("");
        System.out.println("Flight Successfully Created!");
        System.out.println("Flight Id= " + flightId);
        System.out.println("");
    }

    public void deleteFlights(Scanner sc) throws Exception {
        System.out.println("*** YOU HAVE PICKED DELETE FLIGHTS ***\n");
        //print all of the flights
        List<Flight> listOfFlights = flightSessionBeanRemote.retrieveAllFlights();
        
        for (Flight f: listOfFlights) { 
            if (f.getFlightStatus() == FlightStatusEnum.ACTIVE) {
                System.out.println("Flight ID: " + f.getFlightId());
                System.out.println("Aircraft Configuration Name: " + f.getAircraftConfig().getAircraftConfigName());
                System.out.println("Aircraft Route ID: " + f.getFlightRoute().getFlightRouteId());
                System.out.println("");
            }
        }
        
        System.out.print("Enter ID of Flight to be deleted> ");
        Long flightIdNum = sc.nextLong();
        sc.nextLine();

        Long delFlightId = flightSessionBeanRemote.removeFlight(flightIdNum);
        System.out.println(String.format("Flight Id = %s has been Removed", delFlightId));
        System.out.println("");
    }

    public void viewAllFlights(Scanner sc) throws Exception {
        List<Flight> listOfFlights = flightSessionBeanRemote.retrieveAllFlights();
        System.out.println("*** YOU HAVE PICKED VIEW ALL FLIGHTS ***\n");
        
        if (listOfFlights.size() == 0) {
            System.out.println("There are no existing Flights!");
        } else {
            for (Flight f: listOfFlights) { 
                if (f.getFlightStatus() == FlightStatusEnum.ACTIVE) {
                    System.out.println("Flight ID: " + f.getFlightId());
                    System.out.println("Aircraft Configuration Name: " + f.getAircraftConfig().getAircraftConfigName());
                    System.out.println("Aircraft Route ID: " + f.getFlightRoute().getFlightRouteId());
                    System.out.println("");
                }
            }

            System.out.print("Enter ID for more details> ");
            Long flightIdNum = sc.nextLong();
            sc.nextLine();

            Flight flight = flightSessionBeanRemote.getFlightWithId(flightIdNum);
            List<Cabin> listOfCabins = aircraftConfigurationSessionBeanRemote.retrieveCabinsWithId(flight.getAircraftConfig().getAircraftConfigurationId());
            System.out.println("\n*** FLIGHT DETAILS ***");
            System.out.println("Flight Number: " + flight.getPrefix() + flight.getFlightNumber());
            Integer totalSeats = flightSessionBeanRemote.getTotalSeats(flightIdNum);
            Integer reservedSeats = flightSessionBeanRemote.getReservedSeats(flightIdNum);
            System.out.println("Flight Total Seats: " + totalSeats);
            System.out.println("Flight Available Seats: " + (totalSeats - reservedSeats));
            System.out.println("Flight Reserved Seats: " + reservedSeats);

            System.out.println("\n*** FLIGHT ROUTE DETAILS ***");
            FlightRoute f = flightRoutesSessionBeanRemote.getFlightRouteWithId(flight.getFlightRoute().getFlightRouteId());
            System.out.println("Flight Route ID: " + f.getFlightRouteId());
            System.out.println("Origin Airport: " + f.getOrigin().getName());
            System.out.println("Destination Airport: " + f.getDestination().getName());
            System.out.println("");

            if (f.getComplementaryRoute()) {
                System.out.println("Complementary Flight Route");
                System.out.println("Origin Airport: " + f.getDestination().getName());
                System.out.println("Destination Airport: " + f.getOrigin().getName());
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
            for (Flight f: listOfFlights) { 
                if (f.getFlightStatus() == FlightStatusEnum.ACTIVE) {
                    System.out.println("Flight ID: " + f.getFlightId());
                    System.out.println("Aircraft Configuration Name: " + f.getAircraftConfig().getAircraftConfigName());
                    System.out.println("Aircraft Route ID: " + f.getFlightRoute().getFlightRouteId());
                    System.out.println("");
                }
            }
        
            System.out.print("Enter Flight ID to Update> ");
            Long flightIdNum = sc.nextLong();
            sc.nextLine();
            
            Flight flight = flightSessionBeanRemote.getFlightWithId(flightIdNum);

            if (flight.getFlightStatus() == FlightStatusEnum.ACTIVE) {
               System.out.println("*** UPDATE DETAILS FOR FLIGHT ***\n");
                System.out.print("Change Flight Number? (Y/N)> ");
                if (sc.nextLine().trim().equalsIgnoreCase("Y")) {
                    System.out.print("Input New 4 Digit Flight Number> ");
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
    
    //=================================================FLIGHT SCHEDULE PLAN================================================================
    
     public void flightSchedule(Scanner sc) throws Exception {
        System.out.println("*** YOU HAVE PICKED FLIGHT SCHEDULE PLAN ***\n");
        Integer response;
        while(true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create New Flight Schedule Plan ");
            System.out.println("2: View Flight Schedule Plans");
            System.out.println("3: Update Flight Schedule Plan");
            System.out.println("4: Delete Flight Schedule Plan");
            System.out.println("5: Back\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                createFlightSchedulePlan(sc, 1);
            } else if (response == 2) {
                 viewFlightSchedulePlans(sc);
            } else if (response == 3) {
                updateFlightSchedulePlan(sc);
            } else if (response == 4) {
                deleteFlightSchedulePlan(sc);
            } else if (response == 5) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
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
                
                Boolean complementaryReturn = flightSessionBeanRemote.haveComplementaryFlight(flightNumber);
                Integer returnFlightNumber = 0;
                if (complementaryReturn) {
                    returnFlightNumber = flightSessionBeanRemote.returnFlightNumber(flightNumber);
                }
                
                try {
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
                        System.out.print("Is there a return flight> (Y/N)> ");
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
                        System.out.print(String.format("Enter the Number of Fares for Cabin Class %s >", cabins.get(i).getCabinClassName()));
                        
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
                    throw new InvalidInputException("Invalid date and time format. Please use yyyy-MM-dd HH:mm.");
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Invalid number format. Please enter numeric values for hours and minutes.");
                }
            } else if (response == 2) {
                System.out.println("*** PLEASE ENTER THE NUMBER OF SCHEDULES YOU WISH TO CREATE ***\n");
                System.out.print("Enter the Number Of Schedules> ");
                int numSchedules = sc.nextInt();
                List<Long> flightScheduleIds = new ArrayList<>();
                List<Date> departureDates = new ArrayList<>();
                List<Double> durations = new ArrayList<>();
                List<Double> layovers = new ArrayList<>();
                List<String> returnFlights = new ArrayList<>();
                
                System.out.println("");
                System.out.println("*** PLEASE ENTER THE FLIGHT NUMBER, DEPARTURE DETAILS AND FLIGHT DURATION ***\n");
                System.out.print("Enter Flight Number> ");
                Integer flightNumber = sc.nextInt();
                Boolean complementaryReturn = flightSessionBeanRemote.haveComplementaryFlight(flightNumber);
                
                Integer returnFlightNumber = 0;
                if (complementaryReturn) {
                    returnFlightNumber = flightSessionBeanRemote.returnFlightNumber(flightNumber);
                }
                
                try {
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
                            System.out.print("Is there a return flight> (Y/N)> ");
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
                            flightScheduleIds.add(flightSchedule.getFlightScheduleId());
                        }
                    }
                    
                    FlightSchedulePlan flightSchedulePlan = new FlightSchedulePlan(flightNumber);
//                    List<Cabin> cabins = flightSessionBeanRemote.getCabin(flightNumber);
                    List<Cabin> cabins = flightSchedule.getListOfCabins();
                    cabins.size();
                    Long flightSchedulePlanId = flightSchedulePlanSessionBeanRemote.createMultipleFlightSchedulePlan(flightSchedulePlan, flightScheduleIds);
                    
                    for (int i = 0; i < cabins.size(); i++) {
                        System.out.print(String.format("Enter the Number of Fares for Cabin Class %s >", cabins.get(i).getCabinClassName()));
                        
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
                    } 
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException("Invalid date and time format. Please use yyyy-MM-dd HH:mm.");
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Invalid number format. Please enter numeric values for hours and minutes.");
                }
            } else if (response == 3) {
                List<Long> flightScheduleIds = new ArrayList<>();
                List<Date> departureDates = new ArrayList<>();
                List<Double> durations = new ArrayList<>();
                List<Double> layovers = new ArrayList<>();
                
                System.out.println("");
                System.out.println("*** PLEASE ENTER THE FLIGHT NUMBER, DEPARTURE DETAILS AND FLIGHT DURATION ***\n");
                System.out.print("Enter Flight Number> ");
                Integer flightNumber = sc.nextInt();
                Boolean complementaryReturn = flightSessionBeanRemote.haveComplementaryFlight(flightNumber);
                
                Integer returnFlightNumber = 0;
                if (complementaryReturn) {
                    returnFlightNumber = flightSessionBeanRemote.returnFlightNumber(flightNumber);
                }
                
                try {     
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
                        System.out.print("Is there a return flight> (Y/N)> ");
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
                            flightScheduleIds.add(flightSchedule.getFlightScheduleId());
                        }
                    }
                    
                    FlightSchedulePlan flightSchedulePlan = new FlightSchedulePlan(flightNumber);
//                    List<Cabin> cabins = flightSessionBeanRemote.getCabin(flightNumber);
                    List<Cabin> cabins = flightSchedule.getListOfCabins();
                    cabins.size();
                    Long flightSchedulePlanId = flightSchedulePlanSessionBeanRemote.createMultipleFlightSchedulePlan(flightSchedulePlan, flightScheduleIds);
                    
                    for (int i = 0; i < cabins.size(); i++) {
                        System.out.print(String.format("Enter the Number of Fares for Cabin Class %s > ", cabins.get(i).getCabinClassName()));
                        
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
                    } 
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException("Invalid date and time format. Please use yyyy-MM-dd HH:mm.");
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Invalid number format. Please enter numeric values for hours and minutes.");
                }
            } else if (response == 4) {
                List<Long> flightScheduleIds = new ArrayList<>();
                List<Date> departureDates = new ArrayList<>();
                List<Double> durations = new ArrayList<>();
                List<Double> layovers = new ArrayList<>();
                
                System.out.println("");
                System.out.println("*** PLEASE ENTER THE FLIGHT NUMBER, DEPARTURE DETAILS AND FLIGHT DURATION ***\n");
                System.out.print("Enter Flight Number> ");
                Integer flightNumber = sc.nextInt();
                Boolean complementaryReturn = flightSessionBeanRemote.haveComplementaryFlight(flightNumber);
                
                Integer returnFlightNumber = 0;
                if (complementaryReturn) {
                    returnFlightNumber = flightSessionBeanRemote.returnFlightNumber(flightNumber);
                }
                
                try {     
                    System.out.println("ENTER DETAILS FOR THE FLIGHT SCHEDULE ");
                    System.out.print("Enter DEPARTURE DATE AND TIME (yyyy-MM-dd HH:mm)> ");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    sc.nextLine();
                    Date departureDateTime = dateFormat.parse(sc.nextLine());

                    System.out.print("Enter DURATION in Hours> ");
                    double duration = sc.nextDouble();
                    
                    System.out.print("Enter END DATE (yyyy-MM-dd HH:mm)> ");
                    Date endDate = dateFormat.parse(sc.nextLine());
                    
                    System.out.print("Enter the LAYOVER DURATION in Hours> ");
                    double layover = sc.nextDouble();
                    
                    String haveReturn = "";
                    if (complementaryReturn) {
                        System.out.print("Is there a return flight> (Y/N)> ");
                        sc.nextLine();
                        haveReturn = sc.nextLine().trim().toUpperCase();
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
                            flightScheduleIds.add(flightSchedule.getFlightScheduleId());
                        }
                    }
                    
                    FlightSchedulePlan flightSchedulePlan = new FlightSchedulePlan(flightNumber);
//                    List<Cabin> cabins = flightSessionBeanRemote.getCabin(flightNumber);
                    List<Cabin> cabins = flightSchedule.getListOfCabins();
                    cabins.size();
                    Long flightSchedulePlanId = flightSchedulePlanSessionBeanRemote.createMultipleFlightSchedulePlan(flightSchedulePlan, flightScheduleIds);
                    
                    for (int i = 0; i < cabins.size(); i++) {
                        System.out.print(String.format("Enter the Number of Fares for Cabin Class %s > ", cabins.get(i).getCabinClassName()));
                        
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
                    } 
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException("Invalid date and time format. Please use yyyy-MM-dd HH:mm.");
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Invalid number format. Please enter numeric values for hours and minutes.");
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
            for (int i = 0; i < listOfFlightSchedulePlan.size(); i++) { 
                System.out.println("Flight Schedule Plan ID: " + listOfFlightSchedulePlan.get(i).getFlightSchedulePlanId());
                System.out.println("Flight Schedule Plan for Flight Number: " + listOfFlightSchedulePlan.get(i).getFlightNumber());
                System.out.println("");
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
}
