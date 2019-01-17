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

    @OneToMany(mappedBy = "comprador")
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

    public boolean haCalificado(Producto p) {
        if (misCalificaciones == null) {
            return false;
        }
        for (Calificacion calif : misCalificaciones) {
            if (calif instanceof CalificacionProducto) {
                CalificacionProducto cp = (CalificacionProducto) calif;
                if (cp.getProducto() == p) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean haCalificado(Vendedor v) {
        if (misCalificaciones == null) {
            return false;
        }
        for (Calificacion calif : misCalificaciones) {
            if (calif instanceof CalificacionVendedor) {
                CalificacionVendedor cp = (CalificacionVendedor) calif;
                if (cp.getVendedor() == v) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Calificacion> getCalificaciones() {
        return misCalificaciones;
    }

    public void setMisCalificaciones(List<Calificacion> misCalificaciones) {
        this.misCalificaciones = misCalificaciones;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (Comprador)";
    }

}
