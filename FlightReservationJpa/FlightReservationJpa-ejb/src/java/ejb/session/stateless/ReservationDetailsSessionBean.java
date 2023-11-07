/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.FlightSchedule;
import entity.ReservationDetails;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lenovo
 */
@Stateless
public class ReservationDetailsSessionBean implements ReservationDetailsSessionBeanRemote, ReservationDetailsSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createReservationDetails(ReservationDetails reservationDetails, long customerId, long flightScheduleId) {
        Customer cust = em.find(Customer.class, customerId);
        FlightSchedule fs = em.find(FlightSchedule.class, flightScheduleId);
        reservationDetails.setCustomer(cust);
        reservationDetails.setFlightSchedule(fs);
        
        //synch customer to new res details
        List<ReservationDetails> listOfRes = cust.getListOfReservationDetails();
        listOfRes.add(reservationDetails);
        cust.setListOfReservationDetails(listOfRes);
        
        //synch flightshcedule to res details
        fs.setReservationDetails(reservationDetails);
        
        em.persist(reservationDetails);
        em.flush();
        
        return reservationDetails.getId();
    }

    
}
