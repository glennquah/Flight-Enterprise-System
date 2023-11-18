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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@Entity
@XmlRootElement
public class AircraftConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftConfigurationId;
    @Column(length = 32, nullable = false)
    private String aircraftConfigName;
    
    //===============RELATIONSHIPS=================
    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private Aircraft aircraft;
    @OneToMany(mappedBy = "aircraftConfiguration")
    private List<Cabin> listOfCabins;
    @OneToMany(mappedBy = "aircraftConfig")
    private List<Flight> listOfFlights;

    public AircraftConfiguration() {
        this.aircraftConfigName = "";
        this.listOfCabins = new ArrayList<>();
        this.aircraft = new Aircraft();
        this.listOfFlights = new ArrayList<>();
    }

    public AircraftConfiguration(String aircraftConfigName) {
        this.aircraftConfigName = aircraftConfigName;
        this.listOfCabins = new ArrayList<>();
        this.aircraft = new Aircraft();
        this.listOfFlights = new ArrayList<>();
    }

    public Long getAircraftConfigurationId() {
        return aircraftConfigurationId;
    }

    public void setAircraftConfigurationId(Long aircraftConfigurationId) {
        this.aircraftConfigurationId = aircraftConfigurationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aircraftConfigurationId != null ? aircraftConfigurationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the aircraftConfigurationId fields are not set
        if (!(object instanceof AircraftConfiguration)) {
            return false;
        }
        AircraftConfiguration other = (AircraftConfiguration) object;
        if ((this.aircraftConfigurationId == null && other.aircraftConfigurationId != null) || (this.aircraftConfigurationId != null && !this.aircraftConfigurationId.equals(other.aircraftConfigurationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AircraftConfiguration[ id=" + aircraftConfigurationId + " ]";
    }

    /**
     * @return the aircraft
     */
    public Aircraft getAircraft() {
        return aircraft;
    }

    /**
     * @param aircraft the aircraft to set
     */
    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
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
     * @return the aircraftConfigName
     */
    public String getAircraftConfigName() {
        return aircraftConfigName;
    }

    /**
     * @param aircraftConfigName the aircraftConfigName to set
     */
    public void setAircraftConfigName(String aircraftConfigName) {
        this.aircraftConfigName = aircraftConfigName;
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
