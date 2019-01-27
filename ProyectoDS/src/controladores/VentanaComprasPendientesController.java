/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import modelos.Comprador;
import modelos.EstadoPedido;
import modelos.Pedido;
import modelos.Producto;
import modelos.Usuario;
import modelos.Vendedor;
import utils.ConexionSql;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaComprasPendientesController implements Initializable, CanGoBack {

    private CanGoBack returnController;

    @FXML
    private Label titulo;
    @FXML
    private GridPane container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarLista();
    }

    @Override
    public void setReturnController(CanGoBack c) {
        returnController = c;
    }

    @FXML
    public void calificarProducto(ActionEvent e, Producto p) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaCalificarProducto.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaCalificarProductoController controller = loader.<VentanaCalificarProductoController>getController();

            controller.setReturnController(this);
            controller.setData(p);
            stage.show();
            titulo.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaComprasPendientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void calificarVendedor(ActionEvent e, Vendedor v) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaCalificarVendedor.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            VentanaCalificarVendedorController controller = loader.<VentanaCalificarVendedorController>getController();

            controller.setReturnController(this);
            controller.setData(v);
            stage.show();
            titulo.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(VentanaComprasPendientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void indicarRecibido(ActionEvent e, Pedido p) {
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        p.setEstado(EstadoPedido.ENTREGADO);
        ConexionSql.getConexion().endTransaction();

        llenarLista();
    }

    @FXML
    public void anularPedido(ActionEvent e, Pedido p) {
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        p.setEstado(EstadoPedido.ANULADO);
        p.getArticulo().setStock(p.getArticulo().getStock() + p.getNumeroItems());
        ConexionSql.getConexion().endTransaction();

        llenarLista();
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }

    @Override
    public void show() {
        llenarLista();

        ((Stage) titulo.getScene().getWindow()).show();
    }

    private void llenarLista() {
        assert Usuario.getUsuarioActual() instanceof Comprador;
        Comprador comprador = (Comprador) Usuario.getUsuarioActual();

        container.getChildren().clear();

        int i = 0;
        if (comprador.mostrarPedidos() == null) {
            return;
        }
        for (Pedido p : comprador.mostrarPedidos()) {
            if (p.getEstado() == EstadoPedido.PENDIENTE) {
                Label nombre = new Label(p.getNumeroItems() + " unidades de " + p.getArticulo().getNombreArticulo());
                nombre.setPrefWidth(200);
                container.add(nombre, 0, i);

                agregarBotonCalificarVendedor(comprador, p, i);
                agregarBotonCalificarProducto(comprador, p, i);
                agregarBotonRecibido(p, i);
                agregarBotonAnular(p, i);
                i++;
            }
        }
    }

    private void agregarBotonAnular(Pedido p, int i) {
        Button anular = new Button("Anular");
        anular.setWrapText(true);
        anular.setOnAction(e -> anularPedido(e, p));
        container.add(anular, 4, i);
    }

    private void agregarBotonRecibido(Pedido p, int i) {
        Button indicarEntregado = new Button("Entregado");
        indicarEntregado.setOnAction(e -> indicarRecibido(e, p));
        container.add(indicarEntregado, 3, i);
    }

    private void agregarBotonCalificarProducto(Comprador comprador, Pedido p, int i) {
        if (!comprador.haCalificado(p.getArticulo())) {
            Button calificarProducto = new Button("Calificar producto");
            calificarProducto.setWrapText(true);
            calificarProducto.setMinHeight(42);
            calificarProducto.setOnAction(e -> calificarProducto(e, p.getArticulo()));
            container.add(calificarProducto, 2, i);
        }
    }

    private void agregarBotonCalificarVendedor(Comprador comprador, Pedido p, int i) {
        if (!comprador.haCalificado(p.getArticulo().getVendedor())) {
            Button calificarVendedor = new Button("Calificar vendedor");
            calificarVendedor.setWrapText(true);
            calificarVendedor.setMinHeight(42);
            calificarVendedor.setOnAction(e -> calificarVendedor(e, p.getArticulo().getVendedor()));
            container.add(calificarVendedor, 1, i);
        }
    }
}
