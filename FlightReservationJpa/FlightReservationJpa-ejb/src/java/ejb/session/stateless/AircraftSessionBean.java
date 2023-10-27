/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Aircraft;
import entity.Airport;
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
public class AircraftSessionBean implements AircraftSessionBeanRemote, AircraftSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createAircraft(Aircraft aircraft) {
        em.persist(aircraft);
        em.flush();
        
        return aircraft.getAircraftId();
    }
    
    @Override
    public List<Aircraft> retrieveAllAircrafts() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT a FROM Aircraft a");
        return query.getResultList();
    }
    
}
