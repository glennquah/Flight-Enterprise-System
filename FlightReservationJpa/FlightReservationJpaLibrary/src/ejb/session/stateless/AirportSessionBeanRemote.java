/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Airport;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Lenovo
 */
@Remote
public interface AirportSessionBeanRemote {
    public Long createNewAirport(Airport airport);
    public List<Airport> retrieveAllAirports();
    public List<Long> getListOfHubsId();
    public List<Long> getListOfHubsIdConnecting(long destAirportId);
    public Airport getAirportOrigin(long fsId);
    public Airport getAirportDest(long fsId);
    public String getAirportCodeWithAirportId(long airportId);
    public long getAirportIdWithCode(String code);
}
