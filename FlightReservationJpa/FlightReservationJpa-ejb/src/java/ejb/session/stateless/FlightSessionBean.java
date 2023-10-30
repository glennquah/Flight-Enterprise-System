/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.Airport;
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
public class FlightSessionBean implements FlightSessionBeanRemote, FlightSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewFlight(Flight flight, Long flightRouteId, Long aircraftConfigId) {
        FlightRoute fr = em.find(FlightRoute.class, flightRouteId);
        AircraftConfiguration ac = em.find(AircraftConfiguration.class, aircraftConfigId);
        
        //sync flight route to flight
        List<Flight> listOfFlights = fr.getListOfFlights();
        listOfFlights.add(flight);
        fr.setListOfFlights(listOfFlights);
        
        flight.setFlightRoute(fr);
        
        //sync aircraft config to flight
        List<Flight> listOfAcFlights = ac.getListOfFlights();
        listOfAcFlights.add(flight);
        //might have error here when updating
        ac.setListOfFlights(listOfAcFlights);
        flight.setAircraftConfig(ac);
        
        em.persist(flight);
        em.flush();
        
        return flight.getFlightId();
    }
    
    @Override
    public List<Flight> retrieveAllFlights() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT f FROM Flight f");
        return query.getResultList();
    }
    
    @Override
    public Flight getFlightWithId(long id) {
        Query query = em.createQuery("Select f from Flight f WHERE f.flightId = :flightId");
        query.setParameter("flightId", id);
        return (Flight)query.getSingleResult();
//        return em.find(Flight.class, id);
    }

    @Override
    public long removeFlight(long id) {
        Flight flight = getFlightWithId(id);
        em.remove(flight);
        em.flush();
        return flight.getFlightId();
    }
    
}
