/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.exception;

/**
 * Eccezzione in caso di stringe di solo carattere spazio esempio:("") o (" "). 
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class StringEmptyException extends Exception {
    
    public StringEmptyException(String pDescErr) {
        
        super("ERRORE: " + pDescErr);
        
    }
    
}
