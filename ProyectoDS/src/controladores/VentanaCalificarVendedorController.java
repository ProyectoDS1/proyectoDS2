/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import modelos.CalificacionVendedor;
import modelos.Vendedor;
import utils.ConexionSql;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaCalificarVendedorController implements Initializable, CanGoBack, CanGetData {

    private CanGoBack returnController;
    private Vendedor target;

    @FXML
    private Label calificacion;
    @FXML
    private Label nombre;
    @FXML
    private Label titulo;

    @Override
    public void setReturnController(CanGoBack c) {
        returnController = c;
    }

    @Override
    public void setData(Object... data) {
        target = (Vendedor) data[0];

        show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Not required yet
    }

    @Override
    public void show() {
        nombre.setText(target.getNombre() + " " + target.getApellido());
        float promedio = target.getPromedioCalificaciones();
        if (promedio == -1) {
            calificacion.setText("El vendedor aún no ha sido calificado");
        } else {
            calificacion.setText("Calificación promedio actual: " + target.getPromedioCalificaciones() + "/5");
        }

        ((Stage) titulo.getScene().getWindow()).show();
    }

    @FXML
    public void calificar(ActionEvent e) {
        int estrellas = Integer.valueOf(((Button) e.getSource()).getText());
        Logger.getLogger(VentanaCalificarVendedorController.class.getName()).log(Level.FINE, "Calificando vendedor:{0}estrellas", estrellas);
        
        EntityManager entityManager = ConexionSql.getConexion().beginTransaction();
        CalificacionVendedor calificacionVend = target.agregarRating(estrellas);
        entityManager.persist(calificacionVend);
        ConexionSql.getConexion().endTransaction();

        volver(e);
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }

}
