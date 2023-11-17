/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Employee;
import java.util.List;
import javax.ejb.Local;
import util.enumeration.EmployeeAccessRightEnum;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author Lenovo
 */
@Local
public interface EmployeeSessionBeanLocal {
    public Long createNewAccount(Employee newEmpAccount);
    public List<Employee> retrieveAllAccounts();
    public Long login(String email, String password) throws InvalidLoginCredentialException;
    public EmployeeAccessRightEnum retrieveRole(Long employeeId);
}
