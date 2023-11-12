/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.Airport;
import entity.Cabin;
import entity.Fare;
import entity.FlightSchedule;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AircraftConfigurationDoesNotExistException;

/**
 *
 * @author Lenovo
 */
@Stateless
public class CabinCustomerSessionBean implements CabinCustomerSessionBeanRemote, CabinCustomerSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createCabin(Cabin cabin, Long aircraftConfigId) throws AircraftConfigurationDoesNotExistException {
        try {
            AircraftConfiguration ac = em.find(AircraftConfiguration.class, aircraftConfigId);
            List<Cabin> listOfCabs = ac.getListOfCabins();
            // lazy loading??
            listOfCabs.size();
            listOfCabs.add(cabin);
            ac.setListOfCabins(listOfCabs);
            cabin.setAircraftConfiguration(ac);
            em.persist(cabin);
            em.flush();
            return cabin.getCabinId();
        } catch (NoResultException e) {
            throw new AircraftConfigurationDoesNotExistException("Aircraft Configuration Does Not Exist");
        }
    }
    
    @Override
    public Cabin createCabinOnly(Cabin cabin) {
        em.persist(cabin);
        em.flush();
        return cabin;
    }
    
    @Override
    public List<Cabin> retrieveAllCabins() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT c FROM Cabin c");
        return query.getResultList();
    }
    
    @Override
    public long getLowestFareIdInCabin(long id) {
        Cabin cab = em.find(Cabin.class, id);
        List<Fare> listOfFare = cab.getListOfFare();
        System.out.println("SIZE = " + listOfFare.size());
        
        BigDecimal least = BigDecimal.valueOf(Integer.MAX_VALUE);
        long leastFareid = 0;
        
        for (Fare f : listOfFare) {
            if (f.getFareAmount().compareTo(least) < 0) {
                least = f.getFareAmount();
                leastFareid = f.getId();
            }
        }
        
        return leastFareid;
    }
}
