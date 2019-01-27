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
import javax.persistence.ManyToOne;

/**
 *
 * @author User-PC
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Calificacion implements Serializable {

    protected int estrellas;
    @ManyToOne
    @JoinColumn(name = "COMPRADOR_ID")
    protected Comprador autor;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Calificacion() {
    }

    public Calificacion(int estrellas, Comprador autor) {
        this.estrellas = estrellas;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public Comprador getAutor() {
        return autor;
    }

    public void setAutor(Comprador autor) {
        this.autor = autor;
    }

}
