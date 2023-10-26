/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

/**
 *
 * @author Lenovo
 */
@Entity
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
    private Time estimatedTime;
    @Future
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date arrivalDateTime;
    
    @ManyToOne
    private FlightSchedulePlan flightSchedulePlan;

    public FlightSchedule() {
    }

    public FlightSchedule(Date departureDateTime, Time estimatedTime) {
        this.departureDateTime = departureDateTime;
        this.estimatedTime = estimatedTime;
        this.arrivalDateTime = new Date(departureDateTime.getTime() + estimatedTime.getTime());
        this.flightSchedulePlan = new FlightSchedulePlan();
    }
    
    

    public Long getFlightScheduleId() {
        return flightScheduleId;
    }

    public void setFlightScheduleId(Long flightScheduleId) {
        this.flightScheduleId = flightScheduleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightScheduleId != null ? flightScheduleId.hashCode() : 0);
        return hash;
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
        return "entity.FlightSchedule[ id=" + flightScheduleId + " ]";
    }

    /**
     * @return the departureDateTime
     */
    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    /**
     * @param departureDateTime the departureDateTime to set
     */
    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    /**
     * @return the estimatedTime
     */
    public Time getEstimatedTime() {
        return estimatedTime;
    }

    /**
     * @param estimatedTime the estimatedTime to set
     */
    public void setEstimatedTime(Time estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    /**
     * @return the flightSchedulePlan
     */
    public FlightSchedulePlan getFlightSchedulePlan() {
        return flightSchedulePlan;
    }

    /**
     * @param flightSchedulePlan the flightSchedulePlan to set
     */
    public void setFlightSchedulePlan(FlightSchedulePlan flightSchedulePlan) {
        this.flightSchedulePlan = flightSchedulePlan;
    }

    /**    
}
