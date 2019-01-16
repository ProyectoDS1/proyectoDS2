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
public class PagoMonedero extends MetodoPago {

    protected String nombreCliente;
    protected String celular;

    public PagoMonedero() {
    }

    public PagoMonedero(String nombreCliente, String celular, Pedido pedido) {
        super(pedido);
        this.nombreCliente = nombreCliente;
        this.celular = celular;
    }

}
