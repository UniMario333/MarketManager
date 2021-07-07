/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.controller.facade;

import it.uniparthenope.prog3.daniele.mario.marketmanager.model.builder.category.Category;
import it.uniparthenope.prog3.daniele.mario.marketmanager.connection.Connect;
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
 * @see CategoryController
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class ManageCategory {
    
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
     * Crea una nuova lista di categorie.
     *
     * @see Category
     */
    private ArrayList<Category> categoryList = new ArrayList<>();
    
    /** 
     * Inizializzazione oggetto categoria.
     * 
     * @see Category
     */ 
    private Category category = null;
    
    /**
     * Popola la lista delle categorie per il riempimento della tabella nella 
     * view attraverso un builder, prendendo le informazioni direttamente dal
     * database attraverso una query.
     * 
     * @throws java.sql.SQLException
     * @see Connect
     * @see Category
     * @see CategoryController
     * @return la lista delle categorie
     */
    public ArrayList<Category> manageInitTable() throws SQLException {
       
        
        categoryList.clear();
            
        query = "SELECT * FROM CATEGORYTB ORDER BY CATNAME ASC";
            
        ps = connect.prepareStatement(query);
        rs = ps.executeQuery();
            
        while(rs.next()) {
                
            category = new Category.CategoryBuilder(rs.getString(1))
                .setName(rs.getString(1))
                .setDescription(rs.getString(2))
                .build();
                
            categoryList.add(category);
                
        }
        
        return categoryList;
        
    }
    
    /**
     * Aggiunge le informazioni inviate al database.
     * 
     * @see Connect
     * @see CategoryController
     * @param pName il nome della categoria
     * @param pDescription la descrizione della categoria
     * @throws SQLException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException
     */
    public void manageAddPressed(String pName, String pDescription) throws SQLException, StringEmptyException {
        
        Statement addDB = connect.createStatement();
            
        if(!pName.equals("") && !pDescription.equals("")) {

            query = "INSERT INTO CATEGORYTB VALUES('" + pName + "', '" + pDescription + "')";

            addDB.execute(query);

        } else {

            throw new StringEmptyException("Campi \"nome\" e\\o \"descrizione\" sono vuoti");

        }
        
    }
    
    /**
     * le informazioni inviate permettono la modifica di record del database
     * basandosi sul l'attuale chiave primaria che in questo caso è il nome 
     * della categoria.
     *
     * @see Connect
     * @see CategoryController
     * @param pName il nuovo nome della categoria
     * @param pDescription la nuova descrizione della categoria
     * @param pOldName l'attuale nome della categoria su cui fare le modifiche
     * @throws SQLException
     * @throws it.uniparthenope.prog3.daniele.mario.marketmanager.exception.StringEmptyException
     */
    public void manageUpdatePressed(String pName, String pDescription, String pOldName) throws SQLException, StringEmptyException {
        
        Statement updateDB = connect.createStatement();
        
        if(!pName.equals("") && !pDescription.equals("")) {
        
            query = "UPDATE CATEGORYTB SET CATNAME = '" + pName + "', CATDESC = '" + pDescription + "' WHERE CATNAME = '" + pOldName + "'";
    
            updateDB.executeUpdate(query);   
            
        } else {
            
            throw new StringEmptyException("Campi \"nome\" e\\o \"descrizione\" sono vuoti");
            
        }
        
    }
    
    /**
     * Attraverso la chiave primaria che sarebbe il nome della categoria,
     * permette l'eliminazione della riga dal database.
     * 
     * @see Connect
     * @see CategoryController
     * @param pName il nome della categoria
     * @throws SQLException 
     */
    public void manageDeletePressed(String pName) throws SQLException {
        
        Statement deleteDB = connect.createStatement();
            
        query = "DELETE FROM CATEGORYTB WHERE CATNAME = '" + pName + "'";
                             
        deleteDB.execute(query);
   
    }
    
}
