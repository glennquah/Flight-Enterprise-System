/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ejb.session.stateless.singleton;

import ejb.session.stateless.AccountSessionBeanLocal;
import entity.Account;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lenovo
 */
@Singleton
@LocalBean
@Startup

public class DataInitSessionBean {

    @EJB(name = "AccountSessionBeanLocal")
    private AccountSessionBeanLocal accountSessionBeanLocal;

    @PersistenceContext(unitName = "FlightReservationJpa-ejbPU")
    private EntityManager em;

    @PostConstruct
    public void postConstruct() {
        if(em.find(Account.class, 1l) == null) {
            //if data is empty, inject 2 accounts
            accountSessionBeanLocal.createNewAccount(new Account("Glenn", "username", "password"));
            accountSessionBeanLocal.createNewAccount(new Account("Ryan", "username", "password"));
        }
    }
    
}
