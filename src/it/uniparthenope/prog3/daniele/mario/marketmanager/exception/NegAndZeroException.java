/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.exception;

/**
 * Eccezione per in caso di inserimento di valori inferiori o uguali a zero.
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class NegAndZeroException extends Exception {
    
    public NegAndZeroException(String pDescErr) {
        
        super("ERRORE: " + pDescErr);
        
    }
    
}
