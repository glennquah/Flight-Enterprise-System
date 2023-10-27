/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import entity.FlightRoute;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lenovo
 */
@Stateless
public class FlightRoutesSessionBean implements FlightRoutesSessionBeanRemote, FlightRoutesSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewFlightRoute(FlightRoute flightroute) {
        em.persist(flightroute);
        em.flush();
        
        return flightroute.getFlightRouteId();
    }
    
    @Override
    public Long deleteFLightRoute(Long flightRouteId) {
        Query query = em.createQuery("SELECT f FROM FlightRoute f WHERE f.flightRouteId = :flightRouteId");
        query.setParameter("flightRouteId", flightRouteId);
        FlightRoute flightRoute = (FlightRoute)query.getSingleResult();
        
        Query secondQuery = em.createQuery("SELECT f FROM Flight f WHERE f.flightRoute = :flightRoute");
        query.setParameter("flightRoute", flightRoute);
        List<Flight> flights = (List<Flight>)query.getResultList();
        
        for (Flight f: flights) {
            f.setFlightRoute(null);
        }
        
        em.remove(flightRoute);
        em.flush();
        return flightRouteId;
    }
    
    @Override
    public List<FlightRoute> retrieveAllFlightRoutes() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT f FROM FlightRoute f");
        return (List<FlightRoute>) query.getResultList();
    }

    
}
