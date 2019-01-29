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
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelos.Administrador;
import modelos.Comprador;
import modelos.Usuario;
import modelos.Vendedor;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaPrincipalController implements Initializable, CanGoBack {

    @FXML
    private Label usuarioLabel;
    @FXML
    private Button iniciarCerrarSesion;
    @FXML
    private VBox buttonContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Usuario.getUsuarioActual() == null) {
            usuarioLabel.setText("Invitado");
            iniciarCerrarSesion.setText("Iniciar sesión");
        } else {
            usuarioLabel.setText(Usuario.getUsuarioActual().toString());
            iniciarCerrarSesion.setText("Cerrar sesión");
        }
        agregarBotones();

    }

    @FXML
    public void logIn(ActionEvent event) throws IOException {
        if (Usuario.getUsuarioActual() == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaIniciarSesion.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaIniciarSesionController controller = loader.<VentanaIniciarSesionController>getController();

            controller.setReturnController(this);
            stage.show();
            usuarioLabel.getScene().getWindow().hide();
        } else {
            Usuario.setUsuarioActual(null);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaPrincipal.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            loader.<VentanaPrincipalController>getController();
            stage.show();
            ((Stage) usuarioLabel.getScene().getWindow()).close();
        }
    }

    @FXML
    public void verRecientes(ActionEvent event) {
        System.out.println("Ver productos recientes");
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setContentText("No implementado!");
        a.showAndWait();
    }

    @FXML
    public void verMasBuscados(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaProductosMasBuscados.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaProductosMasBuscadosController controller = loader.<VentanaProductosMasBuscadosController>getController();

            controller.setReturnController(this);
            stage.show();
            usuarioLabel.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void busquedaSencilla(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaBusquedaSencilla.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaBusquedaSencillaController controller = loader.<VentanaBusquedaSencillaController>getController();

            controller.setReturnController(this);
            stage.show();
            usuarioLabel.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void busquedaAvanzada(ActionEvent event
    ) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setContentText("No implementado!");
        a.showAndWait();
    }

    @FXML
    public void misPedidos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaMisPedidos.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaMisPedidosController controller = loader.<VentanaMisPedidosController>getController();

            controller.setReturnController(this);
            stage.show();
            usuarioLabel.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void misVentas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaMisVentas.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaMisVentasController controller = loader.<VentanaMisVentasController>getController();

            controller.setReturnController(this);
            stage.show();
            usuarioLabel.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void misProductos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaMisProductos.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaMisProductosController controller = loader.<VentanaMisProductosController>getController();

            controller.setReturnController(this);
            stage.show();
            usuarioLabel.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void administrar(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaAdministracion.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaAdministracionController controller = loader.<VentanaAdministracionController>getController();

            controller.setReturnController(this);
            stage.show();
            usuarioLabel.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setReturnController(CanGoBack c
    ) {
        throw new UnsupportedOperationException("Not supported!");
    }

    @Override
    public void show() {
        ((Stage) usuarioLabel.getScene().getWindow()).show();
    }

    private void agregarBotones() {
        if (Usuario.getUsuarioActual() == null || Usuario.getUsuarioActual() instanceof Comprador) {
            agregarBotonesInvitado();
        }
        if (Usuario.getUsuarioActual() instanceof Comprador) {
            agregarBotonesComprador();
        }
        if (Usuario.getUsuarioActual() instanceof Vendedor) {
            agregarBotonesVendedor();
        }
        if (Usuario.getUsuarioActual() instanceof Administrador) {
            agregarBotonesAdministrador();
        }
    }

    private void agregarBotonesInvitado() {
        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        container.setSpacing(10);
        container.setPadding(new Insets(10));

        Button prodsNuevos = new Button("Productos nuevos");
        prodsNuevos.setOnAction(e -> verRecientes(e));
        container.getChildren().add(prodsNuevos);

        Button prodsMasBuscados = new Button("Productos más buscados");
        prodsMasBuscados.setOnAction(e -> verMasBuscados(e));
        container.getChildren().add(prodsMasBuscados);

        buttonContainer.getChildren().add(container);
    }

    private void agregarBotonesComprador() {
        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        container.setSpacing(10);
        container.setPadding(new Insets(10));

        agregarBotonesBusqueda(container);

        Button misPedidos = new Button("Mis pedidos");
        misPedidos.setOnAction(e -> misPedidos(e));
        container.getChildren().add(misPedidos);

        buttonContainer.getChildren().add(container);
    }

    private void agregarBotonesBusqueda(VBox container) {
        Button busqSencilla = new Button("Búsqueda sencilla");
        busqSencilla.setOnAction(e -> busquedaSencilla(e));
        container.getChildren().add(busqSencilla);

        Button busqAvanzada = new Button("Búsqueda avanzada");
        busqAvanzada.setOnAction(e -> busquedaAvanzada(e));
        container.getChildren().add(busqAvanzada);
    }

    private void agregarBotonesVendedor() {
        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        container.setSpacing(10);
        container.setPadding(new Insets(10));

        Button misVentas = new Button("Mis ventas");
        misVentas.setOnAction(e -> misVentas(e));
        container.getChildren().add(misVentas);

        Button misProductos = new Button("Mis productos");
        misProductos.setOnAction(e -> misProductos(e));
        container.getChildren().add(misProductos);

        buttonContainer.getChildren().add(container);
    }

    private void agregarBotonesAdministrador() {
        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        container.setSpacing(10);
        container.setPadding(new Insets(10));

        agregarBotonesBusqueda(container);

        Button administrar = new Button("Administración");
        administrar.setOnAction(e -> administrar(e));
        container.getChildren().add(administrar);

        buttonContainer.getChildren().add(container);
    }

}
