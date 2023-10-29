/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import util.classLibrary.Pair;

/**
 *
 * @author Lenovo
 */
@Entity
public class FlightRoute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightRouteId;
    private Pair<Airport, Airport> originDestAirport;
    
    @OneToMany(mappedBy="FlightRoute")
    private List<Flight> listOfFlights;
    
//    @ManyToMany(mappedBy = "FlightRoute")
//    private List<Airport> listOfAirports;

    public FlightRoute() {
    }

    public FlightRoute(Airport originAirport, Airport destinationAirport) {
        this.originDestAirport = new Pair<>(originAirport,destinationAirport);
        this.listOfFlights = new ArrayList<Flight>();
    }

    public Long getFlightRouteId() {
        return flightRouteId;
    }

    public void setFlightRouteId(Long flightRouteId) {
        this.flightRouteId = flightRouteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightRouteId != null ? flightRouteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the flightRouteId fields are not set
        if (!(object instanceof FlightRoute)) {
            return false;
        }
        FlightRoute other = (FlightRoute) object;
        if ((this.flightRouteId == null && other.flightRouteId != null) || (this.flightRouteId != null && !this.flightRouteId.equals(other.flightRouteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FlightRoute[ id=" + flightRouteId + " ]";
    }

    /**
     * @return the originDestAirport
     */
    public Pair<Airport, Airport> getOriginDestAirport() {
        return originDestAirport;
    }

    /**
     * @param originDestAirport the originDestAirport to set
     */
    public void setOriginDestAirport(Pair<Airport, Airport> originDestAirport) {
        this.originDestAirport = originDestAirport;
    }

    /**
     * @return the listOfFlights
     */
    public List<Flight> getListOfFlights() {
        return listOfFlights;
    }

    /**
     * @param listOfFlights the listOfFlights to set
     */
    public void setListOfFlights(List<Flight> listOfFlights) {
        this.listOfFlights = listOfFlights;
    }
    
}
