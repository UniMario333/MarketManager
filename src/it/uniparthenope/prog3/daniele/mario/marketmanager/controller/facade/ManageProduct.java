/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.controller.facade;

import it.uniparthenope.prog3.daniele.mario.marketmanager.model.builder.category.Category;
import it.uniparthenope.prog3.daniele.mario.marketmanager.model.builder.product.Product;
import it.uniparthenope.prog3.daniele.mario.marketmanager.connection.Connect;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.NegAndZeroException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException;

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
 * @see ProductController
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class ManageProduct {

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
    
    /** 
     * Inizializzazione 
     */
    private String query = null;
    
    /** 
     * Crea una nuova lista di prodotti.
     *
     * @see Product
     */
    private ArrayList<Product> productList = new ArrayList<>();
    /** 
     * Crea una nuova lista di categorie.
     *
     * @see Category
     */
    private ArrayList<Category> categoryList = new ArrayList<>();
    
    /** 
     * Inizializza un oggetto prodotto
     * 
     * @see Product
     */
    private Product product = null;
    
    /**
     * Popola la lista delle categorie prendendo le informazioni direttamente 
     * dal database attraverso una query, serve per il popolamento della 
     * combo box.
     * 
     * @see Connect
     * @see Category
     * @see ProductController
     * @return la lista dei nomi delle categorie
     * @throws SQLException 
     */
    public ArrayList<Category> manageGetCategoryList() throws SQLException{
        
        query = "SELECT CATNAME FROM CATEGORYTB ORDER BY CATNAME ASC";
        ps = connect.prepareStatement(query);
        rs = ps.executeQuery();
        
        Category category = null;

        while(rs.next()) {
            
            category = new Category.CategoryBuilder(rs.getString(1))
                    .setName(rs.getString(1))
                    .build();
            
            categoryList.add(category);
            
        }
        
        return categoryList;
        
    }
    
    /**
     * Popola la lista dei prodotti per il riempimento della tabella nella view 
     * attraverso un builder, prendendo le informazioni direttamente dal database
     * attraverso una query.
     * 
     * @throws java.sql.SQLException
     * @see Connect
     * @see Product
     * @see ProductController
     * @return la lista dei prodotti
     */
    public ArrayList<Product> manageInitTable() throws SQLException {
        
        
        productList.clear();

        query = "SELECT * FROM PRODUCTTB ORDER BY PRODNAME ASC";

        ps = connect.prepareStatement(query);
        rs = ps.executeQuery();

        while(rs.next()) {

            product = new Product.ProductBuilder(rs.getString(1))
                    .setName(rs.getString(1))
                    .setQuantity(rs.getInt(2))
                    .setPrice(rs.getFloat(3))
                    .setCategory(rs.getString(4))
                    .build();

            productList.add(product);

        }
        
        return productList;
        
    }
    
    /**
     * Aggiunge le informazioni inviate al database.
     * 
     * @see Connect
     * @see ProductController
     * @param pName il nome del prodotto
     * @param pQuantity la quantità del prodotto
     * @param pPrice il prezzo del prodotto
     * @param pProdCategory la categoria del prodotto
     * @throws SQLException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.NegAndZeroException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException
     */
    public void manageAddPressed(String pName, int pQuantity, float pPrice, String pProdCategory) throws SQLException, NumberFormatException, NegAndZeroException, StringEmptyException {
        
        Statement addDB = connect.createStatement();
        
        if(!pName.equals("") && pQuantity > 0  && pPrice > 0 && !pProdCategory.equals("")) {
            
            query = "INSERT INTO PRODUCTTB VALUES('" + pName + "', " + pQuantity + ", " + pPrice + ", '" + pProdCategory + "')";

            addDB.execute(query);
            
        } else if(pName.equals("") || pProdCategory.equals("")) {
            
            throw new StringEmptyException("Inserire dati nei vari campi");
            
        } else if (pQuantity <= 0 || pPrice <= 0) {
            
            throw new NegAndZeroException("Inserire una quantità maggiore di zero");
        
        }
        
    }
    
    /**
     * le informazioni inviate permettono la modifica di record del database
     * basandosi sul l'attuale chiave primaria che in questo caso è il nome 
     * del prodotto.
     * 
     * @see Connect
     * @see ProductController
     * @param pName il nuovo nome del prodotto
     * @param pQuantity la nuova quantità del prodotto
     * @param pPrice il nuovo prezzo del prodotto
     * @param pProdCategory la nuova categoria del prodotto
     * @param pOldName l'attuale nome del prodotto
     * @throws SQLException 
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.NegAndZeroException
     */
    public void manageUpdatePressed(String pName, int pQuantity, float pPrice, String pProdCategory, String pOldName) throws SQLException, NumberFormatException, StringEmptyException, NegAndZeroException {
        
        Statement updateDB = connect.createStatement();
        
        if(!pName.equals("") && pQuantity > 0 && pPrice > 0 && !pProdCategory.equals("")) {
        
            query = "UPDATE PRODUCTTB SET PRODNAME = '" + pName + "', PRODQTY = " + pQuantity + ", PRODPRICE = " + pPrice + ", PRODCAT = '" + pProdCategory + "' WHERE PRODNAME = '" + pOldName + "'";
            
            updateDB.executeUpdate(query);
            
        } else if(pName.equals("") || pProdCategory.equals("")) {
            
            throw new StringEmptyException("Inserire dati nei vari campi");
            
        } else if (pQuantity <= 0 || pPrice <= 0){
            
            throw new NegAndZeroException("Inserire una quantità maggiore di zero");
        
        }
        
    }
    
    /**
     * Attraverso la chiave primaria che sarebbe il nome del prodotto,
     * permette l'eliminazione della riga dal database.
     * 
     * @see Connect
     * @see ProductController
     * @param pName il nome del prodotto
     * @throws SQLException 
     */
    public void manageDeletePressed(String pName) throws SQLException {
        
        Statement deleteDB = connect.createStatement();
            
        query = "DELETE FROM PRODUCTTB WHERE PRODNAME = '" + pName + "'";
        
        deleteDB.execute(query);

    }
    
}
