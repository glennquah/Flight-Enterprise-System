/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author admin
 */
public class FlightNumberAlreadyExistException extends Exception {

    /**
     * Creates a new instance of <code>FlightNumberAlreadyExistException</code>
     * without detail message.
     */
    public FlightNumberAlreadyExistException() {
    }

    /**
     * Constructs an instance of <code>FlightNumberAlreadyExistException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public FlightNumberAlreadyExistException(String msg) {
        super(msg);
    }
}
