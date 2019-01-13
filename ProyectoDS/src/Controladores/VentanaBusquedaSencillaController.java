/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaBusquedaSencillaController implements Initializable, CanGoBack {

    private CanGoBack returnController;

    @FXML
    private TextField texto;

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
        ((Stage) texto.getScene().getWindow()).show();
    }

    @FXML
    public void buscar(ActionEvent e) throws IOException {
        System.out.println("Buscando " + texto.getText());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaResultadosBusqueda.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaResultadosBusquedaController controller = loader.<VentanaResultadosBusquedaController>getController();

        controller.setReturnController(this);
        stage.show();
        texto.getScene().getWindow().hide();
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) texto.getScene().getWindow()).close();
        returnController.show();
    }

}
