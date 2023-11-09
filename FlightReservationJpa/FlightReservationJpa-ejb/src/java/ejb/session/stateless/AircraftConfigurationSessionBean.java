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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AircraftDoesNotExistException;
import util.exception.AirportDoesNotExistException;
import util.exception.CabinDoesNotExistException;

/**
 *
 * @author Lenovo
 */
@Stateless
public class AircraftConfigurationSessionBean implements AircraftConfigurationSessionBeanRemote, AircraftConfigurationSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createAircraftConfiguration(AircraftConfiguration aircraftConfig, Long aircraftId) throws AirportDoesNotExistException {
        try {
            Aircraft ac = em.find(Aircraft.class, aircraftId);
            aircraftConfig.setAircraft(ac);
            
            em.persist(aircraftConfig);
            em.flush();
        } catch (NoResultException e) {
            throw new AirportDoesNotExistException("Aircraft Does Not Exist!");
        }
        
        
        return aircraftConfig.getAircraftConfigurationId();
    }
    
    @Override
    public List<AircraftConfiguration> retrieveAllAircraftConfigurations() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT ai FROM AircraftConfiguration ai ORDER BY ai.aircraft.aircraftName ASC, ai.aircraftConfigName ASC");
        return query.getResultList();
    }
    
    @Override
    public List<Cabin> retrieveCabinsWithId(Long Id) throws CabinDoesNotExistException {
        try {
            AircraftConfiguration ac = em.find(AircraftConfiguration.class, Id);

            List<Cabin> cab = ac.getListOfCabins();
            cab.size();
            
            return cab;
        } catch (NoResultException e) {
            throw new CabinDoesNotExistException("Cabin Does Not Exist!");
        }
    }
    
    @Override
    public void linkAircraft(Long aircraftId, Long aircraftConfigId) throws AircraftDoesNotExistException {
        try {
            Aircraft ac = em.find(Aircraft.class, aircraftId);
            AircraftConfiguration aircraftConfig = em.find(AircraftConfiguration.class, aircraftConfigId);
            aircraftConfig.setAircraft(ac);
        } catch(NoResultException e) {
            throw new AircraftDoesNotExistException("Aircraft Does Not Exist!");
        }
    }
}
