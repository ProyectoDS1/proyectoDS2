/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Utils.NotificadorCelular;
import Utils.NotificadorEmail;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User-PC
 */
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Enumerated(EnumType.ORDINAL)
    protected EstadoPedido estado;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaDePedido;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaDeEntrega;
    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID")
    protected Producto articulo;
    protected int numeroItems;
    @ManyToOne
    @JoinColumn(name = "COMPRADOR_ID")
    protected Comprador comprador;
    @OneToOne
    @JoinColumn(name = "METODOPAGO_ID")
    protected MetodoPago metpago;

    public void anular() {

    }

    public void notificarVendedor() {
        if (articulo.getVendedor().getTelefono() != null) {
            NotificadorCelular.getNotificador().notificarPedido(this);
        }
        if (articulo.getVendedor().getEmail() != null) {
            NotificadorEmail.getNotificador().notificarPedido(this);
        }
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
