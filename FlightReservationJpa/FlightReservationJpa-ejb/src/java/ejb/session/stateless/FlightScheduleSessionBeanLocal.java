/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Cabin;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import util.exception.FlightDoesNotExist;

/**
 *
 * @author admin
 */
@Local
public interface FlightScheduleSessionBeanLocal {
    public FlightSchedule createNewFlightSchedule(FlightSchedule flightSchedule);
    public List<FlightSchedule> retrieveFlightSchedulePlanWithSameTiming(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate) ;
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysBefore(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate);
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysAfter(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate);
    public List<FlightSchedule> retrieveFlightScheduleInPlan(List<FlightSchedulePlan> listOfFlightSchedulePlan);
    public Date retrieveDateOfFlightPicked(Long id) throws FlightDoesNotExist;
    public List<FlightSchedule> retrieveFlightSchedulePlanAfterTiming(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDateTime);
    public FlightSchedule getFlightScheduleWithId(long id);
    public List<Cabin> getCabins(long id);
    public char[][] getCabinSeats(long id, String cabName);
    public Integer[] getIslesPlan(long id, String cabName);
    public long bookSeat(long id, String cabName, int seat, char letter);
}
