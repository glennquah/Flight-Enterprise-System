/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.Cabin;
import java.util.List;
import javax.ejb.Remote;
import util.exception.AircraftDoesNotExistException;
import util.exception.AirportDoesNotExistException;
import util.exception.CabinDoesNotExistException;

/**
 *
 * @author Lenovo
 */
@Remote
public interface AircraftConfigurationSessionBeanRemote {
    public Long createAircraftConfiguration(AircraftConfiguration aircraftConfig, Long aircraftId) throws AirportDoesNotExistException;
    public List<AircraftConfiguration> retrieveAllAircraftConfigurations();
    public List<Cabin> retrieveCabinsWithId(Long Id) throws CabinDoesNotExistException;
    public void linkAircraft(Long aircraftId, Long aircraftConfigId) throws AircraftDoesNotExistException;
}
