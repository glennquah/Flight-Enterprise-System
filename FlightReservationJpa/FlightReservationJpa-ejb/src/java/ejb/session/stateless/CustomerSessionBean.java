/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.Employee;
import entity.FlightSchedule;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author Lenovo
 */
@Stateless
public class CustomerSessionBean implements CustomerSessionBeanRemote, CustomerSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewAccount(Customer newCustAccount) {
        em.persist(newCustAccount);
        em.flush();
        
        return newCustAccount.getAccountId();
    }
    
    @Override
    public List<Customer> retrieveAllAccounts() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT c FROM Customer c");
        return query.getResultList();
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public Long login(String email, String password) throws InvalidLoginCredentialException
    {
        try
        {
            Query query = em.createQuery("SELECT e FROM Customer e WHERE e.email = :email");
            query.setParameter("email", email);
            Customer cust = (Customer)query.getSingleResult();
            
            if(cust.getPassword().equals(password))
            {
                return cust.getAccountId();
            }
            else
            {
                throw new InvalidLoginCredentialException("Invalid login credential");
            }
        }
        catch(NoResultException ex)
        {
            throw new InvalidLoginCredentialException("Invalid login credential");
        }
    }
    
    @Override
    public long linkFlightSchedule(long customerId, long flightScheduleId) {
        Customer cust = em.find(Customer.class, customerId);
        FlightSchedule fs = em.find(FlightSchedule.class, flightScheduleId);
        List<Customer> custs = fs.getCustomer();
        custs.size();
        custs.add(cust);
        List<FlightSchedule> flightSchedules = cust.getListOfFlightSchedules();
        flightSchedules.size();
        flightSchedules.add(fs);
        return customerId;
    }
    
    @Override
    public long linkCreditCard(long customerId, String ccd) {
        Customer cust = em.find(Customer.class, customerId);
        cust.setCreditCardNumber(ccd);
        return customerId;
    }
    
    @Override
    public List<FlightSchedule> getFlightSchedules(long customerId) {
        Customer cust = em.find(Customer.class, customerId);
        List<FlightSchedule> listOFlightSchedules = cust.getListOfFlightSchedules();
        listOFlightSchedules.size();
        return listOFlightSchedules;
    }
}
