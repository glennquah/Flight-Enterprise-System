/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Cabin;
import entity.Fare;
import entity.Flight;
import entity.FlightRoute;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import entity.ReservationDetails;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.enumeration.FlightSchedulePlanStatusEnum;
import util.exception.ConflictingFlightScheduleException;
import util.exception.FlightDoesNotExistException;

/**
 *
 * @author admin
 */
@Stateless
public class FlightSchedulePlanSessionBean implements FlightSchedulePlanSessionBeanRemote, FlightSchedulePlanSessionBeanLocal {

    @EJB(name = "FareSessionBeanLocal")
    private FareSessionBeanLocal fareSessionBeanLocal;

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override 
    public void createFare(Long flightSchedulePlanId, Long cabinId, List<String> fareBasisCodes, List<BigDecimal> fareAmounts) {
        FlightSchedulePlan flightSchedulePlan = em.find(FlightSchedulePlan.class, flightSchedulePlanId);
        //Cabin cabin = em.find(Cabin.class, cabinId);
        
        List<FlightSchedule> listOfFs = flightSchedulePlan.getFlightSchedules();
        listOfFs.size();
        
        for (FlightSchedule fs : listOfFs) {
            List<Cabin> cabins = fs.getListOfCabins();
            cabins.size();
            for (Cabin cabin : cabins) {
                for (int i = 0; i < fareBasisCodes.size(); i++) {
                    Fare fare = new Fare(cabin, fareBasisCodes.get(i), fareAmounts.get(i), flightSchedulePlan);
                    long fareId = fareSessionBeanLocal.createFare(fare);
                    //System.out.println("Fare ID: " + fareId);
                    Fare fareNew = em.find(Fare.class, fareId);
                    List<Fare> listOfFares = flightSchedulePlan.getListOfFares();
                    //System.out.println("listOfFares size before adding: " + listOfFares.size());
                    listOfFares.add(fareNew);
                    //System.out.println("listOfFares size after adding: " + listOfFares.size());
                    flightSchedulePlan.setListOfFares(listOfFares);

                    List<Fare> listOfFaresCabin = cabin.getListOfFare();
                    //System.out.println("listOfFaresCabin size before adding: " + listOfFaresCabin.size());
                    listOfFaresCabin.add(fareNew);
                    //System.out.println("listOfFaresCabin size after adding: " + listOfFaresCabin.size());
                    cabin.setListOfFare(listOfFaresCabin);

                    //System.out.println("Fare persisted: " + fareNew.getId());
                }
            }         
        }

        
        em.flush();
    }
    
    @Override
    public Long createSingleFlightSchedulePlan(FlightSchedulePlan flightSchedulePlan, Long flightScheduleid) throws FlightDoesNotExistException, ConflictingFlightScheduleException {
        try {
            Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
            query.setParameter("flightNumber", flightSchedulePlan.getFlightNumber());
            Flight flight = (Flight)query.getSingleResult();
            FlightSchedule flightSchedule = em.find(FlightSchedule.class, flightScheduleid);
                
            List<FlightSchedulePlan> listOfFlightSchedulePlans = flight.getListOfFlightSchedulePlans();
            listOfFlightSchedulePlans.add(flightSchedulePlan);
            flight.setListOfFlightSchedulePlans(listOfFlightSchedulePlans);

            flightSchedulePlan.setFlight(flight);
            flightSchedule.setFlightSchedulePlan(flightSchedulePlan);

            List<FlightSchedule> flightSchedules = flightSchedulePlan.getFlightSchedules();
            flightSchedules.add(flightSchedule);
            flightSchedulePlan.setFlightSchedules(flightSchedules);

            em.persist(flightSchedulePlan);
            em.flush();       
            
            return flightSchedulePlan.getFlightSchedulePlanId();
        } catch (NoResultException ex) {
            throw new FlightDoesNotExistException("Flight Does Not Exist!");
        }
    }
    
    @Override
    public Long createMultipleFlightSchedulePlan(FlightSchedulePlan flightSchedulePlan, List<Long> flightScheduleIds) throws FlightDoesNotExistException, ConflictingFlightScheduleException {
        try {
            Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
            query.setParameter("flightNumber", flightSchedulePlan.getFlightNumber());
            Flight flight = (Flight)query.getSingleResult();
            
            for (int i = 0; i < flightScheduleIds.size(); i++) {
                FlightSchedule flightSchedule = em.find(FlightSchedule.class, flightScheduleIds.get(i));

                List<FlightSchedulePlan> listOfFlightSchedulePlans = flight.getListOfFlightSchedulePlans();
                listOfFlightSchedulePlans.add(flightSchedulePlan);
                flight.setListOfFlightSchedulePlans(listOfFlightSchedulePlans);

                flightSchedulePlan.setFlight(flight);
                flightSchedule.setFlightSchedulePlan(flightSchedulePlan);

                List<FlightSchedule> flightSchedules = flightSchedulePlan.getFlightSchedules();
                flightSchedules.add(flightSchedule);
                flightSchedulePlan.setFlightSchedules(flightSchedules);
            }
           
           
            em.persist(flightSchedulePlan);
            em.flush();       
            
            return flightSchedulePlan.getFlightSchedulePlanId();
        } catch (NoResultException ex) {
            throw new FlightDoesNotExistException("Flight Does Not Exist!");
        }
    }
    
    @Override
    public List<FlightSchedulePlan> retrieveAllFlightSchedulePlans() {
        Query query = em.createQuery("SELECT f FROM FlightSchedulePlan f ORDER BY f.flight.flightNumber ASC");
        return query.getResultList();
    }  
    
    @Override 
    public List<Fare> getFlightSchedulePlanFares(Long flightSchedulePlanId) {
        FlightSchedulePlan flightSchedulePlan = em.find(FlightSchedulePlan.class, flightSchedulePlanId);
        Query query = em.createQuery("SELECT f FROM Fare f WHERE f.flightSchedulePlan = :flightSchedulePlan");
        query.setParameter("flightSchedulePlan", flightSchedulePlan);
        return query.getResultList();
    }
    
    @Override
    public FlightSchedulePlan retrieveFlightSchedulePlan(Long flightSchedulePlanId) {
        return em.find(FlightSchedulePlan.class, flightSchedulePlanId);
    }
    
    @Override 
    public List<FlightSchedule> retrieveFlightSchedule(Long flightSchedulePlanId) {
        FlightSchedulePlan flightSchedulePlan = em.find(FlightSchedulePlan.class, flightSchedulePlanId);
        Query query = em.createQuery("SELECT f FROM FlightSchedule f WHERE f.flightSchedulePlan = :flightSchedulePlan");
        query.setParameter("flightSchedulePlan", flightSchedulePlan);
        return query.getResultList();
    }
    
    @Override
    public void deleteFlightSchedulePlan(Long flightSchedulePlanId) {
        FlightSchedulePlan flightSchedulePlan = retrieveFlightSchedulePlan(flightSchedulePlanId);
        Boolean used = false;
        
        List<FlightSchedule> flightSchedules = flightSchedulePlan.getFlightSchedules();
        flightSchedules.size();
        
        for (FlightSchedule f: flightSchedules) {
            List<ReservationDetails> reservations = f.getListOfReservationDetails();
            reservations.size();
            
            if (reservations.size() > 0) {
                used = true;
                break;
            }
        }
        
        if (used) {
            flightSchedulePlan.setFlightSchedulePlanStatus(FlightSchedulePlanStatusEnum.DISABLED);
        } else {
            List<Fare> fares = flightSchedulePlan.getListOfFares();
            fares.size();
            flightSchedulePlan.setFlightSchedules(new ArrayList<>());
            flightSchedulePlan.setListOfFares(new ArrayList<>());
            Flight flight = flightSchedulePlan.getFlight();
            List<Date> bookedDates = flight.getBookedDates();
            Cabin cabin;
            
            for (Fare f: fares) {
                cabin = f.getCabin();
                em.remove(cabin);
                em.remove(f);
            }
            
            for (FlightSchedule f: flightSchedules) {
                bookedDates.remove(f.getDepartureDateTime());
                bookedDates.remove(f.getArrivalDateTime());
                em.remove(f);
            }
            
            flight.setBookedDates(bookedDates);
            em.remove(flightSchedulePlan);
            em.flush();
        }
    }
    
    @Override
    public List<FlightSchedulePlan> retrieveFlightSchedulePlanWithSameFlight(List<Flight> listOfFlights) {
        if (listOfFlights.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if no flights are provided
        }

        Query query = em.createQuery("SELECT f FROM FlightSchedulePlan f WHERE f.flightNumber IN :flightNumbers");
        List<Integer> flightNumbers = new ArrayList<>();
        for (Flight flight : listOfFlights) {
            flightNumbers.add(flight.getFlightNumber());
        }

        query.setParameter("flightNumbers", flightNumbers);
        return query.getResultList();
    }
    
    @Override
    public List<FlightSchedulePlan> retrieveFlightSchedulePlanWithSameFlight(List<Flight> listOfFlights, Boolean detach) {
        if (listOfFlights.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if no flights are provided
        }

        Query query = em.createQuery("SELECT f FROM FlightSchedulePlan f WHERE f.flightNumber IN :flightNumbers");
        List<Integer> flightNumbers = new ArrayList<>();
        for (Flight flight : listOfFlights) {
            flightNumbers.add(flight.getFlightNumber());
        }

        query.setParameter("flightNumbers", flightNumbers);
        List<FlightSchedulePlan> fsps = query.getResultList();
        for (FlightSchedulePlan fsp : fsps) {
            em.detach(fsp);
            for (FlightSchedule fs : fsp.getFlightSchedules()) {
                em.detach(fs);
            }
            em.detach(fsp.getFlight());
        }
        return fsps;
    }

    @Override
    public FlightRoute retrieveFlightRouteFromFlightSchedule(Long fsId) {
        FlightSchedule fs = em.find(FlightSchedule.class, fsId);
        return fs.getFlightSchedulePlan().getFlight().getFlightRoute();
    }
}