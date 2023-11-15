/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@Entity
@XmlRootElement
public class FlightSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightScheduleId;
    @Future
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date departureDateTime;
    @Column(nullable = false)
    private Duration estimatedTime;
    @Column(nullable = false)
    private Duration layover;
    @Future
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date arrivalDateTime;
    
    @ManyToOne
    private FlightSchedulePlan flightSchedulePlan;
    
    @ManyToMany
    private List<Customer> customers;
    
    @ManyToMany
    private List<Partner> partners;
    
    @OneToMany(mappedBy = "FlightSchedule")
    private List<ReservationDetails> listOfReservationDetails;
    
    @OneToMany
    private List<Cabin> listOfCabins;

    public FlightSchedule() {
    }

    public FlightSchedule(Date departureDateTime, Duration estimatedTime, Duration layover) {
        this.departureDateTime = departureDateTime;
        this.estimatedTime = estimatedTime;
        Instant instant = departureDateTime.toInstant();
        this.arrivalDateTime = Date.from(instant.plus(estimatedTime));
        this.customers = new ArrayList<>();
        this.partners = new ArrayList<>();
        this.layover = layover;
        this.customer = new ArrayList<>();
        this.listOfReservationDetails = new ArrayList<>();
        this.listOfCabins = new ArrayList<>();
    }

    public Long getFlightScheduleId() {
        return flightScheduleId;
    }

    public void setFlightScheduleId(Long flightScheduleId) {
        this.flightScheduleId = flightScheduleId;
    }

    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Duration getLayover() {
        return layover;
    }

    public void setLayover(Duration layover) {
        this.layover = layover;
    }

    public Date getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(Date arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Duration getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Duration estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public FlightSchedulePlan getFlightSchedulePlan() {
        return flightSchedulePlan;
    }

    public void setFlightSchedulePlan(FlightSchedulePlan flightSchedulePlan) {
        this.flightSchedulePlan = flightSchedulePlan;
    }

        /**
     * @return the customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * @param customers the customers to set
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightScheduleId != null ? flightScheduleId.hashCode() : 0);
        return hash;
    }

    public List<ReservationDetails> getListOfReservationDetails() {
        return listOfReservationDetails;
    }

    public void setListOfReservationDetails(List<ReservationDetails> listOfReservationDetails) {
        this.listOfReservationDetails = listOfReservationDetails;
    }
    
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the flightScheduleId fields are not set
        if (!(object instanceof FlightSchedule)) {
            return false;
        }
        FlightSchedule other = (FlightSchedule) object;
        if ((this.flightScheduleId == null && other.flightScheduleId != null) || (this.flightScheduleId != null && !this.flightScheduleId.equals(other.flightScheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("Flight Schedule of ID %s departs on %s and lands on %s", flightScheduleId, departureDateTime, arrivalDateTime);
    }
    
        /**
     * @return the listOfCabins
     */
    public List<Cabin> getListOfCabins() {
        return listOfCabins;
    }

    /**
     * @param listOfCabins the listOfCabins to set
     */
    public void setListOfCabins(List<Cabin> listOfCabins) {
        this.listOfCabins = listOfCabins;
    }

    /**
     * @return the partners
     */
    public List<Partner> getPartners() {
        return partners;
    }

    /**
     * @param partners the partners to set
     */
    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }
    
    

}

    class FlightScheduleComparator implements Comparator<FlightSchedule> {
        public int compare(FlightSchedule f1, FlightSchedule f2) { 
           return f1.getDepartureDateTime().before(f2.getDepartureDateTime()) ? -1 : 0;
        }
    }


