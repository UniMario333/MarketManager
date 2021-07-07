/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.controller.facade;

import it.uniparthenope.prog3.daniele.mario.marketmanager.model.AccountType;
import static it.uniparthenope.prog3.daniele.mario.marketmanager.model.AccountType.ADMIN;
import static it.uniparthenope.prog3.daniele.mario.marketmanager.model.AccountType.SELLER;
import it.uniparthenope.prog3.daniele.mario.marketmanager.connection.Connect;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.AccountTypeException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.ComboBoxException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.model.factory.Account;
import it.uniparthenope.prog3.daniele.mario.marketmanager.model.factory.AccountFactory;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.ProductView;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.SellingView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Contiene i metodi più complessi che vengono richiamati dall corrispettivo
 * controller.
 * 
 * @see LoginController
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class ManageLogin {
    
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
     * Attraverso le informazioni inviate, permette attraverso un confronto con
     * il database di bloccare l'accesso in caso di informazioni non
     * corrispondenti oppure permette di accedere a determinate finestre a 
     * seconda del ruolo attribuito all'account.
     *
     * @throws java.sql.SQLException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.ComboBoxException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.AccountTypeException
     * @see Account
     * @see AccountType
     * @see AccountFactory
     * @see Seller
     * @see Admin
     * @see LoginController
     * @param pUsername lo username dell'account
     * @param pPassword la password dell'account
     * @param pRole Il ruolo dell'account
     * @param pView l'istanza dell'attuale finestra
     */
    public void manageLoginPressed(String pUsername, String pPassword, String pRole, javax.swing.JFrame pView) throws SQLException, ComboBoxException, AccountTypeException {
        
        AccountType accountType = null;
        
        switch (pRole) {
            
            case "ADMIN":
                
                query = "SELECT * FROM ACCOUNTTB WHERE USERNAME = ? AND PASSWORD = ? AND ACCOUNTTYPE = '" + ADMIN + "'";
                
                accountType = ADMIN;
                
                break;
                
            case "VENDITORE":
                
                query = "SELECT * FROM ACCOUNTTB WHERE USERNAME = ? AND PASSWORD = ? AND ACCOUNTTYPE = '" + SELLER + "'";
                
                accountType = SELLER;
                
                break;
                
            default:
                
                throw new ComboBoxException("Dati errati nella combo box del login");
                
        }
        
        AccountFactory accountFactory = new AccountFactory();
        Account account = accountFactory.getAccount(accountType);
        
        ps = connect.prepareStatement(query);
            
        ps.setString(1, pUsername);
        ps.setString(2, pPassword);
            
        rs = ps.executeQuery();
            
        if(rs.next()) {
                
            account.setUsername(rs.getString(1));
            account.setPassword(rs.getString(2));
            account.setType(rs.getString(3));
                
            JOptionPane.showMessageDialog(null, "Login effettuato con successo!");
               
            switch (accountType) {

                case ADMIN:

                    new ProductView().setVisible(true);

                    pView.dispose();

                    break;

                case SELLER:

                    new SellingView().setVisible(true);

                    pView.dispose();

                    break;

                default:

                    throw new AccountTypeException("Dati tipo account ricevuti inesistenti");
                    
            }
                
            pView.dispose();
                
        } else {
             
            throw new SQLException("Nome utente o password sbagliati");
            
        }
    
    }
    
}
