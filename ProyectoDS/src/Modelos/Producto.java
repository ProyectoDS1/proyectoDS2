/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.Date;
/**
 *
 * @author User-PC
 */
public class Producto {
    private String nombreArticulo;
    private String categoria;
    private float precio;
    private Date tiempoEntrega;
    private CalificacionProducto calificacion;
    private boolean disponible;
    private boolean eliminado;
    private Vendedor vendedor;
    private int numVistas;
    private Date fechaCreacion;
    
    private void agregarRating(int rate){
    
    }
}
