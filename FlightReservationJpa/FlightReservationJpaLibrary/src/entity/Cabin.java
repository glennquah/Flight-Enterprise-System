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
    
    @Column(nullable = false)
    @Min(value = 0)
    @Max(value = 2)
    private int numOfIsles;
    
    @Column(nullable = false)
    private int numOfRows;
    
    @Column(nullable = false)
    private int[] seatingConfiguration;
    
    @ManyToOne
    private AircraftConfiguration aircraftConfiguration;
    
    @OneToMany(mappedBy="Cabin")
    private List<Fare> listOfFare;

    public Cabin() {
    }

    public Cabin(String cabinClassName, int numOfIsles, int numOfRows, int[] seatingConfiguration) {
        this.cabinClassName = cabinClassName;
        this.numOfIsles = numOfIsles;
        this.numOfRows = numOfRows;
        this.seatingConfiguration = seatingConfiguration;
        this.aircraftConfiguration = new AircraftConfiguration();  
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
    
}
