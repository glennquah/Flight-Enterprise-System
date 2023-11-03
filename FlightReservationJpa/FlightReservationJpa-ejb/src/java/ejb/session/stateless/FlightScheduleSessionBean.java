/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Airport;
import entity.FlightRoute;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import util.exception.AirportDoesNotExistException;
import util.exception.FlightRouteAlreadyExistException;

/**
 *
 * @author admin
 */
@Stateless
public class FlightScheduleSessionBean implements FlightScheduleSessionBeanRemote, FlightScheduleSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public FlightSchedule createNewFlightSchedule(FlightSchedule flightSchedule) {
        em.persist(flightSchedule);
        em.flush();
        
        return flightSchedule;
    }
    
    @Override
    public List<FlightSchedule> retrieveFlightSchedulePlanWithSameTiming(List<FlightSchedulePlan> listOfFlightSchedulePlan, Date departureDate) {
        List<Long> flightSchedNumbers = new ArrayList<>();

        for (FlightSchedulePlan flightSchedPlan : listOfFlightSchedulePlan) {
            flightSchedNumbers.add(flightSchedPlan.getFlightSchedulePlanId());
        }

        if (flightSchedNumbers.isEmpty()) {
            return Collections.emptyList();
        }

        Query query = em.createQuery("SELECT f FROM FlightSchedule f WHERE f.flightSchedulePlan.flightSchedulePlanId IN :flightSchedNumbers");
        query.setParameter("flightSchedNumbers", flightSchedNumbers);

        List<FlightSchedule> listOfFlightSchedules = query.getResultList();

        List<FlightSchedule> newList = new ArrayList<>();

        for (FlightSchedule flightSchedule : listOfFlightSchedules) {
            Date departureDateTime = flightSchedule.getDepartureDateTime();
            LocalDate localDate = departureDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (localDate.isEqual(departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                newList.add(flightSchedule);
            }
        }

        return newList;
    }

}
