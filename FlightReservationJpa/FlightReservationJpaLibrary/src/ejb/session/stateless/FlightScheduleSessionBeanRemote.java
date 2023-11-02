/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author admin
 */
@Remote
public interface FlightScheduleSessionBeanRemote {
    public FlightSchedule createNewFlightSchedule(FlightSchedule flightSchedule);
}
