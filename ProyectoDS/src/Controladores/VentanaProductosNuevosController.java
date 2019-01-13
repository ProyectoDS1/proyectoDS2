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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaProductosNuevosController implements Initializable, CanGoBack {

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

    @FXML
    public void productoElegido(ActionEvent e) {
        System.out.println(((Hyperlink) e.getSource()).getText());
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }

    @Override
    public void setReturnController(CanGoBack c) {
        returnController = c;
    }

    @Override
    public void show() {
        ((Stage) titulo.getScene().getWindow()).show();
    }

}
