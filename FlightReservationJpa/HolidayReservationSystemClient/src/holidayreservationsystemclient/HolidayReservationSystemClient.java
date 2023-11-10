/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package holidayreservationsystemclient;

import ws.login.PartnerWebService;
import ws.login.PartnerWebService_Service;

/**
 *
 * @author Lenovo
 */
public class HolidayReservationSystemClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PartnerWebService_Service service = new PartnerWebService_Service();
        PartnerWebService port =  service.getPartnerWebServicePort();
        
        System.out.println("*** PARTNER LOGIN ****");
    }
    
}
