/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Utils.ConexionSql;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

/**
 *
 * @author User-PC
 */
@Entity
public class Administrador extends Usuario {

    public void administrarUsuario(Usuario user) {

    }

    public void administrarProducto(Producto articulo) {

    }

    public ArrayList<Pedido> verComprasPendientes() {
        return null;
    }

    public ArrayList<Pedido> verComprasCompletadas() {
        return null;
    }

    public ArrayList<Pedido> verComprasAnuladas() {
        return null;
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
        Administrador nuevo = new Administrador();
        nuevo.setNombre(u.getNombre());
        nuevo.setApellido(u.getApellido());
        nuevo.setEmail(u.getEmail());
        nuevo.setCedula(u.getCedula());
        nuevo.setMatricula(u.getMatricula());
        nuevo.setTelefono(u.getTelefono());
        nuevo.setContrasenia(u.getContrasenia());
        nuevo.setDireccion(u.getDireccion());
        nuevo.setActivo(u.isActivo());
        em = ConexionSql.getConexion().beginTransaction();
        em.persist(nuevo);
        ConexionSql.getConexion().endTransaction();

        return nuevo;
    }

}
