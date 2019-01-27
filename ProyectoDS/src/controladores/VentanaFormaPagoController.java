/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import modelos.Comprador;
import modelos.EstadoPedido;
import modelos.MetodoPago;
import modelos.PagoEfectivo;
import modelos.PagoMonedero;
import modelos.Pedido;
import modelos.Producto;
import modelos.Usuario;
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

        //((Stage) nombreDE.getScene().getWindow()).show();
    }

    @FXML
    public void comprarEfect(ActionEvent e) {
        Pedido p = crearPedido();
        MetodoPago metodoPago = new PagoEfectivo(nombreEfectivo.getText(), p);
        p.setMetpago(metodoPago);

        comprar(metodoPago, p, e);
    }

    @FXML
    public void comprarDE(ActionEvent e) {
        Pedido p = crearPedido();
        MetodoPago metodoPago = new PagoMonedero(nombreDE.getText(), telfDE.getText(), p);
        p.setMetpago(metodoPago);

        comprar(metodoPago, p, e);
    }

    private Pedido crearPedido() {
        Pedido p = new Pedido();
        p.setArticulo(target);
        p.setComprador((Comprador) Usuario.getUsuarioActual());
        p.setEstado(EstadoPedido.PENDIENTE);
        p.setFechaDePedido(new Date());
        p.setNumeroItems(numItems);

        return p;
    }

    private void comprar(MetodoPago metodoPago, Pedido p, ActionEvent e) {
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
        ((Comprador) Usuario.getUsuarioActual()).mostrarPedidos().add(p);
        ConexionSql.getConexion().endTransaction();

        volver(e);
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombreEfectivo.getScene().getWindow()).close();
        returnController.show();
    }

}
