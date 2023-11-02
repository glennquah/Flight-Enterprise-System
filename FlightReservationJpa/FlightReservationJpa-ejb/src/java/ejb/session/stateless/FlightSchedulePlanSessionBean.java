/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AirportDoesNotExistException;
import util.exception.ConflictingFlightScheduleException;

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
    public Long createSingleFlightSchedulePlan(FlightSchedulePlan flightSchedulePlan, Long flightScheduleid) throws AirportDoesNotExistException, ConflictingFlightScheduleException {
        try {
            Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber");
            query.setParameter("flightNumber", flightSchedulePlan.getFlightNumber());
            Flight flight = (Flight)query.getSingleResult();

            FlightSchedule flightSchedule = em.find(FlightSchedule.class, flightScheduleid);
            
            Date departureDateTime = flightSchedule.getDepartureDateTime();
            List<Date> bookedDates = flight.getBookedDates();
            bookedDates.size();
//            System.out.println("hello " + bookedDates.size());
//            for (Date date: bookedDates) {
//                    System.out.println(date);
//            }
            
            if (bookedDates.size() == 0 || departureDateTime.before(bookedDates.get(0)) || departureDateTime.after(bookedDates.get(bookedDates.size() - 1))) {
                bookedDates.add(departureDateTime);
                bookedDates.add(flightSchedule.getArrivalDateTime());
                flight.setBookedDates(bookedDates);
                
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
            } else {
                for (int i = 1; i < bookedDates.size() - 1; i += 2) {
                    if (bookedDates.get(i).before(departureDateTime)) {
                        System.out.println("Conflicting Flight! Please pick again");
                     
                        throw new ConflictingFlightScheduleException("There are conflicting flight schedules!");
                    }
                }
                
                bookedDates.add(departureDateTime);
                bookedDates.add(flightSchedule.getArrivalDateTime());
                flight.setBookedDates(bookedDates);
                
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
            }
            
            return flightSchedule.getFlightScheduleId();
        } catch (NoResultException ex) {
            throw new AirportDoesNotExistException("Aiport does not exist!");
        }
    }
    
    
}
