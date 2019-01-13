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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author User-PC
 */
public class VentanaRegistroController implements Initializable, CanGoBack {

    private CanGoBack returnController;

    @FXML
    private TextField nombre;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setReturnController(CanGoBack c) {
        returnController = c;
    }

    @Override
    public void show() {
        ((Stage) nombre.getScene().getWindow()).show();
    }

    @FXML
    public void registrar(ActionEvent e) {
        System.out.println("Registrar usuario");

        returnController.show();
        ((Stage) nombre.getScene().getWindow()).close();
    }

}
