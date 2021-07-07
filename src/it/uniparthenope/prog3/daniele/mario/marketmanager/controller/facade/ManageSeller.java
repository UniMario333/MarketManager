/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.controller.facade;

import static it.uniparthenope.prog3.daniele.mario.marketmanager.model.AccountType.SELLER;
import it.uniparthenope.prog3.daniele.mario.marketmanager.connection.Connect;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.model.factory.Account;
import it.uniparthenope.prog3.daniele.mario.marketmanager.model.factory.AccountFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Contiene i metodi più complessi che vengono richiamati dall corrispettivo
 * controller.
 * 
 * @see SellerController
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class ManageSeller {
    
    /**
     * Si connette al database.
     *
     * @see Connect
     */
    private final Connection connect = Connect.connectDB();
    /** Inizializzazione */
    private PreparedStatement ps = null;
    /** Inizializzazione */
    private ResultSet rs = null;
    
    /** Inizializzazione */
    private String query = null;
    
    /** 
     * Crea una nuova lista di account venditori.
     * 
     * @see AccounType
     * @see Account
     * @see AccountFactory
     * @see Seller
     */
    private ArrayList<Account> sellerList = new ArrayList<>();
    
    /** 
     * Inizializza un account venditore
     * 
     * @see AccounType
     * @see Account
     * @see AccountFactory
     * @see Seller
     */
    private Account seller = null;
    /** nuova instanza */ 
    private final AccountFactory accountFactory = new AccountFactory();

    /**
     * Popola la lista degli account venditori per il riempimento della tabella 
     * nella view attraverso na factory di account, prendendo le informazioni 
     * direttamente dal database attraverso una query.
     *
     * @throws SQLException
     * @see Connect
     * @see AccounType
     * @see Account
     * @see AccountFactory
     * @see Seller
     * @return la lista dei venditori
     */
    public ArrayList<Account> manageInitTable() throws SQLException {
       
        sellerList.clear();

        query = "SELECT * FROM ACCOUNTTB WHERE ACCOUNTTYPE = '" + SELLER + "' ORDER BY USERNAME ASC";

        ps = connect.prepareStatement(query);
        rs = ps.executeQuery();

        while(rs.next()) {

            seller = accountFactory.getAccount(SELLER);

            seller.setUsername(rs.getString(1));
            seller.setPassword(rs.getString(2));

            sellerList.add(seller);

        }
        
        return sellerList;
        
    }
    
    /**
     * Aggiunge le informazioni inviate al database.
     *
     * @see Connect
     * @see SellerController
     * @param pUsername lo username dell'account venditore
     * @param pPassword la password dell'account venditore
     * @throws java.sql.SQLException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException
     */
    public void manageAddPressed(String pUsername, String pPassword) throws SQLException, StringEmptyException {
        
        Statement addDB = connect.createStatement();
        
        if(!pUsername.equals("") && !pPassword.equals("")) {
            
            query = "INSERT INTO ACCOUNTTB VALUES('" + pUsername + "', '" + pPassword + "', '" + SELLER + "')";

            addDB.execute(query);
        
        } else {
            
            throw new StringEmptyException("Campi \"username\" e\\o \"password\" sono vuoti");
            
        }
        
    }
    
    /**
     * le informazioni inviate permettono la modifica di record del database
     * basandosi sul l'attuale chiave primaria che in questo caso è l'username.
     * 
     * @see Connect
     * @see SellerController
     * @param pUsername il nuovo username dell'account venditore
     * @param pPassword la nuova password dell'account venditore
     * @param pOldUsername l'attuale username dell'account venditore
     * @throws java.sql.SQLException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException
     */ 
    public void manageUpdatePressed(String pUsername, String pPassword, String pOldUsername) throws SQLException, StringEmptyException {
        
        Statement updateDB = connect.createStatement();
        
        if(!pUsername.equals("") && !pPassword.equals("")) {
        
            query = "UPDATE ACCOUNTTB SET USERNAME = '" + pUsername + "', PASSWORD = '" + pPassword + "' WHERE USERNAME = '" + pOldUsername + "' AND ACCOUNTTYPE = '" + SELLER + "'";

            updateDB.executeUpdate(query);
 
        } else {
            
            throw new StringEmptyException("Campi \"username\" e\\o \"password\" sono vuoti");
            
        }
        
    }
    
    /**
     * Attraverso la chiave primaria che sarebbe l'username 
     * dell'account venditore, permette l'eliminazione della riga dal database.
     * 
     * @param pUsername pUsername lo username dell'account venditore
     * @throws SQLException 
     * @see Connect
     * @see SellerController
     */
    public void manageDeletePressed(String pUsername) throws SQLException {
        
        Statement deleteDB = connect.createStatement();
            
        query = "DELETE FROM ACCOUNTTB WHERE USERNAME = '" + pUsername + "' AND ACCOUNTTYPE = '" + SELLER + "'";
                
        deleteDB.execute(query);
        
    }
    
}
