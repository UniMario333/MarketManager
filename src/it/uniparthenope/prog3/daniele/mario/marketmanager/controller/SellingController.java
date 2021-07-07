/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.controller;

import it.uniparthenope.prog3.daniele.mario.marketmanager.model.builder.product.Product;
import it.uniparthenope.prog3.daniele.mario.marketmanager.controller.facade.ManageSelling;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.UpdateSellingMaxException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.NegAndZeroException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.model.builder.category.Category;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.LoginView;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.SellingView;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Il controller associato alla view {@code SellingView},
 * I metodi principali vengono richiamati dalla {@code ManageSelling} (Façade).
 * 
 * @see Product
 * @see Category
 * @see ManageSelling
 * @see SellingView
 *
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class SellingController extends AbstractController {
    
    /** Nuova istanza di {@code ManageProduct} */
    private ManageSelling manageSelling = new ManageSelling();
    
    /**
     * Costruttore della classe che richiama 
     * la superclasse {@code AbstractController} per richiamare l'istanza view. 
     * 
     * @param pView l'istanza dell'attuale finestra
     */
    public SellingController(SellingView pView) {
         
        super(pView);
        
    }
    
    /** 
     * Richiama il metodo per inizializzare la tabella della view 
     * {@code SellingView} da una lista in {@code ManageSelling}.
     * 
     * @return la lista dei prodotti
     * @throws java.sql.SQLException
     * @see ManageSelling
     * @see SellingView
     */
    public ArrayList<Product> initTable() throws SQLException{
        
        return manageSelling.manageInitTable();
        
    }
    
    /** 
     * Richiama il metodo per inizializzare la tabella della view 
     * {@code SellingView} da una lista filtrata per categoria selezionata
     * in {@code ManageSelling}.
     * 
     * @param pSelectedCategory la categoria selezionata
     * @return la lista dei prodotti filtrata per la categoria selezionata
     * @throws java.sql.SQLException
     * @see ManageSelling
     * @see SellingView
     */
    public ArrayList<Product> filteredTable(String pSelectedCategory) throws SQLException{
        
        return manageSelling.manageFilteredTable(pSelectedCategory);
        
    }
    
    /**
     * Richiama il metodo da {@code ManageProduct} per aggiungere record di
     * dati alla lista dello scontrino.
     * 
     * @param pAddedProductList la lista dei prodotti dello scontrino
     * @param pSelectedProduct il prodotto selezionato
     * @param pSelectedQuantity la quantità selezionata
     * @param pProductDB il prodotto selezionato dalla lista prodotti
     * @throws NegAndZeroException
     * @throws UpdateSellingMaxException 
     * @see ManageSelling
     */
    public void addProductPressed(ArrayList<Product> pAddedProductList, String pSelectedProduct, int pSelectedQuantity, Product pProductDB) throws NegAndZeroException, UpdateSellingMaxException {
         
        manageSelling.manageAddProductPressed(pAddedProductList, pSelectedProduct, pSelectedQuantity, pProductDB);
        
    }
    
    /**
     * Richiama il metodo da {@code ManageSelling} per aggiornare record con il
     * risultato della differenza della quantità tra prodotti della lista 
     * dello scontrino e lista del database.
     * 
     * @param pProductList la lista dei prodotti del database
     * @param pAddedProductList la lista dei prodotti dello scontrino
     * @throws SQLException
     * @see ManageSelling
     */
    public void updateDatabase(ArrayList<Product> pProductList, ArrayList<Product> pAddedProductList) throws SQLException {
    
        manageSelling.manageUpdateDatabase(pProductList, pAddedProductList);
    
    }
    
    /**
     * Richiama il metodo da {@code ManageSelling} per inizializzare con una 
     * stringa lo scontrino.
     * 
     * @return la stringa per l'area di testo dello scontrino
     * @see ManageSelling
     */
    public String recepitInitText() {
        
        return manageSelling.manageRecepitInitText();
        
    }
    
    /**
     * Richiama il metodo da {@code ManageSelling} per aggiungere i prodotti 
     * nello scontrino.
     * 
     * @param pAddedProductList la lista dei prodotti dello scontrino
     * @return la stringa per l'area di testo dello scontrino
     * @see ManageSelling
     */
    public String fillRecepit(ArrayList<Product> pAddedProductList) {
        
        return manageSelling.manageFillRecepit(pAddedProductList);
        
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
     * Richiama il metodo da {@code ManageSelling} per popolare la combo box 
     * della view {@code SellingView} attraverso una lista di nomi di categorie.
     * 
     * @return la lista dei nomi delle categorie
     * @throws SQLException 
     * @see ManageSelling
     * @see SellingView
     */
    public ArrayList<Category> getCategoryList() throws SQLException {
        
        return manageSelling.manageGetCategoryList();
        
    }
    
}
