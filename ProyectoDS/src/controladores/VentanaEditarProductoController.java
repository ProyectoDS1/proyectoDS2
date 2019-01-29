/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import modelos.Producto;
import utils.ConexionSql;
import utils.TextProducto;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaEditarProductoController extends TextProducto implements Initializable, CanGoBack, CanGetData {

    private CanGoBack returnController;
    private Producto target;


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
        if (validarDatos()) {
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
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombre.getScene().getWindow()).close();
        returnController.show();
    }

    

}
