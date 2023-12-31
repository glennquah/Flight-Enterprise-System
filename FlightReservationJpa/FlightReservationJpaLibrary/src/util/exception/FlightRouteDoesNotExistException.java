/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author admin
 */
public class FlightRouteDoesNotExistException extends Exception {

    /**
     * Creates a new instance of <code>FlightRouteDoesNotExistException</code>
     * without detail message.
     */
    public FlightRouteDoesNotExistException() {
    }

    /**
     * Constructs an instance of <code>FlightRouteDoesNotExistException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public FlightRouteDoesNotExistException(String msg) {
        super(msg);
    }
}
