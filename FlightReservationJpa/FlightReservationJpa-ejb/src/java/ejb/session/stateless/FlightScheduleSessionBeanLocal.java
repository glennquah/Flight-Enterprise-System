/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Cabin;
import entity.FlightSchedule;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import util.exception.ConflictingFlightScheduleException;
import util.exception.FlightScheduleDoesNotExistException;
import entity.FlightSchedulePlan;
import entity.ReservationDetails;
import java.math.BigDecimal;
import java.time.LocalTime;
import util.exception.AircraftConfigurationDoesNotExistException;
import util.exception.FlightDoesNotExistException;
import util.exception.FlightScheduleBookedException;

/**
 *
 * @author admin
 */
@Local
public interface FlightScheduleSessionBeanLocal {
    public FlightSchedule createNewFlightSchedule(Integer flightNumber, FlightSchedule flightSchedule) throws AircraftConfigurationDoesNotExistException, ConflictingFlightScheduleException;
    public List<FlightSchedule> getFlightSchedulesWithId(Long id) throws FlightScheduleDoesNotExistException;
    public Long changeFlightScheduleDateTime(Long flightScheduleId, Date departureDateTime, Duration duration) throws FlightScheduleDoesNotExistException;
    public List<FlightSchedule> retrieveFlightSchedulePlanWithSameTiming(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate) ;
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysBefore(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate);
    public List<FlightSchedule> retrieveFlightSchedulePlanWith1DayAfter(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate);
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysAfter(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate);
    public List<FlightSchedule> retrieveFlightScheduleInPlan(List<FlightSchedulePlan> listOfFlightSchedulePlan);
    public Date retrieveDateOfFlightPicked(Long id) throws FlightDoesNotExistException;
    public List<FlightSchedule> retrieveFlightSchedulePlanAfterTiming(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDateTime);
    public FlightSchedule getFlightScheduleWithId(long id) throws FlightScheduleDoesNotExistException ;
    public List<Cabin> getCabins(long id) throws FlightScheduleDoesNotExistException;
    public char[][] getCabinSeats(long id, String cabName) throws FlightScheduleDoesNotExistException;
    public Integer[] getIslesPlan(long id, String cabName) throws FlightScheduleDoesNotExistException;
    public long bookSeat(long id, String cabName, int seat, char letter) throws FlightScheduleDoesNotExistException ;
    public long getLowestFareUsingCabinName(String cabName, long id) throws FlightScheduleDoesNotExistException;
    public List<ReservationDetails> getReservationDetails(long flightScheduleId, long customerId) throws  FlightScheduleDoesNotExistException;
    public Long deleteFlightSchedule(Long flightScheduleId) throws FlightScheduleBookedException;
    public Boolean checkSeatIfAvailable(long flightSchedId, String cabinName, int rowNum, char seat);
    public long getHighestFareUsingCabinName(String cabName, long id) throws FlightScheduleDoesNotExistException;
    public List<ReservationDetails> getReservationDetailsPartner(long flightScheduleId, long partnerId) throws  FlightScheduleDoesNotExistException;
    public List<List<Character>> getCabinSeatsList(long id, String cabName) throws FlightScheduleDoesNotExistException;
    public void checkForConflictingFlights(Integer flightNumber, Date departureDate, Duration duration, Duration layover) throws ConflictingFlightScheduleException;
    public void checkForConflictingFlights(Integer flightNumber, List<Date> departureDates, List<Duration> durations, List<Duration> layovers, List<String> haveReturns) throws ConflictingFlightScheduleException;
    public void checkForConflictingFlights(Integer flightNumber, List<Date> departureDates, List<Duration> durations, List<Duration> layovers, Boolean haveReturn) throws ConflictingFlightScheduleException;
    public List<FlightSchedule> retrieveFlightSchedulePlanWithSameTiming(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate, Boolean detach);
    public List<Cabin> getCabins(long id, Boolean detach) throws FlightScheduleDoesNotExistException;
}
