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
import javax.persistence.EntityManager;
import modelos.Producto;
import modelos.Usuario;
import modelos.Vendedor;
import utils.ConexionSql;
import utils.TextProducto;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaCrearNuevoProductoController extends TextProducto implements Initializable, CanGoBack {

    private CanGoBack returnController;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Not required yet
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
        if (validarDatos()) {
        assert Usuario.getUsuarioActual() instanceof Vendedor;
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        Producto p = new Producto();
        p.setNombreArticulo(nombre.getText());
        p.setDescripcion(descripcion.getText());
        p.setCategoria(categoria.getText());
        p.setPrecio(Float.valueOf(precio.getText()));
        p.setTiempoEntrega(new Date((int) (Float.valueOf(tiempoEntrega.getText()) * 1000 * 60 * 60)));
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
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombre.getScene().getWindow()).close();
        returnController.show();
    }
}
