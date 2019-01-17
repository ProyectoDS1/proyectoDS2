/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User-PC
 */
@Entity
public class Producto implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    protected String nombreArticulo;
    protected String descripcion;
    protected String categoria;
    protected float precio;
    @Temporal(value = TemporalType.TIME)
    protected Date tiempoEntrega;
    @OneToMany(mappedBy = "producto")
    protected List<CalificacionProducto> calificaciones;
    protected boolean disponible;
    protected boolean eliminado;
    @ManyToOne
    @JoinColumn(name = "VENDEDOR_ID")
    protected Vendedor vendedor;
    protected int numVistas;
    @Temporal(value = TemporalType.TIMESTAMP)
    protected Date fechaCreacion;
    protected int stock;

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Date tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public List<CalificacionProducto> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionProducto> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public int getNumVistas() {
        return numVistas;
    }

    public void setNumVistas(int numVistas) {
        this.numVistas = numVistas;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    private void agregarRating(int rate) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nombreArticulo + (eliminado ? " (eliminado)" : "");
    }

}
