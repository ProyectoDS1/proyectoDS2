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

    protected EstadoPedido estado;
    protected Date fechaDePedido;
    protected Date fechaDeEntrega;
    protected Producto articulo;
    protected int numeroItems;
    protected Comprador comprador;
    protected MetodoPago metpago;

    public void anular() {

    }

    public void notificarVendedor() {

    }

    public boolean recibido() {
        return true;
    }

    public void elegirTipoPago(MetodoPago metp) {

    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Date getFechaDePedido() {
        return fechaDePedido;
    }

    public void setFechaDePedido(Date fechaDePedido) {
        this.fechaDePedido = fechaDePedido;
    }

    public Date getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(Date fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public Producto getArticulo() {
        return articulo;
    }

    public void setArticulo(Producto articulo) {
        this.articulo = articulo;
    }

    public int getNumeroItems() {
        return numeroItems;
    }

    public void setNumeroItems(int numeroItems) {
        this.numeroItems = numeroItems;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public MetodoPago getMetpago() {
        return metpago;
    }

    public void setMetpago(MetodoPago metpago) {
        this.metpago = metpago;
    }

}
