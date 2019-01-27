/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author User-PC
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MetodoPago implements Serializable {

    @OneToOne
    @JoinColumn(name = "PEDIDO_ID")
    protected Pedido pedido;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public MetodoPago() {
    }

    public MetodoPago(Pedido pedido) {
        this.pedido = pedido;
    }

    public abstract boolean confirmar();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
