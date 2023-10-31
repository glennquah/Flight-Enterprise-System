/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author admin
 */
public class FlightRouteAlreadyExistException extends Exception {

    /**
     * Creates a new instance of <code>FlightRouteAlreadyExistException</code>
     * without detail message.
     */
    public FlightRouteAlreadyExistException() {
    }

    /**
     * Constructs an instance of <code>FlightRouteAlreadyExistException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public FlightRouteAlreadyExistException(String msg) {
        super(msg);
    }
}
