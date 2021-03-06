/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author User-PC
 */
@Entity
public class CalificacionProducto extends Calificacion {

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID")
    protected Producto producto;

    public CalificacionProducto() {
    }

    public CalificacionProducto(Producto producto, int estrellas, Comprador autor) {
        super(estrellas, autor);
        this.producto = producto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
