/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.model.factory;

/**
 * Bean che rappresenta un account admin.
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class Admin implements Account{
    
    /** Nome utente */
    private String username;
    
    /** Password dell'utente */
    private String password;
    
    /** Tipo dell'utente */
    private String type;
    
    /**
     * Ottiene il nome utente dell'account.
     * 
     * @return il nome utente dell'account
     * @see #setUsername(java.lang.String)
     */
    @Override
    public String getUsername() {
        
        return username;
        
    }
    
    /**
     * Imposta il nome utente dell'account.
     * 
     * @param pUsername il nuovo nome utente dell'account
     * @see #getUsername()
     */
    @Override
    public void setUsername(String pUsername) {
        
        this.username = pUsername;
        
    }
    
    /**
     * Ottiene la password dell'account
     * 
     * @return la password dell'utente
     * @see #setPassword(java.lang.String)
     */
    @Override
    public String getPassword() {
        
        return password;
        
    }
    
    /**
     * Imposta la password dell'account.
     * 
     * @param pPassword la nuova password dell'utente
     * @see #getPassword() 
     */
    @Override
    public void setPassword(String pPassword) {
        
        this.password = pPassword;
        
    }
    
    /**
     * Ottiene il tipo dell'account.
     * 
     * @return il tipo dell'utente
     * @see #setType(UserType)
     * @see UserType
     */
    @Override
    public String getType() {
        
        return type;
        
    }

    /**
     * Imposta il tipo dell'account.
     * 
     * @param pType il nuovo tipo dell'utente
     * @see #getType()
     * @see UserType
     */
    @Override
    public void setType(String pType) {
        
        this.type = pType;
        
    }
    
}
