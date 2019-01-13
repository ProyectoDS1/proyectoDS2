/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
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

    private ConexionSql(String user,String password) throws SQLException {
        this.user=user;
        this.password=password;
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_datos",this.user,this.password);
        this.statement = connection.createStatement();
         
    }
    
    
    public Connection getConexion(){
         return connection;
    }
    
    public void ejecutar(String n){
            try {
                preStatement=connection.prepareStatement(n);
                preStatement.execute();
                JOptionPane.showMessageDialog(null,"El registro fue exitoso","Mensaje", WIDTH);
            } catch (SQLException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"No se pudo realizar la operacion","Problema", WIDTH);
            }
    }
    
    public void desconectar(){
    
    }
    
    
}
