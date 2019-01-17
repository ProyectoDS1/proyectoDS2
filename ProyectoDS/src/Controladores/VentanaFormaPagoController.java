/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Comprador;
import Modelos.EstadoPedido;
import Modelos.MetodoPago;
import Modelos.PagoEfectivo;
import Modelos.PagoMonedero;
import Modelos.Pedido;
import Modelos.Producto;
import Modelos.Usuario;
import Utils.ConexionSql;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

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
        // TODO
    }

    @Override
    public void setReturnController(CanGoBack c) {
        returnController = c;
    }

    @Override
    public void setData(Object... data) {
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
        target.setStock(target.getStock() - numItems);
        p.notificarVendedor();
        ConexionSql.getConexion().endTransaction();

        volver(e);
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombreEfectivo.getScene().getWindow()).close();
        returnController.show();
    }

}
