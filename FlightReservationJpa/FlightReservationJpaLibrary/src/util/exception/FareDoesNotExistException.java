/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author admin
 */
public class FareDoesNotExistException extends Exception {

    /**
     * Creates a new instance of <code>FareDoesNotExistException</code> without
     * detail message.
     */
    public FareDoesNotExistException() {
    }

    /**
     * Constructs an instance of <code>FareDoesNotExistException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FareDoesNotExistException(String msg) {
        super(msg);
    }
}
