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
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import modelos.Administrador;
import modelos.Comprador;
import modelos.Usuario;
import modelos.Vendedor;
import utils.ConexionSql;
import utils.TextUsuario;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaAdministracionCrearNuevoUsuarioController extends TextUsuario implements Initializable, CanGoBack {

    private CanGoBack returnController;

    @FXML
    private ComboBox categoria;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoria.getItems().addAll(comprador1, vendedor1, administrador1);
        categoria.setValue(comprador1);
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
            case comprador1:
                u = new Comprador();
                break;
            case vendedor1:
                u = new Vendedor();
                break;
            case administrador1:
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
