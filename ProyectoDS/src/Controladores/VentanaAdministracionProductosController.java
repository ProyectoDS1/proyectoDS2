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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaAdministracionProductosController implements Initializable, CanGoBack {

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
        refreshProductList();
    }

    @Override
    public void setReturnController(CanGoBack c) {
        returnController = c;
    }

    @FXML
    public void crear(ActionEvent e) throws IOException {
        System.out.println("Crear nuevo producto");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaAdministracionCrearNuevoProducto.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaAdministracionCrearNuevoProductoController controller = loader.<VentanaAdministracionCrearNuevoProductoController>getController();

        controller.setReturnController(this);
        stage.show();
        titulo.getScene().getWindow().hide();
    }

    @FXML
    public void editar(ActionEvent e, Producto p) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaAdministracionEditarProducto.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaAdministracionEditarProductoController controller = loader.<VentanaAdministracionEditarProductoController>getController();

            controller.setReturnController(this);
            controller.setData(p);
            controller.initialize(null, null);

            stage.show();
            titulo.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaAdministracionProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void eliminar(ActionEvent e, Producto p) {
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        p.setEliminado(true);
        ConexionSql.getConexion().endTransaction();

        System.out.println("Eliminar producto");
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Producto eliminado!");
        a.showAndWait();

        refreshProductList();
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }

    @Override
    public void show() {
        ((Stage) container.getScene().getWindow()).show();
        refreshProductList();
    }

    private void refreshProductList() {
        container.getChildren().clear();

        EntityManager em = ConexionSql.getConexion().beginTransaction();
        List<Producto> productos = em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
        ConexionSql.getConexion().endTransaction();

        for (Producto p : productos) {
            HBox product = new HBox();
            product.setPrefWidth(Double.MAX_VALUE);
            product.setSpacing(10);

            Label nombre = new Label(p.toString());
            nombre.setPrefWidth(300);
            product.getChildren().add(nombre);

            Button editar = new Button("Editar");
            editar.setPrefWidth(100);
            editar.setOnAction(e -> editar(e, p));
            product.getChildren().add(editar);

            if (!p.isEliminado()) {
                Button eliminar = new Button("Eliminar");
                eliminar.setPrefWidth(100);
                eliminar.setOnAction(e -> eliminar(e, p));
                product.getChildren().add(eliminar);
            }

            container.getChildren().add(product);
        }
    }

}
