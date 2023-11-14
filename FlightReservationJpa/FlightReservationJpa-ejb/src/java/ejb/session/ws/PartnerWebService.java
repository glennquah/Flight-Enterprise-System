/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/EjbWebService.java to edit this template
 */
package ejb.session.ws;

import ejb.session.stateless.AirportSessionBeanLocal;
import ejb.session.stateless.FlightScheduleSessionBeanLocal;
import ejb.session.stateless.CabinCustomerSessionBeanLocal;
import ejb.session.stateless.FareSessionBeanLocal;
import ejb.session.stateless.FlightSchedulePlanSessionBeanLocal;
import ejb.session.stateless.FlightSessionBeanLocal;
import ejb.session.stateless.PartnerSessionBeanLocal;
import ejb.session.stateless.ReservationDetailsSessionBeanLocal;
import entity.Airport;
import entity.Cabin;
import entity.Customer;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import entity.ReservationDetails;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import entity.Flight;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import util.exception.FlightDoesNotExistException;
import util.exception.FlightScheduleDoesNotExistException;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author Lenovo
 */
@WebService(serviceName = "PartnerWebService")
@Stateless()
public class PartnerWebService {

    @EJB(name = "FlightScheduleSessionBeanLocal")
    private FlightScheduleSessionBeanLocal flightScheduleSessionBeanLocal;
    
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
    
    @WebMethod(operationName = "retrieveFlightScheduleInPlan")
    public List<FlightSchedule> retrieveFlightScheduleInPlan(@WebParam(name = "listOfFlightSchedulePlan") List<FlightSchedulePlan> listOfFlightSchedulePlan) {
        return flightScheduleSessionBeanLocal.retrieveFlightScheduleInPlan(listOfFlightSchedulePlan);
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
    
    
    @WebMethod(operationName = "retrieveFlightSchedulePlanWithSameTiming")
    public List<FlightSchedule> retrieveFlightSchedulePlanWithSameTiming(@WebParam(name = "listOfFlightSchedulePlan") List<FlightSchedulePlan> listOfFlightSchedulePlan, @WebParam(name = "departureDate") Date departureDate) {
        return flightScheduleSessionBeanLocal.retrieveFlightSchedulePlanWithSameTiming(listOfFlightSchedulePlan, departureDate);
    }
    
    @WebMethod(operationName = "retrieveFlightSchedulePlanWith3DaysBefore")
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysBefore(@WebParam(name = "listOfFlightSchedulePlan") List<FlightSchedulePlan> listOfFlightSchedulePlan, @WebParam(name = "departureDate") Date departureDate) {
        return flightScheduleSessionBeanLocal.retrieveFlightSchedulePlanWith3DaysBefore(listOfFlightSchedulePlan, departureDate);
    }
    
    @WebMethod(operationName = "retrieveFlightSchedulePlanWith3DaysAfter")
    public List<FlightSchedule> retrieveFlightSchedulePlanWith3DaysAfter(@WebParam(name = "listOfFlightSchedulePlan") List<FlightSchedulePlan> listOfFlightSchedulePlan, @WebParam(name = "departureDate") Date departureDate) {
        return flightScheduleSessionBeanLocal.retrieveFlightSchedulePlanWith3DaysAfter(listOfFlightSchedulePlan, departureDate);
    }
    
    @WebMethod(operationName = "getFlightScheduleWithId")
    public FlightSchedule getFlightScheduleWithId(long id) throws FlightScheduleDoesNotExistException {
        return flightScheduleSessionBeanLocal.getFlightScheduleWithId(id);
    }
    
    @WebMethod(operationName = "retrieveDateOfFlightPicked")
    public Date retrieveDateOfFlightPicked(@WebParam(name = "id") Long id) throws FlightDoesNotExistException {
        return flightScheduleSessionBeanLocal.retrieveDateOfFlightPicked(id);
    }
    
    @WebMethod(operationName = "getCabins")
    public List<Cabin> getCabins(@WebParam(name = "id") long id) throws FlightScheduleDoesNotExistException {
        return flightScheduleSessionBeanLocal.getCabins(id);
    }
    
    @WebMethod(operationName = "getCabinSeats")
    public char[][] getCabinSeats(@WebParam(name = "id") long id, @WebParam(name = "cabName") String cabName) throws FlightScheduleDoesNotExistException {
       return flightScheduleSessionBeanLocal.getCabinSeats(id, cabName);
    }
    
    @WebMethod(operationName = "getIslesPlan")
    public Integer[] getIslesPlan(@WebParam(name = "id") long id, @WebParam(name = "cabName") String cabName) throws FlightScheduleDoesNotExistException {
       return flightScheduleSessionBeanLocal.getIslesPlan(id, cabName);
    }
    
    @WebMethod(operationName = "bookSeat")
    public long bookSeat(@WebParam(name = "id") long id, @WebParam(name = "cabName") String cabName, @WebParam(name = "seat") int seat, @WebParam(name = "letter") char letter) throws FlightScheduleDoesNotExistException {
        return flightScheduleSessionBeanLocal.bookSeat(id, cabName, seat, letter);
    }
    
    @WebMethod(operationName = "getHighestFareUsingCabinName")
    public long getHighestFareUsingCabinName(@WebParam(name = "cabName") String cabName, @WebParam(name = "id") long id) throws FlightScheduleDoesNotExistException {
        return flightScheduleSessionBeanLocal.getHighestFareUsingCabinName(cabName, id);
    }
    
    @WebMethod(operationName = "checkSeatIfAvailable")
    public Boolean checkSeatIfAvailable(@WebParam(name = "flightSchedId") long flightSchedId, @WebParam(name = "cabName") String cabinName,@WebParam(name = "rowNum") int rowNum, @WebParam(name = "seat") char seat) {
        return flightScheduleSessionBeanLocal.checkSeatIfAvailable(flightSchedId, cabinName, rowNum, seat);
    }
    
    @WebMethod(operationName = "getReservationDetails")
    public List<ReservationDetails> getReservationDetails(@WebParam(name = "flightScheduleId") long flightScheduleId, @WebParam(name = "customerId") long customerId) throws  FlightScheduleDoesNotExistException {
        return flightScheduleSessionBeanLocal.getReservationDetails(flightScheduleId, customerId);
    }
    
    @WebMethod(operationName = "retrieveFlightSchedulePlanAfterTiming")
    public List<FlightSchedule> retrieveFlightSchedulePlanAfterTiming(@WebParam(name = "listOfFlightSchedulePlan") List<FlightSchedulePlan> listOfFlightSchedulePlan, @WebParam(name = "departureDateTime") Date departureDateTime) {
        return flightScheduleSessionBeanLocal.retrieveFlightSchedulePlanAfterTiming(listOfFlightSchedulePlan, departureDateTime);
    }
}
