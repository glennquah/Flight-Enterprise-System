/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Airport;
import entity.FlightRoute;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AirportDoesNotExistException;
import util.exception.FlightRouteAlreadyExistException;

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
    public Long createSingleFlightSchedulePlan(Long flightNumber, FlightSchedule flightSchedule) {
        try {
            Query query = em.createQuery("SELECT f FROM FlightSchedulePlan f WHERE f.flightNumber = :flightNumber");
            query.setParameter("flightNumber", flightNumber);
            List<FlightSchedulePlan> flightSchedulePlans = (List<FlightSchedulePlan>)query.getResultList();

            for (FlightSchedulePlan f:flightSchedulePlans) {
                if (f.getOrigin() == airportOne && f.getDestination() == airportTwo) {
                    throw new FlightRouteAlreadyExistException("Flight route already exist!");
                } else if(f.getOrigin() == airportTwo && f.getDestination() == airportOne && f.getComplementaryRoute()) {
                    throw new FlightRouteAlreadyExistException("Flight route already exist!");
                }
            }
            
            FlightRoute newFlightRoute = new FlightRoute(airportOne, airportTwo);
            em.persist(newFlightRoute);
            em.flush();
            
            return newFlightRoute.getFlightRouteId();
        } catch (NoResultException ex) {
            throw new AirportDoesNotExistException("Aiport does not exist!");
        }
    }
    
    
}
