/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package flightreservationjpaclient;

import ejb.session.stateless.AccountSessionBeanRemote;
import entity.Account;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Lenovo
 */
public class Main {

    @EJB
    private static AccountSessionBeanRemote accountSessionBeanRemote;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Look up session bean
        List<Account> retrieveAllAccounts = accountSessionBeanRemote.retrieveAllAccounts();
        for (Account acc: retrieveAllAccounts) {
            System.out.println("Account ID: " + acc.getAccountId());
            System.out.println("Account Name: " + acc.getName());
            System.out.println("Account UserName: " + acc.getUserName());
            System.out.println("Account Password: " + acc.getPassWord()+ "\n\n");
        }
    }
    
}
