/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author admin
 */
public class FlightSchedulePlanDoesNotExistException extends Exception {

    /**
     * Creates a new instance of
     * <code>FlightSchedulePlanDoesNotExistException</code> without detail
     * message.
     */
    public FlightSchedulePlanDoesNotExistException() {
    }

    /**
     * Constructs an instance of
     * <code>FlightSchedulePlanDoesNotExistException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FlightSchedulePlanDoesNotExistException(String msg) {
        super(msg);
    }
}
