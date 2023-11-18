/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Cabin;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Remote;
import util.exception.AircraftConfigurationDoesNotExistException;

/**
 *
 * @author Lenovo
 */
@Remote
public interface CabinCustomerSessionBeanRemote {
    public Long createCabin(Cabin cabin, Long aircraftConfigId) throws AircraftConfigurationDoesNotExistException; 
    public List<Cabin> retrieveAllCabins();
    public long getLowestFareIdInCabin(long id);
    public Cabin createCabinOnly(Cabin cabin);
    public long getHighestFareIdInCabin(long id);
    public Cabin getCabinFromRd(long rdId);
}
