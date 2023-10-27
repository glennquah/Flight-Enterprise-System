/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

/**
 *
 * @author Lenovo
 */
@Entity
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    @Column(length = 32, nullable = false)
    private String airCraftType;
    @Future
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date departureDate;
    @Future
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date returnDate;
    @Column(nullable = false)
    private int totalNumOfPassengers;
    @Column(nullable = false)
    private int numOfSeatsLeft;
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private FlightRoute flightRoute;
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Aircraft aircraft;
    
    @OneToMany(mappedBy = "Flight")
    private List<ReservationDetails> listOfReservationDetails;

    public Flight() {
    }

    public Flight(String airCraftType, Date departureDate, Date returnDate, int totalNumOfPassengers, int numOfSeatsLeft) {
        this.airCraftType = airCraftType;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.totalNumOfPassengers = totalNumOfPassengers;
        this.numOfSeatsLeft = numOfSeatsLeft;
    }

    public FlightRoute getFlightRoute() {
        return flightRoute;
    }

    public void setFlightRoute(FlightRoute flightRoute) {
        this.flightRoute = flightRoute;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public List<ReservationDetails> getListOfReservationDetails() {
        return listOfReservationDetails;
    }

    public void setListOfReservationDetails(List<ReservationDetails> listOfReservationDetails) {
        this.listOfReservationDetails = listOfReservationDetails;
    }
    
    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightId != null ? flightId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the flightId fields are not set
        if (!(object instanceof Flight)) {
            return false;
        }
        Flight other = (Flight) object;
        if ((this.flightId == null && other.flightId != null) || (this.flightId != null && !this.flightId.equals(other.flightId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Flight[ id=" + flightId + " ]";
    }

    /**
     * @return the airCraftType
     */
    public String getAirCraftType() {
        return airCraftType;
    }

    /**
     * @param airCraftType the airCraftType to set
     */
    public void setAirCraftType(String airCraftType) {
        this.airCraftType = airCraftType;
    }

    /**
     * @return the departureDate
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * @param departureDate the departureDate to set
     */
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * @return the returnDate
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return the totalNumOfPassengers
     */
    public int getTotalNumOfPassengers() {
        return totalNumOfPassengers;
    }

    /**
     * @param totalNumOfPassengers the totalNumOfPassengers to set
     */
    public void setTotalNumOfPassengers(int totalNumOfPassengers) {
        this.totalNumOfPassengers = totalNumOfPassengers;
    }

    /**
     * @return the numOfSeatsLeft
     */
    public int getNumOfSeatsLeft() {
        return numOfSeatsLeft;
    }

    /**
     * @param numOfSeatsLeft the numOfSeatsLeft to set
     */
    public void setNumOfSeatsLeft(int numOfSeatsLeft) {
        this.numOfSeatsLeft = numOfSeatsLeft;
    }
    
}
