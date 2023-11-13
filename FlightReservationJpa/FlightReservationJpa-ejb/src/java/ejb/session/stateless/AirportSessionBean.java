
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Airport;
import entity.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lenovo
 */
@Stateless
public class AirportSessionBean implements AirportSessionBeanRemote, AirportSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewAirport(Airport airport) {
        em.persist(airport);
        em.flush();
        
        return airport.getAirportId();
    }
    
    @Override
    public List<Airport> retrieveAllAirports() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT a FROM Airport a");
        return query.getResultList();
    }

    public void persist(Object object) {
        em.persist(object);
    }

    
}