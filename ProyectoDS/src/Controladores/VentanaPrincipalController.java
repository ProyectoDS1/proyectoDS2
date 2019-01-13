/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaPrincipalController implements Initializable, CanGoBack {
    @FXML
    private Label usuarioLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void logIn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaIniciarSesion.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaIniciarSesionController controller = loader.<VentanaIniciarSesionController>getController();

        controller.setReturnController(this);
        stage.show();
        usuarioLabel.getScene().getWindow().hide();
    }

    @FXML
    public void verRecientes(ActionEvent event) {
        System.out.println("Ver productos recientes");
    }

    @FXML
    public void verMasBuscados(ActionEvent event) {
        System.out.println("Ver productos más buscados");
    }

    @FXML
    public void busquedaSencilla(ActionEvent event) {
        System.out.println("Búsqueda sencilla");
    }

    @FXML
    public void busquedaAvanzada(ActionEvent event) {
        System.out.println("Búsqueda avanzada");
    }

    @FXML
    public void misPedidos(ActionEvent event) {
        System.out.println("Mis pedidos");
    }

    @FXML
    public void misVentas(ActionEvent event) {
        System.out.println("Mis ventas");
    }

    @FXML
    public void misProductos(ActionEvent event) {
        System.out.println("Mis productos");
    }

    @Override
    public void setReturnController(CanGoBack c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
