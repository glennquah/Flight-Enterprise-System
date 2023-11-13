/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Employee;
import entity.Partner;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author admin
 */
@Stateless
public class PartnerSessionBean implements PartnerSessionBeanRemote, PartnerSessionBeanLocal {
    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;
    
    @Override
    public Long createNewAccount(Partner newPartAccount) {
        em.persist(newPartAccount);
        em.flush();
        
        return newPartAccount.getAccountId();
    }
    
    @Override
    public Long login(String email, String password) throws InvalidLoginCredentialException
    {
        try
        {
            Query query = em.createQuery("SELECT p FROM Partner p WHERE p.email = :email");
            query.setParameter("email", email);
            Partner partner = (Partner)query.getSingleResult();
            
            if(partner.getPassword().equals(password))
            {
                return partner.getAccountId();
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
}
