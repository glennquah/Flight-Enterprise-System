/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import util.enumeration.FlightRouteStatusEnum;

/**
 *
 * @author Lenovo
 */
@Entity
@XmlRootElement
public class FlightRoute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightRouteId;
    @Column(nullable = false)
    private FlightRouteStatusEnum flightRouteStatus;
    private Boolean complementaryRoute;
    
    //===============RELATIONSHIPS=================
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "origin")
    private Airport origin;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "destination")
    private Airport destination;
    @OneToMany(mappedBy="FlightRoute")
    private List<Flight> listOfFlights;

    public FlightRoute() {
    }

    public FlightRoute(Airport origin, Airport destination) {
        this.origin = origin;
        this.destination = destination;
        this.flightRouteStatus = FlightRouteStatusEnum.ACTIVE;
        this.complementaryRoute = false;
        this.listOfFlights = new ArrayList<>();
    }

    public FlightRoute(Airport originAirport, Airport destinationAirport, boolean haveComplementaryRoute) {
        this.origin = originAirport;
        this.destination = destinationAirport;
        this.flightRouteStatus = FlightRouteStatusEnum.ACTIVE;
        this.complementaryRoute = true;
        this.listOfFlights = new ArrayList<>();
    }

    public Long getFlightRouteId() {
        return flightRouteId;
    }

    public void setFlightRouteId(Long flightRouteId) {
        this.flightRouteId = flightRouteId;
    }
    
    public Airport getOrigin() {
        return origin;
    }
    
    public Boolean getComplementaryRoute() {
        return complementaryRoute;
    }

    public void setComplementaryRoute(Boolean complementaryRoute) {
        this.complementaryRoute = complementaryRoute;
    }
    
    public FlightRouteStatusEnum getFlightRouteStatus() {
        return flightRouteStatus;
    }

    public void setFlightRouteStatus(FlightRouteStatusEnum flightRouteStatus) {
        this.flightRouteStatus = flightRouteStatus;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
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
     * @return the listOfFlights
     */
    public List<Flight> getListOfFlights() {
        return listOfFlights;
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param listOfFlights the listOfFlights to set
     */
    public void setListOfFlights(List<Flight> listOfFlights) {
        this.listOfFlights = listOfFlights;
    }
    
}
