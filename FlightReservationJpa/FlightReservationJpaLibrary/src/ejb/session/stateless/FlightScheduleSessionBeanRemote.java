/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Cabin;
import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import util.exception.FlightDoesNotExist;

/**
 *
 * @author admin
 */
@Remote
public interface FlightScheduleSessionBeanRemote {
    public FlightSchedule createNewFlightSchedule(FlightSchedule flightSchedule);
    public List<FlightSchedule> retrieveFlightSchedulePlanWithSameTiming(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate);
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysBefore(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate);
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysAfter(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate);
    public List<FlightSchedule> retrieveFlightScheduleInPlan(List<FlightSchedulePlan> listOfFlightSchedulePlan);
    public Date retrieveDateOfFlightPicked(Long id) throws FlightDoesNotExist;
    public List<FlightSchedule> retrieveFlightSchedulePlanAfterTiming(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDateTime);
    public FlightSchedule getFlightScheduleWithId(long id);
    public List<Cabin> getCabins(long id);
}
