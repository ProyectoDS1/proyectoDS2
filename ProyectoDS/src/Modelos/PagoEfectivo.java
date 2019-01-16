/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import javax.persistence.Entity;

/**
 *
 * @author User-PC
 */
@Entity
public class PagoEfectivo extends MetodoPago {

    protected String nombreCliente;

    public PagoEfectivo() {
    }

    public PagoEfectivo(String nombreCliente, Pedido pedido) {
        super(pedido);
        this.nombreCliente = nombreCliente;
    }

}
