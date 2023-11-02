/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author admin
 */
public class ConflictingFlightScheduleException extends Exception {

    /**
     * Creates a new instance of <code>ConflictingFlightScheduleException</code>
     * without detail message.
     */
    public ConflictingFlightScheduleException() {
    }

    /**
     * Constructs an instance of <code>ConflictingFlightScheduleException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ConflictingFlightScheduleException(String msg) {
        super(msg);
    }
}
