/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Comprador;
import Modelos.Usuario;
import Utils.ConexionSql;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

/**
 *
 * @author User-PC
 */
public class VentanaRegistroController implements Initializable, CanGoBack {

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
    private CheckBox whatsapp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
    public void registrar(ActionEvent e) {
        if (!validarDatosMinimos()) {
            return;
        }

        Usuario u = new Comprador();
        u.setNombre(nombre.getText());
        u.setApellido(apellido.getText());
        u.setEmail(email.getText());
        u.setCedula(cedula.getText());
        u.setMatricula(matricula.getText());
        u.setTelefono(telefono.getText());
        u.setContrasenia(contrasenia.getText());
        u.setDireccion(direccion.getText());
        u.setWhatsapp(whatsapp.isSelected());
        u.setActivo(true);
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        em.persist(u);
        ConexionSql.getConexion().endTransaction();

        returnController.show();
        ((Stage) nombre.getScene().getWindow()).close();
    }

    private boolean validarDatosMinimos() {
        if (nombre.getText().trim().length() == 0
                || email.getText().trim().length() == 0
                || contrasenia.getText().trim().length() == 0
                || matricula.getText().trim().length() == 0) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Al menos el nombre, el email, la contraseña y la matrícula deben contener datos!", ButtonType.OK);
            a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            a.showAndWait();
            return false;
        }
        return true;
    }
    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombre.getScene().getWindow()).close();
        returnController.show();
    }

}
