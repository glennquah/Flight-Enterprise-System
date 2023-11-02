/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.FlightSchedulePlan;
import javax.ejb.Remote;
import util.exception.AirportDoesNotExistException;
import util.exception.ConflictingFlightScheduleException;

/**
 *
 * @author admin
 */
@Remote
public interface FlightSchedulePlanSessionBeanRemote {
    public Long createSingleFlightSchedulePlan(FlightSchedulePlan flightSchedulePlan, Long flightScheduleid) throws AirportDoesNotExistException, ConflictingFlightScheduleException;
}