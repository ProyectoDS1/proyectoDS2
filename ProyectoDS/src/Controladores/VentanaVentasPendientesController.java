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
public class VentanaVentasPendientesController implements Initializable, CanGoBack {

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
    public void verMapa(ActionEvent e) throws IOException {
        System.out.println("Ver mapa de producto");
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("No implementado!");
        a.showAndWait();
    }

    @FXML
    public void anularPedido(ActionEvent e) throws IOException {
        System.out.println("Anular pedido");
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("No implementado!");
        a.showAndWait();
    }

    public void show() {
        ((Stage) titulo.getScene().getWindow()).show();
    }
}
