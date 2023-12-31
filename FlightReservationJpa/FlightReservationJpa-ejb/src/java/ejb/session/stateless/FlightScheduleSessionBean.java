/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Airport;
import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.time.Instant;
import entity.Cabin;
import entity.Customer;
import entity.Fare;
import entity.Partner;
import entity.ReservationDetails;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebParam;
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

    @EJB(name = "FlightSchedulePlanSessionBeanLocal")
    private FlightSchedulePlanSessionBeanLocal flightSchedulePlanSessionBeanLocal;

    @EJB(name = "FlightSessionBeanLocal")
    private FlightSessionBeanLocal flightSessionBeanLocal;

    @EJB(name = "CabinCustomerSessionBeanLocal")
    private CabinCustomerSessionBeanLocal cabinCustomerSessionBeanLocal;    

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;
    
    
  
     
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void checkForConflictingFlights(Integer flightNumber, Date departureDate, double duration, double layover) throws ConflictingFlightScheduleException {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight)query.getSingleResult();

        List<Date> bookedDates = flight.getBookedDates();
        bookedDates.size();
        
        long hours = (long) (int) duration;
        long minutes = (long) ((duration - hours) * 60);
        Instant instant = departureDate.toInstant();
        Instant instantHours = instant.plus(hours, ChronoUnit.HOURS);

        Date arrivalDateTime = Date.from(instantHours.plus(minutes, ChronoUnit.MINUTES));
        instant = arrivalDateTime.toInstant();
        Date arrivalWithLayover = Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
            
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
    public void checkForConflictingFlights(Integer flightNumber, List<Date> departureDates, List<Double> durations, List<Double> layovers, Boolean haveReturn) throws ConflictingFlightScheduleException {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight)query.getSingleResult();

        List<Date> bookedDates = flight.getBookedDates();
        bookedDates.size();
        
       
        for (int i = 0; i < departureDates.size(); i++) { 
            Date departureDateTime = departureDates.get(i);
            double duration = durations.get(i);
            double layover = layovers.get(i);

            long hours = (long) (int) duration;
            long minutes = (long) ((duration - hours) * 60);
            Instant instant = departureDateTime.toInstant();
            Instant instantHours = instant.plus(hours, ChronoUnit.HOURS);
            
            Date arrivalDateTime = Date.from(instantHours.plus(minutes, ChronoUnit.MINUTES));
            instant = arrivalDateTime.toInstant();
            Date arrivalWithLayover = Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
            
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
                Integer returnFlightNumber = flight.getReturnFlightNumber();
                Query secondQuery = em.createQuery("SELECT f FROM Flight f WHERE f.returnFlightNumber = :returnFlightNumber");
                secondQuery.setParameter("returnFlightNumber", returnFlightNumber);
                Flight returnFlight = (Flight) secondQuery.getSingleResult();
                List<Date> bookedDatesReturn  = returnFlight.getBookedDates();
                bookedDatesReturn.size();
                
                instant = arrivalWithLayover.toInstant();
                instantHours = instant.plus(hours, ChronoUnit.HOURS);

                Date arrivalDateTimeReturn = Date.from(instantHours.plus(minutes, ChronoUnit.MINUTES));
                instant = arrivalDateTimeReturn.toInstant();
                Date arrivalReturnWithLayover = Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
                
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
    public void checkForConflictingFlights(Integer flightNumber, List<Date> departureDates, List<Double> durations, List<Double> layovers, List<String> haveReturns) throws ConflictingFlightScheduleException {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight)query.getSingleResult();

        List<Date> bookedDates = flight.getBookedDates();
        bookedDates.size();
        
       
        for (int i = 0; i < departureDates.size(); i++) { 
            Date departureDateTime = departureDates.get(i);
            double duration = durations.get(i);
            double layover = layovers.get(i);
            String haveReturn = haveReturns.get(i);
            
            long hours = (long) (int) duration;
            long minutes = (long) ((duration - hours) * 60);
            Instant instant = departureDateTime.toInstant();
            Instant instantHours = instant.plus(hours, ChronoUnit.HOURS);
            
            Date arrivalDateTime = Date.from(instantHours.plus(minutes, ChronoUnit.MINUTES));
            instant = arrivalDateTime.toInstant();
            Date arrivalWithLayover = Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
            
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
                Integer returnFlightNumber = flight.getReturnFlightNumber();
                Query secondQuery = em.createQuery("SELECT f FROM Flight f WHERE f.returnFlightNumber = :returnFlightNumber");
                secondQuery.setParameter("returnFlightNumber", returnFlightNumber);
                Flight returnFlight = (Flight) secondQuery.getSingleResult();
                List<Date> bookedDatesReturn = returnFlight.getBookedDates();
                bookedDatesReturn.size();
                
                instant = arrivalWithLayover.toInstant();
                instantHours = instant.plus(hours, ChronoUnit.HOURS);

                Date arrivalDateTimeReturn = Date.from(instantHours.plus(minutes, ChronoUnit.MINUTES));
                instant = arrivalDateTimeReturn.toInstant();
                Date arrivalReturnWithLayover = Date.from(instant.plus((long) layover, ChronoUnit.HOURS));
                
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
    public FlightSchedule getFlightSchedulesWithFsId(Long id) throws FlightScheduleDoesNotExistException {
        try {
            FlightSchedule flightSchedule = em.find(FlightSchedule.class, id);
            return flightSchedule;
        } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
        }
    }
    
    @Override
    public Long changeFlightScheduleDateTime(Long flightScheduleId, Date departureDateTime, double duration) throws FlightScheduleDoesNotExistException {
        try {
            FlightSchedule flightSchedule = em.find(FlightSchedule.class, flightScheduleId);
            flightSchedule.setDepartureDateTime(departureDateTime);
            flightSchedule.setEstimatedTime(duration);

            long hours = (long) (int) duration;
            long minutes = (long) ((duration - hours) * 60);

            Instant instant = departureDateTime.toInstant();
            Instant instantHours = instant.plus(hours, ChronoUnit.HOURS);            
            Date arrivalDateTime = Date.from(instantHours.plus(minutes, ChronoUnit.MINUTES));
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
    public FlightSchedule getFlightScheduleWithId(long id, Boolean detach) throws FlightScheduleDoesNotExistException {
        try {
            FlightSchedule fs = em.find(FlightSchedule.class, id);
            em.detach(fs);
            em.detach(fs.getFlightSchedulePlan());
            for(ReservationDetails rd : fs.getListOfReservationDetails()) {
                em.detach(rd);
            }
            for (Customer cs : fs.getCustomers()) {
                em.detach(cs);
            }
            for (Partner p : fs.getPartners()) {
                em.detach(p);
            }
            return fs;      
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
    public String getFirstAvailableCabin(long id, int numOfSeatsNeeded) throws FlightScheduleDoesNotExistException {
        try {
            FlightSchedule fs = getFlightScheduleWithId(id);
            List<Cabin> listOfCabins = fs.getListOfCabins();
            listOfCabins.size();
            for (Cabin c : listOfCabins) {
                if ((c.getTotalSeats() - c.getReservedSeats())>= numOfSeatsNeeded) {
                    return c.getCabinClassName();
                }
            }
            return null;
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
    public List<String> getCabinSeatsList(long id, String cabName) throws FlightScheduleDoesNotExistException {
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

    private List<String> convertCharArrayToList(char[][] charArray) {
        List<String> result = new ArrayList<>();
        result.add("" + charArray[0].length);

        for (char[] row : charArray) {
            for (char c : row) {
                result.add("" + c);
            }
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
                if (rd.getCustomer().getAccountId() == cust.getAccountId()) {
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
            
            for (ReservationDetails rd : newDetails) {
                em.detach(rd);
                if (rd.getCustomer() != null) {
                    em.detach(rd.getCustomer());
                }
                if (rd.getPartner() != null) {
                    em.detach(rd.getPartner());
                }
                em.detach(rd.getFlightSchedule());
                em.detach(rd.getFare().getCabin());
                em.detach(rd.getFare());
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
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWithSameTimingPartner(Date departureDate, long depAirport, long destAirport)  {
        List<Flight> listOfFlights = flightSessionBeanLocal.retrieveFlightsThatHasDepAndDest(depAirport, destAirport);
        List<FlightSchedulePlan> listOfFsPlan = flightSchedulePlanSessionBeanLocal.retrieveFlightSchedulePlanWithSameFlight(listOfFlights);
        List<FlightSchedule> listOfFs = retrieveFlightSchedulePlanWithSameTiming(listOfFsPlan, departureDate);
        for (FlightSchedule fs : listOfFs) {
            em.detach(fs);
            em.detach(fs.getFlightSchedulePlan());
            for (Customer c : fs.getCustomers()) {
                em.detach(c);
            }
            for (ReservationDetails rd : fs.getListOfReservationDetails()) {
                em.detach(rd);
            }
            for (Cabin c : fs.getListOfCabins()) {
                em.detach(c);
            }
            for (Partner p : fs.getPartners()) {
                em.detach(p);
            }   
        }
        return listOfFs;
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysAfterPartner(Date departureDate, long depAirport, long destAirport)  {
        List<Flight> listOfFlights = flightSessionBeanLocal.retrieveFlightsThatHasDepAndDest(depAirport, destAirport);
        List<FlightSchedulePlan> listOfFsPlan = flightSchedulePlanSessionBeanLocal.retrieveFlightSchedulePlanWithSameFlight(listOfFlights);
        List<FlightSchedule> listOfFs = retrieveFlightSchedulePlanWith3DaysAfter(listOfFsPlan, departureDate);
        for (FlightSchedule fs : listOfFs) {
            em.detach(fs);
            em.detach(fs.getFlightSchedulePlan());
            for (Customer c : fs.getCustomers()) {
                em.detach(c);
            }
            for (ReservationDetails rd : fs.getListOfReservationDetails()) {
                em.detach(rd);
            }
            for (Cabin c : fs.getListOfCabins()) {
                em.detach(c);
            }
            for (Partner p : fs.getPartners()) {
                em.detach(p);
            }       
        }
        return listOfFs;
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysBeforePartner(Date departureDate, long depAirport, long destAirport)  {
        List<Flight> listOfFlights = flightSessionBeanLocal.retrieveFlightsThatHasDepAndDest(depAirport, destAirport);
        List<FlightSchedulePlan> listOfFsPlan = flightSchedulePlanSessionBeanLocal.retrieveFlightSchedulePlanWithSameFlight(listOfFlights);
        List<FlightSchedule> listOfFs = retrieveFlightSchedulePlanWith3DaysBefore(listOfFsPlan, departureDate);
        for (FlightSchedule fs : listOfFs) {
            em.detach(fs);
            em.detach(fs.getFlightSchedulePlan());
            for (Customer c : fs.getCustomers()) {
                em.detach(c);
            }
            for (ReservationDetails rd : fs.getListOfReservationDetails()) {
                em.detach(rd);
            }
            for (Cabin c : fs.getListOfCabins()) {
                em.detach(c);
            }
            for (Partner p : fs.getPartners()) {
                em.detach(p);
            }   
        }
        return listOfFs;
    }
    
    @Override
    public long getAirportIdWithFlightScheduleId(long flightSchedId) {
        FlightSchedule fs = em.find(FlightSchedule.class, flightSchedId);
        return fs.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportId();
    }

    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWithSameTimingConnectingPartner(long depAirport, long destAirport, List<Long> listOfHubIds, Date departureDate) {
        List<Flight> listOfFlightsToHub = new ArrayList<>();
        for (Long hubId : listOfHubIds) {
            List<Flight> listToHub = flightSessionBeanLocal.retrieveFlightsThatHasDepAndDest(depAirport, hubId);
            for (Flight f : listToHub) {
                listOfFlightsToHub.add(f);
            }
        }
        List<FlightSchedulePlan> listOfFsPlan = flightSchedulePlanSessionBeanLocal.retrieveFlightSchedulePlanWithSameFlight(listOfFlightsToHub);
        List<FlightSchedule> listOfFs = retrieveFlightSchedulePlanWithSameTimingConnecting(listOfFsPlan, departureDate, destAirport);
        
        
        for (FlightSchedule fs : listOfFs) {
            em.detach(fs);
            em.detach(fs.getFlightSchedulePlan());
            for (Customer c : fs.getCustomers()) {
                em.detach(c);
            }
            for (ReservationDetails rd : fs.getListOfReservationDetails()) {
                em.detach(rd);
            }
            for (Cabin c : fs.getListOfCabins()) {
                em.detach(c);
            }
            for (Partner p : fs.getPartners()) {
                em.detach(p);
            }   
        }
        return listOfFs;
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysBeforeConnectingPartner(long depAirport, long destAirport, List<Long> listOfHubIds, Date departureDate) {
        List<Flight> listOfFlightsToHub = new ArrayList<>();
        for (Long hubId : listOfHubIds) {
            List<Flight> listToHub = flightSessionBeanLocal.retrieveFlightsThatHasDepAndDest(depAirport, hubId);
            for (Flight f : listToHub) {
                listOfFlightsToHub.add(f);
            }
        }
        List<FlightSchedulePlan> listOfFsPlan = flightSchedulePlanSessionBeanLocal.retrieveFlightSchedulePlanWithSameFlight(listOfFlightsToHub);
        List<FlightSchedule> listOfFs = retrieveFlightSchedulePlanWith3DaysBeforeConnecting(listOfFsPlan, departureDate, destAirport);
        for (FlightSchedule fs : listOfFs) {
            em.detach(fs);
            em.detach(fs.getFlightSchedulePlan());
            for (Customer c : fs.getCustomers()) {
                em.detach(c);
            }
            for (ReservationDetails rd : fs.getListOfReservationDetails()) {
                em.detach(rd);
            }
            for (Cabin c : fs.getListOfCabins()) {
                em.detach(c);
            }
            for (Partner p : fs.getPartners()) {
                em.detach(p);
            }   
        }
        return listOfFs;
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysAfterConnectingPartner(long depAirport, long destAirport, List<Long> listOfHubIds, Date departureDate) {
        List<Flight> listOfFlightsToHub = new ArrayList<>();
        for (Long hubId : listOfHubIds) {
            List<Flight> listToHub = flightSessionBeanLocal.retrieveFlightsThatHasDepAndDest(depAirport, hubId);
            for (Flight f : listToHub) {
                listOfFlightsToHub.add(f);
            }
        }
        List<FlightSchedulePlan> listOfFsPlan = flightSchedulePlanSessionBeanLocal.retrieveFlightSchedulePlanWithSameFlight(listOfFlightsToHub);
        List<FlightSchedule> listOfFs = retrieveFlightSchedulePlanWith3DaysAfterConnecting(listOfFsPlan, departureDate, destAirport);
        for (FlightSchedule fs : listOfFs) {
            em.detach(fs);
            em.detach(fs.getFlightSchedulePlan());
            for (Customer c : fs.getCustomers()) {
                em.detach(c);
            }
            for (ReservationDetails rd : fs.getListOfReservationDetails()) {
                em.detach(rd);
            }
            for (Cabin c : fs.getListOfCabins()) {
                em.detach(c);
            }
            for (Partner p : fs.getPartners()) {
                em.detach(p);
            }   
        }
        return listOfFs;
    }

    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanAfterTimingReturnConnectingPartner(long pickedAirport, long destAirport, Date departureDate) {

        List<Flight> listToHub = flightSessionBeanLocal.retrieveFlightsThatHasDepAndDest(pickedAirport, destAirport);
        List<FlightSchedulePlan> listOfFsPlan = flightSchedulePlanSessionBeanLocal.retrieveFlightSchedulePlanWithSameFlight(listToHub);
        
        List<FlightSchedule> listOfFs = retrieveFlightSchedulePlanAfterTiming(listOfFsPlan, departureDate);
        for (FlightSchedule fs : listOfFs) {
            em.detach(fs);
            em.detach(fs.getFlightSchedulePlan());
            for (Customer c : fs.getCustomers()) {
                em.detach(c);
            }
            for (ReservationDetails rd : fs.getListOfReservationDetails()) {
                em.detach(rd);
            }
            for (Cabin c : fs.getListOfCabins()) {
                em.detach(c);
            }
            for (Partner p : fs.getPartners()) {
                em.detach(p);
            }   
        }
        return listOfFs;
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWith1DayAfterReturnConnectingPartner(long pickedAirport, long destAirport,  Date departureDate) {

        List<Flight> listToHub = flightSessionBeanLocal.retrieveFlightsThatHasDepAndDest(pickedAirport, destAirport);
        List<FlightSchedulePlan> listOfFsPlan = flightSchedulePlanSessionBeanLocal.retrieveFlightSchedulePlanWithSameFlight(listToHub);
        
        List<FlightSchedule> listOfFs = retrieveFlightSchedulePlanWith1DayAfter(listOfFsPlan, departureDate);
        for (FlightSchedule fs : listOfFs) {
            em.detach(fs);
            em.detach(fs.getFlightSchedulePlan());
            for (Customer c : fs.getCustomers()) {
                em.detach(c);
            }
            for (ReservationDetails rd : fs.getListOfReservationDetails()) {
                em.detach(rd);
            }
            for (Cabin c : fs.getListOfCabins()) {
                em.detach(c);
            }
            for (Partner p : fs.getPartners()) {
                em.detach(p);
            }   
        }
        return listOfFs;
    }
    
    @Override
    public List<FlightSchedule> getFlightSchedulesUsingFlightNum(int flightNum) {
        Query query = em.createQuery("SELECT f FROM FlightSchedule f WHERE f.flightSchedulePlan.flightNumber = :flightNum");
        query.setParameter("flightNum", flightNum);
        return (List<FlightSchedule>) query.getResultList();
    }

    @Override
    public Boolean checkIfFlightSchedIdIsDirect(long flightSchedId, long destId) {
        FlightSchedule fs = em.find(FlightSchedule.class, flightSchedId);
        if (fs.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportId() == destId) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWithSameTimingConnecting(List<FlightSchedulePlan> listOfFlightSchedulePlanToHub, Date departureDate, long depAirport) {
        List<FlightSchedule> listOfFlightSchedules = retrieveFlightScheduleInPlan(listOfFlightSchedulePlanToHub);

        List<FlightSchedule> newList = new ArrayList<>();

        for (FlightSchedule flightSchedule : listOfFlightSchedules) {
            Date departureDateTime = flightSchedule.getDepartureDateTime();
            LocalDate localDate = departureDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (localDate.isEqual(departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                newList.add(flightSchedule);
            }
        }
        
        List<FlightSchedule> listThatAccountsForNextFlight = new ArrayList<>();
        for (FlightSchedule fs : newList) {
            Date flightEndDate = fs.getArrivalDateTime();
            long nextAirportStartId = fs.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportId();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(flightEndDate);
            calendar.add(Calendar.DATE, 1);
            Date OneDaysAfter = calendar.getTime();

            Query query = em.createQuery("SELECT f FROM FlightSchedule f WHERE f.departureDateTime BETWEEN :startDate AND :endDate AND f.flightSchedulePlan.flight.flightRoute.origin.airportId = :origin AND f.flightSchedulePlan.flight.flightRoute.destination.airportId = :destination");
            query.setParameter("startDate", flightEndDate);
            query.setParameter("endDate", OneDaysAfter);
            query.setParameter("origin", nextAirportStartId);
            query.setParameter("destination", depAirport);

            List<FlightSchedule> resultList = query.getResultList();
            if (resultList.size() > 0) {
                listThatAccountsForNextFlight.add(fs);
            } else if (fs.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportId() == depAirport) {
                listThatAccountsForNextFlight.add(fs);
            }
        }
        return listThatAccountsForNextFlight;

    }
    
     @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysAfterConnecting(List<FlightSchedulePlan> listOfFlightSchedulePlanToHub, Date departureDate, long depAirport) {
        List<FlightSchedule> listOfFlightSchedules = retrieveFlightScheduleInPlan(listOfFlightSchedulePlanToHub);

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
        
        List<FlightSchedule> listThatAccountsForNextFlight = new ArrayList<>();
        for (FlightSchedule fs : newList) {
            Date flightEndDate = fs.getArrivalDateTime();
            long nextAirportStartId = fs.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportId();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(flightEndDate);
            calendar.add(Calendar.DATE, 1);
            Date OneDaysAfter = calendar.getTime();

            Query query = em.createQuery("SELECT f FROM FlightSchedule f WHERE f.departureDateTime BETWEEN :startDate AND :endDate AND f.flightSchedulePlan.flight.flightRoute.origin.airportId = :origin AND f.flightSchedulePlan.flight.flightRoute.destination.airportId = :destination");
            query.setParameter("startDate", flightEndDate);
            query.setParameter("endDate", OneDaysAfter);
            query.setParameter("origin", nextAirportStartId);
            query.setParameter("destination", depAirport);

            List<FlightSchedule> resultList = query.getResultList();
            if (resultList.size() > 0) {
                listThatAccountsForNextFlight.add(fs);
            } else if (fs.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportId() == depAirport) {
                listThatAccountsForNextFlight.add(fs);
            }
        }

        return listThatAccountsForNextFlight;

    }
    
     @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysBeforeConnecting(List<FlightSchedulePlan> listOfFlightSchedulePlanToHub, Date departureDate, long depAirport) {
        List<FlightSchedule> listOfFlightSchedules = retrieveFlightScheduleInPlan(listOfFlightSchedulePlanToHub);

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
        List<FlightSchedule> listThatAccountsForNextFlight = new ArrayList<>();
        for (FlightSchedule fs : newList) {
            Date flightEndDate = fs.getArrivalDateTime();
            long nextAirportStartId = fs.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportId();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(flightEndDate);
            calendar.add(Calendar.DATE, 1);
            Date OneDaysAfter = calendar.getTime();

            Query query = em.createQuery("SELECT f FROM FlightSchedule f WHERE f.departureDateTime BETWEEN :startDate AND :endDate AND f.flightSchedulePlan.flight.flightRoute.origin.airportId = :origin AND f.flightSchedulePlan.flight.flightRoute.destination.airportId = :destination");
            query.setParameter("startDate", flightEndDate);
            query.setParameter("endDate", OneDaysAfter);
            query.setParameter("origin", nextAirportStartId);
            query.setParameter("destination", depAirport);

            List<FlightSchedule> resultList = query.getResultList();
            if (resultList.size() > 0) {
                listThatAccountsForNextFlight.add(fs);
            } else if (fs.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportId() == depAirport) {
                listThatAccountsForNextFlight.add(fs);
            }
        }
        return listThatAccountsForNextFlight;

    }
}


