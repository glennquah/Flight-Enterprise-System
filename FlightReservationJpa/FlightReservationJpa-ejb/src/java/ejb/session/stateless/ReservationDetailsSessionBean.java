/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.Fare;
import entity.FlightSchedule;
import entity.Partner;
import entity.ReservationDetails;
import java.util.Collections;
import java.util.Comparator;
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
public class ReservationDetailsSessionBean implements ReservationDetailsSessionBeanRemote, ReservationDetailsSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createReservationDetails(ReservationDetails reservationDetails, long customerId, long flightScheduleId, long lowestFareId) {
        Customer cust = em.find(Customer.class, customerId);
        FlightSchedule fs = em.find(FlightSchedule.class, flightScheduleId);
        reservationDetails.setCustomer(cust);
        reservationDetails.setFlightSchedule(fs);
        
        //synch customer to new res details
        List<ReservationDetails> listOfResCust = cust.getListOfReservationDetails();
        listOfResCust.size();
        listOfResCust.add(reservationDetails);
        cust.setListOfReservationDetails(listOfResCust);
        
        //synch flightshcedule to res details
        List<ReservationDetails> listOfResFs = fs.getListOfReservationDetails();
        listOfResFs.size();
        listOfResFs.add(reservationDetails);
        fs.setListOfReservationDetails(listOfResFs);
        
        Fare f = em.find(Fare.class, lowestFareId);
        reservationDetails.setFare(f);
        
        em.persist(reservationDetails);
        em.flush();
        
        return reservationDetails.getId();
    }
    
    
    @Override
    public Long createReservationDetails(long reservationDetailsId, long customerId, long flightScheduleId, long lowestFareId) {
        Customer cust = em.find(Customer.class, customerId);
        FlightSchedule fs = em.find(FlightSchedule.class, flightScheduleId);
        ReservationDetails reservationDetails = em.find(ReservationDetails.class, reservationDetailsId);
        reservationDetails.setCustomer(cust);
        reservationDetails.setFlightSchedule(fs);
        
        //synch customer to new res details
        List<ReservationDetails> listOfResCust = cust.getListOfReservationDetails();
        listOfResCust.size();
        listOfResCust.add(reservationDetails);
        cust.setListOfReservationDetails(listOfResCust);
        
        //synch flightshcedule to res details
        List<ReservationDetails> listOfResFs = fs.getListOfReservationDetails();
        listOfResFs.size();
        listOfResFs.add(reservationDetails);
        fs.setListOfReservationDetails(listOfResFs);
        
        Fare f = em.find(Fare.class, lowestFareId);
        reservationDetails.setFare(f);
        
        em.persist(reservationDetails);
        em.flush();
       
        return reservationDetails.getId();
    }

    @Override
    public Long createReservationDetailsForPartner(ReservationDetails reservationDetails, long partnerId, long flightScheduleId, long lowestFareId) {
        Partner partner = em.find(Partner.class, partnerId);
        FlightSchedule fs = em.find(FlightSchedule.class, flightScheduleId);
        reservationDetails.setPartner(partner);
        reservationDetails.setFlightSchedule(fs);
        
        //synch customer to new res details
        List<ReservationDetails> listOfResPartner = partner.getListOfReservationDetails();
        listOfResPartner.size();
        listOfResPartner.add(reservationDetails);
        partner.setListOfReservationDetails(listOfResPartner);
        
        //synch flightshcedule to res details
        List<ReservationDetails> listOfResFs = fs.getListOfReservationDetails();
        listOfResFs.size();
        listOfResFs.add(reservationDetails);
        fs.setListOfReservationDetails(listOfResFs);
        
        Fare f = em.find(Fare.class, lowestFareId);
        reservationDetails.setFare(f);
        
        em.persist(reservationDetails);
        em.flush();
        
        return reservationDetails.getId();
    }
    
    @Override
    public List<ReservationDetails> getReservationsWithFsId(long id) {
        FlightSchedule fs = em.find(FlightSchedule.class, id);
        List<ReservationDetails> listOfRd = fs.getListOfReservationDetails();

        // Sort the list of ReservationDetails
        Collections.sort(listOfRd, new Comparator<ReservationDetails>() {
            public int compare(ReservationDetails rd1, ReservationDetails rd2) {
                // First, sort by rowNum
                int compareByRowNum = Integer.compare(rd1.getRowNum(), rd2.getRowNum());
                if (compareByRowNum != 0) {
                    return compareByRowNum;
                }
                // If rowNums are equal, then sort by seatLetter
                return Character.compare(rd1.getSeatLetter(), rd2.getSeatLetter());
            }
        });

        return listOfRd;
    }
}
