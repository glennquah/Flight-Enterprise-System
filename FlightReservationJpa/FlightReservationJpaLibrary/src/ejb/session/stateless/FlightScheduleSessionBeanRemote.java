/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Cabin;
import entity.Flight;
import entity.FlightSchedule;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
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
@Remote
public interface FlightScheduleSessionBeanRemote {
    public FlightSchedule createNewFlightSchedule(Integer flightNumber, FlightSchedule flightSchedule) throws AircraftConfigurationDoesNotExistException, ConflictingFlightScheduleException;
    public void checkForConflictingFlights(Integer flightNumber, Date departureDate, double duration, double layover) throws ConflictingFlightScheduleException;
    public void checkForConflictingFlights(Integer flightNumber, List<Date> departureDates, List<Double> durations, List<Double> layovers, Boolean haveReturn) throws ConflictingFlightScheduleException;
    public List<FlightSchedule> getFlightSchedulesWithId(Long id) throws FlightScheduleDoesNotExistException;
    public Long changeFlightScheduleDateTime(Long flightScheduleId, Date departureDateTime, double duration) throws FlightScheduleDoesNotExistException;
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
    public List<String> getCabinSeatsList(long id, String cabName) throws FlightScheduleDoesNotExistException;
    public void checkForConflictingFlights(Integer flightNumber, List<Date> departureDates, List<Double> durations, List<Double> layovers, List<String> haveReturns) throws ConflictingFlightScheduleException;
    public List<Cabin> getCabins(long id, Boolean detach) throws FlightScheduleDoesNotExistException;
    public List<FlightSchedule> retrieveFlightSchedulePlanWithSameTimingPartner(Date departureDate, long depAirport, long destAirport);
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysAfterPartner(Date departureDate, long depAirport, long destAirport);
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysBeforePartner(Date departureDate, long depAirport, long destAirport);
    public long getAirportIdWithFlightScheduleId(long flightSchedId);
    public List<FlightSchedule> retrieveFlightSchedulePlanWithSameTimingConnecting(long depAirport, List<Long> listOfHubIds, Date departureDate);
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysBeforeConnecting(long depAirport, List<Long> listOfHubIds, Date departureDate);
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysAfterConnecting(long depAirport, List<Long> listOfHubIds, Date departureDate);
    public FlightSchedule getFlightScheduleWithId(long id, Boolean detach) throws FlightScheduleDoesNotExistException;;
}
