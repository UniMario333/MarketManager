/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.controller;

import it.uniparthenope.prog3.daniele.mario.marketmanager.controller.facade.ManageProduct;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.NegAndZeroException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.model.builder.category.Category;
import it.uniparthenope.prog3.daniele.mario.marketmanager.model.builder.product.Product;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.CategoryView;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.LoginView;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.ProductView;
import it.uniparthenope.prog3.daniele.mario.marketmanager.view.SellerView;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Il controller associato alla view {@code ProductView},
 * I metodi principali vengono richiamati dalla {@code ManageProduct} (Façade).
 * 
 * @see Product
 * @see ManageProduct
 * @see ProductView
 *
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class ProductController extends AbstractController {
    
    /**
     * Costruttore della classe che richiama 
     * la superclasse {@code AbstractController} per richiamare l'istanza view. 
     * 
     * @param pView l'istanza dell'attuale finestra
     */
    public ProductController(ProductView pView) {
         
        super(pView);
        
    }
    
    /** Nuova istanza di {@code ManageProduct} */
    private ManageProduct manageProduct = new ManageProduct();
    
    /** 
     * Richiama il metodo per inizializzare la tabella della view 
     * {@code ProductView} da una lista in {@code ManageProduct}.
     * 
     * @return la lista dei prodotti
     * @throws java.sql.SQLException
     * @see ManageProduct
     * @see ProductView
     */
    public ArrayList<Product> initTable() throws SQLException {
        
        return manageProduct.manageInitTable();
        
    }
    
    /**
     * Richiama il metodo da {@code ManageProduct} per aggiungere record di
     * dati al database.
     * 
     * @param pName il nome del prodotto
     * @param pQuantity la quantità del prodotto
     * @param pPrice il prezzo del prodotto
     * @param pProdCategory la categoria del prodotto
     * @throws SQLException
     * @throws NumberFormatException
     * @throws NegAndZeroException
     * @throws StringEmptyException
     * @see ManageProduct
     */
    public void addPressed(String pName, int pQuantity, float pPrice, String pProdCategory) throws SQLException, NumberFormatException, NegAndZeroException, StringEmptyException {
        
        manageProduct.manageAddPressed(pName, pQuantity, pPrice, pProdCategory);
        
    }
    
    /**
     * Richiama il metodo da {@code ManageProduct} per aggiornare record già 
     * esistenti nel database.
     * 
     * @param pName il nuovo nome del prodotto
     * @param pQuantity la nuova quantità del prodotto
     * @param pPrice il nuovo prezzo del prodotto
     * @param pProdCategory la nuova categoria del prodotto
     * @param pOldName l'attuale nome del prodotto
     * @throws SQLException
     * @throws NumberFormatException
     * @throws StringEmptyException
     * @throws NegAndZeroException 
     * @see ManageProduct
     */
    public void updatePressed(String pName, int pQuantity, float pPrice, String pProdCategory, String pOldName) throws SQLException, NumberFormatException, StringEmptyException, NegAndZeroException {
        
        manageProduct.manageUpdatePressed(pName, pQuantity, pPrice, pProdCategory, pOldName);
        
    }
    
    /**
     * Richiama il metodo da {@code ManageProduct} rimuovere record nel 
     * database.
     * 
     * @param pName il nome del prodotto
     * @throws SQLException 
     * @see ManageProduct
     */
    public void deleteProductPressed(String pName) throws SQLException {
        
        manageProduct.manageDeletePressed(pName);
        
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
     * Chiude la view corrente e apre una {@code SellerView}.
     * 
     * @throws java.sql.SQLException
     * @see SellerView
     */
    public void switchToSellerView() throws SQLException {
        
        new SellerView().setVisible(true);
        
        this.view.dispose();
        
    }
    
    /**
     * Chiude la view corrente e apre una {@code CategoryView}.
     * 
     * @throws java.sql.SQLException
     * @see CategoryView
     */
    public void switchToCategoryView() throws SQLException {
        
        new CategoryView().setVisible(true);
        
        this.view.dispose();
        
    }
    
    /**
     * Richiama il metodo da {@code ManageProduct} per popolare la combo box 
     * della view {@code ProductView} attraverso una lista di nomi di categorie.
     * 
     * @return la lista dei nomi delle categorie
     * @throws SQLException 
     * @see ManageProduct
     * @see ProductView
     */
    public ArrayList<Category> getCategoryList() throws SQLException {
        
        return manageProduct.manageGetCategoryList();
        
    }
    
}
