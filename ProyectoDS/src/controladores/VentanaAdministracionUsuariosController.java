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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import modelos.Usuario;
import utils.ConexionSql;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaAdministracionUsuariosController implements Initializable, CanGoBack {

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
        refreshUserList();
    }

    @Override
    public void setReturnController(CanGoBack c) {
        returnController = c;
    }

    @FXML
    public void crear(ActionEvent e) throws IOException {
        System.out.println("Crear nuevo usuario");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaAdministracionCrearNuevoUsuario.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaAdministracionCrearNuevoUsuarioController controller = loader.<VentanaAdministracionCrearNuevoUsuarioController>getController();

        controller.setReturnController(this);
        stage.show();
        titulo.getScene().getWindow().hide();
    }

    @FXML
    public void editar(ActionEvent e, Usuario u) {
        try {
            System.out.println("Editar usuario");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaAdministracionEditarUsuario.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaAdministracionEditarUsuarioController controller = loader.<VentanaAdministracionEditarUsuarioController>getController();

            controller.setReturnController(this);
            controller.setData(u);
            stage.show();
            titulo.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaAdministracionUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void eliminar(ActionEvent e, Usuario u) {

        EntityManager em = ConexionSql.getConexion().beginTransaction();
        u.setActivo(false);
        ConexionSql.getConexion().endTransaction();

        System.out.println("Eliminar usuario");
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Usuario eliminado!");
        a.showAndWait();

        refreshUserList();
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }

    @Override
    public void show() {
        ((Stage) titulo.getScene().getWindow()).show();
        refreshUserList();
    }

    private void refreshUserList() {
        container.getChildren().clear();

        EntityManager em = ConexionSql.getConexion().beginTransaction();
        List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        ConexionSql.getConexion().endTransaction();

        for (Usuario u : usuarios) {
            HBox product = new HBox();
            product.setPrefWidth(Double.MAX_VALUE);
            product.setSpacing(10);

            Label nombre = new Label(u.toString());
            nombre.setPrefWidth(300);
            product.getChildren().add(nombre);

            Button editar = new Button("Editar");
            editar.setPrefWidth(100);
            editar.setOnAction(e -> editar(e, u));
            product.getChildren().add(editar);

            if (u.isActivo()) {
                Button eliminar = new Button("Eliminar");
                eliminar.setPrefWidth(100);
                eliminar.setOnAction(e -> eliminar(e, u));
                product.getChildren().add(eliminar);
            }

            container.getChildren().add(product);
        }
    }
}
