/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Cabin;
import entity.Flight;
import java.util.List;
import javax.ejb.Remote;
import util.exception.AircraftConfigurationDoesNotExistException;
import util.exception.FlightDoesNotExistException;
import util.exception.FlightRouteDisabledException;
import util.exception.FlightRouteDoesNotExistException;
import util.exception.FlightScheduleDoesNotExistException;

/**
 *
 * @author Lenovo
 */
@Remote
public interface FlightSessionBeanRemote {
    public Long createNewFlight(Flight flight, Long flightRouteId, Long aircraftConfigId) throws FlightRouteDisabledException, FlightRouteDoesNotExistException, AircraftConfigurationDoesNotExistException;
    public List<Flight> retrieveAllFlights();
    public Flight getFlightWithId(Long id) throws FlightDoesNotExistException;
    public long removeFlight(Long id) throws FlightDoesNotExistException;
    public Integer getReservedSeats(Long id) throws FlightDoesNotExistException;
    public Integer getTotalSeats(Long id) throws FlightDoesNotExistException;
    public Long changeFlightNumber(Long flightIdNum, Integer newFlightNum) throws FlightDoesNotExistException;
    public Long changeFlightRoute(Long flightIdNum, Long newFlightRouteId, Long oldFlightRouteId) throws FlightRouteDisabledException, FlightDoesNotExistException;
    public Long changeFlightConfig(Long flightIdNum, Long newFlightConfigId, Long oldFlightConfigId) throws FlightDoesNotExistException;
    public List<Cabin> getCabin(Integer flightNumber) throws FlightScheduleDoesNotExistException;
    public List<Flight> retrieveFlightsThatHasDepAndDest(Long originAirport, Long destAirport);
    public List<Flight> retrieveFlightsThatHasDepAndDestConnectingFlight(Long originAirport, Long destAirport);
    public Boolean haveComplementaryFlight(Integer flightNumber);
}
