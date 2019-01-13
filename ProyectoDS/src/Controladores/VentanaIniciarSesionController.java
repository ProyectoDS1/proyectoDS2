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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaIniciarSesionController implements Initializable, CanGoBack {

    @FXML
    private CanGoBack returnController;

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
    public void newUser(ActionEvent event) throws IOException {
        System.out.println("Registrar nuevo Usuario");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaRegistro.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaRegistroController controller = loader.<VentanaRegistroController>getController();

        controller.setReturnController(this);
        stage.show();
    }

}
