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
public class VentanaMisProductosController implements Initializable, CanGoBack {

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
        System.out.println("Crear nuevo producto");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaCrearNuevoProducto.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaCrearNuevoProductoController controller = loader.<VentanaCrearNuevoProductoController>getController();

        controller.setReturnController(this);
        stage.show();
        titulo.getScene().getWindow().hide();
    }

    @FXML
    public void editar(ActionEvent e) throws IOException {
        System.out.println("Editar producto");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaEditarProducto.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaEditarProductoController controller = loader.<VentanaEditarProductoController>getController();

        controller.setReturnController(this);
        stage.show();
        titulo.getScene().getWindow().hide();
    }

    @FXML
    public void eliminar(ActionEvent e) throws IOException {
        System.out.println("Eliminar producto");
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Producto eliminado!");
        a.showAndWait();
    }

    public void show() {
        ((Stage) titulo.getScene().getWindow()).show();
    }
}
