/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Lenovo
 */
@Entity
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    @Column(nullable = false)
    private int rowNum;
    @Column(nullable = false)
    private char rowLetter;
    @Column(nullable = false)
    private Boolean taken;
    
    @Column(nullable = false)
    @ManyToOne
    private Cabin cabin;

    public Seat() {
    }

    public Seat(int rowNum, char rowLetter) {
        this.rowNum = rowNum;
        this.rowLetter = rowLetter;
        this.taken = false;
        this.cabin = new Cabin();
    }
    
    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seatId != null ? seatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the seatId fields are not set
        if (!(object instanceof Seat)) {
            return false;
        }
        Seat other = (Seat) object;
        if ((this.seatId == null && other.seatId != null) || (this.seatId != null && !this.seatId.equals(other.seatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Seat[ id=" + seatId + " ]";
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
     * @return the rowLetter
     */
    public char getRowLetter() {
        return rowLetter;
    }

    /**
     * @param rowLetter the rowLetter to set
     */
    public void setRowLetter(char rowLetter) {
        this.rowLetter = rowLetter;
    }

    /**
     * @return the taken
     */
    public Boolean getTaken() {
        return taken;
    }

    /**
     * @param taken the taken to set
     */
    public void setTaken(Boolean taken) {
        this.taken = taken;
    }
    
}
