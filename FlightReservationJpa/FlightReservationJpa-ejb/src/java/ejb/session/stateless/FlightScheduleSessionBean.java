/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.time.Duration;
import java.time.Instant;
import entity.Cabin;
import entity.Customer;
import entity.Fare;
import entity.Partner;
import entity.ReservationDetails;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.ConflictingFlightScheduleException;
import util.exception.FlightScheduleDoesNotExistException;
import javax.persistence.TypedQuery;
import util.exception.AircraftConfigurationDoesNotExistException;
import util.exception.FlightDoesNotExistException;
import util.exception.FlightScheduleBookedException;

/**
 *
 * @author admin
 */
@Stateless
public class FlightScheduleSessionBean implements FlightScheduleSessionBeanRemote, FlightScheduleSessionBeanLocal {

    @EJB(name = "CabinCustomerSessionBeanLocal")
    private CabinCustomerSessionBeanLocal cabinCustomerSessionBeanLocal;    

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;
  
     
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void checkForConflictingFlights(Integer flightNumber, Date departureDate, Duration duration, Duration layover) throws ConflictingFlightScheduleException {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight)query.getSingleResult();

        List<Date> bookedDates = flight.getBookedDates();
        bookedDates.size();
        
        Instant instant = departureDate.toInstant();
        Date arrivalDateTime = Date.from(instant.plus(duration));
        instant = arrivalDateTime.toInstant();
        Date arrivalWithLayover = Date.from(instant.plus(layover));
            
        for (int j = 1; j < bookedDates.size() - 1; j += 2) {
            if (bookedDates.get(j).after(departureDate)) {
                throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
            } else if(departureDate.after(bookedDates.get(j)) && bookedDates.get(j + 1).before(departureDate)) {
                if (arrivalWithLayover.after(bookedDates.get(j + 1))) {
                        throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
                } else {
                    break;
                }
            }
        }
    }
    
    @Override
    public void checkForConflictingFlights(Integer flightNumber, List<Date> departureDates, List<Duration> durations, List<Duration> layovers, Boolean haveReturn) throws ConflictingFlightScheduleException {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight)query.getSingleResult();

        List<Date> bookedDates = flight.getBookedDates();
        bookedDates.size();
        
       
        for (int i = 0; i < departureDates.size(); i++) { 
            Date departureDateTime = departureDates.get(i);
            Duration duration = durations.get(i);
            Duration layover = layovers.get(i);
            
            Instant instant = departureDateTime.toInstant();
            Date arrivalDateTime = Date.from(instant.plus(duration));
            instant = arrivalDateTime.toInstant();
            Date arrivalWithLayover = Date.from(instant.plus(layover));
            
            for (int j = 1; j < bookedDates.size() - 1; j += 2) {
                if (bookedDates.get(j).after(departureDateTime)) {
                    throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
                } else if(bookedDates.get(j).after(departureDateTime) && departureDateTime.before(bookedDates.get(j + 1))) {
                    if (arrivalWithLayover.after(bookedDates.get(j + 1))) {
                        throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
                    } else {
                        break;
                    }
                }
            }
            
            if (haveReturn) {
                Query secondQuery = em.createQuery("SELECT f FROM Flight f WHERE f.flightRoute.origin = :origin AND f.flightRoute.destination = :destination");
                secondQuery.setParameter("origin", flight.getFlightRoute().getDestination());
                secondQuery.setParameter("destination", flight.getFlightRoute().getOrigin());
                Flight returnFlight = (Flight) secondQuery.getSingleResult();
                List<Date> bookedDatesReturn  = returnFlight.getBookedDates();
                bookedDatesReturn.size();
                
                instant = arrivalWithLayover.toInstant();
                Date arrivalDateTimeReturn = Date.from(instant.plus(duration));
                instant = arrivalDateTimeReturn.toInstant();
                Date arrivalReturnWithLayover = Date.from(instant.plus(layover));
                
                for (int j = 1; j < bookedDatesReturn.size() - 1; j += 2) {
                    if (bookedDatesReturn.get(j).after(departureDateTime)) {
                        throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
                    } else if(bookedDatesReturn.get(j).after(arrivalWithLayover) && arrivalWithLayover.before(bookedDatesReturn.get(j + 1))) {
                        if (arrivalReturnWithLayover.after(bookedDatesReturn.get(j + 1))) {
                            throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
                        } else {
                            break;
                        }
                    }
                }
            }
        } 
    }
    
    @Override
    public void checkForConflictingFlights(Integer flightNumber, List<Date> departureDates, List<Duration> durations, List<Duration> layovers, List<String> haveReturns) throws ConflictingFlightScheduleException {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight)query.getSingleResult();

        List<Date> bookedDates = flight.getBookedDates();
        bookedDates.size();
        
       
        for (int i = 0; i < departureDates.size(); i++) { 
            Date departureDateTime = departureDates.get(i);
            Duration duration = durations.get(i);
            Duration layover = layovers.get(i);
            String haveReturn = haveReturns.get(i);
            
            Instant instant = departureDateTime.toInstant();
            Date arrivalDateTime = Date.from(instant.plus(duration));
            instant = arrivalDateTime.toInstant();
            Date arrivalWithLayover = Date.from(instant.plus(layover));
            
            for (int j = 1; j < bookedDates.size() - 1; j += 2) {
                if (bookedDates.get(j).after(departureDateTime)) {
                    throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
                } else if(bookedDates.get(j).after(departureDateTime) && departureDateTime.before(bookedDates.get(j + 1))) {
                    if (arrivalWithLayover.after(bookedDates.get(j + 1))) {
                        throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
                    } else {
                        break;
                    }
                }
            }
            
            if (haveReturn.equals("Y")) {
                Query secondQuery = em.createQuery("SELECT f FROM Flight f WHERE f.flightRoute.origin = :origin AND f.flightRoute.destination = :destination");
                secondQuery.setParameter("origin", flight.getFlightRoute().getDestination());
                secondQuery.setParameter("destination", flight.getFlightRoute().getOrigin());
                Flight returnFlight = (Flight) secondQuery.getSingleResult();
                List<Date> bookedDatesReturn = returnFlight.getBookedDates();
                bookedDatesReturn.size();
                
                instant = arrivalWithLayover.toInstant();
                Date arrivalDateTimeReturn = Date.from(instant.plus(duration));
                instant = arrivalDateTimeReturn.toInstant();
                Date arrivalReturnWithLayover = Date.from(instant.plus(layover));
                
                for (int j = 1; j < bookedDatesReturn.size() - 1; j += 2) {
                    if (bookedDatesReturn.get(j).after(arrivalWithLayover)) {
                        throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
                    } else if(bookedDatesReturn.get(j).after(arrivalWithLayover) && arrivalWithLayover.before(bookedDatesReturn.get(j + 1))) {
                        if (arrivalReturnWithLayover.after(bookedDatesReturn.get(j + 1))) {
                            throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
                        } else {
                            break;
                        }
                    }
                }
            }
        } 
    }
    
    @Override
    public FlightSchedule createNewFlightSchedule(Integer flightNumber, FlightSchedule flightSchedule) throws AircraftConfigurationDoesNotExistException, ConflictingFlightScheduleException {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight)query.getSingleResult();
        long aircraftConfigId = flight.getAircraftConfig().getAircraftConfigurationId();
        //set cabin       
        //duplicate cabins, if not now u are juz linking to the other cabins
        List<Cabin> listOfCabins = new ArrayList<>(flight.getAircraftConfig().getListOfCabins());
        listOfCabins.size();
        List<Cabin> newCabins = new ArrayList<>();
        for (Cabin c : listOfCabins) { 
            //List<Fare> listOfFares = c.getListOfFare();
            Cabin newCab = new Cabin(c.getCabinClassName(), c.getNumOfIsles(), c.getNumOfRows(), c.getSeatingConfiguration());
            newCab.setAircraftConfiguration(null);
            //newCab.setListOfFare(listOfFares);
            Cabin cab = cabinCustomerSessionBeanLocal.createCabinOnly(newCab);
            newCabins.add(cab);
        }

        flightSchedule.setListOfCabins(newCabins);

        
        flightSchedule.setListOfCabins(newCabins);
            
        Date departureDateTime = flightSchedule.getDepartureDateTime();
        List<Date> bookedDates = flight.getBookedDates();
        bookedDates.size();
        
        bookedDates.add(departureDateTime);
        bookedDates.add(flightSchedule.getArrivalDateTime());
        flight.setBookedDates(bookedDates);

        em.persist(flightSchedule);
        em.flush();
        
        return flightSchedule;
    }
    
    @Override
    public List<FlightSchedule> getFlightSchedulesWithId(Long id) throws FlightScheduleDoesNotExistException {
        try {
            FlightSchedulePlan flightSchedulePlan = em.find(FlightSchedulePlan.class, id);
            Query query = em.createQuery("Select f from FlightSchedule f WHERE f.flightSchedulePlan = :flightSchedulePlan");
            query.setParameter("flightSchedulePlan", flightSchedulePlan);
            List<FlightSchedule> f = (List<FlightSchedule>)query.getResultList();
            return f;
        } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
        }
    }
    
    @Override
    public Long changeFlightScheduleDateTime(Long flightScheduleId, Date departureDateTime, Duration duration) throws FlightScheduleDoesNotExistException {
        try {
            FlightSchedule flightSchedule = em.find(FlightSchedule.class, flightScheduleId);
            flightSchedule.setDepartureDateTime(departureDateTime);
            flightSchedule.setEstimatedTime(duration);
            
            Instant instant = departureDateTime.toInstant();
            Date arrivalDateTime = Date.from(instant.plus(duration));
            flightSchedule.setArrivalDateTime(arrivalDateTime);
            
            return flightSchedule.getFlightScheduleId();
        } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
        }
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightScheduleInPlan(List<FlightSchedulePlan> listOfFlightSchedulePlan) {
        List<Long> flightSchedNumbers = new ArrayList<>();

        for (FlightSchedulePlan flightSchedPlan : listOfFlightSchedulePlan) {
            flightSchedNumbers.add(flightSchedPlan.getFlightSchedulePlanId());
        }

        if (flightSchedNumbers.isEmpty()) {
            return Collections.emptyList();
        }

        Query query = em.createQuery("SELECT f FROM FlightSchedule f WHERE f.flightSchedulePlan.flightSchedulePlanId IN :flightSchedNumbers");
        query.setParameter("flightSchedNumbers", flightSchedNumbers);

        return query.getResultList();
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWithSameTiming(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate) {
        List<FlightSchedule> listOfFlightSchedules = retrieveFlightScheduleInPlan(listOfFlightSchedulePlan);

        List<FlightSchedule> newList = new ArrayList<>();

        for (FlightSchedule flightSchedule : listOfFlightSchedules) {
            Date departureDateTime = flightSchedule.getDepartureDateTime();
            LocalDate localDate = departureDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (localDate.isEqual(departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                newList.add(flightSchedule);
            }
        }

        return newList;
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWithSameTiming(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate, Boolean detach) {
        List<FlightSchedule> listOfFlightSchedules = retrieveFlightScheduleInPlan(listOfFlightSchedulePlan);

        List<FlightSchedule> newList = new ArrayList<>();

        for (FlightSchedule flightSchedule : listOfFlightSchedules) {
            Date departureDateTime = flightSchedule.getDepartureDateTime();
            LocalDate localDate = departureDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (localDate.isEqual(departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                newList.add(flightSchedule);
            }
        }
        
        for (FlightSchedule fs : newList) {
            em.detach(fs);
            em.detach(fs.getFlightSchedulePlan());
            for (Cabin c : fs.getListOfCabins()) {
                em.detach(c);
            }
            for (Customer cust : fs.getCustomers()) {
                em.detach(cust);
            }
            for (ReservationDetails rd : fs.getListOfReservationDetails()) {
                em.detach(rd);
            }
        }

        return newList;
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysBefore(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate) {
        List<FlightSchedule> listOfFlightSchedules = retrieveFlightScheduleInPlan(listOfFlightSchedulePlan);

        List<FlightSchedule> newList = new ArrayList<>();

        // Calculate the date that is 3 days before departureDate
        LocalDate threeDaysBeforeDate = departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays(4);

        for (FlightSchedule flightSchedule : listOfFlightSchedules) {
            Date departureDateTime = flightSchedule.getDepartureDateTime();
            LocalDate localDate = departureDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (localDate.isAfter(threeDaysBeforeDate) && localDate.isBefore(threeDaysBeforeDate.plusDays(4))) {
                newList.add(flightSchedule);
            }
        }

        return newList;
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWith1DayAfter(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate) {
        List<FlightSchedule> listOfFlightSchedules = retrieveFlightScheduleInPlan(listOfFlightSchedulePlan);

        List<FlightSchedule> newList = new ArrayList<>();

        // Calculate the date that is 1 day after departureDate
        LocalDate threeDaysAfterDate = departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(2);

        for (FlightSchedule flightSchedule : listOfFlightSchedules) {
            Date departureDateTime = flightSchedule.getDepartureDateTime();
            LocalDate localDate = departureDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (localDate.isBefore(threeDaysAfterDate) && localDate.isAfter(threeDaysAfterDate.minusDays(2))) {
                newList.add(flightSchedule);
            }
        }

        return newList;
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysAfter(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate) {
        List<FlightSchedule> listOfFlightSchedules = retrieveFlightScheduleInPlan(listOfFlightSchedulePlan);

        List<FlightSchedule> newList = new ArrayList<>();

        // Calculate the date that is 1 day after departureDate
        LocalDate threeDaysAfterDate = departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(4);

        for (FlightSchedule flightSchedule : listOfFlightSchedules) {
            Date departureDateTime = flightSchedule.getDepartureDateTime();
            LocalDate localDate = departureDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (localDate.isBefore(threeDaysAfterDate) && localDate.isAfter(threeDaysAfterDate.minusDays(4))) {
                newList.add(flightSchedule);
            }
        }

        return newList;
    }
    
    @Override
    public Date retrieveDateOfFlightPicked(Long id) throws FlightDoesNotExistException {
        FlightSchedule flightSchedule = em.find(FlightSchedule.class, id);
        //add buffer?
        return flightSchedule.getArrivalDateTime();
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanAfterTiming(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDateTime) {
        List<FlightSchedule> listOfFlightSchedules = retrieveFlightScheduleInPlan(listOfFlightSchedulePlan);

        List<FlightSchedule> newList = new ArrayList<>();

        LocalDate desiredDate = departureDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime desiredTime = departureDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

        for (FlightSchedule flightSchedule : listOfFlightSchedules) {
            Date scheduleDepartureDateTime = flightSchedule.getDepartureDateTime();
            LocalDate localDate = scheduleDepartureDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime localTime = scheduleDepartureDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

            if (localDate.isEqual(desiredDate) && localTime.isAfter(desiredTime)) {
                newList.add(flightSchedule);
            }
        }

        return newList;
    }

    @Override
    public FlightSchedule getFlightScheduleWithId(long id) throws FlightScheduleDoesNotExistException {
        try {
            return em.find(FlightSchedule.class, id);
        } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
        }
    }
    
    @Override
    public List<Cabin> getCabins(long id) throws FlightScheduleDoesNotExistException {
        try {
            FlightSchedule fs = getFlightScheduleWithId(id);
            List<Cabin> listOfCabins = fs.getListOfCabins();
            listOfCabins.size();

            return listOfCabins;
        } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
        }
    }
    
    @Override
    public List<Cabin> getCabins(long id, Boolean detach) throws FlightScheduleDoesNotExistException {
        try {
            FlightSchedule fs = getFlightScheduleWithId(id);
            List<Cabin> listOfCabins = fs.getListOfCabins();
            listOfCabins.size();
            for (Cabin c : listOfCabins) {
                for (Fare f : c.getListOfFare()) {
                    em.detach(f);
                }
                em.detach(c);
            }
            return listOfCabins;
        } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
        }
    }
    
    @Override
    public char[][] getCabinSeats(long id, String cabName) throws FlightScheduleDoesNotExistException {
       try { 
       List<Cabin> cabins = getCabins(id);
       
       for (Cabin c : cabins) {
           if (c.getCabinClassName().equalsIgnoreCase(cabName)) {
               return c.getSeatingPlan();
           }
       }
       
       return null;
       } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
       }
    }
    
    
    @Override
    public List<List<Character>> getCabinSeatsList(long id, String cabName) throws FlightScheduleDoesNotExistException {
        try {
            List<Cabin> cabins = getCabins(id);

            for (Cabin c : cabins) {
                if (c.getCabinClassName().equalsIgnoreCase(cabName)) {
                    return convertCharArrayToList(c.getSeatingPlan());
                }
            }

            return null;
        } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
        }
    }

    private List<List<Character>> convertCharArrayToList(char[][] charArray) {
        List<List<Character>> result = new ArrayList<>();

        for (char[] row : charArray) {
            List<Character> charList = new ArrayList<>();
            for (char c : row) {
                charList.add(c);
            }
            result.add(charList);
        }

        return result;
    }


    @Override
    public Integer[] getIslesPlan(long id, String cabName) throws FlightScheduleDoesNotExistException {
       try {
            List<Cabin> cabins = getCabins(id);

            for (Cabin c : cabins) {
                if (c.getCabinClassName().equalsIgnoreCase(cabName)) {
                    return c.getSeatingConfiguration();
                }
            }

            return null;
       } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
       }
    }
    
    @Override
    public long bookSeat(long id, String cabName, int seat, char letter) throws FlightScheduleDoesNotExistException {
        try {
            List<Cabin> cabins = getCabins(id);

            for (Cabin c : cabins) {
                if (c.getCabinClassName().equalsIgnoreCase(cabName)) {
                    c.bookSeat(seat, letter);
                }
            }

            return id;
        } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
       }
    }
    
    @Override
    public long getLowestFareUsingCabinName(String cabName, long id) throws FlightScheduleDoesNotExistException {
        try {
            List<Cabin> cabins = getCabins(id);
            long lowestFareId = -1;

            for (Cabin c : cabins) {
                if (c.getCabinClassName().equalsIgnoreCase(cabName)) {
                    lowestFareId = cabinCustomerSessionBeanLocal.getLowestFareIdInCabin(c.getCabinId());
                }
            }

            return lowestFareId;
       } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
       }
    }
    
    @Override
    public long getHighestFareUsingCabinName(String cabName, long id) throws FlightScheduleDoesNotExistException {
        try {
            List<Cabin> cabins = getCabins(id);
            long lowestFareId = -1;

            for (Cabin c : cabins) {
                if (c.getCabinClassName().equalsIgnoreCase(cabName)) {
                    lowestFareId = cabinCustomerSessionBeanLocal.getHighestFareIdInCabin(c.getCabinId());
                }
            }

            return lowestFareId;
       } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
       }
    }
    
    @Override
    public List<ReservationDetails> getReservationDetails(long flightScheduleId, long customerId) throws  FlightScheduleDoesNotExistException {
        try {
            String jpql = "SELECT fs FROM FlightSchedule fs LEFT JOIN FETCH fs.listOfReservationDetails WHERE fs.flightScheduleId = :flightScheduleId";
            TypedQuery<FlightSchedule> query = em.createQuery(jpql, FlightSchedule.class);
            query.setParameter("flightScheduleId", flightScheduleId);

            FlightSchedule fs = query.getSingleResult(); 

            Customer cust = em.find(Customer.class, customerId);

            List<ReservationDetails> newDetails = new ArrayList<>();
            List<ReservationDetails> listOfResDetails = fs.getListOfReservationDetails();
            for (ReservationDetails rd : listOfResDetails) {
                if (Objects.equals(rd.getCustomer().getAccountId(), cust.getAccountId())) {
                    newDetails.add(rd);
                }
            }

            return newDetails;
        }   catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
       }
    }
    
    @Override
    public List<ReservationDetails> getReservationDetailsPartner(long flightScheduleId, long partnerId) throws  FlightScheduleDoesNotExistException {
        try {
            String jpql = "SELECT fs FROM FlightSchedule fs LEFT JOIN FETCH fs.listOfReservationDetails WHERE fs.flightScheduleId = :flightScheduleId";
            TypedQuery<FlightSchedule> query = em.createQuery(jpql, FlightSchedule.class);
            query.setParameter("flightScheduleId", flightScheduleId);

            FlightSchedule fs = query.getSingleResult(); 

            Partner partner = em.find(Partner.class, partnerId);

            List<ReservationDetails> newDetails = new ArrayList<>();
            List<ReservationDetails> listOfResDetails = fs.getListOfReservationDetails();
            for (ReservationDetails rd : listOfResDetails) {
                if (Objects.equals(rd.getPartner().getAccountId(), partner.getAccountId())) {
                    newDetails.add(rd);
                }
            }

            return newDetails;
        }   catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
       }
    }

    
    @Override
    public Long deleteFlightSchedule(Long flightScheduleId) throws FlightScheduleBookedException {
        FlightSchedule flightSchedule = em.find(FlightSchedule.class, flightScheduleId);
        
        List<ReservationDetails> reservationDetails = flightSchedule.getListOfReservationDetails();
        reservationDetails.size();
        
        if (reservationDetails.size() == 0) {
            em.remove(flightSchedule);
        } else {
            throw new FlightScheduleBookedException("This Flight Schedule has bookings and cannot be deleted!");
        }
        
        return flightSchedule.getFlightScheduleId();
    }
    
    @Override
    public Boolean checkSeatIfAvailable(long flightSchedId, String cabinName, int rowNum, char seat) {
        FlightSchedule flightSchedule = em.find(FlightSchedule.class, flightSchedId);
        List<Cabin> listOfCabins = flightSchedule.getListOfCabins();
        listOfCabins.size();
        for (Cabin c : listOfCabins) {
            if (c.getCabinClassName().equalsIgnoreCase(cabinName)) {     
                char[][] seatingPlan = c.getSeatingPlan();
                int seatNum = seat - 65;
                if (seatingPlan[rowNum - 1][seatNum] == 'X') {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
