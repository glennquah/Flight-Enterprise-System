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
import javax.persistence.OneToMany;

/**
 *
 * @author admin
 */
@Entity
public class Customer extends Account implements Serializable {
    @Column(length = 32, nullable = false)
    private String email;
    @Column(length = 8, nullable = false)
    private String phoneNumber;
    @Column(length = 64, nullable = false)
    private String address;
    
    @OneToMany(mappedBy="Customer")
    private List<Flight> listOfScheduledFlights;
    
    @OneToMany(mappedBy="Customer")
    private List<ReservationDetails> listOfReservationDetails;

    public Customer() {
    }

    public Customer(String email, String phoneNumber, String address) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.listOfScheduledFlights = null;
        this.listOfReservationDetails = new ArrayList<ReservationDetails>();
    }
    

    public List<ReservationDetails> getListOfReservationDetails() {
        return listOfReservationDetails;
    }

    public void setListOfReservationDetails(List<ReservationDetails> resDetails) {
        this.listOfReservationDetails = resDetails;
    }


    public List<Flight> getListOfScheduledFlights() {
        return listOfScheduledFlights;
    }


    public void setListOfScheduledFlights(List<Flight> flights) {
        this.listOfScheduledFlights = flights;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    } 
}
