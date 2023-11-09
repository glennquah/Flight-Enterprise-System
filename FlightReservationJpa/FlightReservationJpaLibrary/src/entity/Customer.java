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

/**
 *
 * @author admin
 */
@Entity
public class Customer extends Account implements Serializable {
    
    @Column(length = 8, nullable = false)
    private String phoneNumber;
    @Column(length = 64, nullable = false)
    private String address;
    @Column(length = 32)
    private String creditCardNumber;
    
    @OneToMany(mappedBy="Customer")
    private List<ReservationDetails> listOfReservationDetails;
    
    @ManyToMany(mappedBy = "Customer")
    private List<FlightSchedule> listOfFlightSchedules;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String password, String phoneNumber, String address) {
        super(firstName, lastName, email, password);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.listOfReservationDetails = new ArrayList<>();
        this.listOfFlightSchedules = new ArrayList<>();
    }

    public List<ReservationDetails> getListOfReservationDetails() {
        return listOfReservationDetails;
    }

    public void setListOfReservationDetails(List<ReservationDetails> resDetails) {
        this.listOfReservationDetails = resDetails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

        /**
     * @return the creditCardNumber
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * @param creditCardNumber the creditCardNumber to set
     */
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }


    /**
     * Get the value of phoneNumber
     *
     * @return the value of phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the value of phoneNumber
     *
     * @param phoneNumber new value of phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
