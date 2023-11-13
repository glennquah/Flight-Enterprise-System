/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.FlightSchedule;
import entity.Partner;
import java.util.List;
import javax.ejb.Local;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author admin
 */
@Local
public interface PartnerSessionBeanLocal {
    public Long login(String email, String password) throws InvalidLoginCredentialException;
    public Long createNewAccount(Partner newPartAccount);
    public long linkCreditCard(long partnerId, String ccd);
    public long linkFlightSchedule(long partnerId, long flightScheduleId);
    public List<FlightSchedule> getFlightSchedules(long partnerId);
}
