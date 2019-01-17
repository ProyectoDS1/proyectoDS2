/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Producto;
import Modelos.Usuario;
import Utils.ConexionSql;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaProductosMasBuscadosController implements Initializable, CanGoBack {

    private CanGoBack returnController;

    @FXML
    private Label titulo;
    @FXML
    private VBox container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        List<Producto> masBuscados = em.createQuery("SELECT p FROM Producto p WHERE p.eliminado=false ORDER BY p.numVistas DESC").setMaxResults(15).getResultList();
        for (Producto p : masBuscados) {
            Hyperlink h = new Hyperlink(p.getNombreArticulo());
            h.setOnAction(e -> productoElegido(e, p));
            container.getChildren().add(h);
        }
        ConexionSql.getConexion().endTransaction();
    }

    private void productoElegido(ActionEvent e, Producto p) {
        if (Usuario.getUsuarioActual() != null) {
            mostrarVentanaProducto(p);
        } else {
            mostrarVentanaLogin();
        }
    }

    private void mostrarVentanaProducto(Producto p) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaDetallesProducto.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaDetallesProductoController controller = loader.<VentanaDetallesProductoController>getController();

            controller.setReturnController(this);
            controller.setData(p);
            stage.show();
            titulo.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaProductosMasBuscadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarVentanaLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaIniciarSesion.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaIniciarSesionController controller = loader.<VentanaIniciarSesionController>getController();

            controller.setReturnController(this);
            stage.show();
            titulo.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaProductosMasBuscadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }

    @Override
    public void setReturnController(CanGoBack c) {
        returnController = c;
    }

    @Override
    public void show() {
        ((Stage) titulo.getScene().getWindow()).show();
    }

}
