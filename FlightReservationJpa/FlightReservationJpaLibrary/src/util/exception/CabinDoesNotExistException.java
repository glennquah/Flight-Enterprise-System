/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author Lenovo
 */
public class CabinDoesNotExistException extends Exception {

    /**
     * Creates a new instance of <code>CabinDoesNotExistException</code> without
     * detail message.
     */
    public CabinDoesNotExistException() {
    }

    /**
     * Constructs an instance of <code>CabinDoesNotExistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CabinDoesNotExistException(String msg) {
        super(msg);
    }
}
