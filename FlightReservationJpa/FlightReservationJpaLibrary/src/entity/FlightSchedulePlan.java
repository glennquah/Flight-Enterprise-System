/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import util.enumeration.FlightSchedulePlanStatusEnum;

/**
 *
 * @author Lenovo
 */
@Entity
@XmlRootElement
public class FlightSchedulePlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightSchedulePlanId;
    @Column(length = 32, nullable = false)
    private Integer flightNumber;
    
    @Column(nullable = false)
    private FlightSchedulePlanStatusEnum flightSchedulePlanStatus;
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Flight flight;
    
    @OneToMany(mappedBy="FlightSchedulePlan")
    private List<FlightSchedule> flightSchedules;
    
    @OneToMany(mappedBy="FlightSchedulePlan")
    private List<Fare> listOfFares;

    public FlightSchedulePlan() {
    }

    public FlightSchedulePlan(Integer flightNumber) {
        this.flightNumber = flightNumber;
        this.flightSchedulePlanStatus = FlightSchedulePlanStatusEnum.ACTIVE;
        this.flightSchedules = new ArrayList<>();
        this.listOfFares = new ArrayList<>();
    }

    public Long getFlightSchedulePlanId() {
        return flightSchedulePlanId;
    }

    public void setFlightSchedulePlanId(Long flightSchedulePlanId) {
        this.flightSchedulePlanId = flightSchedulePlanId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightSchedulePlanId != null ? flightSchedulePlanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the flightSchedulePlanId fields are not set
        if (!(object instanceof FlightSchedulePlan)) {
            return false;
        }
        FlightSchedulePlan other = (FlightSchedulePlan) object;
        if ((this.flightSchedulePlanId == null && other.flightSchedulePlanId != null) || (this.flightSchedulePlanId != null && !this.flightSchedulePlanId.equals(other.flightSchedulePlanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FlightSchedulePlan[ id=" + flightSchedulePlanId + " ]";
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public List<FlightSchedule> getFlightSchedules() {
        return flightSchedules;
    }

    public void setFlightSchedules(List<FlightSchedule> flightSchedules) {
        FlightScheduleComparator flightComp = new FlightScheduleComparator();
        this.flightSchedules = flightSchedules;
        
        if (this.flightSchedules != null) {
            Collections.sort(this.flightSchedules, flightComp);
        }
    }

    public List<Fare> getListOfFares() {
        return listOfFares;
    }

    public void setListOfFares(List<Fare> listOfFares) {
        this.listOfFares = listOfFares;
    }

    /**
     * @return the flight
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @param flight the flight to set
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    
    public FlightSchedulePlanStatusEnum getFlightSchedulePlanStatus() {
        return flightSchedulePlanStatus;
    }

    public void setFlightSchedulePlanStatus(FlightSchedulePlanStatusEnum flightSchedulePlanStatus) {
        this.flightSchedulePlanStatus = flightSchedulePlanStatus;
    }

}
