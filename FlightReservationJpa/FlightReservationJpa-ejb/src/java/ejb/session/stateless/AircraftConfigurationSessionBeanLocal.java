/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lenovo
 */
@Local
public interface AircraftConfigurationSessionBeanLocal {
    public Long createAircraftConfiguration(AircraftConfiguration aircraftConfig);
    public List<AircraftConfiguration> retrieveAllAircraftConfigurations();
    public void linkAircraft(Long aircraftId, Long aircraftConfigId);
    public void addCabin(Long cabinId, Long aircraftConfigId);
}
