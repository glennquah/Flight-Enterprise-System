/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import util.enumeration.EmployeeAccessRightEnum;

/**
 *
 * @author Lenovo
 */
@Entity
public class Employee extends Account implements Serializable {

    private EmployeeAccessRightEnum userRole;

    public Employee() {
    }

    public Employee(EmployeeAccessRightEnum userRole) {
        this.userRole = userRole;
    }

    /**
     * @return the userRole
     */
    public EmployeeAccessRightEnum getUserRole() {
        return userRole;
    }

    /**
     * @param userRole the userRole to set
     */
    public void setUserRole(EmployeeAccessRightEnum userRole) {
        this.userRole = userRole;
    }
    
    
    
}
