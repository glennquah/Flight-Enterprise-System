/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Airport;
import entity.Flight;
import entity.FlightRoute;
import entity.FlightSchedulePlan;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AirportDoesNotExistException;
import util.exception.FlightRouteAlreadyExistException;
import util.exception.FlightRouteDoesNotExistException;
import util.exception.FlightScheduleDoesNotExistException;

/**
 *
 * @author Lenovo
 */
@Stateless
public class FlightRoutesSessionBean implements FlightRoutesSessionBeanRemote, FlightRoutesSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewFlightRoute(Long airportOneId, Long airportTwoId) throws AirportDoesNotExistException, FlightRouteAlreadyExistException {
        try {
            Query query = em.createQuery("SELECT a FROM Airport a WHERE a.airportId = :airportId");
            query.setParameter("airportId", airportOneId);
            Airport airportOne = (Airport)query.getSingleResult();

            Query secondQuery = em.createQuery("SELECT a FROM Airport a WHERE a.airportId = :airportId");
            secondQuery.setParameter("airportId", airportTwoId);
            Airport airportTwo = (Airport)secondQuery.getSingleResult();
            
            Query thirdQuery = em.createQuery("SELECT f FROM FlightRoute f");
            List<FlightRoute> flightRoutes = (List<FlightRoute>)thirdQuery.getResultList();
  
            for (FlightRoute f:flightRoutes) {
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
    
    @Override
    public Long createNewFlightRouteWithReturn(Long airportOneId, Long airportTwoId) throws AirportDoesNotExistException, FlightRouteAlreadyExistException {
        try {
            Query query = em.createQuery("SELECT a FROM Airport a WHERE a.airportId = :airportId");
            query.setParameter("airportId", airportOneId);
            Airport airportOne = (Airport)query.getSingleResult();

            Query secondQuery = em.createQuery("SELECT a FROM Airport a WHERE a.airportId = :airportId");
            secondQuery.setParameter("airportId", airportTwoId);
            Airport airportTwo = (Airport)secondQuery.getSingleResult();

            Query thirdQuery = em.createQuery("SELECT f FROM FlightRoute f");
            List<FlightRoute> flightRoutes = (List<FlightRoute>)thirdQuery.getResultList();
  
            for (FlightRoute f:flightRoutes) {
                if (f.getOrigin() == airportOne && f.getDestination() == airportTwo) {
                    throw new FlightRouteAlreadyExistException("Flight route already exist!");
                } else if(f.getOrigin() == airportTwo && f.getDestination() == airportOne && f.getComplementaryRoute()) {
                    throw new FlightRouteAlreadyExistException("Flight route already exist!");
                }
            }
            
            FlightRoute newFlightRoute = new FlightRoute(airportOne, airportTwo, true);
            em.persist(newFlightRoute);
            em.flush();
            
            return newFlightRoute.getFlightRouteId();
        } catch (NoResultException ex) {
            throw new AirportDoesNotExistException("Aiport does not exist!");
        }
    }
    
    @Override
    public Long deleteFlightRoute(Long flightRouteId) throws FlightRouteDoesNotExistException {
        try {
            Query query = em.createQuery("SELECT f FROM FlightRoute f WHERE f.flightRouteId = :flightRouteId");
            query.setParameter("flightRouteId", flightRouteId);
            FlightRoute flightRoute = (FlightRoute)query.getSingleResult();
            
//            Add when flight is settled
//            Query secondQuery = em.createQuery("SELECT f FROM Flight f WHERE f.flightRoute = :flightRoute");
//            query.setParameter("flightRoute", flightRoute);
//            List<Flight> flights = (List<Flight>)query.getResultList();
//            
//            if (flights != null) {
//                for (Flight f: flights) {
//                    f.setFlightRoute(null);
//                }
//            }
            
            em.remove(flightRoute);
            em.flush();
            
            return flightRouteId;
        } catch (NoResultException ex) {
            throw new FlightRouteDoesNotExistException("Flight Route does not exist!");
        }
    }
    
    @Override
    public List<FlightRoute> retrieveAllFlightRoutes() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT f FROM FlightRoute f ORDER BY f.origin.name ASC");
        return query.getResultList();
    }
    
    @Override
    public FlightRoute getFlightRouteWithId(Long id) {
        return em.find(FlightRoute.class, id);
    }

    @Override 
    public FlightRoute getFlightRouteWithFS(Long flightSchedulePlanId) throws FlightScheduleDoesNotExistException {
        try {
            FlightSchedulePlan flightSchedulePlan = em.find(FlightSchedulePlan.class, flightSchedulePlanId);
            Flight flight = flightSchedulePlan.getFlight();
            return flight.getFlightRoute();
        } catch (NoResultException e) {
            throw new FlightScheduleDoesNotExistException("Flight Schedule Does Not Exist");
        }
    }
}
