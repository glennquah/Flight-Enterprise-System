/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author Lenovo
 */
public class FlightDoesNotExist extends Exception {

    /**
     * Creates a new instance of <code>FlightDoesNotExist</code> without detail
     * message.
     */
    public FlightDoesNotExist() {
    }

    /**
     * Constructs an instance of <code>FlightDoesNotExist</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FlightDoesNotExist(String msg) {
        super(msg);
    }
}
