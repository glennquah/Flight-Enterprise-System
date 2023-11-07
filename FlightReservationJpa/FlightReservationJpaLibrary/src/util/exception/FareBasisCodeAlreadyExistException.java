/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author admin
 */
public class FareBasisCodeAlreadyExistException extends Exception {

    /**
     * Creates a new instance of <code>FareBasisCodeAlreadyExistException</code>
     * without detail message.
     */
    public FareBasisCodeAlreadyExistException() {
    }

    /**
     * Constructs an instance of <code>FareBasisCodeAlreadyExistException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public FareBasisCodeAlreadyExistException(String msg) {
        super(msg);
    }
}
