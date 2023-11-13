/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/EjbWebService.java to edit this template
 */
package ejb.session.ws;

import ejb.session.stateless.AirportSessionBeanLocal;
import ejb.session.stateless.CabinCustomerSessionBeanLocal;
import ejb.session.stateless.FareSessionBeanLocal;
import ejb.session.stateless.FlightSchedulePlanSessionBeanLocal;
import ejb.session.stateless.FlightSessionBeanLocal;
import ejb.session.stateless.PartnerSessionBeanLocal;
import ejb.session.stateless.ReservationDetailsSessionBeanLocal;
import entity.Airport;
import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import entity.ReservationDetails;
import java.math.BigDecimal;
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

    @EJB(name = "ReservationDetailsSessionBeanLocal")
    private ReservationDetailsSessionBeanLocal reservationDetailsSessionBeanLocal;

    @EJB(name = "FareSessionBeanLocal")
    private FareSessionBeanLocal fareSessionBeanLocal;

    @EJB(name = "CabinCustomerSessionBeanLocal")
    private CabinCustomerSessionBeanLocal cabinCustomerSessionBeanLocal;

    @EJB(name = "FlightSessionBeanLocal")
    private FlightSessionBeanLocal flightSessionBeanLocal;

    @EJB(name = "FlightSchedulePlanSessionBeanLocal")
    private FlightSchedulePlanSessionBeanLocal flightSchedulePlanSessionBeanLocal;

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
    
    @WebMethod(operationName = "retrieveFlightSchedulePlanWithSameFlight")
    public List<FlightSchedulePlan> retrieveFlightSchedulePlanWithSameFlight(@WebParam(name = "listOfFlightsToHub") List<Flight> listOfFlightsToHub) {
        return flightSchedulePlanSessionBeanLocal.retrieveFlightSchedulePlanWithSameFlight(listOfFlightsToHub);
    }
    
    
    @WebMethod(operationName = "retrieveFlightsThatHasDepAndDest")
    public List<Flight> retrieveFlightsThatHasDepAndDest(@WebParam(name = "depAirport") long depAirport, @WebParam(name = "hubId") long hubId) {
        return flightSessionBeanLocal.retrieveFlightsThatHasDepAndDest(depAirport, hubId);
    }
    
    @WebMethod(operationName = "getHighestFareIdInCabin")
    public Long getHighestFareIdInCabin(@WebParam(name = "cabinId") long cabinId) {
        return cabinCustomerSessionBeanLocal.getHighestFareIdInCabin(cabinId);
    }
    
    @WebMethod(operationName = "getFareUsingId")
    public BigDecimal getFareUsingId(@WebParam(name = "fareId") long fareId) {
        return fareSessionBeanLocal.getFareUsingId(fareId);
    }
    
    @WebMethod(operationName = "createReservationDetails")
    public Long createReservationDetails(@WebParam(name = "reservationDetails") ReservationDetails reservationDetails,
                                         @WebParam(name = "partnerId") long partnerId,
                                         @WebParam(name = "flightScheduleId") long flightScheduleId,
                                         @WebParam(name = "highestFareId") long highestFareId) {
        return reservationDetailsSessionBeanLocal.createReservationDetails(reservationDetails, partnerId, flightScheduleId, highestFareId);
    }
    
    @WebMethod(operationName = "linkCreditCard")
    public Long linkCreditCard(@WebParam(name = "partnerId") long partnerId, @WebParam(name = "ccd") String ccd) {
        return partnerSessionBeanLocal.linkCreditCard(partnerId, ccd);
    }
    
    @WebMethod(operationName = "linkFlightSchedule")
    public Long linkFlightSchedule(@WebParam(name = "partnerId") long partnerId, @WebParam(name = "fsId") long fsId) {
        return partnerSessionBeanLocal.linkFlightSchedule(partnerId, fsId);
    }
    
    @WebMethod(operationName = "getFlightSchedules")
    public List<FlightSchedule> getFlightSchedules(@WebParam(name = "partnerId") long partnerId) {
        return partnerSessionBeanLocal.getFlightSchedules(partnerId);
    }
    
    
}
