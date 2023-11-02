/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.Cabin;
import java.util.List;
import javax.ejb.Local;
import util.exception.AircraftDoesNotExistException;
import util.exception.AirportDoesNotExistException;
import util.exception.CabinDoesNotExistException;

/**
 *
 * @author Lenovo
 */
@Local
public interface AircraftConfigurationSessionBeanLocal {
    public Long createAircraftConfiguration(AircraftConfiguration aircraftConfig, Long aircraftId) throws AirportDoesNotExistException;
    public List<AircraftConfiguration> retrieveAllAircraftConfigurations();
    public List<Cabin> retrieveCabinsWithId(Long Id) throws CabinDoesNotExistException;
    public void linkAircraft(Long aircraftId, Long aircraftConfigId) throws AircraftDoesNotExistException;
}
