/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author User-PC
 */
@Entity
public class CalificacionVendedor extends Calificacion {

    @ManyToOne
    @JoinColumn(name = "VENDEDOR_ID")
    protected Vendedor vendedor;

    public CalificacionVendedor() {
    }

    public CalificacionVendedor(Vendedor vendedor, int estrellas, Comprador autor) {
        super(estrellas, autor);
        this.vendedor = vendedor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

}
