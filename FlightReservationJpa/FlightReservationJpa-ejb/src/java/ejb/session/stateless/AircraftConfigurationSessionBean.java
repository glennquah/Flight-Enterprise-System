/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Aircraft;
import entity.AircraftConfiguration;
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
public class AircraftConfigurationSessionBean implements AircraftConfigurationSessionBeanRemote, AircraftConfigurationSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createAircraftConfiguration(AircraftConfiguration aircraftConfig) {
        em.persist(aircraftConfig);
        em.flush();
        
        return aircraftConfig.getAircraftConfigurationId();
    }
    
    @Override
    public List<AircraftConfiguration> retrieveAllAircraftConfigurations() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT ai FROM AircraftConfiguration ai");
        return query.getResultList();
    }
    
    @Override
    public void linkAircraft(Long aircraftId, Long aircraftConfigId) {
        Aircraft ac = em.find(Aircraft.class, aircraftId);
        AircraftConfiguration aircraftConfig = em.find(AircraftConfiguration.class, aircraftConfigId);
        aircraftConfig.setAircraft(ac);
    }
    
    @Override
    public void addCabin(Long cabinId, Long aircraftConfigId) {
        Cabin cabin = em.find(Cabin.class, cabinId);
        AircraftConfiguration aircraftConfig = em.find(AircraftConfiguration.class, aircraftConfigId);
        List<Cabin> listOfCabs = aircraftConfig.getListOfCabins();
        listOfCabs.add(cabin);
        aircraftConfig.setListOfCabins(listOfCabs);
    }
}
