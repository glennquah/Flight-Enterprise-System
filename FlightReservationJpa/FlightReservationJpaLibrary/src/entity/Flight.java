/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
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
    private String prefix; 
    @Column(nullable = false, unique = true, length = 4)
    private int flightNumber; 
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private FlightRoute flightRoute;
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private AircraftConfiguration aircraftConfig;
    
    @OneToMany(mappedBy = "Flight")
    private List<ReservationDetails> listOfReservationDetails;
    
    @OneToMany(mappedBy = "Flight")
    private List<FlightSchedule> listOfFlightSchedules;

    public Flight() {
    }

    public Flight(int flightNumber) {
        this.flightNumber = flightNumber;
        this.prefix = "IATA";
        this.flightRoute = new FlightRoute();
        this.aircraftConfig = new AircraftConfiguration();
        this.listOfReservationDetails = new ArrayList<ReservationDetails>();
        this.listOfFlightSchedules = new ArrayList<FlightSchedule>();
    }
    
    

    public FlightRoute getFlightRoute() {
        return flightRoute;
    }

    public void setFlightRoute(FlightRoute flightRoute) {
        this.flightRoute = flightRoute;
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
     * @return the listOfFlightSchedules
     */
    public List<FlightSchedule> getListOfFlightSchedules() {
        return listOfFlightSchedules;
    }

    /**
     * @param listOfFlightSchedules the listOfFlightSchedules to set
     */
    public void setListOfFlightSchedules(List<FlightSchedule> listOfFlightSchedules) {
        this.listOfFlightSchedules = listOfFlightSchedules;
    }

    /**
     * @return the aircraftConfig
     */
    public AircraftConfiguration getAircraftConfig() {
        return aircraftConfig;
    }

    /**
     * @param aircraftConfig the aircraftConfig to set
     */
    public void setAircraftConfig(AircraftConfiguration aircraftConfig) {
        this.aircraftConfig = aircraftConfig;
    }

    
}
