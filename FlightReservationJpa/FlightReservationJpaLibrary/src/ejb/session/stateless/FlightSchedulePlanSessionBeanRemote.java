/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Cabin;
import entity.Fare;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.math.BigDecimal;
import entity.Flight;
import entity.FlightRoute;
import java.util.List;
import javax.ejb.Remote;
import util.exception.AirportDoesNotExistException;
import util.exception.ConflictingFlightScheduleException;
import util.exception.FlightDoesNotExistException;
import util.exception.FlightSchedulePlanDoesNotExistException;

/**
 *
 * @author admin
 */
@Remote
public interface FlightSchedulePlanSessionBeanRemote {
    public Long createSingleFlightSchedulePlan(FlightSchedulePlan flightSchedulePlan, Long flightScheduleid) throws FlightDoesNotExistException, ConflictingFlightScheduleException;
    
    public Long createMultipleFlightSchedulePlan(FlightSchedulePlan flightSchedulePlan, List<Long> flightScheduleIds) throws FlightDoesNotExistException, ConflictingFlightScheduleException;
    
    public List<FlightSchedulePlan> retrieveAllFlightSchedulePlans();
    
    public List<Fare> getFlightSchedulePlanFares(Long flightSchedulePlanId);
    
    public FlightSchedulePlan retrieveFlightSchedulePlan(Long flightSchedulePlanId);
    
    public void deleteFlightSchedulePlan(Long flightSchedulePlanId);
    
    public void createFare(Long flightSchedulePlanId, Long cabinId, List<String> fareBasisCodes, List<BigDecimal> fareAmounts);
    
    public List<FlightSchedule> retrieveFlightSchedule(Long flightSchedulePlanId);
    
    public List<FlightSchedulePlan> retrieveFlightSchedulePlanWithSameFlight(List<Flight> listOfFlights);
    
    public FlightRoute retrieveFlightRouteFromFlightSchedule(Long fsId);

}
