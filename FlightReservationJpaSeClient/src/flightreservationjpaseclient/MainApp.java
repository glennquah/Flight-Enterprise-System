/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightreservationjpaseclient;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ws.partner.InvalidLoginCredentialException;
import ws.partner.InvalidLoginCredentialException_Exception;
import ws.partner.PartnerWebService;
import ws.partner.PartnerWebService_Service;

/**
 *
 * @author admin
 */
public class MainApp {
    public void runApp() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Long customerId;
        Integer response;
        
        while(true)
        {
            System.out.println("*** Welcome to the Flight Reservation Client ***\n");
            System.out.println("1: Login");
            System.out.println("2: Exit\n");
            response = 0;
            
            while(response < 1 || response > 2)
            {
                System.out.print("> ");

                response = scanner.nextInt();
                scanner.nextLine();
                if (response == 1)
                {   
                    try {
                        System.out.println("\n*** Holiday Reservation System :: Login ***\n");
                        System.out.print("Enter email> ");
                        String email = scanner.nextLine().trim();
                        System.out.print("Enter password> ");
                        String password = scanner.nextLine().trim();
                        Long partnerId = login(email, password);
                        
                        HolidayReservationModule holidayReservationModule = new HolidayReservationModule();            
                        holidayReservationModule.partnerLoginPage();
                    } catch (InvalidLoginCredentialException_Exception ex) {
                        System.out.println("The login credentials are wrong!");
                    } 
                }
                if (response == 2)
                {   
                    break;
                }
                
            }
            if (response == 2) {
                break;
            }
        }
        
    }
    
    private static Long login(java.lang.String email, java.lang.String password) throws InvalidLoginCredentialException_Exception {
        PartnerWebService_Service service = new PartnerWebService_Service();
        PartnerWebService port = service.getPartnerWebServicePort();
        return port.login(email, password);
    }
}
    
