/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.enumeration.EmployeeAccessRightEnum;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author Lenovo
 */
@Stateless
public class EmployeeSessionBean implements EmployeeSessionBeanRemote, EmployeeSessionBeanLocal {

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long login(String email, String password) throws InvalidLoginCredentialException
    {
        try
        {
            Query query = em.createQuery("SELECT e FROM Employee e WHERE e.email = :email");
            query.setParameter("email", email);
            Employee employee = (Employee)query.getSingleResult();
            
            if(employee.getPassword().equals(password))
            {
                return employee.getAccountId();
            }
            else
            {
                throw new InvalidLoginCredentialException("Invalid login credential");
            }
        }
        catch(NoResultException ex)
        {
            throw new InvalidLoginCredentialException("Invalid login credential");
        }
    }
    
    @Override
    public Long createNewAccount(Employee newEmpAccount) {
        em.persist(newEmpAccount);
        em.flush();
        
        return newEmpAccount.getAccountId();
    }
    
    @Override
    public List<Employee> retrieveAllAccounts() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT e FROM Employee e");
        return query.getResultList();
    }
    
    @Override
    public EmployeeAccessRightEnum retrieveRole(Long employeeId) {
        Employee employee = em.find(Employee.class, employeeId);
        
        return employee.getUserRole();
    }
}
