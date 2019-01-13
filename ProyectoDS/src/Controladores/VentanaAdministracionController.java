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
public class VentanaAdministracionController implements Initializable, CanGoBack {

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

    @Override
    public void show() {
        ((Stage) titulo.getScene().getWindow()).show();
    }

    @FXML
    public void administrarUsuarios(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaAdministracionUsuarios.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaAdministracionUsuariosController controller = loader.<VentanaAdministracionUsuariosController>getController();

        controller.setReturnController(this);
        stage.show();
        titulo.getScene().getWindow().hide();
    }

    @FXML
    public void administrarProductos(ActionEvent e) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("No implementado!");
        a.showAndWait();
    }
    
    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }

}
