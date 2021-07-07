/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.controller.facade;

import it.uniparthenope.prog3.daniele.mario.marketmanager.model.builder.category.Category;
import it.uniparthenope.prog3.daniele.mario.marketmanager.model.builder.product.Product;
import it.uniparthenope.prog3.daniele.mario.marketmanager.connection.Connect;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.UpdateSellingMaxException;
import it.uniparthenope.prog3.daniele.mario.marketmanager.exception.NegAndZeroException;

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
public class ManageSelling {
   
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
     * Crea una nuova lista di prodotti tra quelli presenti nel database.
     *
     * @see Product
     */
    private ArrayList<Product> productList = new ArrayList<>();
    /** 
     * Crea una nuova lista di prodotti che vengono aggiunti allo scontrino.
     *
     * @see Product
     */
    private ArrayList<Product> filteredProductList = new ArrayList<>();
    /** 
     * inizializza una lista di prodotti.
     *
     * @see Product
     */
    private ArrayList<Product> printList = null;
    /** 
     * Crea una nuova lista di categorie.
     *
     * @see Product
     */
    private ArrayList<Category> categoryList = new ArrayList<>();
    
    /** 
     * Inizializza un oggetto prodotto
     * 
     * @see Product
     */
    private Product product = null;
    
    /**
     * Popola la lista dei prodotti per il riempimento della tabella nella view 
     * attraverso un builder, prendendo le informazionidirettamente dal database
     * attraverso una query.
     * 
     * @throws java.sql.SQLException
     * @see Connect
     * @see Product
     * @see ProductController
     * @return la lista dei prodotti
     */
    public ArrayList<Product> manageInitTable() throws SQLException{
        
        productList.clear();

        query = "SELECT PRODNAME, PRODQTY, PRODPRICE, PRODCAT, CAT.CATDESC FROM PRODUCTTB PROD INNER JOIN CATEGORYTB CAT ON PROD.PRODCAT = CAT.CATNAME WHERE PRODQTY != 0 ORDER BY PRODNAME ASC";

        ps = connect.prepareStatement(query);
        rs = ps.executeQuery();

        while(rs.next()) {

            product = new Product.ProductBuilder(rs.getString(1))
                    .setName(rs.getString(1))
                    .setQuantity(rs.getInt(2))
                    .setPrice(rs.getFloat(3))
                    .setCategory(rs.getString(4))
                    .setCatDescription(rs.getString(5))
                    .build();

            productList.add(product);

        }
        
        return productList;
        
    }
    
    /**
     * Popola la lista dei prodotti filtrati per categorie per il popolamento
     * della tabella nella view attraverso un builder, prendendo le informazioni
     * direttamente dal database attraverso una query.
     * 
     * @param pSelectedCategory la categoria selezionata
     * @throws java.sql.SQLException
     * @see Connect
     * @see Product
     * @see ProductController
     * @return la lista dei prodotti filtrata per categoria selezionata
     */
    public ArrayList<Product> manageFilteredTable(String pSelectedCategory) throws SQLException {
        
        filteredProductList.clear();
        
        query = "SELECT PRODNAME, PRODQTY, PRODPRICE, PRODCAT, CAT.CATDESC FROM PRODUCTTB PROD INNER JOIN CATEGORYTB CAT ON PROD.PRODCAT = CAT.CATNAME WHERE PRODQTY != 0 AND PROD.PRODCAT = '" + pSelectedCategory + "' ORDER BY PRODNAME ASC";
        
        ps = connect.prepareStatement(query);
        rs = ps.executeQuery();

        while(rs.next()) {
            
            product = new Product.ProductBuilder(rs.getString(1))
                    .setName(rs.getString(1))
                    .setQuantity(rs.getInt(2))
                    .setPrice(rs.getFloat(3))
                    .setCategory(rs.getString(4))
                    .setCatDescription(rs.getString(5))
                    .build();
                
            filteredProductList.add(product);
            
        }
        
        return filteredProductList;
        
    }
    
    /**
     * Operazioni di aggiunta dei prodotti allo scontrino bloccando 
     * l'inserimento di altri prodotti con lo stesso nome, e creazione della
     * lista dei prodotti dello scontrino.
     * 
     * @param pAddedProductList la lista dei prodotti dello scontrino
     * @param pSelectedProduct il prodotto selezionato
     * @param pSelectedQuantity la quantità selezionata
     * @param pProductDB il prodotto selezionato dalla lista prodotti
     * @see Product
     * @throws NegAndZeroException
     * @throws UpdateSellingMaxException 
     */
    public void manageAddProductPressed(ArrayList<Product> pAddedProductList, String pSelectedProduct, int pSelectedQuantity, Product pProductDB) throws NegAndZeroException, UpdateSellingMaxException {
        
        Boolean flag = true; 
       
        for(Product productAdd : pAddedProductList) {

            if(pSelectedQuantity > 0 && pSelectedQuantity <= pProductDB.getQuantity() && productAdd.getName().equals(pSelectedProduct)) {

                flag = false; 

                productAdd.setQuantity(pSelectedQuantity);

            }

        }

        if(flag) {

            if(pProductDB.getName().equals(pSelectedProduct)) {

                if(pSelectedQuantity > 0 && pSelectedQuantity <= pProductDB.getQuantity()) {

                    product = new Product.ProductBuilder(pSelectedProduct)
                            .setName(pProductDB.getName())
                            .setQuantity(pSelectedQuantity)
                            .setPrice(pProductDB.getPrice())
                            .setCategory(pProductDB.getCategory())
                            .build();

                    pAddedProductList.add(product);

                } else if(pSelectedQuantity <= 0) {

                    throw new NegAndZeroException("Inserire una quantità maggiore di zero");

                } else if(pSelectedQuantity > pProductDB.getQuantity()) {

                    throw new UpdateSellingMaxException("Richiesta maggiore rispetto ai prodotti disponibili");

                }

            }

        }      
        
    }
    
    /**
     * Effettua l'aggiornamento del quantitativo dei prodotti attraverso la
     * differenza tra i prodotti della lista lista scontrino e i prodotti della 
     * lista del database eattravesro query.
     * 
     * @param pProductList la lista dei prodotti del database
     * @param pAddedProductList la lista dei prodotti dello scontrino
     * @see Connect
     * @see Product
     * @throws SQLException 
     */
    public void manageUpdateDatabase(ArrayList<Product> pProductList, ArrayList<Product> pAddedProductList) throws SQLException {
    
        Statement updateDB = connect.createStatement();
        
        int originalQTY = 0;
        
        for(Product prodAdd : pAddedProductList) {
            
            for(Product productDB : pProductList) {
                
                if(prodAdd.getName().equals(productDB.getName())){
                    
                    originalQTY = productDB.getQuantity();
                    
                }
                
            }
            
            query = "UPDATE PRODUCTTB SET PRODQTY = " + (originalQTY - prodAdd.getQuantity()) + " WHERE PRODNAME = '" + prodAdd.getName() + "'";
                
                updateDB.executeUpdate(query);
            
        }
    
    }
    
    /**
     * Inizializza l'area di testo dello scontrino.
     * 
     * @return la stringa per l'area di testo dello scontrino
     */
    
    public String manageRecepitInitText() {
        
        StringBuilder recepitText = new StringBuilder("\t============= MarketManager =============\n\n")
            .append("Prodotto\tCategoria\tPrezzo\tQuantità\tSubtotale\n\n")
            .append("\nTotale: ");
        
        return recepitText.toString();
        
    }
    
    /**
     * Riempe lo scontrino con i dati di differenti prodotti presi dalla lista
     * dello scontrino.
     * 
     * @param pAddedProductList la lista dei prodotti dello scontrino
     * @return la stringa per l'area di testo dello scontrino
     */
    public String manageFillRecepit(ArrayList<Product> pAddedProductList) {
        
        float totalPrice = 0f;
        float subTotalPrice = 0f;
        
        StringBuilder recepitProduct = new StringBuilder();
        
        for(Product productAdd : pAddedProductList) {
            
            subTotalPrice = productAdd.getPrice() * productAdd.getQuantity();
            
            recepitProduct.append(productAdd.getName() + "\t" + productAdd.getCategory() + "\t" + String.format("%.2f", productAdd.getPrice()) + " €\t" + productAdd.getQuantity() + "\t" + String.format("%.2f", subTotalPrice) + " €\n");
            
            totalPrice += subTotalPrice;
            
        }
        
        StringBuilder recepitText = new StringBuilder("\t============= MarketManager =============\n\n")
            .append("Prodotto\tCategoria\tPrezzo\tQuantità\tSubtotale\n\n")
            .append(recepitProduct)
            .append("\nTotale: ")
            .append(String.format("%.2f", totalPrice))
            .append(" €");
        
        return recepitText.toString();
        
    }
    
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
            
            //category.setName(rs.getString(1));
            
            categoryList.add(category);
            
        }
        
        return categoryList;
        
    }
    
}
