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
public class Pedido {
    private boolean estado;
    private Date fechaDePedido;
    private Date fechaDeEntrega;
    private Producto articulo;
    private Comprador comprador;
    private MetodoPago metpago;
    
    public void anular(){
     
    }
    
    public void notificarVendedor(){
    
    }
    
    public boolean recibido(){
     return true;
    }
    
    public void elegirTipoPago(MetodoPago metp){
     
    }
}
