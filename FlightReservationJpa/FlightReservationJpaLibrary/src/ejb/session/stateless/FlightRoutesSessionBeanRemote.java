/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.FlightRoute;
import java.util.List;
import javax.ejb.Remote;
import util.exception.AirportDoesNotExistException;
import util.exception.FlightRouteAlreadyExistException;
import util.exception.FlightRouteDoesNotExistException;
import util.exception.FlightScheduleDoesNotExistException;

/**
 *
 * @author Lenovo
 */
@Remote
public interface FlightRoutesSessionBeanRemote {
    public Long createNewFlightRoute(Long airportOneId, Long airportTwoId) throws AirportDoesNotExistException, FlightRouteAlreadyExistException;
    public Long createNewFlightRouteWithReturn(Long airportOneId, Long airportTwoId) throws AirportDoesNotExistException, FlightRouteAlreadyExistException;
    public List<FlightRoute> retrieveAllFlightRoutes();
    public Long deleteFlightRoute(Long flightRouteId) throws FlightRouteDoesNotExistException;
    public FlightRoute getFlightRouteWithId(Long id);
    public FlightRoute getFlightRouteWithFS(Long flightSchedulePlanId) throws FlightScheduleDoesNotExistException;
    public FlightRoute getFRUsingFSId(Long flightSchedId);
    public long getFlightRouteIdWithOGandDest(String og, String dest) throws AirportDoesNotExistException;
    public Long getReturnFlightRouteId(Long flightRouteId);
}
