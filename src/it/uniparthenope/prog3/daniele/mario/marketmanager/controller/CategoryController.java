/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.controller;

import it.uniparthenope.prog3.daniele.mario.marketmanager.controller.facade.ManageCategory;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.model.builder.category.Category;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.CategoryView;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.LoginView;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.ProductView;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.SellerView;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Il controller associato alla view {@code CategoryView},
 * I metodi principali vengono richiamati dalla {@code ManageCategory} (Façade).
 * 
 * @see Category
 * @see ManageCategory
 * @see CategoryView
 * @see AbstractController
 *
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class CategoryController extends AbstractController {
    
    /** Nuova istanza di {@code ManageCategory} */
    private ManageCategory manageCategory = new ManageCategory();
    
    /**
     * Costruttore della classe che richiama 
     * la superclasse {@code AbstractController} per richiamare l'istanza view. 
     * 
     * @param pView l'istanza dell'attuale finestra
     */
    public CategoryController(CategoryView pView) {
         
        super(pView);
        
    }
    
    /** 
     * Richiama il metodo per inizializzare la tabella della view 
     * {@code CategoryView} da una lista in {@code ManageCategory}.
     * 
     * @return la lista dei prodotti
     * @throws java.sql.SQLException
     * @see ManageCategory
     * @see CategoryView
     */
    public ArrayList<Category> initTable() throws SQLException {
        
        return manageCategory.manageInitTable();
        
    }
    
    /** 
     * Richiama il metodo da {@code ManageCategory} per aggiungere record di
     * dati al database.
     * 
     * @param pName il nome della categoria
     * @param pDescription la descrizione della categoria
     * @see ManageCategory
     * @throws java.sql.SQLException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException
     */
    public void addPressed(String pName, String pDescription) throws SQLException, StringEmptyException {
        
        manageCategory.manageAddPressed(pName, pDescription);
        
    }
    
    /**
     * Richiama il metodo da {@code ManageCategory} per aggiornare record già 
     * esistenti nel database.
     * 
     * @param pName il nuovo nome della categoria
     * @param pDescription la nuova descrizione della categoria
     * @param pOldName l'attuale nome della categoria
     * @see ManageCategory
     * @throws SQLException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException
     */
    public void updatePressed(String pName, String pDescription, String pOldName) throws SQLException, StringEmptyException {
         
        manageCategory.manageUpdatePressed(pName, pDescription, pOldName);
        
    }
    
    /**
     * Richiama il metodo da {@code ManageCategory} rimuovere record nel 
     * database.
     * 
     * @param pName
     * @throws SQLException
     * @see ManageCategory
     */
    public void deletePressed(String pName) throws SQLException {
        
        manageCategory.manageDeletePressed(pName);
        
    }
    
    /**
     * Chiude la view corrente e apre una {@code LoginView}.
     * 
     * @see LoginView
     * @see AbstractController
     */
    public void logoutPressed() {
        
        new LoginView().setVisible(true);
        
        this.view.dispose();
        
    }
    
    /**
     * Chiude la view corrente e apre una {@code SellerView}.
     * 
     * @throws java.sql.SQLException
     * @see SellerView
     * @see AbstractController
     */
    public void switchToSellerView() throws SQLException {
        
        new SellerView().setVisible(true);
        
        this.view.dispose();
        
    }
    
    /**
     * Chiude la view corrente e apre una {@code ProductView}.
     * 
     * @throws java.sql.SQLException
     * @see ProductView
     * @see AbstractController
     */
    public void switchToProductView() throws SQLException {
        
        new ProductView().setVisible(true);
        
        this.view.dispose();
        
    }
    
}
