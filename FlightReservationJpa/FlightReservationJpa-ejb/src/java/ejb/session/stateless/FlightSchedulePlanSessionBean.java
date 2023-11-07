/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Cabin;
import entity.Fare;
import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.ConflictingFlightScheduleException;
import util.exception.FlightDoesNotExistException;
import util.exception.FlightSchedulePlanDoesNotExistException;

/**
 *
 * @author admin
 */
@Stateless
public class FlightSchedulePlanSessionBean implements FlightSchedulePlanSessionBeanRemote, FlightSchedulePlanSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override 
    public void createFare(Long flightSchedulePlanId, Long cabinId, List<String> fareBasisCodes, List<BigDecimal> fareAmounts) {
        FlightSchedulePlan flightSchedulePlan = em.find(FlightSchedulePlan.class, flightSchedulePlanId);
        Cabin cabin = em.find(Cabin.class, cabinId);
        
        
        for (int i = 0; i < fareBasisCodes.size(); i++) {
            Fare fare = new Fare(cabin, fareBasisCodes.get(i), fareAmounts.get(i), flightSchedulePlan);
            List<Fare> listOfFares = flightSchedulePlan.getListOfFares();
            listOfFares.add(fare);
            flightSchedulePlan.setListOfFares(listOfFares);

            List<Fare> listOfFaresCabin = cabin.getListOfFare();
            listOfFaresCabin.add(fare);
            cabin.setListOfFare(listOfFaresCabin);

            em.persist(fare);
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
            throw new FlightDoesNotExistException("Flight does not exist!");
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
            throw new FlightDoesNotExistException("Flight does not exist!");
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
        em.remove(flightSchedulePlan);
        em.flush();
    }
}