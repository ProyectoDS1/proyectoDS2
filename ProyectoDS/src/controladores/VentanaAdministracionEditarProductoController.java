/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import modelos.Producto;
import modelos.Vendedor;
import utils.ConexionSql;
import utils.TextProducto;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaAdministracionEditarProductoController extends TextProducto implements Initializable, CanGoBack, CanGetData {

    private CanGoBack returnController;
    private Producto target;

    @FXML
    private ComboBox vendedor;
    @FXML
    private TextField numVistas;
    @FXML
    private CheckBox eliminado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setData(Object... data) {
        target = (Producto) data[0];

        nombre.setText(target.getNombreArticulo());
        descripcion.setText(target.getDescripcion());
        categoria.setText(target.getCategoria());
        precio.setText(Float.toString(target.getPrecio()));
        tiempoEntrega.setText(target.getTiempoEntrega() != null ? target.getTiempoEntrega().toString() : "");
        stock.setText(Integer.toString(target.getStock()));

        EntityManager em = ConexionSql.getConexion().beginTransaction();
        List<Vendedor> vendedores = em.createQuery("SELECT v FROM Vendedor v", Vendedor.class).getResultList();
        ConexionSql.getConexion().endTransaction();
        assert vendedores.contains(target.getVendedor());
        for (Vendedor v : vendedores) {
            vendedor.getItems().add(v);
        }
        vendedor.setValue(target.getVendedor());
        numVistas.setText(Integer.toString(target.getNumVistas()));
        eliminado.setSelected(target.isEliminado());
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
    public void guardar(ActionEvent e) {
        target.setNombreArticulo(nombre.getText());
        target.setDescripcion(descripcion.getText());
        target.setCategoria(categoria.getText());
        target.setPrecio(Float.valueOf(precio.getText()));
        target.setTiempoEntrega(null);
        target.setStock(Integer.valueOf(stock.getText()));
        target.setVendedor((Vendedor) vendedor.getValue());
        target.setNumVistas(Integer.valueOf(numVistas.getText()));
        target.setDisponible(true);
        target.setEliminado(eliminado.isSelected());
        ConexionSql.getConexion().endTransaction();

        volver(e);
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) nombre.getScene().getWindow()).close();
        returnController.show();
    }

    

}
