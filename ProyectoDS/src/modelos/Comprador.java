/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Date;
import java.util.List;
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
public class Comprador extends Usuario {

    @OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL)
    protected List<Pedido> misPedidos; // NOSONAR la lista no puede ser transient porque JPA necesita serializarla
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    protected List<Calificacion> misCalificaciones; // NOSONAR la lista no puede ser transient porque JPA necesita serializarla

    public Pedido comprar(Producto articulo, int numItems, MetodoPago mp) {
        Pedido p = new Pedido();
        p.setArticulo(articulo);
        p.setComprador((Comprador) Usuario.getUsuarioActual());
        p.setEstado(EstadoPedido.PENDIENTE);
        p.setFechaDePedido(new Date());
        p.setNumeroItems(numItems);
        p.setMetpago(mp);

        this.misPedidos.add(p);
        mp.setPedido(p);

        return p;
    }

    public List<Pedido> mostrarPedidos() {
        return misPedidos;
    }

    public void setPedidos(List<Pedido> misPedidos) {
        this.misPedidos = misPedidos;
    }

    public List<Pedido> mostrarHistorialPedidos() {
        return misPedidos;
    }

    public boolean haCalificado(Producto p) {
        if (misCalificaciones == null) {
            return false;
        }
        for (Calificacion calif : misCalificaciones) {
            if (calif instanceof CalificacionProducto) {
                CalificacionProducto cp = (CalificacionProducto) calif;
                if (cp.getProducto() == p) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean haCalificado(Vendedor v) {
        if (misCalificaciones == null) {
            return false;
        }
        for (Calificacion calif : misCalificaciones) {
            if (calif instanceof CalificacionVendedor) {
                CalificacionVendedor cp = (CalificacionVendedor) calif;
                if (cp.getVendedor() == v) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Calificacion> getCalificaciones() {
        return misCalificaciones;
    }

    public void setMisCalificaciones(List<Calificacion> misCalificaciones) {
        this.misCalificaciones = misCalificaciones;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (Comprador)";
    }

    public static Comprador transferir(Usuario u) {
        if (u instanceof Comprador && !(u instanceof Vendedor)) {
            // Usuario ya es comprador y no es vendedor, retornarlo directamente
            return (Comprador) u;
        }

        // Si no, no era un Comprador
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        em.remove(u); // Eliminar de la base de datos
        ConexionSql.getConexion().endTransaction();

        // Crear nuevo comprador y pasarle todos los datos del usuario eliminado
        Comprador nuevo = new Comprador();
        nuevo.copiarAtributos(u);
        em = ConexionSql.getConexion().beginTransaction();
        if (u instanceof Vendedor) { // Si el antiguo era un vendedor, preservar sus pedidos y sus calificaciones
            nuevo.setMisCalificaciones(((Vendedor) u).getCalificaciones());
            for (Calificacion c : nuevo.getCalificaciones()) {
                c.setAutor(nuevo);
                em.persist(c);
            }
            nuevo.setPedidos(((Vendedor) u).mostrarPedidos());
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
