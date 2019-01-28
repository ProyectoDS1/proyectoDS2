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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import modelos.Comprador;
import modelos.Usuario;
import utils.ConexionSql;

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
        // Not required yet
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
        crearUsuario(u);
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        em.persist(u);
        ConexionSql.getConexion().endTransaction();

        returnController.show();
        ((Stage) nombre.getScene().getWindow()).close();
    }

    private void crearUsuario(Usuario u) {
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
    }

    private boolean validarDatosMinimos() {
        int errorCampo = 0;
        boolean cond;
        String colorRed = "-fx-border-color: red";
        cond=nombre.getText().trim().length() == 0;
        sombrear(nombre,cond,colorRed,errorCampo);
        
        cond=email.getText().trim().length() == 0;
        sombrear(email,cond,colorRed,errorCampo);
        
        cond=cedula.getText().trim().length() != 10;
        sombrear(cedula,cond,colorRed,errorCampo);
         
        cond=matricula.getText().trim().length() != 9;
        sombrear(matricula,cond,colorRed,errorCampo);
         
        cond=contrasenia.getText().trim().length() < 4;
        sombrear(contrasenia,cond,colorRed,errorCampo);
        
        if (errorCampo > 0) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Por favor, corrija los campos resaltados en rojo.", ButtonType.OK);
            a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            a.showAndWait();
            return false;
        }
        return true;
    }

    private void sombrear(TextField textf,boolean con,String color, int err) {
        if(con){
            textf.setStyle(color);
            err++;
        }else{
            textf.setStyle("");
        }
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombre.getScene().getWindow()).close();
        returnController.show();
    }

}
