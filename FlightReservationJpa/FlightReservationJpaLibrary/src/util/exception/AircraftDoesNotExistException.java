/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author Lenovo
 */
public class AircraftDoesNotExistException extends Exception{

    /**
     * Creates a new instance of <code>AircraftDoesNotExistException</code>
     * without detail message.
     */
    public AircraftDoesNotExistException() {
    }

    /**
     * Constructs an instance of <code>AircraftDoesNotExistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public AircraftDoesNotExistException(String msg) {
        super(msg);
    }
}
