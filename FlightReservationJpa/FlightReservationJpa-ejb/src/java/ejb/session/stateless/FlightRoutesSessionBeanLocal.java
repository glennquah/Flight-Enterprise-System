/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.FlightRoute;
import java.util.List;
import javax.ejb.Local;
import util.exception.AirportDoesNotExistException;
import util.exception.FlightRouteDoesNotExistException;

/**
 *
 * @author Lenovo
 */
@Local
public interface FlightRoutesSessionBeanLocal {
    public List<FlightRoute> retrieveAllFlightRoutes();
    public Long deleteFlightRoute(Long flightRouteId) throws FlightRouteDoesNotExistException;
    public Long createNewFlightRoute(Long airportOneId, Long airportTwoId) throws AirportDoesNotExistException;
    public Long createNewFlightRouteWithReturn(Long airportOneId, Long airportTwoId) throws AirportDoesNotExistException;
}
