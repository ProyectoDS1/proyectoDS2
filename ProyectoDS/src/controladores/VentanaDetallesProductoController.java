/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import modelos.Administrador;
import modelos.Producto;
import modelos.Usuario;

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
    @FXML
    private Button btnComprar;
    @FXML
    private Label btnComprarWrapper;

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
        tiempoEntrega.setText(producto.getTiempoEntrega() != null ? producto.getTiempoEntrega().getTime() / (1000.0 * 60 * 60) + " horas" : "");
        llenarCalificaciones();
        stock.setText(Integer.toString(producto.getStock()));

        if (Usuario.getUsuarioActual() == null) {
            btnComprar.setDisable(true);
            btnComprarWrapper.setTooltip(new Tooltip("Los usuarios anónimos no pueden comprar!"));
        }
        if (Usuario.getUsuarioActual() instanceof Administrador) {
            btnComprar.setDisable(true);
            btnComprarWrapper.setTooltip(new Tooltip("Los administradores no pueden comprar!"));
        }

        ((Stage) titulo.getScene().getWindow()).show();
    }

    private void llenarCalificaciones() {
        float califProducto = producto.getPromedioCalificaciones();
        if (califProducto == -1) {
            calificacionProducto.setText("Este producto no tiene calificaciones");
        } else {
            calificacionProducto.setText(califProducto + "/5");
        }
        float califVendedor = producto.getVendedor().getPromedioCalificaciones();
        if (califVendedor == -1) {
            calificacionVendedor.setText("Este vendedor no tiene calificaciones");
        } else {
            calificacionVendedor.setText(califVendedor + "/5");
        }
    }

    @FXML
    public void comprarProducto(ActionEvent e
    ) {
        if (!validarNumItems()) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaFormaPago.fxml"));
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
    public void volver(ActionEvent e
    ) {
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
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setContentText("El número de ítems deseados no está disponible!");
        a.showAndWait();
        return false;
    }
}
