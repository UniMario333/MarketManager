/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.model;

/**
 *
 * @author mario
 */
public enum AccountType {
    
    /** Indica un utente di tipo admin */
    ADMIN("A"),
    /** Indica un utente di tipo seller (venditore) */
    SELLER("S");
    
    /** Codice del tipo associato nel database */
    private final String code;
    
    /** Costruisce un tipo utente */
    private AccountType(String code) {
        
        this.code = code;
        
    }
    
    /**
     * Ottiene il codice del tipo utilizzato nel database.
     * 
     * @return il codice del tipo
     */
    public String getCode() {
        
        return code;
        
    }
    
    /**
     * Ritorna il codice carattere che rappresenta questa istanza di
     * {@code UserType}.
     * 
     * @return Il codice carattere usato nel database che rappresenta l'istanza
     */
    @Override
    public String toString() {
        
        return this.code;
        
    }
    
}
