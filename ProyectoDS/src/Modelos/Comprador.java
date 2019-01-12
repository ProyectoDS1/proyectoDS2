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
public class Comprador extends Usuario{
    
    protected ArrayList<Pedido> misPedidos;
    protected ArrayList<Calificacion> misCalificaciones;
    
    public Pedido comprar(Producto articulo){
     
    }
    
    public ArrayList<Pedido> mostrarPedidos(){
      return misPedidos;
    }
    
    public ArrayList<Pedido> mostrarHistorialPedidos(){
      return misPedidos;
    }
    
}
