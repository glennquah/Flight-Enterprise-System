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
import ejb.session.stateless.FlightRoutesSessionBeanRemote;
import ejb.session.stateless.FlightSessionBeanRemote;
import entity.Aircraft;
import entity.AircraftConfiguration;
import entity.Cabin;
import entity.Flight;
import entity.FlightRoute;
import java.util.List;
import java.util.Scanner;
import util.exception.AirportDoesNotExistException;
import util.exception.FlightRouteDoesNotExistException;

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
                            FlightSessionBeanRemote flightSessionBeanRemote) {
        this.employeeId = employeeId;
        this.employeeSessionBean = employeeSessionBean;
        this.customerSessionBean = customerSessionBean;
        this.aircraftSessionBeanRemote = aircraftSessionBeanRemote;
        this.aircraftConfigurationSessionBeanRemote = aircraftConfigurationSessionBeanRemote;
        this.cabinCustomerSessionBeanRemote = cabinCustomerSessionBeanRemote;
        this.flightRoutesSessionBeanRemote = flightRoutesSessionBeanRemote;
        this.airportSessionBeanRemote = airportSessionBeanRemote;
        this.flightSessionBeanRemote = flightSessionBeanRemote;
    }
    
    //=================================================ADMIN PAGE================================================================
    
    public void adminLoginPage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*** SUCCESSFULLY LOGIN! *** \n");
        Integer response;
        while(true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Aircraft Configuration ");
            System.out.println("2: Flight Route");
            System.out.println("3: Flight");
            System.out.println("4: Flight Schedule Plan");
            System.out.println("5: Logout\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                aircraftConfigurationOptions(sc);
            } else if (response == 2) {
                flightRoute(sc);
            } else if (response == 3) {
                flightOptions(sc);
            } else if (response == 4) {
                System.out.println("4");
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
        while(true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create New Flight Route ");
            System.out.println("2: View All Flight Route");
            System.out.println("3: Delete Flight Route");
            System.out.println("4: Back\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                createFlightRoute(sc);
            } else if (response == 2) {
//                List<FlightRoute> flightRoutes = flightRoutesSessionBeanRemote.retrieveAllFlightRoutes();
//                for (FlightRoute flightRoute: flightRoutes) {
//                    System.out.println(flightRoute.)
//                }
                   System.out.println("2");
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
        while(true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create new Flight Route ");
            System.out.println("2: Create new Flight Route with Return Route");
            System.out.println("3: Back\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                System.out.println("*** PLEASE ENTER THE ORIGIN AND DESTINATION AIRPORT NAME ***\n");
                System.out.println("Enter Origin Airport ID ");
                System.out.print("> ");
                Long originAirport = sc.nextLong();
                System.out.println("Enter Destination Airport ID ");
                System.out.print("> ");
                Long destAirport = sc.nextLong();
                try {
                    Long flightRouteId = flightRoutesSessionBeanRemote.createNewFlightRoute(originAirport, destAirport);
                    System.out.println("Successfully created Flight Route with ID: " + flightRouteId + "!");
                } catch (AirportDoesNotExistException ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (response == 2) {
                System.out.println("*** PLEASE ENTER THE ORIGIN AND DESTINATION AIRPORT NAME ***\n");
                System.out.println("Enter Origin Airport ID ");
                System.out.print("> ");
                Long originAirport = sc.nextLong();
                System.out.println("Enter Destination Airport ID ");
                System.out.print("> ");
                Long destAirport = sc.nextLong();
                
                try {
                    Long flightRouteId = flightRoutesSessionBeanRemote.createNewFlightRouteWithReturn(originAirport, destAirport);
                    System.out.println("Successfully created Flight Route with ID: " + flightRouteId + "!");
                } catch (AirportDoesNotExistException ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (response == 3) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
     
    public void deleteFlightRoute(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED DELETE FLIGHT ROUTE ***\n");
        Integer response;
        while(true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Delete Flight Route ");
            System.out.println("2: Back\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                System.out.println("*** PLEASE ENTER THE ORIGIN AND DESTINATION AIRPORT NAME ***\n");
                System.out.println("Enter Flight Route ID ");
                System.out.print("> ");
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
    
    public void aircraftConfigurationOptions(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED AIRCRAFT CONFIGURATION ***\n");
        Integer response;
        while(true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create Aircraft Configuration ");
            System.out.println("2: View All Aircraft Configuration");
            System.out.println("3: Back\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
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
    public void createFlightConfiguration(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED CREATE AIRCRAFT CONFIGURATION ***");
        System.out.println("*** PICK AIRCRAFT TYPE ***\n"); 
        List<Aircraft> listOfAircrafts = aircraftSessionBeanRemote.retrieveAllAircrafts();
        for (int i = 0; i < listOfAircrafts.size(); i++) {
            System.out.println(String.format("%s: Aircraft Name: ", i + 1) + listOfAircrafts.get(i).getAircraftName());
            System.out.println("Number of Seats: " + listOfAircrafts.get(i).getNumOfSeats());
            System.out.println("");
        }
        System.out.print("Enter Aircraft Number> ");
        int aircraftNum = sc.nextInt();
        sc.nextLine();
        System.out.println("");
        System.out.print("Enter Aircraft Configuration Name> "); 
        String AircraftConfigName = sc.nextLine().trim();
        long aircraftNumLong = aircraftNum;
        Long aircraftConfigId = aircraftConfigurationSessionBeanRemote.createAircraftConfiguration(new AircraftConfiguration(AircraftConfigName), aircraftNumLong);
        
        System.out.print("Enter Number of Cabin Class (1 to 4)> "); 
        int numCabinClass = sc.nextInt();
        sc.nextLine();
        if (numCabinClass < 1 || numCabinClass > 4) {
            System.out.println("ERROR Please select from 1 - 4");
            createFlightConfiguration(sc);
        }
        
        for (int i = 0; i < numCabinClass; i++) {
            System.out.print(String.format("\nEnter Name of No.%s Cabin Class> ", i + 1));
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
            int[] sconfigInt = new int[numOfIsles + 1];
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
    
    public void viewAllFlightConfiguration(Scanner sc) {
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
        int aircraftConfigOption = sc.nextInt();
        sc.nextLine();
        long aircraftNum = aircraftConfigOption;
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
    
    public void flightOptions(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED FLIGHT ***\n");
        Integer response;
        while(true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create Flight ");
            System.out.println("2: View All Flight");
            System.out.println("3: Update Flight");
            System.out.println("4: Delete Flight");
            System.out.println("5: Back\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                createFlight(sc);
            } else if (response == 2) {
                viewAllFlights(sc);
            } else if (response == 3) {
                System.out.println("3");
            } else if (response == 4) {
                deleteFlights(sc);
            } else if (response == 5) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
    
    public void createFlight(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED CREATE FLIGHT ***");
        System.out.println("*** FILL UP FLIGHT DETAILS ***\n"); 
        System.out.println("*** SELECT AIRLINE ***");
        System.out.println("1. Merline Airlines : IATA\n");
        System.out.print("Enter Airline Number> ");
        int airlineNum = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter 4 Digit Flight Number> ");
        int flightNum = sc.nextInt();
        sc.nextLine();
        System.out.println("");
        System.out.println("*** SELECT FLIGHT ROUTE ***\n"); 
//        List<FlightRoute> listOfFlightRoute = flightRoutesSessionBeanRemote.retrieveAllFlightRoutes();
//        for (int i = 0; i < listOfFlightRoute.size(); i++) {
//            System.out.println("Flight Route No." + i + 1);
//            System.out.println("Origin: " + listOfFlightRoute.get(i).getOriginDestAirport().first());
//            System.out.println("Destination: " + listOfFlightRoute.get(i).getOriginDestAirport().second());
//            System.out.println("");
//        }
        System.out.print("Enter Flight Route ID> ");
        int flightRoute = sc.nextInt();
        sc.nextLine();
        long flightRouteId = flightRoute;
        System.out.println("\n*** SELECT AIRCRAFT CONFIGURATION ***\n"); 
        List<AircraftConfiguration> listOfac = aircraftConfigurationSessionBeanRemote.retrieveAllAircraftConfigurations();
        
        for (int i = 0; i < listOfac.size(); i++) { 
            System.out.println("ID: " + listOfac.get(i).getAircraftConfigurationId());
            System.out.println("Aircraft Type: " + listOfac.get(i).getAircraft().getAircraftName());
            System.out.println("Aircraft Configuration Name: " + listOfac.get(i).getAircraftConfigName());
            System.out.println("");
        }
        System.out.print("Enter Flight Configuration Id> ");
        int aircraftConfig = sc.nextInt();
        long aircraftConfigId = aircraftConfig;
        sc.nextLine();
        Flight flight = new Flight(flightNum);
        Long flightId = flightSessionBeanRemote.createNewFlight(flight, flightRouteId, aircraftConfigId);
        System.out.println("");
        System.out.println("Flight Successfully Created!");
        System.out.println("Flight Id= " + flightId);
        System.out.println("");
    }
    
    public void deleteFlights(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED DELETE FLIGHTS ***\n");
        //print all of the flights
        System.out.print("Enter ID of Flight to be deleted> ");
        int flightId = sc.nextInt();
        sc.nextLine();
        long flightIdNum = flightId;
        long delFlightId = flightSessionBeanRemote.removeFlight(flightIdNum);
        System.out.println(String.format("Flight Id=%s has been Removed", delFlightId));
        System.out.println("");
    }
    
    public void viewAllFlights(Scanner sc) {
//        List<Flight> listOfFlights = flightSessionBeanRemote.retrieveAllFlights();
//        System.out.println("*** YOU HAVE PICKED VIEW ALL FLIGHTS ***\n");
//        for (int i = 0; i < listOfFlights.size(); i++) { 
//            System.out.println("ID: " + listOfFlights.get(i).getFlightId());
//            System.out.println("Aircraft Configuration Name: " + listOfFlights.get(i).getAircraftConfig().getAircraftConfigName());
//            System.out.println("Aircraft Route: " + listOfFlights.get(i).getFlightRoute().getFlightRouteId());
//            System.out.println("");
//        }
        
        System.out.print("Enter ID for more details> ");
//        String aircraftName = sc.nextLine().trim();
        int flightId = sc.nextInt();
        sc.nextLine();
        long flightIdNum = flightId;
        if (flightSessionBeanRemote == null) {
            System.out.println("ITS NULL");
        }
        System.out.println("ITS NOT NULL");
        System.out.println(flightSessionBeanRemote);
        Flight flight = flightSessionBeanRemote.getFlightWithId(flightIdNum);    
        List<Cabin> listOfCabins = aircraftConfigurationSessionBeanRemote.retrieveCabinsWithId(flight.getAircraftConfig().getAircraftConfigurationId());
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
    
    
}
