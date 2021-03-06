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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelos.Producto;
import utils.ConexionSql;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaBusquedaSencillaController implements Initializable, CanGoBack {

    private CanGoBack returnController;

    @FXML
    private TextField texto;

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
        ((Stage) texto.getScene().getWindow()).show();
    }

    @FXML
    public void buscar(ActionEvent e) throws IOException {
        if (texto.getText().trim().length() < 3) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Ingrese al menos 3 caracteres!");
            a.showAndWait();
            return;
        }

        EntityManager em = ConexionSql.getConexion().beginTransaction();
        TypedQuery<Producto> q = em.createQuery("SELECT p FROM Producto p WHERE p.eliminado=false AND (p.nombreArticulo LIKE :text OR p.descripcion LIKE :text)", Producto.class);
        q.setParameter("text", "%" + texto.getText().trim() + "%");
        List<Producto> productos = q.getResultList();
        ConexionSql.getConexion().endTransaction();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaResultadosBusqueda.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaResultadosBusquedaController controller = loader.<VentanaResultadosBusquedaController>getController();

        controller.setReturnController(this);
        controller.setData(productos);
        stage.show();
        texto.getScene().getWindow().hide();
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) texto.getScene().getWindow()).close();
        returnController.show();
    }

}
