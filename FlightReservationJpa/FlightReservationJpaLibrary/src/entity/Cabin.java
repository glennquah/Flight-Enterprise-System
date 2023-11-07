/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author Lenovo
 */
@Entity
public class Cabin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cabinId;
    @Column(length = 32, nullable = false)
    private String cabinClassName;
    private Integer availableSeats;
    private Integer reservedSeats;
    private Integer totalSeats;
    
    @Column(nullable = false)
    @Min(value = 0)
    @Max(value = 2)
    private Integer numOfIsles;
    
    @Column(nullable = false)
    private Integer numOfRows;
    
    @Column(nullable = false)
    private Integer[] seatingConfiguration;
    
    @ManyToOne
    private AircraftConfiguration aircraftConfiguration;
    
    @OneToMany(mappedBy="Cabin")
    private List<Fare> listOfFare;

    public Cabin() {
    }

    public Cabin(String cabinClassName, Integer numOfIsles, Integer numOfRows, Integer[] seatingConfiguration) {
        this.cabinClassName = cabinClassName;
        this.numOfIsles = numOfIsles;
        this.numOfRows = numOfRows;
        this.seatingConfiguration = seatingConfiguration;
        this.aircraftConfiguration = new AircraftConfiguration();
        int colm = 0;
        for (int i = 0; i < seatingConfiguration.length; i ++) {
            colm += seatingConfiguration[i];
        }
        this.availableSeats = numOfRows * colm;
        this.totalSeats = numOfRows * colm;
        this.reservedSeats = 0;
        this.listOfFare = new ArrayList<>();
    }

    public Long getCabinId() {
        return cabinId;
    }

    public void setCabinId(Long cabinId) {
        this.cabinId = cabinId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cabinId != null ? cabinId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the cabinId fields are not set
        if (!(object instanceof Cabin)) {
            return false;
        }
        Cabin other = (Cabin) object;
        if ((this.cabinId == null && other.cabinId != null) || (this.cabinId != null && !this.cabinId.equals(other.cabinId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Cabin[ id=" + cabinId + " ]";
    }

    /**
     * @return the cabinClassName
     */
    public String getCabinClassName() {
        return cabinClassName;
    }

    /**
     * @param cabinClassName the cabinClassName to set
     */
    public void setCabinClassName(String cabinClassName) {
        this.cabinClassName = cabinClassName;
    }

    /**
     * @return the aircraftConfiguration
     */
    public AircraftConfiguration getAircraftConfiguration() {
        return aircraftConfiguration;
    }

    /**
     * @param aircraftConfiguration the aircraftConfiguration to set
     */
    public void setAircraftConfiguration(AircraftConfiguration aircraftConfiguration) {
        this.aircraftConfiguration = aircraftConfiguration;
    }

    /**
     * @return the numOfIsles
     */
    public Integer getNumOfIsles() {
        return numOfIsles;
    }

    /**
     * @param numOfIsles the numOfIsles to set
     */
    public void setNumOfIsles(Integer numOfIsles) {
        this.numOfIsles = numOfIsles;
    }

    /**
     * @return the numOfRows
     */
    public Integer getNumOfRows() {
        return numOfRows;
    }

    /**
     * @param numOfRows the numOfRows to set
     */
    public void setNumOfRows(Integer numOfRows) {
        this.numOfRows = numOfRows;
    }

    /**
     * @return the seatingConfiguration
     */
    public Integer[] getSeatingConfiguration() {
        return seatingConfiguration;
    }

    /**
     * @param seatingConfiguration the seatingConfiguration to set
     */
    public void setSeatingConfiguration(Integer[] seatingConfiguration) {
        this.seatingConfiguration = seatingConfiguration;
    }

    /**
     * @return the availableSeats
     */
    public Integer getAvailableSeats() {
        return availableSeats;
    }

    /**
     * @param availableSeats the availableSeats to set
     */
    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    /**
     * @return the reservedSeats
     */
    public Integer getReservedSeats() {
        return reservedSeats;
    }

    /**
     * @param reservedSeats the reservedSeats to set
     */
    public void setReservedSeats(Integer reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    /**
     * @return the totalSeats
     */
    public Integer getTotalSeats() {
        return totalSeats;
    }

    /**
     * @param totalSeats the totalSeats to set
     */
    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void bookSeat(Integer num) {
        this.reservedSeats += num;
        this.availableSeats -= num;
    }

    public List<Fare> getListOfFare() {
        return listOfFare;
    }

    public void setListOfFare(List<Fare> listOfFare) {
        this.listOfFare = listOfFare;
    }
}
