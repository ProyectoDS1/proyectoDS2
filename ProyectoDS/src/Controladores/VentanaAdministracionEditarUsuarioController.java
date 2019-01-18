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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaAdministracionEditarUsuarioController implements Initializable, CanGoBack, CanGetData {

    private CanGoBack returnController;
    private Usuario target;

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
    private ComboBox categoria;
    @FXML
    private CheckBox activo;

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
    public void setData(Object... data) {
        target = (Usuario) data[0];

        nombre.setText(target.getNombre());
        apellido.setText(target.getApellido());
        email.setText(target.getEmail());
        direccion.setText(target.getDireccion());
        telefono.setText(target.getTelefono());
        contrasenia.setText(target.getContrasenia());
        matricula.setText(target.getMatricula());
        cedula.setText(target.getCedula());
        activo.setSelected(target.isActivo());

        categoria.getItems().addAll("Comprador", "Vendedor", "Administrador");
        if (target instanceof Comprador) {
            categoria.setValue("Comprador");
        }
        if (target instanceof Vendedor) {
            categoria.setValue("Vendedor");
        }
        if (target instanceof Administrador) {
            categoria.setValue("Administrador");
        }
    }

    @Override
    public void show() {
        ((Stage) nombre.getScene().getWindow()).show();
    }

    @FXML
    public void guardar(ActionEvent e) {
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        target.setNombre(nombre.getText());
        target.setApellido(apellido.getText());
        target.setEmail(email.getText());
        target.setContrasenia(contrasenia.getText());
        target.setTelefono(telefono.getText());
        target.setDireccion(direccion.getText());
        target.setCedula(cedula.getText());
        target.setMatricula(matricula.getText());
        target.setActivo(activo.isSelected());
        ConexionSql.getConexion().endTransaction();
        switch ((String) categoria.getValue()) {
            case "Comprador":
                Comprador.transferir(target);
                break;
            case "Vendedor":
                Vendedor.transferir(target);
                break;
            case "Administrador":
                Administrador.transferir(target);
                break;
            default:
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("ERROR: Categoría inválida!");
                a.showAndWait();
                return;
        }

        volver(e);
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombre.getScene().getWindow()).close();
        returnController.show();
    }

}
