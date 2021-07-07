/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.model.factory;

import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.AccountTypeException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.model.AccountType;
import static it.uniparthenope.prog3.daniele.mario.marketmanager.model.AccountType.ADMIN;
import static it.uniparthenope.prog3.daniele.mario.marketmanager.model.AccountType.SELLER;

import javax.swing.JOptionPane;

/**
 * Rappresenta il tipo di utente.
 * 
 * @see Account
 * @see AccountFactory
 * @see Seller
 * @see Admin
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class AccountFactory {
    
   /**
    * A seconda del tipo dell'account, crea un account {@code Admin} o 
    * {@code Seller}, se il tipo non dovesse corrispondere a nessuno esistente,
    * lancia un'eccezione.
    * 
    * @param pAccountType il tipo di account
    * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.AccountTypeException
    */
    public Account getAccount(AccountType pAccountType) throws AccountTypeException {
        
        switch(pAccountType) {
            
            case SELLER: 
                
                return new Seller();
                
            case ADMIN:
                
                return new Admin();
            
            default:
                
                throw new AccountTypeException("Dati tipo account ricevuti inesistenti");
                
                //JOptionPane.showMessageDialog(null, "Dati tipo account ricevuti inesistenti");
                
                //break;
            
        }
        
        //return null;

    }
    
}
