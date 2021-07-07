/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.exception;

/**
 * Eccezione in caso di tipo di account insesistente o errato .
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class AccountTypeException extends Exception {
    
    public AccountTypeException(String pDescErr) {
        
        super("ERRORE: " + pDescErr);
        
    }
    
}
