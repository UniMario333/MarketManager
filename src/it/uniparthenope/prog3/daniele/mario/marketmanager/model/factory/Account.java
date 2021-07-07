/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.model.factory;

/**
 * Fornisce un'interfaccia per i metodi della factory method.
 * 
 * @see AccountFactory
 * @see Admin
 * @see Seller
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public interface Account {
    
    public String getUsername();
    
    public void setUsername(String username);
    
    public String getPassword();
    
    public void setPassword(String password);
    
    public String getType();

    public void setType(String type);
    
}
