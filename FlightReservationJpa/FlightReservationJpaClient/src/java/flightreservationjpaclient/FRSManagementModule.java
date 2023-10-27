/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightreservationjpaclient;

import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class FRSManagementModule {

    public FRSManagementModule() {
    }
    
    public void ManagementLoginPage(){
            Scanner sc = new Scanner(System.in);
            Integer response;
            while(true) {
                System.out.println("Please select the following options \n");
                System.out.println("1: ");
                System.out.println("2: ");
                System.out.println("3: ");
                System.out.println("4: back\n");

                System.out.print("> ");
                response = sc.nextInt();
                sc.nextLine();
                if(response == 1) {
                    //openDepositAccount(sc);
                } else if (response == 2) {
                    //issueAtmCard(sc);
                } else if (response == 3) {
                    //issueReplacementCard(sc);
                } else if (response == 4) {
                    break;
                } else {
                    System.out.println("Invalid option, please try again!\n");
                }
            }
        }
    
}
