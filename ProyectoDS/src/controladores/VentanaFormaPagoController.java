/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import modelos.*;
import utils.ConexionSql;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaFormaPagoController implements Initializable, CanGoBack, CanGetData {

    private CanGoBack returnController;
    private Producto target;
    private int numItems;

    @FXML
    private TextField nombreEfectivo;
    @FXML
    private TextField nombreDE;
    @FXML
    private TextField telfDE;
    @FXML
    private Label resumenCompra;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
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
        this.nombreEfectivo.setText(Usuario.getUsuarioActual().getNombre());
        this.nombreDE.setText(Usuario.getUsuarioActual().getNombre());
        this.telfDE.setText(Usuario.getUsuarioActual().getTelefono());
        target = (Producto) data[0];
        numItems = (Integer) data[1];

        show();
    }

    @Override
    public void show() {
        resumenCompra.setText("Comprando " + numItems + " unidades del producto " + target.getNombreArticulo() + ". Precio total $" + target.getPrecio() * numItems);
    }

    @FXML
    public void comprarEfect(ActionEvent e) {
        MetodoPago metodoPago = new PagoEfectivo(nombreEfectivo.getText(), null);

        comprar(metodoPago, e);
    }

    @FXML
    public void comprarDE(ActionEvent e) {
        MetodoPago metodoPago = new PagoMonedero(nombreDE.getText(), telfDE.getText(), null);

        comprar(metodoPago, e);
    }

    private void comprar(MetodoPago metodoPago, ActionEvent e) {
        Pedido p = ((Comprador) Usuario.getUsuarioActual()).comprar(target, numItems, metodoPago);

        EntityManager em = ConexionSql.getConexion().beginTransaction();
        em.persist(metodoPago);
        em.persist(p);
        if (metodoPago.confirmar()) {
            target.setStock(target.getStock() - numItems);
            p.notificarVendedor();
        } else {
            p.setEstado(EstadoPedido.ANULADO);
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("El pago ha sido rechazado!");
            a.showAndWait();
        }
        ConexionSql.getConexion().endTransaction();

        volver(e);
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombreEfectivo.getScene().getWindow()).close();
        returnController.show();
    }

}
