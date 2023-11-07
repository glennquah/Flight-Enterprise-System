/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import java.math.BigDecimal;
import javax.ejb.Local;
import util.exception.FareDoesNotExistException;

/**
 *
 * @author admin
 */
@Local
public interface FareSessionBeanLocal {
    public Long updateFare(Long fareId, String fareBasisCode, BigDecimal fareAmount) throws FareDoesNotExistException;
    
}
