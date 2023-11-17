/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ejb.session.singleton;

import ejb.session.stateless.AircraftConfigurationSessionBeanLocal;
import ejb.session.stateless.AircraftSessionBeanLocal;
import ejb.session.stateless.AirportSessionBeanLocal;
import ejb.session.stateless.CabinCustomerSessionBeanLocal;
import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.EmployeeSessionBeanLocal;
import ejb.session.stateless.FareSessionBeanLocal;
import ejb.session.stateless.FlightRoutesSessionBeanLocal;
import ejb.session.stateless.FlightSchedulePlanSessionBeanLocal;
import ejb.session.stateless.FlightScheduleSessionBeanLocal;
import ejb.session.stateless.FlightSessionBeanLocal;
import ejb.session.stateless.PartnerSessionBeanLocal;
import entity.Aircraft;
import entity.AircraftConfiguration;
import entity.Airport;
import entity.Cabin;
import entity.Employee;
import entity.Flight;
import entity.FlightRoute;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import entity.Partner;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.EmployeeAccessRightEnum;
import util.exception.AircraftConfigurationDoesNotExistException;
import util.exception.AirportDoesNotExistException;
import util.exception.ConflictingFlightScheduleException;
import util.exception.FlightDoesNotExistException;
import util.exception.FlightRouteAlreadyExistException;
import util.exception.FlightRouteDisabledException;
import util.exception.FlightRouteDoesNotExistException;

/**
 *
 * @author Lenovo
 */
@Singleton
@LocalBean
@Startup

public class DataInitSessionBean {

    @EJB(name = "FlightScheduleSessionBeanLocal")
    private FlightScheduleSessionBeanLocal flightScheduleSessionBeanLocal;
    
    @EJB(name = "FareSessionBeanLocal")
    private FareSessionBeanLocal fareSessionBeanLocal;

    @EJB(name = "FlightSchedulePlanSessionBeanLocal")
    private FlightSchedulePlanSessionBeanLocal flightSchedulePlanSessionBeanLocal;

    @EJB(name = "FlightSessionBeanLocal")
    private FlightSessionBeanLocal flightSessionBeanLocal;

    @EJB(name = "FlightRoutesSessionBeanLocal")
    private FlightRoutesSessionBeanLocal flightRoutesSessionBeanLocal;

    @EJB(name = "CabinCustomerSessionBeanLocal")
    private CabinCustomerSessionBeanLocal cabinCustomerSessionBeanLocal;

    @EJB(name = "AircraftConfigurationSessionBeanLocal")
    private AircraftConfigurationSessionBeanLocal aircraftConfigurationSessionBeanLocal;

    @EJB(name = "CustomerSessionBeanLocal")
    private CustomerSessionBeanLocal customerSessionBeanLocal;

    @EJB(name = "EmployeeSessionBeanLocal")
    private EmployeeSessionBeanLocal employeeSessionBeanLocal;

    @EJB(name = "AirportSessionBeanLocal")
    private AirportSessionBeanLocal airportSessionBeanLocal;

    @EJB(name = "AircraftSessionBeanLocal")
    private AircraftSessionBeanLocal aircraftSessionBeanLocal;
    
    @EJB(name = "PartnerSessionBeanLocal")
    private PartnerSessionBeanLocal partnerSessionBeanLocal;
    
    
    
    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @PostConstruct
    public void postConstruct() {
        try {
            loadEmployees();
            loadAirports();
            loadAircrafts();
            loadPartners();
            loadAircraftConfigurations();
            loadCabins();
            loadFlightRoutes();
            loadFlights();
            loadFlightSchedulePlan();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadEmployees() {
        //===================LOAD EMPLOYEE DATA========================
        if(em.find(Employee.class, 1l) == null) {
            //if data is empty, inject 2 accounts
            Employee em1 = new Employee("Fleet", "Manager", "fleetmanager@mlair.com.sg", "password", EmployeeAccessRightEnum.FLEETMANAGER);
            Employee em2 = new Employee("Route", "Planner", "routeplanner@mlair.com.sg", "password", EmployeeAccessRightEnum.ROUTEPLANNER);
            Employee em3 = new Employee("Schedule", "Manager", "schedulemanager@mlair.com.sg", "password", EmployeeAccessRightEnum.SCHEDULEMANAGER);
            Employee em4 = new Employee("Sales", "Manager", "salesmanager@mlair.com.sg", "password", EmployeeAccessRightEnum.SALESMANAGER);
            employeeSessionBeanLocal.createNewAccount(em1);
            employeeSessionBeanLocal.createNewAccount(em2);
            employeeSessionBeanLocal.createNewAccount(em3);
            employeeSessionBeanLocal.createNewAccount(em4);
        }
    }

    private void loadAirports() {
        //===================LOAD AIRPORT DATA========================
        if(em.find(Airport.class, 1l) == null) {     
            Airport a1 = new Airport("Changi", "SIN", "Singapore", "Singapore", "Singapore");
            Airport a2 = new Airport("Hong Kong", "HKG", "Chek Lap Kok", "Hong Kong", "China");
            Airport a3 = new Airport("Taoyuan", "TPE", "Taoyuan", "Taipei", "Taiwan R.O.C.");
            Airport a4 = new Airport("Narita", "NRT", "Narita", "Chiba", "Japan");
            Airport a5 = new Airport("Sydney", "SYD", "Sydney", "New South Wales", "Australia");

            airportSessionBeanLocal.createNewAirport(a1);
            airportSessionBeanLocal.createNewAirport(a2);
            airportSessionBeanLocal.createNewAirport(a3);
            airportSessionBeanLocal.createNewAirport(a4);
            airportSessionBeanLocal.createNewAirport(a5);
        }
    }

    private void loadAircrafts() {
        //===================LOAD AIRCRAFT DATA========================
        if(em.find(Aircraft.class, 1l) == null) {
            Aircraft a1 = new Aircraft("Boeing 737", 200);
            Aircraft a2 = new Aircraft("Airbus 747", 400);
            aircraftSessionBeanLocal.createAircraft(a1);
            aircraftSessionBeanLocal.createAircraft(a2);
        }
    }

    private void loadPartners() {
        //===================LOAD PARTNER DATA========================
        if(em.find(Partner.class, 1l) == null) {
            Partner em1 = new Partner("Holiday.com", "mlair@holiday.com", "password");
            partnerSessionBeanLocal.createNewAccount(em1);
        }
    }

    private void loadAircraftConfigurations() throws AirportDoesNotExistException {
        //===================LOAD AIRCRAFT CONFIGURATION DATA========================
        if(em.find(AircraftConfiguration.class, 1l) == null) {
            AircraftConfiguration ac1 = new AircraftConfiguration("Boeing 737 All Economy");
            AircraftConfiguration ac2 = new AircraftConfiguration("Boeing 737 Three Classes");
            AircraftConfiguration ac3 = new AircraftConfiguration("Boeing 747 All Economy");
            AircraftConfiguration ac4 = new AircraftConfiguration("Boeing 747 Three Classes");
            aircraftConfigurationSessionBeanLocal.createAircraftConfiguration(ac1, 1l);
            aircraftConfigurationSessionBeanLocal.createAircraftConfiguration(ac2, 1l);
            aircraftConfigurationSessionBeanLocal.createAircraftConfiguration(ac3, 2l);
            aircraftConfigurationSessionBeanLocal.createAircraftConfiguration(ac4, 2l);  
        }
    }

    private void loadCabins() throws AircraftConfigurationDoesNotExistException {
        //===================LOAD CABIN DATA========================
        if(em.find(Cabin.class, 1l) == null) {
            Integer[] seatConfig1 = new Integer[2];
            seatConfig1[0] = 3;
            seatConfig1[1] = 3; 
            Cabin cab1 = new Cabin("Y", 1, 30, seatConfig1);
            cabinCustomerSessionBeanLocal.createCabin(cab1, 1l);

            Integer[] seatConfig2 = new Integer[2];
            seatConfig2[0] = 1;
            seatConfig2[1] = 1; 
            Cabin cab2 = new Cabin("F", 1, 5, seatConfig2);
            cabinCustomerSessionBeanLocal.createCabin(cab2, 2l);
            Integer[] seatConfig3 = new Integer[2];
            seatConfig3[0] = 2;
            seatConfig3[1] = 2; 
            Cabin cab3 = new Cabin("J", 1, 5, seatConfig3);
            cabinCustomerSessionBeanLocal.createCabin(cab3, 2l);
            Integer[] seatConfig4 = new Integer[2];
            seatConfig4[0] = 3;
            seatConfig4[1] = 3; 
            Cabin cab4 = new Cabin("Y", 1, 25, seatConfig4);
            cabinCustomerSessionBeanLocal.createCabin(cab3, 2l);

            Integer[] seatConfig5 = new Integer[3];
            seatConfig5[0] = 3;
            seatConfig5[1] = 4; 
            seatConfig5[2] = 3; 
            Cabin cab5 = new Cabin("Y", 2, 38, seatConfig5);
            cabinCustomerSessionBeanLocal.createCabin(cab5, 3l);

            Integer[] seatConfig6 = new Integer[2];
            seatConfig6[0] = 1;
            seatConfig6[1] = 1; 
            Cabin cab6 = new Cabin("F", 1, 5, seatConfig6);
            cabinCustomerSessionBeanLocal.createCabin(cab6, 4l);
            Integer[] seatConfig7 = new Integer[3];
            seatConfig7[0] = 2;
            seatConfig7[1] = 2; 
            seatConfig7[2] = 2; 
            Cabin cab7 = new Cabin("J", 2, 5, seatConfig7);
            cabinCustomerSessionBeanLocal.createCabin(cab7, 4l);
            Integer[] seatConfig8 = new Integer[3];
            seatConfig8[0] = 3;
            seatConfig8[1] = 4; 
            seatConfig8[2] = 3; 
            Cabin cab8 = new Cabin("Y", 2, 10, seatConfig8);
            cabinCustomerSessionBeanLocal.createCabin(cab8, 4l);
        }
    }

    private void loadFlightRoutes() throws AirportDoesNotExistException, FlightRouteAlreadyExistException {
        //===================LOAD FLIGHT ROUTE DATA========================
        if(em.find(FlightRoute.class, 1l) == null) {
            flightRoutesSessionBeanLocal.createNewFlightRouteWithReturn(1l, 2l);
            flightRoutesSessionBeanLocal.createNewFlightRouteWithReturn(1l, 3l);
            flightRoutesSessionBeanLocal.createNewFlightRouteWithReturn(1l, 4l);
            flightRoutesSessionBeanLocal.createNewFlightRouteWithReturn(2l, 4l);
            flightRoutesSessionBeanLocal.createNewFlightRouteWithReturn(3l, 4l);
            flightRoutesSessionBeanLocal.createNewFlightRouteWithReturn(5l, 1l);
            flightRoutesSessionBeanLocal.createNewFlightRouteWithReturn(5l, 4l);
        }
    }

    private void loadFlights() throws FlightRouteDisabledException, FlightRouteDoesNotExistException, AircraftConfigurationDoesNotExistException {
        //===================LOAD FLIGHT ROUTE DATA========================
        if(em.find(Flight.class, 1l) == null) {
            Long flightId = flightSessionBeanLocal.createNewFlight(new Flight(111), 1l, 2l);
            Long returnFlightId = flightSessionBeanLocal.createNewFlight(new Flight(112), 2l, 2l);
            flightSessionBeanLocal.setReturnFlight(flightId, returnFlightId);
            flightSessionBeanLocal.setReturnFlight(returnFlightId, flightId);  
            

            flightId = flightSessionBeanLocal.createNewFlight(new Flight(211), 3l, 2l);
            returnFlightId = flightSessionBeanLocal.createNewFlight(new Flight(212), 4l, 2l);
            flightSessionBeanLocal.setReturnFlight(flightId, returnFlightId);
            flightSessionBeanLocal.setReturnFlight(returnFlightId, flightId);  

            flightId = flightSessionBeanLocal.createNewFlight(new Flight(311), 5l, 4l);
            returnFlightId = flightSessionBeanLocal.createNewFlight(new Flight(312), 6l, 4l);
            flightSessionBeanLocal.setReturnFlight(flightId, returnFlightId);
            flightSessionBeanLocal.setReturnFlight(returnFlightId, flightId);  

            flightId = flightSessionBeanLocal.createNewFlight(new Flight(411), 7l, 2l);
            returnFlightId = flightSessionBeanLocal.createNewFlight(new Flight(412), 8l, 2l);
            flightSessionBeanLocal.setReturnFlight(flightId, returnFlightId);
            flightSessionBeanLocal.setReturnFlight(returnFlightId, flightId);  

            flightId = flightSessionBeanLocal.createNewFlight(new Flight(511), 9l, 2l);
            returnFlightId = flightSessionBeanLocal.createNewFlight(new Flight(512), 10l, 2l);
            flightSessionBeanLocal.setReturnFlight(flightId, returnFlightId);
            flightSessionBeanLocal.setReturnFlight(returnFlightId, flightId);  

            flightId = flightSessionBeanLocal.createNewFlight(new Flight(611), 11l, 2l);
            returnFlightId = flightSessionBeanLocal.createNewFlight(new Flight(612), 12l, 2l);
            flightSessionBeanLocal.setReturnFlight(flightId, returnFlightId);
            flightSessionBeanLocal.setReturnFlight(returnFlightId, flightId);  

            flightId = flightSessionBeanLocal.createNewFlight(new Flight(621), 11l, 1l);
            returnFlightId = flightSessionBeanLocal.createNewFlight(new Flight(622), 12l, 1l);
            flightSessionBeanLocal.setReturnFlight(flightId, returnFlightId);
            flightSessionBeanLocal.setReturnFlight(returnFlightId, flightId);  

            flightId = flightSessionBeanLocal.createNewFlight(new Flight(711), 13l, 4l);
            returnFlightId = flightSessionBeanLocal.createNewFlight(new Flight(712), 14l, 4l);
            flightSessionBeanLocal.setReturnFlight(flightId, returnFlightId);
            flightSessionBeanLocal.setReturnFlight(returnFlightId, flightId);  
        }
    }
    
    private void loadFlightSchedulePlan() throws ParseException, AircraftConfigurationDoesNotExistException, ConflictingFlightScheduleException, FlightDoesNotExistException {
        //===================LOAD FLIGHT SCHEDULE PLAN DATA========================
        if(em.find(FlightSchedulePlan.class, 1l) == null) {
            //ML711, Recurrent Weekly
            List<Long> flightScheduleIds = new ArrayList<>();
            List<Date> departureDates = new ArrayList<>();
            List<Double> durations = new ArrayList<>();
            List<Double> layovers = new ArrayList<>();
            double duration = 14;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date departureDateTime = dateFormat.parse(("2023-12-01 09:00"));
            Date endDate = dateFormat.parse("2023-12-31 09:00");
            double layover = 2;
            while(!departureDateTime.equals(endDate)) {
                departureDates.add(departureDateTime);
                durations.add(duration);
                layovers.add(layover);

                departureDateTime = new Date(departureDateTime.getTime() + (7 * 1000 * 60 * 60 * 24));

                if (endDate.before(departureDateTime)) {
                    break;
                }
            }
            Integer flightNumber = 711;
            Integer returnFlightNumber = 712;
            FlightSchedule flightSchedule = new FlightSchedule();
            for (int i = 0; i < departureDates.size(); i++) { 
                Date departureDate = departureDates.get(i);
                flightSchedule = flightScheduleSessionBeanLocal.createNewFlightSchedule(flightNumber, new FlightSchedule(departureDate, duration, layover));
                flightScheduleIds.add(flightSchedule.getFlightScheduleId());
                Instant instant = flightSchedule.getArrivalDateTime().toInstant();
                Date returnDepartureDateTime =  Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
                flightSchedule = flightScheduleSessionBeanLocal.createNewFlightSchedule(returnFlightNumber, new FlightSchedule(returnDepartureDateTime, duration, layover));
                flightScheduleIds.add(flightSchedule.getFlightScheduleId());   
            }
            
            FlightSchedulePlan flightSchedulePlan = new FlightSchedulePlan(flightNumber);
            List<Cabin> cabins = flightSchedule.getListOfCabins();
            cabins.size();
            Long flightSchedulePlanId = flightSchedulePlanSessionBeanLocal.createMultipleFlightSchedulePlan(flightSchedulePlan, flightScheduleIds);
            for (int i = 0; i < cabins.size(); i++) {
                if (i == 0) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(6000));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
                if (i == 1) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(300));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
                
                if (i == 2) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(1000));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
            }
            
            //ML611, Recurrent Weekly
            flightScheduleIds = new ArrayList<>();
            departureDates = new ArrayList<>();
            durations = new ArrayList<>();
            layovers = new ArrayList<>();
            duration = 8;
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            departureDateTime = dateFormat.parse(("2023-12-01 12:00"));
            endDate = dateFormat.parse("2023-12-31 12:00");
            layover = 2;
            while(!departureDateTime.equals(endDate)) {
                departureDates.add(departureDateTime);
                durations.add(duration);
                layovers.add(layover);

                departureDateTime = new Date(departureDateTime.getTime() + (7 * 1000 * 60 * 60 * 24));

                if (endDate.before(departureDateTime)) {
                    break;
                }
            }
            flightNumber = 611;
            returnFlightNumber = 612;
            flightSchedule = new FlightSchedule();
            for (int i = 0; i < departureDates.size(); i++) { 
                Date departureDate = departureDates.get(i);
                flightSchedule = flightScheduleSessionBeanLocal.createNewFlightSchedule(flightNumber, new FlightSchedule(departureDate, duration, layover));
                flightScheduleIds.add(flightSchedule.getFlightScheduleId());
                Instant instant = flightSchedule.getArrivalDateTime().toInstant();
                Date returnDepartureDateTime =  Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
                flightSchedule = flightScheduleSessionBeanLocal.createNewFlightSchedule(returnFlightNumber, new FlightSchedule(returnDepartureDateTime, duration, layover));
                flightScheduleIds.add(flightSchedule.getFlightScheduleId());   
            }
            
            flightSchedulePlan = new FlightSchedulePlan(flightNumber);
            cabins = flightSchedule.getListOfCabins();
            cabins.size();
            flightSchedulePlanId = flightSchedulePlanSessionBeanLocal.createMultipleFlightSchedulePlan(flightSchedulePlan, flightScheduleIds);
            for (int i = 0; i < cabins.size(); i++) {
                if (i == 0) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(3000));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
                if (i == 1) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(1500));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
                
                if (i == 2) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(500));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
            }
            
            
            //ML621, Recurrent Weekly
            flightScheduleIds = new ArrayList<>();
            departureDates = new ArrayList<>();
            durations = new ArrayList<>();
            layovers = new ArrayList<>();
            duration = 8;
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            departureDateTime = dateFormat.parse(("2023-12-01 10:00"));
            endDate = dateFormat.parse("2023-12-31 10:00");
            layover = 2;
            while(!departureDateTime.equals(endDate)) {
                departureDates.add(departureDateTime);
                durations.add(duration);
                layovers.add(layover);

                departureDateTime = new Date(departureDateTime.getTime() + (7 * 1000 * 60 * 60 * 24));

                if (endDate.before(departureDateTime)) {
                    break;
                }
            }
            flightNumber = 621;
            returnFlightNumber = 622;
            flightSchedule = new FlightSchedule();
            for (int i = 0; i < departureDates.size(); i++) { 
                Date departureDate = departureDates.get(i);
                flightSchedule = flightScheduleSessionBeanLocal.createNewFlightSchedule(flightNumber, new FlightSchedule(departureDate, duration, layover));
                flightScheduleIds.add(flightSchedule.getFlightScheduleId());
                Instant instant = flightSchedule.getArrivalDateTime().toInstant();
                Date returnDepartureDateTime =  Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
                flightSchedule = flightScheduleSessionBeanLocal.createNewFlightSchedule(returnFlightNumber, new FlightSchedule(returnDepartureDateTime, duration, layover));
                flightScheduleIds.add(flightSchedule.getFlightScheduleId());   
            }
            
            flightSchedulePlan = new FlightSchedulePlan(flightNumber);
            cabins = flightSchedule.getListOfCabins();
            cabins.size();
            flightSchedulePlanId = flightSchedulePlanSessionBeanLocal.createMultipleFlightSchedulePlan(flightSchedulePlan, flightScheduleIds);
            for (int i = 0; i < cabins.size(); i++) {
                if (i == 0) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(700));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
            }
            
            //ML311, Recurrent Weekly
            flightScheduleIds = new ArrayList<>();
            departureDates = new ArrayList<>();
            durations = new ArrayList<>();
            layovers = new ArrayList<>();
            duration = 6.5;
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            departureDateTime = dateFormat.parse(("2023-12-01 10:00"));
            endDate = dateFormat.parse("2023-12-31 10:00");
            layover = 3;
            while(!departureDateTime.equals(endDate)) {
                departureDates.add(departureDateTime);
                durations.add(duration);
                layovers.add(layover);

                departureDateTime = new Date(departureDateTime.getTime() + (7 * 1000 * 60 * 60 * 24));

                if (endDate.before(departureDateTime)) {
                    break;
                }
            }
            flightNumber = 311;
            returnFlightNumber = 312;
            flightSchedule = new FlightSchedule();
            for (int i = 0; i < departureDates.size(); i++) { 
                Date departureDate = departureDates.get(i);
                flightSchedule = flightScheduleSessionBeanLocal.createNewFlightSchedule(flightNumber, new FlightSchedule(departureDate, duration, layover));
                flightScheduleIds.add(flightSchedule.getFlightScheduleId());
                Instant instant = flightSchedule.getArrivalDateTime().toInstant();
                Date returnDepartureDateTime =  Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
                flightSchedule = flightScheduleSessionBeanLocal.createNewFlightSchedule(returnFlightNumber, new FlightSchedule(returnDepartureDateTime, duration, layover));
                flightScheduleIds.add(flightSchedule.getFlightScheduleId());   
            }
            
            flightSchedulePlan = new FlightSchedulePlan(flightNumber);
            cabins = flightSchedule.getListOfCabins();
            cabins.size();
            flightSchedulePlanId = flightSchedulePlanSessionBeanLocal.createMultipleFlightSchedulePlan(flightSchedulePlan, flightScheduleIds);
            for (int i = 0; i < cabins.size(); i++) {
                if (i == 0) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(3100));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
                if (i == 1) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(1600));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
                
                if (i == 2) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(600));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
            }
            
            //ML411, Recurrent Every 2 days
            flightScheduleIds = new ArrayList<>();
            departureDates = new ArrayList<>();
            durations = new ArrayList<>();
            layovers = new ArrayList<>();
            duration = 4;
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            departureDateTime = dateFormat.parse(("2023-12-01 13:00"));
            endDate = dateFormat.parse("2023-12-31 13:00");
            layover = 4;
            int days = 2;
            while(!departureDateTime.equals(endDate) || endDate.after(departureDateTime)) {
                departureDates.add(departureDateTime);
                durations.add(duration);
                layovers.add(layover);

                departureDateTime = new Date(departureDateTime.getTime() + (days * 1000 * 60 * 60 * 24));

                if (endDate.before(departureDateTime)) {
                    break;
                }
            }
            flightNumber = 411;
            returnFlightNumber = 412;
            flightSchedule = new FlightSchedule();
            for (int i = 0; i < departureDates.size(); i++) { 
                Date departureDate = departureDates.get(i);
                flightSchedule = flightScheduleSessionBeanLocal.createNewFlightSchedule(flightNumber, new FlightSchedule(departureDate, duration, layover));
                flightScheduleIds.add(flightSchedule.getFlightScheduleId());
                Instant instant = flightSchedule.getArrivalDateTime().toInstant();
                Date returnDepartureDateTime =  Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
                flightSchedule = flightScheduleSessionBeanLocal.createNewFlightSchedule(returnFlightNumber, new FlightSchedule(returnDepartureDateTime, duration, layover));
                flightScheduleIds.add(flightSchedule.getFlightScheduleId());   
            }
            
            flightSchedulePlan = new FlightSchedulePlan(flightNumber);
            cabins = flightSchedule.getListOfCabins();
            cabins.size();
            flightSchedulePlanId = flightSchedulePlanSessionBeanLocal.createMultipleFlightSchedulePlan(flightSchedulePlan, flightScheduleIds);
            for (int i = 0; i < cabins.size(); i++) {
                if (i == 0) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(2900));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
                if (i == 1) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(1400));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
                
                if (i == 2) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(400));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
            }
            
            //ML511, Manual Multiple
            flightScheduleIds = new ArrayList<>();
            departureDates = new ArrayList<>();
            durations = new ArrayList<>();
            layovers = new ArrayList<>();
            duration = 3;
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            departureDateTime = dateFormat.parse(("2023-12-07 17:00"));
            departureDates.add(departureDateTime);
            departureDateTime = dateFormat.parse(("2023-12-08 17:00"));
            departureDates.add(departureDateTime);
            departureDateTime = dateFormat.parse(("2023-12-09 17:00"));
            departureDates.add(departureDateTime);
            layover = 2;
            durations.add(duration);
            durations.add(duration);
            durations.add(duration);
            layovers.add(layover);
            layovers.add(layover);
            layovers.add(layover);
            
            flightNumber = 511;
            returnFlightNumber = 512;
            flightSchedule = new FlightSchedule();
            for (int i = 0; i < departureDates.size(); i++) { 
                Date departureDate = departureDates.get(i);
                flightSchedule = flightScheduleSessionBeanLocal.createNewFlightSchedule(flightNumber, new FlightSchedule(departureDate, duration, layover));
                flightScheduleIds.add(flightSchedule.getFlightScheduleId());
                Instant instant = flightSchedule.getArrivalDateTime().toInstant();
                Date returnDepartureDateTime =  Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
                flightSchedule = flightScheduleSessionBeanLocal.createNewFlightSchedule(returnFlightNumber, new FlightSchedule(returnDepartureDateTime, duration, layover));
                flightScheduleIds.add(flightSchedule.getFlightScheduleId());   
            }
            
            flightSchedulePlan = new FlightSchedulePlan(flightNumber);
            cabins = flightSchedule.getListOfCabins();
            cabins.size();
            flightSchedulePlanId = flightSchedulePlanSessionBeanLocal.createMultipleFlightSchedulePlan(flightSchedulePlan, flightScheduleIds);
            for (int i = 0; i < cabins.size(); i++) {
                if (i == 0) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(3100));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
                if (i == 1) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(1600));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
                
                if (i == 2) {
                    ArrayList<String> fareBasisCodes = new ArrayList<>();
                    fareBasisCodes.add("BLANK");
                    ArrayList<BigDecimal> fareAmounts = new ArrayList<>();
                    fareAmounts.add(BigDecimal.valueOf(600));
                    flightSchedulePlanSessionBeanLocal.createFare(flightSchedulePlanId, cabins.get(i).getCabinId(), fareBasisCodes, fareAmounts);
                }
            }
        }
    }
}
