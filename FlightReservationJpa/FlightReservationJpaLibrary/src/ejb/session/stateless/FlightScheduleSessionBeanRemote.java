/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.FlightSchedule;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import util.exception.ConflictingFlightScheduleException;
import util.exception.FlightScheduleDoesNotExistException;

/**
 *
 * @author admin
 */
@Remote
public interface FlightScheduleSessionBeanRemote {
    public FlightSchedule createNewFlightSchedule(Integer flightNumber, FlightSchedule flightSchedule) throws ConflictingFlightScheduleException;
    
    public void checkForConflictingFlights(Integer flightNumber, Date departureDate, Duration duration) throws ConflictingFlightScheduleException;
    
    public void checkForConflictingFlights(Integer flightNumber, List<Date> departureDates, List<Duration> durations) throws ConflictingFlightScheduleException;
    
    public List<FlightSchedule> getFlightScheduleWithId(Long id) throws FlightScheduleDoesNotExistException;
    
    public Long changeFlightScheduleDateTime(Long flightScheduleId, Date departureDateTime, Duration duration) throws FlightScheduleDoesNotExistException;
}
