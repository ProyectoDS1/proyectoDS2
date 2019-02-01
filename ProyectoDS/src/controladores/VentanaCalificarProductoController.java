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
import modelos.CalificacionProducto;
import modelos.Comprador;
import modelos.Producto;
import modelos.Usuario;
import utils.ConexionSql;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaCalificarProductoController implements Initializable, CanGoBack, CanGetData {

    private CanGoBack returnController;
    private Producto target;

    @FXML
    private Label titulo;
    @FXML
    private Label nombre;
    @FXML
    private Label calificacion;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setReturnController(CanGoBack c) {
        returnController = c;
    }

    @Override
    public void setData(Object... data) {
        target = (Producto) data[0];

        show();
    }

    @Override
    public void show() {
        nombre.setText(target.getNombreArticulo());
        float promedio = target.getPromedioCalificaciones();
        if (promedio == -1) {
            calificacion.setText("El producto aún no ha sido calificado");
        } else {
            calificacion.setText("Calificación promedio actual: " + target.getPromedioCalificaciones() + "/5");
        }

        ((Stage) titulo.getScene().getWindow()).show();
    }

    @FXML
    public void calificar(ActionEvent e) {
        int estrellas = Integer.valueOf(((Button) e.getSource()).getText());
        //System.out.println("Calificando producto: " + estrellas + " estrellas");
        Logger.getLogger(VentanaVentasPendientesController.class.getName()).log(Level.SEVERE, "Calificando producto: " + estrellas + " estrellas");

        EntityManager em = ConexionSql.getConexion().beginTransaction();
        CalificacionProducto c = new CalificacionProducto(target, estrellas, (Comprador) Usuario.getUsuarioActual());
        em.persist(c);
        target.getCalificaciones().add(c);
        ((Comprador) Usuario.getUsuarioActual()).getCalificaciones().add(c);
        ConexionSql.getConexion().endTransaction();

        ((VentanaComprasPendientesController) returnController).show();

        ((Stage) titulo.getScene().getWindow()).close();
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }


}
