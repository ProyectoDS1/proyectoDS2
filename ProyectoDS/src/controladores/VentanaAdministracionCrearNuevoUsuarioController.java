/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import modelos.Administrador;
import modelos.Comprador;
import modelos.Usuario;
import modelos.Vendedor;
import utils.ConexionSql;

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
    private PasswordField contrasenia;
    @FXML
    private TextField cedula;
    @FXML
    private TextField matricula;
    @FXML
    private ComboBox categoria;
    private final String comprador="Comprador";
    private final String vendedor="Vendedor";
    private final String administrador="Administrador";
           

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoria.getItems().addAll(comprador, vendedor, administrador);
        categoria.setValue(comprador);
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
        switch ((String) categoria.getValue()) {
            case comprador:
                u = new Comprador();
                break;
            case vendedor:
                u = new Vendedor();
                break;
            case administrador:
                u = new Administrador();
                break;
            default:
                alert();
                return;
        }
        crearUsuario(u);
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        em.persist(u);
        ConexionSql.getConexion().endTransaction();

        volver(e);
    }
    private void crearUsuario(Usuario u){
        u.setNombre(nombre.getText());
        u.setApellido(apellido.getText());
        u.setEmail(email.getText());
        u.setCedula(cedula.getText());
        u.setMatricula(matricula.getText());
        u.setTelefono(telefono.getText());
        u.setContrasenia(contrasenia.getText());
        u.setDireccion(direccion.getText());
        u.setActivo(true);
    }
    
    private void alert(){
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setContentText("WARNING: Categoría inválida!");
        a.showAndWait();
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombre.getScene().getWindow()).close();
        returnController.show();
    }

}
