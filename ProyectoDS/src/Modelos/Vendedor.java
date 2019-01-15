/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.ArrayList;

/**
 *
 * @author User-PC
 */
public class Vendedor extends Comprador {

    protected ArrayList<Producto> misArticulos;
    protected ArrayList<CalificacionVendedor> calificaciones;

    public ArrayList<Producto> mostrarArticulos() {
        return this.misArticulos;
    }

    public void agregarRating(int rate) {

    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (Vendedor)";
    }

}
