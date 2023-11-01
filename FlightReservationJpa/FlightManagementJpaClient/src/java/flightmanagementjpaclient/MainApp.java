/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightmanagementjpaclient;

import ejb.session.stateless.AircraftConfigurationSessionBeanRemote;
import ejb.session.stateless.AircraftSessionBeanRemote;
import ejb.session.stateless.AirportSessionBeanRemote;
import ejb.session.stateless.CabinCustomerSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import ejb.session.stateless.FlightRoutesSessionBeanRemote;
import ejb.session.stateless.FlightSchedulePlanSessionBeanRemote;
import ejb.session.stateless.FlightScheduleSessionBeanRemote;
import ejb.session.stateless.FlightSessionBeanRemote;
import java.util.Scanner;
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
    private FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote;
    private FlightSchedulePlanSessionBeanRemote flightSchedulePlanSessionBeanRemote;
            

    public MainApp() {
    }

    public MainApp(EmployeeSessionBeanRemote employeeSessionBean,
                   CustomerSessionBeanRemote customerSessionBean,
                   AircraftSessionBeanRemote aircraftSessionBeanRemote,
                   AircraftConfigurationSessionBeanRemote aircraftConfigurationSessionBeanRemote,
                   CabinCustomerSessionBeanRemote cabinCustomerSessionBeanRemote,
                   FlightRoutesSessionBeanRemote flightRoutesSessionBeanRemote,
                   AirportSessionBeanRemote airportSessionBeanRemote,
                   FlightSessionBeanRemote flightSessionBeanRemote,
                   FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote,
                   FlightSchedulePlanSessionBeanRemote flightSchedulePlanSessionBeanRemote) {
        this.employeeSessionBean = employeeSessionBean;
        this.customerSessionBean = customerSessionBean;
        this.aircraftSessionBeanRemote = aircraftSessionBeanRemote;
        this.aircraftConfigurationSessionBeanRemote = aircraftConfigurationSessionBeanRemote;
        this.cabinCustomerSessionBeanRemote = cabinCustomerSessionBeanRemote;
        this.flightRoutesSessionBeanRemote = flightRoutesSessionBeanRemote;
        this.airportSessionBeanRemote = airportSessionBeanRemote;
        this.flightSessionBeanRemote = flightSessionBeanRemote;
        this.flightScheduleSessionBeanRemote = flightScheduleSessionBeanRemote;
        this.flightSchedulePlanSessionBeanRemote = flightSchedulePlanSessionBeanRemote;
    }
    
    public void runApp() {
        Scanner scanner = new Scanner(System.in);
        Long employeeId;
        Integer response;
        
        while(true)
        {
            System.out.println("*** Welcome to the Management Client ***\n");
            System.out.println("1: Login");
            System.out.println("2: Exit\n");
            response = 0;
            
            while(response < 1 || response > 2)
            {
                System.out.print("> ");

                response = scanner.nextInt();

                if (response == 1)
                {   
                    try {
                        employeeId = doLogin(scanner);
                        ManagementModule managementModule = new ManagementModule(employeeId,
                                                                                 employeeSessionBean,
                                                                                 customerSessionBean,
                                                                                 aircraftSessionBeanRemote,
                                                                                 aircraftConfigurationSessionBeanRemote,
                                                                                 cabinCustomerSessionBeanRemote,
                                                                                 flightRoutesSessionBeanRemote,
                                                                                 airportSessionBeanRemote,
                                                                                 flightSessionBeanRemote,
                                                                                 flightScheduleSessionBeanRemote,
                                                                                 flightSchedulePlanSessionBeanRemote);
                        managementModule.adminLoginPage();
                    }
                    catch(InvalidLoginCredentialException ex) 
                    {
                        System.out.println("Invalid login credential: " + ex.getMessage() + "\n");
                    } 
                }
                
                if (response == 2) {
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
        System.out.println("\n*** Management client :: Login ***\n");
        System.out.print("Enter email> ");
        scanner.nextLine();
        String email = scanner.nextLine().trim();
        System.out.print("Enter password> ");
        String password = scanner.nextLine().trim();
        
        if(email.length() > 0 && password.length() > 0)
        {
            return employeeSessionBean.login(email, password);
        }
        else
        {
            throw new InvalidLoginCredentialException("Missing login credential!");
        }
    }
}
