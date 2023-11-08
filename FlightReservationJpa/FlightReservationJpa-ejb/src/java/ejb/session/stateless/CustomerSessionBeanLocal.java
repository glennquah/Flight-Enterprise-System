/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.FlightSchedule;
import java.util.List;
import javax.ejb.Local;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author Lenovo
 */
@Local
public interface CustomerSessionBeanLocal {
    public List<Customer> retrieveAllAccounts();
    public Long createNewAccount(Customer newCustAccount);
    public Long login(String email, String password) throws InvalidLoginCredentialException;
    public long linkFlightSchedule(long customerId, long flightScheduleId, String ccd);
    public List<FlightSchedule> getFlightSchedules(long customerId);
}
