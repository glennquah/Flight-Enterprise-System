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
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.ConflictingFlightScheduleException;
import util.exception.FlightScheduleDoesNotExistException;

/**
 *
 * @author admin
 */
@Stateless
public class FlightScheduleSessionBean implements FlightScheduleSessionBeanRemote, FlightScheduleSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void checkForConflictingFlights(Integer flightNumber, Date departureDate, Duration duration) throws ConflictingFlightScheduleException {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight)query.getSingleResult();

        List<Date> bookedDates = flight.getBookedDates();
        bookedDates.size();
        
        Instant instant = departureDate.toInstant();
        Date arrivalDateTime = Date.from(instant.plus(duration));
            
        for (int j = 1; j < bookedDates.size() - 1; j += 2) {
            if (bookedDates.get(j).after(departureDate)) {
                throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
            } else if(departureDate.after(bookedDates.get(j)) && bookedDates.get(j + 1).before(departureDate)) {
                break;
            }
        }
    }
    
    @Override
    public void checkForConflictingFlights(Integer flightNumber, List<Date> departureDates, List<Duration> durations) throws ConflictingFlightScheduleException {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight)query.getSingleResult();

        List<Date> bookedDates = flight.getBookedDates();
        bookedDates.size();
        
       
        for (int i = 0; i < departureDates.size(); i++) { 
            Date departureDateTime = departureDates.get(i);
            Duration duration = durations.get(i);
            
            Instant instant = departureDateTime.toInstant();
            Date arrivalDateTime = Date.from(instant.plus(duration));
            
            for (int j = 1; j < bookedDates.size() - 1; j += 2) {
                if (bookedDates.get(j).after(departureDateTime)) {
                    throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
                } else if(bookedDates.get(j).after(departureDateTime) && departureDateTime.before(bookedDates.get(j + 1))) {
                    if (arrivalDateTime.after(bookedDates.get(j + 1))) {
                        throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
                    }
                }
            }
        } 
    }
    
    @Override
    public FlightSchedule createNewFlightSchedule(Integer flightNumber, FlightSchedule flightSchedule) {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight)query.getSingleResult();
            
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
    public List<FlightSchedule> getFlightScheduleWithId(Long id) throws FlightScheduleDoesNotExistException {
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
}
