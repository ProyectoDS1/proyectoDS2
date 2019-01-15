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
public abstract class Calificacion {

    protected int estrellas;
    protected Comprador autor;

    public Calificacion(int estrellas, Comprador autor) {
        this.estrellas = estrellas;
        this.autor = autor;
    }

}
