/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

/**
 *
 * @author leone
 */
public class TextProducto {
    
    @FXML
    protected TextField nombre;
    @FXML
    protected TextArea descripcion;
    @FXML
    protected TextField categoria;
    @FXML
    protected TextField precio;
    @FXML
    protected TextField tiempoEntrega;
    @FXML
    protected TextField stock;
    
    protected boolean validarDatos() {
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
            Alert a = new Alert(Alert.AlertType.WARNING, errorMessage, ButtonType.OK);
            a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            a.showAndWait();
        }

        return !error;
    }

    public TextField getNombre() {
        return nombre;
    }

    public void setNombre(TextField nombre) {
        this.nombre = nombre;
    }

    public TextArea getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(TextArea descripcion) {
        this.descripcion = descripcion;
    }

    public TextField getCategoria() {
        return categoria;
    }

    public void setCategoria(TextField categoria) {
        this.categoria = categoria;
    }

    public TextField getPrecio() {
        return precio;
    }

    public void setPrecio(TextField precio) {
        this.precio = precio;
    }

    public TextField getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(TextField tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public TextField getStock() {
        return stock;
    }

    public void setStock(TextField stock) {
        this.stock = stock;
    }
    
    
}
