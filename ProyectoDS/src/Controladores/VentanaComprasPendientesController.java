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
public class VentanaComprasPendientesController implements Initializable, CanGoBack {

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
    public void calificarProducto(ActionEvent e) throws IOException {
        System.out.println("Calificar producto");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaCalificarProducto.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaCalificarProductoController controller = loader.<VentanaCalificarProductoController>getController();

        controller.setReturnController(this);
        stage.show();
        titulo.getScene().getWindow().hide();
    }

    @FXML
    public void calificarVendedor(ActionEvent e) throws IOException {
        System.out.println("Calificar vendedor");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaCalificarVendedor.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaCalificarVendedorController controller = loader.<VentanaCalificarVendedorController>getController();

        controller.setReturnController(this);
        stage.show();
        titulo.getScene().getWindow().hide();
    }

    @FXML
    public void indicarRecibido(ActionEvent e) throws IOException {
        System.out.println("Indicar producto recibido");
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
    
    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }

    @Override
    public void show() {
        ((Stage) titulo.getScene().getWindow()).show();
    }
}
