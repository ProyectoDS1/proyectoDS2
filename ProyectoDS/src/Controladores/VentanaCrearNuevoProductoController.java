/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Producto;
import Modelos.Usuario;
import Modelos.Vendedor;
import Utils.ConexionSql;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class VentanaCrearNuevoProductoController implements Initializable, CanGoBack {

    private CanGoBack returnController;

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
    public void show() {
        ((Stage) nombre.getScene().getWindow()).show();
    }

    @FXML
    public void crear(ActionEvent e) {
        assert Usuario.getUsuarioActual() instanceof Vendedor;

        EntityManager em = ConexionSql.getConexion().beginTransaction();
        Producto p = new Producto();
        p.setNombreArticulo(nombre.getText());
        p.setDescripcion(descripcion.getText());
        p.setCategoria(categoria.getText());
        p.setPrecio(Float.valueOf(precio.getText()));
        p.setTiempoEntrega(null);
        p.setStock(Integer.valueOf(stock.getText()));
        p.setVendedor((Vendedor) Usuario.getUsuarioActual());
        p.setFechaCreacion(new Date());
        p.setNumVistas(0);
        p.setDisponible(true);
        p.setEliminado(false);
        em.persist(p);
        ((Vendedor) Usuario.getUsuarioActual()).mostrarArticulos().add(p);
        ConexionSql.getConexion().endTransaction();

        volver(e);
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombre.getScene().getWindow()).close();
        returnController.show();
    }

}
