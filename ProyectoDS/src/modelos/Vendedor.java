/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import controladores.VentanaAdministracionController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import utils.ConexionSql;

/**
 *
 * @author User-PC
 */
@Entity
public class Vendedor extends Comprador {

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    transient List<Producto> misArticulos;
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    transient List<CalificacionVendedor> calificaciones;

    public List<Producto> mostrarArticulos() {
        return this.misArticulos;
    }

    public CalificacionVendedor agregarRating(int rate) {
        CalificacionVendedor calificacionVend = new CalificacionVendedor(this, rate, (Comprador) Usuario.getUsuarioActual());
        ((Comprador) Usuario.getUsuarioActual()).getCalificaciones().add(calificacionVend);
        this.getCalificacionesV().add(calificacionVend);
        return calificacionVend;
    }

    public List<Pedido> getMisPedidos() {
        return misPedidos;
    }

    public void setMisPedidos(List<Pedido> misPedidos) {
        this.misPedidos = misPedidos;
    }

    public int totalProductosPendientes() {
        int cont = 0;
        for (int i = 0; i < this.misPedidos.size(); i++) {
            boolean cond = this.misPedidos.get(i).estado.equals(EstadoPedido.PENDIENTE);
            if (cond) {
                cont++;
            }
        }
        return cont;
    }

    public float getPromedioCalificaciones() {
        if (calificaciones.isEmpty()) {
            return -1;
        }

        float promedio = 0;
        for (CalificacionVendedor c : calificaciones) {
            promedio += c.getEstrellas();
        }

        return promedio / this.calificaciones.size();
    }

    public List<Producto> getMisArticulos() {
        return misArticulos;
    }

    public void setMisArticulos(List<Producto> misArticulos) {
        this.misArticulos = misArticulos;
    }

    public List<CalificacionVendedor> getCalificacionesV() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionVendedor> calificaciones) {
        this.calificaciones = calificaciones;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (Vendedor)";
    }

    public static Vendedor transferir(Usuario u) {
        if (u instanceof Vendedor) {
            // Usuario ya es vendedor
            return (Vendedor) u;
        }

        // Si no, no era un Vendedor
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        em.remove(u); // Eliminar de la base de datos
        ConexionSql.getConexion().endTransaction();

        // Crear nuevo vendedor y pasarle todos los datos del usuario eliminado
        Vendedor nuevo = new Vendedor();
        nuevo.copiarAtributos(u);

        em = ConexionSql.getConexion().beginTransaction();
        if ((u instanceof Comprador) && !(u instanceof Vendedor)) { // Si el antiguo era un comprador no vendedor, preservar sus pedidos y sus calificaciones
            nuevo.setMisCalificaciones(((Comprador) u).getCalificaciones());
            for (Calificacion c : nuevo.getCalificaciones()) {
                c.setAutor(nuevo);
                em.persist(c);
            }
            nuevo.setPedidos(((Comprador) u).mostrarPedidos());
            Logger.getLogger(VentanaAdministracionController.class.getName()).log(Level.SEVERE, nuevo.mostrarPedidos().toString());

            for (Pedido p : nuevo.mostrarPedidos()) {
                p.setComprador(nuevo);
                em.persist(p);
            }
        }

        em.persist(nuevo);
        ConexionSql.getConexion().endTransaction();

        return nuevo;
    }

}
