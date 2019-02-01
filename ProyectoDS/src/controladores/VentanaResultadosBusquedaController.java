/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

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
import modelos.Producto;
import utils.ConexionSql;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaResultadosBusquedaController implements Initializable, CanGoBack, CanGetData {

    private CanGoBack returnController;
    private List<Producto> productos;

    @FXML
    private Label titulo;
    @FXML
    private VBox container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    public void productoElegido(ActionEvent e, Producto p) {
        try {
             Logger.getLogger(VentanaCalificarVendedorController.class.getName()).log(Level.SEVERE, "Se ha elegido {0}", p.getNombreArticulo());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaDetallesProducto.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaDetallesProductoController controller = loader.<VentanaDetallesProductoController>getController();

            controller.setReturnController(this);
            controller.setData(p);
            stage.show();
            titulo.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaResultadosBusquedaController.class.getName()).log(Level.SEVERE, null, ex);
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
    public void setData(Object... data) {
        productos = (List<Producto>) data[0];

        show();
    }

    @Override
    public void show() {
        ((Stage) titulo.getScene().getWindow()).show();

        container.getChildren().clear();
        for (Producto p : productos) {
            p.setNumVistas(p.getNumVistas() + 1);
            Hyperlink h = new Hyperlink(p.getNombreArticulo());
            h.setOnAction(e -> productoElegido(e, p));
            container.getChildren().add(h);
        }
        ConexionSql.getConexion().endTransaction();
    }

    

}
