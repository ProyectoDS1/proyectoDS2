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
public class Vendedor extends Comprador {

    @OneToMany(mappedBy = "vendedor")
    protected List<Producto> misArticulos;
    @OneToMany(mappedBy = "vendedor")
    protected List<CalificacionVendedor> calificaciones;

    public List<Producto> mostrarArticulos() {
        return this.misArticulos;
    }

    public void agregarRating(int rate) {

    }

    public float getPromedioCalificaciones() {
        if (calificaciones.isEmpty()) {
            return -1;
        }

        float promedio = 0;
        for (CalificacionVendedor c : calificaciones) {
            promedio += c.getEstrellas();
        }

        return promedio / this.calificaciones.size();
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (Vendedor)";
    }

}
