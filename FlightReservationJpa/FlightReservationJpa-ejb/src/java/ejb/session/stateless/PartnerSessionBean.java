/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.Employee;
import entity.FlightSchedule;
import entity.Partner;
import java.util.List;
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
    
    @Override
    public long linkCreditCard(long partnerId, String ccd) {
        Partner partner = em.find(Partner.class, partnerId);
        partner.setCreditCardNumber(ccd);
        return partnerId;
    }
    
    @Override
    public long linkFlightSchedule(long partnerId, long flightScheduleId) {
        Partner partner = em.find(Partner.class, partnerId);
        FlightSchedule fs = em.find(FlightSchedule.class, flightScheduleId);
        List<Partner> partners = fs.getPartners();
        partners.size();
        partners.add(partner);
        fs.setPartners(partners);
        
        List<FlightSchedule> flightSchedules = partner.getListOfFlightSchedules();
        flightSchedules.size();
        flightSchedules.add(fs);
        partner.setListOfFlightSchedules(flightSchedules);
        
        return partnerId;
    }
    
    @Override
    public List<FlightSchedule> getFlightSchedules(long partnerId) {
        Partner partner = em.find(Partner.class, partnerId);
        List<FlightSchedule> listOFlightSchedules = partner.getListOfFlightSchedules();
        listOFlightSchedules.size();
        return listOFlightSchedules;
    }

    @Override
    public Long getPartnerId(String email) {
        Query query = em.createQuery("SELECT p FROM Partner p WHERE p.email = :email");
        query.setParameter("email", email);
        Partner partner = (Partner)query.getSingleResult();
        
        return partner.getAccountId();
    }
}
