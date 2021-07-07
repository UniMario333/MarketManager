/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.exception;

/**
 * Eccezione specifica per il {@code ManageSelling} in caso di un valore 
 * superiore rispetto a quello del database.
 * 
 * @see ManageSelling
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class UpdateSellingMaxException extends Exception {
    
    public UpdateSellingMaxException(String pDescErr) {
        
        super("ERRORE: " + pDescErr);
        
    }
    
}
