/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author User-PC
 */
public class CalificacionProducto extends Calificacion {

    protected Producto producto;

    public CalificacionProducto(Producto producto, int estrellas, Comprador autor) {
        super(estrellas, autor);
        this.producto = producto;
    }

}
