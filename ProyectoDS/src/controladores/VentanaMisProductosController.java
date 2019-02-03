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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelos.Producto;
import modelos.Usuario;
import modelos.Vendedor;
import utils.ConexionSql;
import utils.VentasPendientesController;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaMisProductosController extends VentasPendientesController {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarLista();
    }

    @FXML
    public void crear(ActionEvent e) throws IOException {
        Logger.getLogger(VentanaMisProductosController.class.getName()).log(Level.INFO, "Crear nuevo producto");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaCrearNuevoProducto.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VentanaCrearNuevoProductoController controller = loader.<VentanaCrearNuevoProductoController>getController();
        controller.setReturnController(this);
        stage.show();
        titulo.getScene().getWindow().hide();
    }

    @FXML
    public void editar(ActionEvent e, Producto p) {
        try {
            Logger.getLogger(VentanaMisProductosController.class.getName()).log(Level.INFO, "Editar producto");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaEditarProducto.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaEditarProductoController controller = loader.<VentanaEditarProductoController>getController();

            controller.setReturnController(this);
            controller.setData(p);
            stage.show();
            titulo.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaMisProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void eliminar(ActionEvent e, Producto p) {
        
        ConexionSql.getConexion().beginTransaction();
        p.setEliminado(true);
        ConexionSql.getConexion().endTransaction();
        llenarLista();
    }



    @Override
    public void show() {
        llenarLista();
        ((Stage) titulo.getScene().getWindow()).show();
    }

    private void llenarLista() {
        assert Usuario.getUsuarioActual() instanceof Vendedor;
        Vendedor vendedor = (Vendedor) Usuario.getUsuarioActual();

        container.getChildren().clear();

        int i = 0;
        if (vendedor.mostrarArticulos() == null) {
            return;
        }
        for (Producto p : vendedor.mostrarArticulos()) {
            if (!p.isEliminado()) {
                Label nombre = new Label(p.getNombreArticulo());
                nombre.setMinWidth(300);
                container.add(nombre, 0, i);

                agregarBotonEditar(p, i);
                agregarBotonEliminar(p, i);
                i++;
            }
        }
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }


    private void agregarBotonEditar(Producto p, int i) {
        Button editar = new Button("Editar");
        editar.setOnAction(e -> editar(e, p));
        container.add(editar, 3, i);
    }

    private void agregarBotonEliminar(Producto p, int i) {
        Button eliminar = new Button("Eliminar");
        eliminar.setOnAction(e -> eliminar(e, p));
        container.add(eliminar, 4, i);
    }
}
