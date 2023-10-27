/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightmanagementjpaclient;

import ejb.session.stateless.AircraftSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class ManagementModule {
    private Long employeeId;
    private EmployeeSessionBeanRemote employeeSessionBean;
    private CustomerSessionBeanRemote customerSessionBean;
    private AircraftSessionBeanRemote aircraftSessionBeanRemote;
    

    public ManagementModule() {
    }

    public ManagementModule(Long employeeId, EmployeeSessionBeanRemote employeeSessionBean, CustomerSessionBeanRemote customerSessionBean, AircraftSessionBeanRemote aircraftSessionBeanRemote) {
        this.employeeId = employeeId;
        this.employeeSessionBean = employeeSessionBean;
        this.customerSessionBean = customerSessionBean;
    }
    
    //=================================================ADMIN PAGE================================================================
    
    public void adminLoginPage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*** SUCCESSFULLY LOGIN! *** \n");
        Integer response;
        while(true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Aircraft Configuration ");
            System.out.println("2: Flight Route");
            System.out.println("3: Flight");
            System.out.println("4: Flight Schedule Plan");
            System.out.println("5: Logout\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                aircraftConfigurationOptions(sc);
            } else if (response == 2) {
                System.out.println("2");
            } else if (response == 3) {
                flightOptions(sc);
            } else if (response == 4) {
                System.out.println("4");
            } else if (response == 5) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
    
    //=================================================AIRCRAFT CONFIGURATION================================================================
    
    public void aircraftConfigurationOptions(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED AIRCRAFT CONFIGURATION ***\n");
        Integer response;
        while(true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create Aircraft Configuration ");
            System.out.println("2: View All Aircraft Configuration");
            System.out.println("3: Back\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                createFlightConfiguration(sc);
            } else if (response == 2) {
                System.out.println("2");
            } else if (response == 3) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
    
    //Create Flight Config
    public void createFlightConfiguration(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED CREATE AIRCRAFT CONFIGURATION ***");
        System.out.println("*** PICK AIRCRAFT TYPE ***\n"); 
        
       
    }
    
    //=================================================FLIGHT================================================================
    
    public void flightOptions(Scanner sc) {
        System.out.println("*** YOU HAVE PICKED FLIGHT ***\n");
        Integer response;
        while(true) {
            System.out.println("*** PLEASE SELECT THE FOLLOWING OPTION ***\n");
            System.out.println("1: Create Flight ");
            System.out.println("2: View All Flight");
            System.out.println("3: Update Flight");
            System.out.println("4: Delete Flight");
            System.out.println("5: Back\n");

            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                System.out.println("1");
            } else if (response == 2) {
                System.out.println("2");
            } else if (response == 3) {
                System.out.println("3");
            } else if (response == 4) {
                System.out.println("4");
            } else if (response == 5) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
    
    
}
