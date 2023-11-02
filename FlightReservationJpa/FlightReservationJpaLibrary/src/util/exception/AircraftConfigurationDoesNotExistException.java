/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author Lenovo
 */
public class AircraftConfigurationDoesNotExistException extends Exception {

    /**
     * Creates a new instance of
     * <code>AircraftConfigurationDoesNotExistException</code> without detail
     * message.
     */
    public AircraftConfigurationDoesNotExistException() {
    }

    /**
     * Constructs an instance of
     * <code>AircraftConfigurationDoesNotExistException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AircraftConfigurationDoesNotExistException(String msg) {
        super(msg);
    }
}
