/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.exception;

/**
 * Eccezione in caso di errori vari presenti nella combo box.
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class ComboBoxException extends Exception {
    
    public ComboBoxException(String pDescErr) {
        
        super("ERRORE: " + pDescErr);
        
    }
    
}
