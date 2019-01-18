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
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
public class VentanaAdministracionCrearNuevoProductoController implements Initializable, CanGoBack {

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
    @FXML
    private ComboBox vendedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        List<Vendedor> vendedores = em.createQuery("SELECT v FROM Vendedor v",Vendedor.class).getResultList();
        ConexionSql.getConexion().endTransaction();

        for (Vendedor v : vendedores) {
            vendedor.getItems().add(v);
        }
        vendedor.setValue(vendedores.get(0));
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
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        Producto p = new Producto();
        p.setNombreArticulo(nombre.getText());
        p.setDescripcion(descripcion.getText());
        p.setCategoria(categoria.getText());
        p.setPrecio(Float.valueOf(precio.getText()));
        p.setTiempoEntrega(null);
        p.setStock(Integer.valueOf(stock.getText()));
        p.setVendedor((Vendedor) vendedor.getValue());
        p.setFechaCreacion(new Date());
        p.setNumVistas(0);
        p.setDisponible(true);
        p.setEliminado(false);
        em.persist(p);
        ConexionSql.getConexion().endTransaction();

        volver(e);
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombre.getScene().getWindow()).close();
        returnController.show();
    }

}
