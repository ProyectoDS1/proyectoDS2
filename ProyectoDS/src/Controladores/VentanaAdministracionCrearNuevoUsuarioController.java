/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Administrador;
import Modelos.Comprador;
import Modelos.Usuario;
import Modelos.Vendedor;
import Utils.ConexionSql;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaAdministracionCrearNuevoUsuarioController implements Initializable, CanGoBack {

    private CanGoBack returnController;

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField email;
    @FXML
    private TextField telefono;
    @FXML
    private TextField direccion;
    @FXML
    private TextField contrasenia;
    @FXML
    private TextField cedula;
    @FXML
    private TextField matricula;
    @FXML
    private TextField categoria;

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

        Usuario u;
        switch (categoria.getText()) {
            case "Comprador":
                u = new Comprador();
                break;
            case "Vendedor":
                u = new Vendedor();
                break;
            case "Administrador":
                u = new Administrador();
                break;
            default:
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("ERROR: Categoría inválida!");
                a.showAndWait();
                return;
        }
        u.setNombre(nombre.getText());
        u.setApellido(apellido.getText());
        u.setEmail(email.getText());
        u.setCedula(cedula.getText());
        u.setMatricula(matricula.getText());
        u.setTelefono(telefono.getText());
        u.setContrasenia(contrasenia.getText());
        u.setDireccion(direccion.getText());
        u.setActivo(true);
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        em.persist(u);
        ConexionSql.getConexion().endTransaction();

        volver(e);
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombre.getScene().getWindow()).close();
        returnController.show();
    }

}
