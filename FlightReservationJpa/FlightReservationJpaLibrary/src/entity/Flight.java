/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    private Integer flightNumber;
    @Column(nullable = false)
    private List<Date> bookedDates;
    
    @ManyToOne
    private FlightRoute flightRoute;
    
    @ManyToOne
    private AircraftConfiguration aircraftConfig;
    
    @OneToMany(mappedBy = "Flight")
    private List<FlightSchedulePlan> listOfFlightSchedulePlans;

    public Flight() {
    }

    public Flight(Integer flightNumber) {
        this.flightNumber = flightNumber;
        this.prefix = "IATA";
        this.bookedDates = new ArrayList<Date>();
        this.flightRoute = new FlightRoute();
        this.aircraftConfig = new AircraftConfiguration();
        this.listOfFlightSchedulePlans = new ArrayList<FlightSchedulePlan>();
    }

    public List<Date> getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(List<Date> bookedDates) {
        Collections.sort(bookedDates);
        this.bookedDates = bookedDates;
    }

    public FlightRoute getFlightRoute() {
        return flightRoute;
    }

    public void setFlightRoute(FlightRoute flightRoute) {
        this.flightRoute = flightRoute;
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

    public List<FlightSchedulePlan> getListOfFlightSchedulePlans() {
        return listOfFlightSchedulePlans;
    }

    public void setListOfFlightSchedulePlans(List<FlightSchedulePlan> listOfFlightSchedulePlans) {
        this.listOfFlightSchedulePlans = listOfFlightSchedulePlans;
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

    /**
     * @return the flightNumber
     */
    public Integer getFlightNumber() {
        return flightNumber;
    }

    /**
     * @param flightNumber the flightNumber to set
     */
    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    
}
