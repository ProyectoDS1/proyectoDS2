/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
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

    public static Administrador transferir(Usuario u) {
        if (u instanceof Administrador) {
            // Usuario ya es administrador, retornarlo directamente
            return (Administrador) u;
        }

        // Si no, no era un Administrador
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        em.remove(u); // Eliminar de la base de datos
        ConexionSql.getConexion().endTransaction();

        // Crear nuevo administrador y pasarle todos los datos del ya eliminado
        Administrador nuevo = (Administrador)u;
        /*
                new Administrador();
        nuevo.setNombre(u.getNombre());
        nuevo.setApellido(u.getApellido());
        nuevo.setEmail(u.getEmail());
        nuevo.setCedula(u.getCedula());
        nuevo.setMatricula(u.getMatricula());
        nuevo.setTelefono(u.getTelefono());
        nuevo.setContrasenia(u.getContrasenia());
        nuevo.setDireccion(u.getDireccion());
        nuevo.setActivo(u.isActivo());
        */
        em = ConexionSql.getConexion().beginTransaction();
        em.persist(nuevo);
        ConexionSql.getConexion().endTransaction();

        return nuevo;
    }

}
