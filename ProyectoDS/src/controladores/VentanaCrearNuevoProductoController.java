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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import modelos.Producto;
import modelos.Usuario;
import modelos.Vendedor;
import utils.ConexionSql;

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
        if (!validarDatos()) {
            return;
        }

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
            Alert a = new Alert(Alert.AlertType.WARNING, errorMessage, ButtonType.OK);
            a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            a.showAndWait();
        }

        return !error;
    }

}
