/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controladores.CanGoBack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author leone
 */
public abstract class VentasPendientesController implements Initializable, CanGoBack{
    protected CanGoBack returnController;

    @FXML
    protected Label titulo;
    @FXML
    protected GridPane container;

    /**
     * Initializes the controller class.
     */
    

    @Override
    public void setReturnController(CanGoBack c) {
        returnController = c;
    }
    
    
    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }
}

