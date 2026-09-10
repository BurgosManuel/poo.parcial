/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mburgos
 */
public class Conector {
    private final String driver = "org.mariadb.jdbc.Driver";
    private final String url = "jdbc:mariadb://localhost:3306/parcial";
    private final String user = "mburgos";
    private final String pass = "";
    Connection conn = null;
    
    public Connection getConexion() {
        try {
            if(null != conn && !conn.isClosed()) {
                return conn;
            }
            
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
}
