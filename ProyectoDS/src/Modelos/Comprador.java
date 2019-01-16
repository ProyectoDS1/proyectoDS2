/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author User-PC
 */
@Entity
public class Comprador extends Usuario {

    @Transient
    protected List<Pedido> misPedidos;
    @OneToMany(mappedBy = "autor")
    protected List<Calificacion> misCalificaciones;

    public Pedido comprar(Producto articulo) {
        return null;
    }

    public List<Pedido> mostrarPedidos() {
        return misPedidos;
    }

    public List<Pedido> mostrarHistorialPedidos() {
        return misPedidos;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (Comprador)";
    }

}
