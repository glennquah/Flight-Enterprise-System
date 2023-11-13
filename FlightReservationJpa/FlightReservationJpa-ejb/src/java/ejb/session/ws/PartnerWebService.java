/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/EjbWebService.java to edit this template
 */
package ejb.session.ws;

import ejb.session.stateless.AirportSessionBeanLocal;
import ejb.session.stateless.PartnerSessionBeanLocal;
import entity.Airport;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author Lenovo
 */
@WebService(serviceName = "PartnerWebService")
@Stateless()
public class PartnerWebService {

    @EJB(name = "AirportSessionBeanLocal")
    private AirportSessionBeanLocal airportSessionBeanLocal;

    @EJB(name = "PartnerSessionBeanLocal")
    private PartnerSessionBeanLocal partnerSessionBeanLocal;
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "login")
    public Long login(@WebParam(name = "email") String email,
                      @WebParam(name = "password") String password) throws InvalidLoginCredentialException {
        if(email.length() > 0 && password.length() > 0)
        {
            return partnerSessionBeanLocal.login(email, password);
        }
        else
        {
            throw new InvalidLoginCredentialException("Missing login credential!");
        }
    }
    
    @WebMethod(operationName = "getPartnerId")
    public Long getPartnerId(@WebParam(name = "email") String email) throws InvalidLoginCredentialException {
        return partnerSessionBeanLocal.getPartnerId(email);
    }
    
    
    @WebMethod(operationName = "retrieveAllAirports")
    public List<Airport> retrieveAllAirports() {
        return airportSessionBeanLocal.retrieveAllAirports();
    }
    
    
}
