/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
        Query query = em.createQuery("SELECT c FROM Customer e");
        return query.getResultList();
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
