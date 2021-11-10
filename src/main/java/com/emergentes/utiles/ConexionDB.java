package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {

    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/bd_eventos";
    static String usuario = "root";
    static String password = "";

    Connection con = null;

    public ConexionDB() {
        try {
           Class.forName(driver);
           con = DriverManager.getConnection(url,usuario,password);
           //verificamos la conexion
           if(con != null){
               System.out.println("Conexion Ok" + con);
           }
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public Connection conectar(){
        return con;
    }
    
    public void desconectar(){
       try {
           con.close();
       } catch (SQLException ex) {
           Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

}
