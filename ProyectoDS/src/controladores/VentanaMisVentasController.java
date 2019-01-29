/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import utils.PedidosController;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class VentanaMisVentasController extends PedidosController{

    @FXML
    public void verPendientes(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VentanaVentasPendientes.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VentanaVentasPendientesController controller = loader.<VentanaVentasPendientesController>getController();

        controller.setReturnController(this);
        stage.show();
        titulo.getScene().getWindow().hide();
    }

    @FXML
    public void verResumen(ActionEvent e) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setContentText("No implementado!");
        a.showAndWait();
    }

    @FXML
    public void volver(ActionEvent e) {
        ((Stage) titulo.getScene().getWindow()).close();
        returnController.show();
    }
}
