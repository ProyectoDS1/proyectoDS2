/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaCalificarProductoController implements Initializable, CanGoBack {

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
    public void calificar(ActionEvent e) {
        System.out.println("Calificando producto: " + ((Button) e.getSource()).getText() + " estrellas");

        ((VentanaComprasPendientesController) returnController).show();

        ((Stage) titulo.getScene().getWindow()).close();
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }

}
