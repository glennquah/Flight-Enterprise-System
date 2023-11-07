/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Fare;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import util.exception.FareDoesNotExistException;

/**
 *
 * @author admin
 */
@Stateless
public class FareSessionBean implements FareSessionBeanRemote, FareSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override 
    public Long updateFare(Long fareId, String fareBasisCode, BigDecimal fareAmount) throws FareDoesNotExistException {
        try {
            Fare fare = em.find(Fare.class, fareId);
        
            fare.setFareBasisCode(fareBasisCode);
            fare.setFareAmount(fareAmount);
            return fare.getId();
        } catch(NoResultException ex) {
            throw new FareDoesNotExistException("Fare Does Not Exist!");
        }
        
    }
}
