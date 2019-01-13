/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaAdministracionUsuariosController implements Initializable, CanGoBack {

    private CanGoBack returnController;

    @FXML
    private Label titulo;

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

    @FXML
    public void crear(ActionEvent e) throws IOException {
        System.out.println("Crear nuevo usuario");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaAdministracionCrearNuevoUsuario.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaAdministracionCrearNuevoUsuarioController controller = loader.<VentanaAdministracionCrearNuevoUsuarioController>getController();

        controller.setReturnController(this);
        stage.show();
        titulo.getScene().getWindow().hide();
    }

    @FXML
    public void editar(ActionEvent e) throws IOException {
        System.out.println("Editar usuario");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaAdministracionEditarUsuario.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaAdministracionEditarUsuarioController controller = loader.<VentanaAdministracionEditarUsuarioController>getController();

        controller.setReturnController(this);
        stage.show();
        titulo.getScene().getWindow().hide();
    }

    @FXML
    public void eliminar(ActionEvent e) throws IOException {
        System.out.println("Eliminar usuario");
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Usuario eliminado!");
        a.showAndWait();
    }
    
    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }

    @Override
    public void show() {
        ((Stage) titulo.getScene().getWindow()).show();
    }
}
