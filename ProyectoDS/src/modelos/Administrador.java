/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import utils.ConexionSql;

/**
 *
 * @author User-PC
 */
@Entity
public class Administrador extends Usuario {

    public void administrarUsuario(Usuario user) {
        // Not required yet
    }

    public void administrarProducto(Producto articulo) {
        // Not required yet
    }
    
    public List<Pedido> verComprasPendientes() {
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        TypedQuery<Pedido> q = em.createQuery("SELECT p FROM Pedido p WHERE p.estado=:estado", Pedido.class);
        q.setParameter("estado", EstadoPedido.PENDIENTE);
        List<Pedido> pedidos = q.getResultList();

        ConexionSql.getConexion().endTransaction();

        return pedidos;

    }

    public List<Pedido> verComprasCompletadas() {
        EntityManager em = ConexionSql.getConexion().beginTransaction();

        TypedQuery<Pedido> q = em.createQuery("SELECT p FROM Pedido p WHERE p.estado=:estado", Pedido.class);
        q.setParameter("estado", EstadoPedido.ENTREGADO);
        List<Pedido> pedidos = q.getResultList();

        ConexionSql.getConexion().endTransaction();

        return pedidos;
    }

    public List<Pedido> verComprasAnuladas() {
        EntityManager em = ConexionSql.getConexion().beginTransaction();

        TypedQuery<Pedido> q = em.createQuery("SELECT p FROM Pedido p WHERE p.estado=:estado", Pedido.class);
        q.setParameter("estado", EstadoPedido.ANULADO);
        List<Pedido> pedidos = q.getResultList();

        ConexionSql.getConexion().endTransaction();

        return pedidos;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (Administrador)";
    }

    public static Administrador transferir(Usuario usuario) {
        if (usuario instanceof Administrador) {
            return (Administrador) usuario;
        }

        EntityManager em = ConexionSql.getConexion().beginTransaction();
        em.remove(usuario); 
        ConexionSql.getConexion().endTransaction();

        Administrador nuevoAdministrador = new Administrador();
        nuevoAdministrador.setNombre(usuario.getNombre());
        nuevoAdministrador.setApellido(usuario.getApellido());
        nuevoAdministrador.setEmail(usuario.getEmail());
        nuevoAdministrador.setCedula(usuario.getCedula());
        nuevoAdministrador.setMatricula(usuario.getMatricula());
        nuevoAdministrador.setTelefono(usuario.getTelefono());
        nuevoAdministrador.setContrasenia(usuario.getContrasenia());
        nuevoAdministrador.setDireccion(usuario.getDireccion());
        nuevoAdministrador.setActivo(usuario.isActivo());
        em = ConexionSql.getConexion().beginTransaction();
        em.persist(nuevoAdministrador);
        ConexionSql.getConexion().endTransaction();

        return nuevoAdministrador;
    }

}
