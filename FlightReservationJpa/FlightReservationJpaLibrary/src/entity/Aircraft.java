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
import javax.persistence.OneToMany;

/**
 *
 * @author Lenovo
 */
@Entity
public class Aircraft implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftId;
    @Column(length = 32, nullable = false)
    private String aircraftName;
    @Column(nullable = false)
    private int numOfSeats;
    
    @Column(nullable = false)
    @OneToMany(mappedBy = "Aircraft")
    private List<Flight> listOfFlights;
    
    @Column(nullable = false)
    @OneToMany(mappedBy = "Aircraft")
    private List<Cabin> listOfCabins;
    
    

    public Aircraft() {
    }

    public Aircraft(String aircraftName, int numOfSeats) {
        this.aircraftName = aircraftName;
        this.numOfSeats = numOfSeats;
        this.listOfFlights = new ArrayList<Flight>();
        this.listOfCabins = new ArrayList<Cabin>();
    }
    
    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aircraftId != null ? aircraftId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the aircraftId fields are not set
        if (!(object instanceof Aircraft)) {
            return false;
        }
        Aircraft other = (Aircraft) object;
        if ((this.aircraftId == null && other.aircraftId != null) || (this.aircraftId != null && !this.aircraftId.equals(other.aircraftId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Aircraft[ id=" + aircraftId + " ]";
    }

    /**
     * @return the aircraftName
     */
    public String getAircraftName() {
        return aircraftName;
    }

    /**
     * @param aircraftName the aircraftName to set
     */
    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    /**
     * @return the numOfSeats
     */
    public int getNumOfSeats() {
        return numOfSeats;
    }

    /**
     * @param numOfSeats the numOfSeats to set
     */
    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }
    
}
