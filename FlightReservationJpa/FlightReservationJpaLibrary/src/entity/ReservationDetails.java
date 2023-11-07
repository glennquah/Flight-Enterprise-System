/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author admin
 */
@Entity
public class ReservationDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 32, nullable = false)
    private String firstName;
    @Column(length = 32, nullable = false)
    private String lastName;
    @Column(length = 32, nullable = false)
    private String passportNumber;
    @Column(length = 3, nullable = false)
    private int rowNum;
    @Column(nullable = false)
    private char seatLetter;
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Customer customer;
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private FlightSchedule flightSchedule;

    public ReservationDetails() {
    }

    public ReservationDetails(String firstName, String lastName, String passportNumber, int rowNum, char seatLetter) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.rowNum = rowNum;
        this.seatLetter = seatLetter;
        this.customer = new Customer();
        this.flightSchedule = new FlightSchedule();
    }
    
    

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservationDetails)) {
            return false;
        }
        ReservationDetails other = (ReservationDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReservationDetails[ id=" + id + " ]";
    }

    /**
     * @return the flightSchedule
     */
    public FlightSchedule getFlightSchedule() {
        return flightSchedule;
    }

    /**
     * @param flightSchedule the flightSchedule to set
     */
    public void setFlightSchedule(FlightSchedule flightSchedule) {
        this.flightSchedule = flightSchedule;
    }

    /**
     * @return the rowNum
     */
    public int getRowNum() {
        return rowNum;
    }

    /**
     * @param rowNum the rowNum to set
     */
    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * @return the seatLetter
     */
    public char getSeatLetter() {
        return seatLetter;
    }

    /**
     * @param seatLetter the seatLetter to set
     */
    public void setSeatLetter(char seatLetter) {
        this.seatLetter = seatLetter;
    }
    
}
