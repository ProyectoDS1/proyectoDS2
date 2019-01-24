/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Usuario;
import Utils.ConexionSql;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaIniciarSesionController implements Initializable, CanGoBack {

    @FXML
    private CanGoBack returnController;

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void setReturnController(CanGoBack c) {
        returnController = c;
    }

    @Override
    public void show() {
        ((Stage) email.getScene().getWindow()).show();
    }

    @FXML
    public void newUser(ActionEvent event) throws IOException {
        System.out.println("Registrar nuevo Usuario");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaRegistro.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaRegistroController controller = loader.<VentanaRegistroController>getController();

        controller.setReturnController(this);
        stage.show();
        email.getScene().getWindow().hide();
    }

    @FXML
    public void iniciarSesion(ActionEvent e) throws IOException {
        conexion();
    }
    @FXML
    public void volver(ActionEvent e) {
        ((Stage) email.getScene().getWindow()).close();
        returnController.show();
    }
    @FXML
    public void inicarSesionButton(KeyEvent e)throws IOException{
        if(e.getCode().toString().equalsIgnoreCase("enter")){
            conexion();
        }
    }
    
    private void conexion()throws IOException{
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        TypedQuery<Usuario> q = em.createQuery("SELECT u FROM Usuario u WHERE u.email=:email AND u.contrasenia=:pass AND u.activo=true", Usuario.class);
        q.setParameter("email", email.getText());
        q.setParameter("pass", password.getText());

        try {
            Usuario.setUsuarioActual(q.getSingleResult());

            Parent root = FXMLLoader.load(getClass().getResource("/Vistas/VentanaPrincipal.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

            ((Stage) email.getScene().getWindow()).close();
        } catch (NoResultException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Usuario o contraseña erróneo!");
            a.showAndWait();
        }
        ConexionSql.getConexion().endTransaction();
    }

}
