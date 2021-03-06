/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

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
    @Temporal(value = TemporalType.TIMESTAMP)
    protected Date tiempoEntrega;
    @OneToMany(mappedBy = "producto")
    protected List<CalificacionProducto> calificaciones; // NOSONAR la lista no puede ser transient porque JPA necesita serializarla
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

    public CalificacionProducto agregarRating(int rate) {
        CalificacionProducto c = new CalificacionProducto(this, rate, (Comprador) Usuario.getUsuarioActual());
        this.getCalificaciones().add(c);
        ((Comprador) Usuario.getUsuarioActual()).getCalificaciones().add(c);

        return c;
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

    public float getPromedioCalificaciones() {
        if (calificaciones.isEmpty()) {
            return -1;
        }

        float promedio = 0;
        for (CalificacionProducto c : calificaciones) {
            promedio += c.getEstrellas();
        }

        return promedio / this.calificaciones.size();
    }

    public void buscado() {
        numVistas++;
    }

    public String getTiempoEntregaTexto() {
        return tiempoEntrega != null ? tiempoEntrega.getTime() / (1000.0 * 60 * 60) + " horas" : "";
    }

    public String getPromedioCalificacionesTexto() {
        float califProducto = getPromedioCalificaciones();
        if (califProducto == -1) {
            return "Este producto no tiene calificaciones";
        }
        return califProducto + "/5";
    }

}
