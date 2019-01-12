/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author User-PC
 */
public class ConexionSql {

 private Connection connection;
 private PreparedStatement preStatement;
 private Statement statement;
 private String password;
 private String user;

    private ConexionSql() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_datos","usuario", "contrasenia");
        this.statement = connection.createStatement();
         
    }
    
    public Connection getConexion(){
         return connection;
    }
    
    public void ejecutar(){
        
    }
    
    public void desconectar(){
    
    }
    
    
}
