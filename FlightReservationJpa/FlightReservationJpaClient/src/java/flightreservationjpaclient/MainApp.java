/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightreservationjpaclient;

import ejb.session.stateless.AircraftConfigurationSessionBeanRemote;
import ejb.session.stateless.AircraftSessionBeanRemote;
import ejb.session.stateless.AirportSessionBeanRemote;
import ejb.session.stateless.CabinCustomerSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import ejb.session.stateless.FlightRoutesSessionBeanRemote;
import ejb.session.stateless.FlightSessionBeanRemote;
import entity.Customer;
import java.util.Scanner;
import javax.ejb.EJB;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author admin
 */
public class MainApp {
    private EmployeeSessionBeanRemote employeeSessionBean;
    private CustomerSessionBeanRemote customerSessionBean;
    private AircraftSessionBeanRemote aircraftSessionBeanRemote;
    private AircraftConfigurationSessionBeanRemote aircraftConfigurationSessionBeanRemote;
    private CabinCustomerSessionBeanRemote cabinCustomerSessionBeanRemote;
    private FlightRoutesSessionBeanRemote flightRoutesSessionBeanRemote;
    private AirportSessionBeanRemote airportSessionBeanRemote;
    private FlightSessionBeanRemote flightSessionBeanRemote;
            

    public MainApp() {
    }

    public MainApp(EmployeeSessionBeanRemote employeeSessionBean,
                   CustomerSessionBeanRemote customerSessionBean,
                   AircraftSessionBeanRemote aircraftSessionBeanRemote,
                   AircraftConfigurationSessionBeanRemote aircraftConfigurationSessionBeanRemote,
                   CabinCustomerSessionBeanRemote cabinCustomerSessionBeanRemote,
                   FlightRoutesSessionBeanRemote flightRoutesSessionBeanRemote,
                   AirportSessionBeanRemote airportSessionBeanRemote,
                   FlightSessionBeanRemote flightSessionBeanRemote) {
        this.employeeSessionBean = employeeSessionBean;
        this.customerSessionBean = customerSessionBean;
        this.aircraftSessionBeanRemote = aircraftSessionBeanRemote;
        this.aircraftConfigurationSessionBeanRemote = aircraftConfigurationSessionBeanRemote;
        this.cabinCustomerSessionBeanRemote = cabinCustomerSessionBeanRemote;
        this.flightRoutesSessionBeanRemote = flightRoutesSessionBeanRemote;
        this.airportSessionBeanRemote = airportSessionBeanRemote;
        this.flightSessionBeanRemote = flightSessionBeanRemote;
    }
    
    public void runApp() {
        Scanner scanner = new Scanner(System.in);
        Long customerId;
        Integer response;
        
        while(true)
        {
            System.out.println("*** Welcome to the Flight Reservation Client ***\n");
            System.out.println("1: Register");
            System.out.println("2: Login");
            System.out.println("3: Exit\n");
            response = 0;
            
            while(response < 1 || response > 3)
            {
                System.out.print("> ");

                response = scanner.nextInt();
                if (response == 1)
                {   
                    try {
                        customerId = doRegister(scanner);
                        ReservationModule resModule = new ReservationModule(customerId,
                                                                    employeeSessionBean,
                                                                    customerSessionBean,
                                                                    aircraftSessionBeanRemote,
                                                                    aircraftConfigurationSessionBeanRemote,
                                                                    cabinCustomerSessionBeanRemote,
                                                                    flightRoutesSessionBeanRemote,
                                                                    airportSessionBeanRemote,
                                                                    flightSessionBeanRemote);
                        resModule.customerLoginPage();
                    }
                    catch(InvalidLoginCredentialException ex) 
                    {
                        System.out.println("Invalid login credential: " + ex.getMessage() + "\n");
                    } 
                }

                if (response == 2)
                {   
                    try {
                        customerId = doLogin(scanner);
                        ReservationModule resModule = new ReservationModule(customerId,
                                                                    employeeSessionBean,
                                                                    customerSessionBean,
                                                                    aircraftSessionBeanRemote,
                                                                    aircraftConfigurationSessionBeanRemote,
                                                                    cabinCustomerSessionBeanRemote,
                                                                    flightRoutesSessionBeanRemote,
                                                                    airportSessionBeanRemote,
                                                                    flightSessionBeanRemote);
                        resModule.customerLoginPage();
                    }
                    catch(InvalidLoginCredentialException ex) 
                    {
                        System.out.println("Invalid login credential: " + ex.getMessage() + "\n");
                    } 
                }
                
                if (response == 3) {
                    break;
                }
            }
            if (response == 2) {
                break;
            }
        }
    }
    
    private Long doLogin(Scanner scanner) throws InvalidLoginCredentialException 
    {
        System.out.println("\n*** Reservation client :: Login ***\n");
        System.out.print("Enter email> ");
        scanner.nextLine();
        String email = scanner.nextLine().trim();
        System.out.print("Enter password> ");
        String password = scanner.nextLine().trim();
        
        if(email.length() > 0 && password.length() > 0)
        {
            System.out.println("IT IS NULL?" + customerSessionBean);
            return customerSessionBean.login(email, password);
        }
        else
        {
            throw new InvalidLoginCredentialException("Missing login credential!");
        }
    }
    
    private Long doRegister(Scanner scanner) throws InvalidLoginCredentialException 
    {
        System.out.println("\n*** Reservation client :: Register ***\n");
        System.out.print("Enter First Name> ");
        scanner.nextLine();
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter Last Name> ");
        String lastName = scanner.nextLine().trim();
        System.out.print("Enter Email Name> ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Password Name> ");
        String password = scanner.nextLine().trim();
        System.out.print("Enter Phone Number Name> ");
        String phoneNum = scanner.nextLine().trim();
        System.out.print("Enter Address Name> ");
        String address = scanner.nextLine().trim();
        Customer cust = new Customer(firstName, lastName, email, password, phoneNum, address);
        
        if(email.length() > 0 && password.length() > 0)
        {
            return customerSessionBean.createNewAccount(cust);
        }
        else
        {
            throw new InvalidLoginCredentialException("Missing login credential!");
        }
    }
}
