/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Lenovo
 */
@Remote
public interface AircraftConfigurationSessionBeanRemote {
    public Long createAircraftConfiguration(AircraftConfiguration aircraftConfig);
    public List<AircraftConfiguration> retrieveAllAircraftConfigurations();
    public void linkAircraft(Long aircraftId, Long aircraftConfigId);
    public void addCabin(Long cabinId, Long aircraftConfigId);
}
