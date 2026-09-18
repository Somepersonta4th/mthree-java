package org.WileyEdgeCorp.FlooringMastery.exceptions;

public class PersistenceException extends Exception {
    public PersistenceException(String message, Exception e) {
        super(message,e);
    }
}
