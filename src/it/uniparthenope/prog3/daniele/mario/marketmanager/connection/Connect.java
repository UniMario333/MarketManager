/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Properties;
import javax.swing.JOptionPane;


/**
 * Permette la connessione con il database in caso di mancata connessione
 * lancia un messaggio di errore e una finestra di errore.
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class Connect {

    public static Connection connectDB() {
        
        String dbURL = "jdbc:derby://localhost:1527/marketmanagerdb";
        
        Properties properties = new Properties();
        
        properties.put("create", "true");
        properties.put("user", "root");
        properties.put("password", "m333");
        
        Connection connect = null;
         
        try {
            
            connect = DriverManager.getConnection(dbURL, properties);
                   
        } catch(SQLException e) {
                
            JOptionPane.showMessageDialog(null, "ERRORE: Connessione al database fallita");
            
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, e);
            
        }
        
        return connect;
       
    }
    
}
