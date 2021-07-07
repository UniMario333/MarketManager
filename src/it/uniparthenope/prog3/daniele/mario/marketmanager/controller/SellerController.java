/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.controller;

import it.uniparthenope.prog3.daniele.mario.marketmanager.controller.facade.ManageSeller;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.model.factory.Account;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.CategoryView;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.LoginView;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.ProductView;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.SellerView;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Il controller associato alla view {@code SellerView},
 * I metodi principali vengono richiamati dalla {@code ManageSeller} (Façade).
 * 
 * @see Product
 * @see ManageSeller
 * @see SellerView
 *
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class SellerController extends AbstractController {
    
    /** Nuova istanza di {@code ManageSeller} */
    private ManageSeller manageSeller = new ManageSeller();
    
    /**
     * Costruttore della classe che richiama 
     * la superclasse {@code AbstractController} per richiamare l'istanza view. 
     * 
     * @param pView l'istanza dell'attuale finestra
     */
    public SellerController(SellerView pView) {
         
        super(pView);
        
    }
    
    /** 
     * Richiama il metodo per inizializzare la tabella della view 
     * {@code SellerView} da una lista in {@code ManageSeller}.
     * 
     * @return la lista dei prodotti
     * @throws java.sql.SQLException
     * @see ManageSeller
     * @see SellerView
     */
    public ArrayList<Account> initTable() throws SQLException {
        
        return manageSeller.manageInitTable();
        
    }
    
    /**
     * Richiama il metodo da {@code ManageSeller} per aggiungere record di
     * dati al database.
     * 
     * @param pUsername lo username dell'account venditore
     * @param pPassword la password dell'account venditore
     * @throws SQLException
     * @throws StringEmptyException
     * @see ManageSeller
     */
    public void addPressed(String pUsername, String pPassword) throws SQLException, StringEmptyException {
        
        manageSeller.manageAddPressed(pUsername, pPassword);
        
    }
    
    /**
     * Richiama il metodo da {@code ManageSeller} per aggiornare record già 
     * esistenti nel database.
     * 
     * @param pUsername il nuovo username dell'account venditore
     * @param pPassword la nuova password dell'account venditore
     * @param pOldUsername l'attuale username dell'account venditore
     * @throws SQLException
     * @throws StringEmptyException 
     * @see ManageSeller
     */
    public void updatePressed(String pUsername, String pPassword, String pOldUsername) throws SQLException, StringEmptyException {
        
        manageSeller.manageUpdatePressed(pUsername, pPassword, pOldUsername);
        
    }
    
    /**
     * Richiama il metodo da {@code ManageSeller} rimuovere record nel 
     * database.
     * 
     * @param pUsername
     * @throws SQLException 
     * @see ManageSeller
     */
    public void deletePressed(String pUsername) throws SQLException {
        
        manageSeller.manageDeletePressed(pUsername);
        
    }
    
    /**
     * Chiude la view corrente e apre una {@code LoginView}.
     * 
     * @see LoginView
     */
    public void logoutPressed() {
        
        new LoginView().setVisible(true);
        
        this.view.dispose();
        
    }
    
    /**
     * Chiude la view corrente e apre una {@code CategoryView}.
     * 
     * @throws java.sql.SQLException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException
     * @see CategoryView
     */
    public void switchToCategoryView() throws SQLException, StringEmptyException {
        
        new CategoryView().setVisible(true);
        
        this.view.dispose();
        
    }
    
    /**
     * Chiude la view corrente e apre una {@code ProductView}.
     * 
     * @throws java.sql.SQLException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException
     * @see ProductView
     */
    public void switchToProductView() throws SQLException, StringEmptyException {
        
        new ProductView().setVisible(true);
        
        this.view.dispose();
        
    }
    
}
