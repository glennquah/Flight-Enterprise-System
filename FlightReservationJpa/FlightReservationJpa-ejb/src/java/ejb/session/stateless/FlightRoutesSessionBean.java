/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Airport;
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
    public List<FlightRoute> retrieveAllFlightRoutes() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT f FROM FlightRoute f");
        return query.getResultList();
    }

    
}
