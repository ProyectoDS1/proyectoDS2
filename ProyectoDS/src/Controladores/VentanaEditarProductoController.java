/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Producto;
import Utils.ConexionSql;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaEditarProductoController implements Initializable, CanGoBack, CanGetData {

    private CanGoBack returnController;
    private Producto target;

    @FXML
    private TextField nombre;
    @FXML
    private TextArea descripcion;
    @FXML
    private TextField categoria;
    @FXML
    private TextField precio;
    @FXML
    private TextField tiempoEntrega;
    @FXML
    private TextField stock;

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
    public void setData(Object... data) {
        target = (Producto) data[0];

        nombre.setText(target.getNombreArticulo());
        descripcion.setText(target.getDescripcion());
        categoria.setText(target.getCategoria());
        precio.setText(Float.toString(target.getPrecio()));
        tiempoEntrega.setText(target.getTiempoEntrega() != null ? target.getTiempoEntrega().getTime() / (1000.0 * 60 * 60) + "" : "");
        stock.setText(Integer.toString(target.getStock()));
    }

    @Override
    public void show() {
        ((Stage) nombre.getScene().getWindow()).show();
    }

    @FXML
    public void guardar(ActionEvent e) {
        if (!validarDatos()) {
            return;
        }

        EntityManager em = ConexionSql.getConexion().beginTransaction();
        target.setNombreArticulo(nombre.getText());
        target.setDescripcion(descripcion.getText());
        target.setCategoria(categoria.getText());
        target.setPrecio(Float.valueOf(precio.getText()));
        if (tiempoEntrega.getText().endsWith(" horas")) {
            tiempoEntrega.setText(tiempoEntrega.getText().substring(0, tiempoEntrega.getLength() - 6));
        }
        target.setTiempoEntrega(new Date((int) (Float.valueOf(tiempoEntrega.getText()) * 1000 * 60 * 60)));
        target.setStock(Integer.valueOf(stock.getText()));
        ConexionSql.getConexion().endTransaction();

        volver(e);
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombre.getScene().getWindow()).close();
        returnController.show();
    }

    private boolean validarDatos() {
        boolean error = false;
        String errorMessage = "";
        if (nombre.getText().trim().length() == 0) {
            error = true;
            errorMessage = "Debe especificar el nombre del producto!";
        }
        try {
            if (Float.parseFloat(precio.getText()) <= 0) {
                error = true;
                errorMessage = "El precio no puede ser negativo";
            }
            if (Integer.parseInt(stock.getText()) < 0) {
                error = true;
                errorMessage = "El stock no puede ser negativo";
            }
            if (Float.parseFloat(tiempoEntrega.getText()) <= 0) {
                error = true;
                errorMessage = "El tiempo de entrega debe ser positivo";
            }
        } catch (NumberFormatException ex) {
            error = true;
            errorMessage = "Debe especificar un precio, stock y tiempo de entrega!";
        }

        if (error) {
            Alert a = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.OK);
            a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            a.showAndWait();
        }

        return !error;
    }

}
