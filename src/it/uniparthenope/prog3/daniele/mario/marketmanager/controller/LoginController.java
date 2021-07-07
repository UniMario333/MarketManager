/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.controller;

import it.uniparthenope.prog3.daniele.mario.marketmanager.controller.facade.ManageLogin;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.AccountTypeException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.ComboBoxException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.LoginView;

import java.sql.SQLException;

/**
 * Il controller associato alla view {@code LoginView},
 * I metodi principali vengono richiamati dalla {@code ManageLogin} (Façade).
 * 
 * @see Account
 * @see AccountType
 * @see AccountFactory
 * @see Seller
 * @see Admin
 * @see ManageLogin
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class LoginController extends AbstractController {
    
    /**
     * Costruttore della classe che richiama 
     * la superclasse {@code AbstractController} per richiamare l'istanza view. 
     * 
     * @param pView l'istanza dell'attuale finestra
     */
    public LoginController(LoginView pView) {
         
        super(pView);
        
    }
    
    /**
     * Richiama il metodo da {@code ManageCategory} per effettueare il login.
     *
     * @param pUsername lo username dell'account
     * @param pPassword la password dell'account
     * @param pRole Il ruolo dell'account
     * @param pView l'oggetto dell'attuale finestra
     * @throws java.sql.SQLException 
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.ComboBoxException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.AccountTypeException
     * @see ManageLogin
     */
    public void loginPressed(String pUsername, String pPassword, String pRole, LoginView pView) throws SQLException, ComboBoxException, AccountTypeException{
        
        ManageLogin manageLogin = new ManageLogin();
        
        manageLogin.manageLoginPressed(pUsername, pPassword, pRole, pView);
        
    }
    
}
