/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import java.util.List;
import javax.ejb.Remote;
import util.exception.AircraftConfigurationDoesNotExistException;
import util.exception.FlightDoesNotExist;
import util.exception.FlightRouteDoesNotExistException;

/**
 *
 * @author Lenovo
 */
@Remote
public interface FlightSessionBeanRemote {
    public Long createNewFlight(Flight flight, Long flightRouteId, Long aircraftConfigId) throws FlightRouteDoesNotExistException, AircraftConfigurationDoesNotExistException;
    public List<Flight> retrieveAllFlights();
    public Flight getFlightWithId(Long id) throws FlightDoesNotExist;
    public long removeFlight(Long id) throws FlightDoesNotExist;
    public Integer getReservedSeats(Long id) throws FlightDoesNotExist;
    public Integer getTotalSeats(Long id) throws FlightDoesNotExist;
    public Long changeFlightNumber(Long flightIdNum, Integer newFlightNum) throws FlightDoesNotExist;
    public Long changeFlightRoute(Long flightIdNum, Long newFlightRouteId, Long oldFlightRouteId) throws FlightDoesNotExist;
    public Long changeFlightConfig(Long flightIdNum, Long newFlightConfigId, Long oldFlightConfigId) throws FlightDoesNotExist;
    public List<Flight> retrieveFlightsThatHasDepAndDest(Long originAirport, Long destAirport);
    public List<Flight> retrieveFlightsThatHasDepAndDestConnectingFlight(Long originAirport, Long destAirport);
}
