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
import javafx.scene.control.Alert;
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
    public void verRecientes(ActionEvent event) throws IOException {
        System.out.println("Ver productos recientes");
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("No implementado!");
        a.showAndWait();
    }

    @FXML
    public void verMasBuscados(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaProductosMasBuscados.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaProductosMasBuscadosController controller = loader.<VentanaProductosMasBuscadosController>getController();

        controller.setReturnController(this);
        stage.show();
        usuarioLabel.getScene().getWindow().hide();
    }

    @FXML
    public void busquedaSencilla(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaBusquedaSencilla.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaBusquedaSencillaController controller = loader.<VentanaBusquedaSencillaController>getController();

        controller.setReturnController(this);
        stage.show();
        usuarioLabel.getScene().getWindow().hide();
    }

    @FXML
    public void busquedaAvanzada(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("No implementado!");
        a.showAndWait();
    }

    @FXML
    public void misPedidos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaMisPedidos.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaMisPedidosController controller = loader.<VentanaMisPedidosController>getController();

        controller.setReturnController(this);
        stage.show();
        usuarioLabel.getScene().getWindow().hide();
    }

    @FXML
    public void misVentas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaMisVentas.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaMisVentasController controller = loader.<VentanaMisVentasController>getController();

        controller.setReturnController(this);
        stage.show();
        usuarioLabel.getScene().getWindow().hide();
    }

    @FXML
    public void misProductos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaMisProductos.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaMisProductosController controller = loader.<VentanaMisProductosController>getController();

        controller.setReturnController(this);
        stage.show();
        usuarioLabel.getScene().getWindow().hide();
    }

    @FXML
    public void administrar(ActionEvent e) throws IOException {
        System.out.println("Ventana de administración");

        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setContentText("Sólo los administradores pueden acceder!");
        a.showAndWait();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VentanaAdministracion.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaAdministracionController controller = loader.<VentanaAdministracionController>getController();

        controller.setReturnController(this);
        stage.show();
        usuarioLabel.getScene().getWindow().hide();
    }

    @Override
    public void setReturnController(CanGoBack c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
