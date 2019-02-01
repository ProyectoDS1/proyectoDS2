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
    protected List<Pedido> misPedidos;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    protected List<Calificacion> misCalificaciones;

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

    public static Comprador transferir(Usuario usuario) {
        if (usuario instanceof Comprador && !(usuario instanceof Vendedor)) {
            return (Comprador) usuario;
        }

        EntityManager em = ConexionSql.getConexion().beginTransaction();
        em.remove(usuario); 
        ConexionSql.getConexion().endTransaction();

        Comprador nuevoComprador = new Comprador();
        nuevoComprador.setNombre(usuario.getNombre());
        nuevoComprador.setApellido(usuario.getApellido());
        nuevoComprador.setEmail(usuario.getEmail());
        nuevoComprador.setCedula(usuario.getCedula());
        nuevoComprador.setMatricula(usuario.getMatricula());
        nuevoComprador.setTelefono(usuario.getTelefono());
        nuevoComprador.setContrasenia(usuario.getContrasenia());
        nuevoComprador.setDireccion(usuario.getDireccion());
        nuevoComprador.setActivo(usuario.isActivo());
        em = ConexionSql.getConexion().beginTransaction();
        if (usuario instanceof Vendedor) { // Si el antiguo era un vendedor, preservar sus pedidos y sus calificaciones
            nuevoComprador.setMisCalificaciones(((Vendedor) usuario).getCalificaciones());
            for (Calificacion c : nuevoComprador.getCalificaciones()) {
                c.setAutor(nuevoComprador);
                em.persist(c);
            }
            nuevoComprador.setPedidos(((Vendedor) usuario).mostrarPedidos());
            for (Pedido p : nuevoComprador.mostrarPedidos()) {
                p.setComprador(nuevoComprador);
                em.persist(p);
            }
        }

        em.persist(nuevoComprador);
        ConexionSql.getConexion().endTransaction();

        return nuevoComprador;
    }
}
