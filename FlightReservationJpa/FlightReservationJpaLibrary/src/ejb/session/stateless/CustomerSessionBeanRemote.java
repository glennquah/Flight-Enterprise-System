/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import java.util.List;
import javax.ejb.Remote;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author Lenovo
 */
@Remote
public interface CustomerSessionBeanRemote {
    public List<Customer> retrieveAllAccounts();
    public Long createNewAccount(Customer newCustAccount);
    public Long login(String email, String password) throws InvalidLoginCredentialException;
    public long linkFlightSchedule(long customerId, long flightScheduleId, String ccd);
}
