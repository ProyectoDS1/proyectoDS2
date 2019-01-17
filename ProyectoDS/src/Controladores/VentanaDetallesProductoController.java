/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Producto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaDetallesProductoController implements Initializable, CanGoBack, CanGetData {

    private CanGoBack returnController;
    private Producto producto;

    @FXML
    private Label titulo;
    @FXML
    private Label vendedor;
    @FXML
    private Label precio;
    @FXML
    private Label categoria;
    @FXML
    private Label tiempoEntrega;
    @FXML
    private Label calificacionProducto;
    @FXML
    private Label calificacionVendedor;
    @FXML
    private Label stock;
    @FXML
    private TextField numItems;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numItems.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numItems.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @Override
    public void setReturnController(CanGoBack c) {
        returnController = c;
    }

    @Override
    public void setData(Object... data) {
        producto = (Producto) data[0];

        show();
    }

    @Override
    public void show() {
        titulo.setText(producto.getNombreArticulo());
        vendedor.setText(producto.getVendedor().getNombre() + " " + producto.getVendedor().getApellido());
        precio.setText("$" + Float.toString(producto.getPrecio()));
        categoria.setText(producto.getCategoria());
        tiempoEntrega.setText(producto.getTiempoEntrega() != null ? producto.getTiempoEntrega().toString() : "");
        calificacionProducto.setText("TODO");
        calificacionVendedor.setText("TODO");
        stock.setText(Integer.toString(producto.getStock()));

        ((Stage) titulo.getScene().getWindow()).show();
    }

    @FXML
    public void comprarProducto(ActionEvent e) {
        if (!validarNumItems()) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaFormaPago.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaFormaPagoController controller = loader.<VentanaFormaPagoController>getController();

            controller.setReturnController(this);
            controller.setData(producto, Integer.valueOf(numItems.getText()));
            stage.show();
            titulo.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaDetallesProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }

    private boolean validarNumItems() {
        try {
            int numItemsDeseados = Integer.valueOf(numItems.getText());
            if (numItemsDeseados > 0 && numItemsDeseados <= producto.getStock()) {
                return true;
            }
        } catch (NumberFormatException ex) {
            // No need to do anything, execution will fall to the error section
        }
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("El número de ítems deseados no está disponible!");
        a.showAndWait();
        return false;
    }
}
