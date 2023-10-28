/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author admin
 */
public class AirportDoesNotExistException extends Exception {

    /**
     * Creates a new instance of <code>AirportDoesNotExistException</code>
     * without detail message.
     */
    public AirportDoesNotExistException() {
    }

    /**
     * Constructs an instance of <code>AirportDoesNotExistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public AirportDoesNotExistException(String msg) {
        super(msg);
    }
}
