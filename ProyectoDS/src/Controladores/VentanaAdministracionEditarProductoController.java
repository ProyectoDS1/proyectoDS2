/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Producto;
import Modelos.Vendedor;
import Utils.ConexionSql;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaAdministracionEditarProductoController implements Initializable, CanGoBack, CanGetData {

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
    @FXML
    private TextField pkVendedor;
    @FXML
    private TextField numVistas;
    @FXML
    private CheckBox eliminado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void setData(Object... data) {
        target = (Producto) data[0];

        nombre.setText(target.getNombreArticulo());
        descripcion.setText(target.getDescripcion());
        categoria.setText(target.getCategoria());
        precio.setText(Float.toString(target.getPrecio()));
        tiempoEntrega.setText(target.getTiempoEntrega() != null ? target.getTiempoEntrega().toString() : "");
        stock.setText(Integer.toString(target.getStock()));
        pkVendedor.setText(Long.toString(target.getVendedor().getId()));
        numVistas.setText(Integer.toString(target.getNumVistas()));
        eliminado.setSelected(target.isEliminado());
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
    public void guardar(ActionEvent e) {
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        target.setNombreArticulo(nombre.getText());
        target.setDescripcion(descripcion.getText());
        target.setCategoria(categoria.getText());
        target.setPrecio(Float.valueOf(precio.getText()));
        target.setTiempoEntrega(null);
        target.setStock(Integer.valueOf(stock.getText()));
        Vendedor v = em.find(Vendedor.class, Long.valueOf(pkVendedor.getText()));
        target.setVendedor(v);
        target.setNumVistas(Integer.valueOf(numVistas.getText()));
        target.setDisponible(true);
        target.setEliminado(eliminado.isSelected());
        ConexionSql.getConexion().endTransaction();

        volver(e);
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombre.getScene().getWindow()).close();
        returnController.show();
    }

}
