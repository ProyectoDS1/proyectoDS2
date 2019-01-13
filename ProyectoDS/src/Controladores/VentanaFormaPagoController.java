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
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaFormaPagoController implements Initializable, CanGoBack {

    private CanGoBack returnController;

    @FXML
    private TextField nombreEfectivo, nombreDE, telfDE;

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
        ((Stage) nombreEfectivo.getScene().getWindow()).show();
    }

    @FXML
    public void comprar(ActionEvent e) throws IOException {
        ((VentanaDetallesProductoController) returnController).show();

        ((Stage) nombreEfectivo.getScene().getWindow()).close();
    }
    
    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombreEfectivo.getScene().getWindow()).close();
        returnController.show();
    }

}
