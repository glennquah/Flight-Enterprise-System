/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.ReservationDetails;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lenovo
 */
@Local
public interface ReservationDetailsSessionBeanLocal {
    public Long createReservationDetails(ReservationDetails reservationDetails, long customerId, long flightScheduleId, long lowestFareId);
    public Long createReservationDetailsForPartner(ReservationDetails reservationDetails, long partnerId, long flightScheduleId, long lowestFareId);
    public Long createReservationDetails(long reservationDetailsId, long customerId, long flightScheduleId, long lowestFareId);
    public List<ReservationDetails> getReservationsWithFsId(long id) ;
}
