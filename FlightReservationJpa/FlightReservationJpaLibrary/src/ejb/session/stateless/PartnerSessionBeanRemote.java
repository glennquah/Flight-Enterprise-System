/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.FlightSchedule;
import entity.Partner;
import java.util.List;
import javax.ejb.Remote;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author admin
 */
@Remote
public interface PartnerSessionBeanRemote {
    public Long login(String email, String password) throws InvalidLoginCredentialException;
    public long linkCreditCard(long partnerId, String ccd);
    public Long createNewAccount(Partner newPartAccount);
    public long linkFlightSchedule(long partnerId, long flightScheduleId);
    public List<FlightSchedule> getFlightSchedules(long partnerId);
    public Long getPartnerId(String email);
}
