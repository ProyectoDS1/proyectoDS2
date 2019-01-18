/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.EstadoPedido;
import Modelos.Pedido;
import Modelos.Producto;
import Modelos.Usuario;
import Modelos.Vendedor;
import Utils.ConexionSql;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaVentasPendientesController implements Initializable, CanGoBack {

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
    public void verMapa(ActionEvent e, Pedido p) {
        System.out.println("Ver mapa de producto");
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("No implementado!");
        a.showAndWait();
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
        assert Usuario.getUsuarioActual() instanceof Vendedor;
        Vendedor vendedor = (Vendedor) Usuario.getUsuarioActual();

        container.getChildren().clear();

        int i = 0;
        if (vendedor.mostrarArticulos() == null) {
            return;
        }
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        TypedQuery<Pedido> q = em.createQuery("SELECT p FROM Pedido p WHERE p.articulo.vendedor=:vendedor AND p.estado=:estado", Pedido.class);
        q.setParameter("vendedor", vendedor);
        q.setParameter("estado", EstadoPedido.PENDIENTE);
        List<Pedido> pedidos = q.getResultList();
        ConexionSql.getConexion().endTransaction();

        for (Pedido p : pedidos) {

            if (p.getEstado() == EstadoPedido.PENDIENTE) {
                Label nombre = new Label(p.getNumeroItems() + " unidades de "
                        + p.getArticulo().getNombreArticulo() + " para "
                        + p.getComprador().getNombre() + " " + p.getComprador().getApellido());
                nombre.setPrefWidth(350);
                container.add(nombre, 0, i, 3, 1);

                agregarBotonVerMapa(p, i);
                agregarBotonAnular(p, i);
                i++;
            }
        }
    }

    private void agregarBotonVerMapa(Pedido p, int i) {
        Button verMapa = new Button("Ver mapa");
        verMapa.setOnAction(e -> verMapa(e, p));
        container.add(verMapa, 3, i);
    }

    private void agregarBotonAnular(Pedido p, int i) {
        Button anular = new Button("Anular");
        anular.setOnAction(e -> anularPedido(e, p));
        container.add(anular, 4, i);
    }
}
