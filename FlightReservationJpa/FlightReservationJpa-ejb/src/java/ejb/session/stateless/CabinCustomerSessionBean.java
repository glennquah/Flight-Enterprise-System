/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.Airport;
import entity.Cabin;
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
public class CabinCustomerSessionBean implements CabinCustomerSessionBeanRemote, CabinCustomerSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createCabin(Cabin cabin, Long aircraftConfigId) {
        AircraftConfiguration ac = em.find(AircraftConfiguration.class, aircraftConfigId);
        List<Cabin> listOfCabs = ac.getListOfCabins();
        // lazy loading??
        listOfCabs.add(cabin);
        ac.setListOfCabins(listOfCabs);
        cabin.setAircraftConfiguration(ac);
        em.persist(cabin);
        em.flush();
        
        return cabin.getCabinId();
    }
    
    @Override
    public List<Cabin> retrieveAllCabins() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT c FROM Cabin c");
        return query.getResultList();
    }
    
    
}
