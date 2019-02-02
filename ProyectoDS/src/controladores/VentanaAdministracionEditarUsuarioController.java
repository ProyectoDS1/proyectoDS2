/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modelos.*;
import utils.ConexionSql;
import utils.TextUsuario;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaAdministracionEditarUsuarioController extends TextUsuario implements Initializable, CanGoBack, CanGetData {

    private CanGoBack returnController;
    private Usuario target;

    @FXML
    private ComboBox categoria;
    @FXML
    private CheckBox activo;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Not required yet

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

        categoria.getItems().addAll(COMPRADOR, VENDEDOR, ADMINISTRADOR);
        if (target instanceof Comprador) {
            categoria.setValue(COMPRADOR);
        }
        if (target instanceof Vendedor) {
            categoria.setValue(VENDEDOR);
        }
        if (target instanceof Administrador) {
            categoria.setValue(ADMINISTRADOR);
        }
    }

    @Override
    public void show() {
        ((Stage) nombre.getScene().getWindow()).show();
    }

    @FXML
    public void guardar(ActionEvent e) {
        ConexionSql.getConexion().beginTransaction();
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
            case COMPRADOR:
                Comprador.transferir(target);
                break;
            case VENDEDOR:
                Vendedor.transferir(target);
                break;
            case ADMINISTRADOR:
                Administrador.transferir(target);
                break;
            default:
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("WARNING: Categoría inválida!");
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
