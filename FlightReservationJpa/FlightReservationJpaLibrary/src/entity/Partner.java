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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@XmlRootElement
public class Partner extends Account implements Serializable {
    
    @OneToMany(mappedBy="partner")
    private List<ReservationDetails> listOfReservationDetails;
    
    @ManyToMany
    private List<FlightSchedule> listOfFlightSchedules;
    
    @Column(length = 32)
    private String creditCardNumber;
    

    public Partner() {
    }

    public Partner(String firstName, String email, String password) {
        super(firstName, "", email, password);
        this.listOfReservationDetails = new ArrayList<>();
        this.listOfFlightSchedules = new ArrayList<>();
        this.creditCardNumber = null;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }


    /**
     * @return the listOfReservationDetails
     */
    public List<ReservationDetails> getListOfReservationDetails() {
        return listOfReservationDetails;
    }

    /**
     * @param listOfReservationDetails the listOfReservationDetails to set
     */
    public void setListOfReservationDetails(List<ReservationDetails> listOfReservationDetails) {
        this.listOfReservationDetails = listOfReservationDetails;
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
    
    

    
}
