/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.ArrayList;
import javax.persistence.Entity;

/**
 *
 * @author User-PC
 */
@Entity
public class Administrador extends Usuario {

    public void administrarUsuario(Usuario user) {

    }

    public void administrarProducto(Producto articulo) {

    }

    public ArrayList<Pedido> verComprasPendientes() {
        return null;
    }

    public ArrayList<Pedido> verComprasCompletadas() {
        return null;
    }

    public ArrayList<Pedido> verComprasAnuladas() {
        return null;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (Administrador)";
    }

}
